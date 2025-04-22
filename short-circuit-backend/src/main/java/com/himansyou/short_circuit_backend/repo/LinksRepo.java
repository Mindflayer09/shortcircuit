package com.himansyou.short_circuit_backend.repo;

import com.himansyou.short_circuit_backend.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LinksRepo extends JpaRepository<Links, Long> {
    Optional<Links> findByshortUrl(String shortUrl);

    List<Links> findAllByUserId(long userId);
}
