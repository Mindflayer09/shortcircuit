package com.himansyou.short_circuit_backend.controllers;

import com.himansyou.short_circuit_backend.Links;
import com.himansyou.short_circuit_backend.services.ShortenUrl;
import com.himansyou.short_circuit_backend.UrlDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UrlController {

    @Autowired
    private ShortenUrl service;

    @Operation(
            summary = "shorten url",
            description = "takes long url and returns short url code only not actual short url"
    )
    @PostMapping("/shorten")
    public String shorten(@RequestBody UrlDTO urlDto) {
        return service.shortenUrl(urlDto);
    }


    @Operation(
            summary = "redirect to original url",
            description = "uses short url to redirect to original url"
    )
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = service.getOriginalUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    @Operation(
            summary = "Get URLs by user ID",
            description = "Retrieves all shortened URLs created by a specific user"
    )
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<?> redirectByUserId(@PathVariable long userId) {
        List<Links> links = service.getByUserId(userId);
        return new ResponseEntity<>(links,HttpStatus.OK);
    }


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        long userId = service.createUser(username);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable Long id) {
        try {
            service.deleteUrl(id);
            return ResponseEntity.ok("URL deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting URL");
        }
    }
}

