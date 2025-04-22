package com.himansyou.short_circuit_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "links")
@Getter
@Setter
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private long userId;

    private String OriginalUrl;

    private String shortUrl;
    private long count;

}
