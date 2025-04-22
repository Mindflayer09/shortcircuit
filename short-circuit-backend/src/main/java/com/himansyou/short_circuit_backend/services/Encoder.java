package com.himansyou.short_circuit_backend.services;

import org.springframework.stereotype.Component;

@Component
public class Encoder {

        private final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public String encode(long id) {
            StringBuilder sb = new StringBuilder();
            while (id > 0) {
                sb.append(BASE62.charAt((int)(id % 62)));
                id /= 62;
            }
            return sb.reverse().toString();
        }
    }


