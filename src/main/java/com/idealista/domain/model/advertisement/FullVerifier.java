package com.idealista.domain.model.advertisement;

import org.springframework.stereotype.Component;

@Component
public class FullVerifier {
    public boolean verify(Advertisement advertisement) {
        return advertisement.isFull();
    }
}
