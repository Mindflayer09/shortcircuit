package com.himansyou.short_circuit_backend.services;

import com.himansyou.short_circuit_backend.Links;
import com.himansyou.short_circuit_backend.UrlDTO;
import com.himansyou.short_circuit_backend.Users;
import com.himansyou.short_circuit_backend.repo.LinksRepo;
import com.himansyou.short_circuit_backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ShortenUrl {


    private final LinksRepo repository;
    private final UserRepo userRepo;
    private final Encoder encoder;

    @Autowired
    public ShortenUrl(LinksRepo repository,UserRepo userRepo, Encoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    public String shortenUrl(UrlDTO urlDto) {
        // Step 1: Save to DB to get the ID
        Links url = new Links();
        url.setOriginalUrl(urlDto.getOriginalUrl());
        Links saved = repository.save(url);

        // Step 2: Encode ID to Base62
        String shortCode = encoder.encode(saved.getId() + 100000);

        // Step 3: Update the record with shortCode
        saved.setShortUrl(shortCode);
        saved.setUserId(urlDto.getUserId());
        repository.save(saved);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        repository.findByshortUrl(shortCode)
                .ifPresent(url -> {
                    url.setCount(url.getCount() + 1);
                    repository.save(url);
                });
        return repository.findByshortUrl(shortCode)
                .map(Links::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }
    public List<Links> getByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }
    public long createUser(String username) {
       Users user = new Users();
       user.setUserName(username);
        userRepo.save(user);
        return user.getUserId();
    }

    public ResponseEntity<String> deleteUrl(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("URL deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting URL");
        }
    }

}
