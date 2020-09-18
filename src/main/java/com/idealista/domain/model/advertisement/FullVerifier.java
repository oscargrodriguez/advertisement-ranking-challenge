package com.idealista.domain.model.advertisement;

import org.springframework.stereotype.Component;

@Component
public class FullVerifier {
    public boolean verify(Advertisement advertisement) {
        if (advertisement.isGarage()) {
            return advertisement.hasPhoto() && advertisement.hasSize();
        }
        return advertisement.hasPhoto() && advertisement.hasDescription() && advertisement.hasSize();
    }
}
