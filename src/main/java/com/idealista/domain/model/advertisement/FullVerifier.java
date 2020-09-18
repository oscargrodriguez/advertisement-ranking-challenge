package com.idealista.domain.model.advertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FullVerifier {
    @Autowired
    private SizeVerifier sizeVerifier;

    public boolean verify(Advertisement advertisement) {
        if (advertisement.isGarage()) {
            return advertisement.hasPhoto() && sizeVerifier.verify(advertisement);
        }
        return advertisement.hasPhoto() && advertisement.hasDescription() && sizeVerifier.verify(advertisement);
    }
}
