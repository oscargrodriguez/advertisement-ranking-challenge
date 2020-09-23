package com.idealista.domain.model.advertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.idealista.domain.model.advertisement.Advertisement.isGarage;

@Component
public class FullVerifier {
    @Autowired
    private SizeVerifier sizeVerifier;

    public boolean verify(Advertisement advertisement) {
        return isGarage().test(advertisement) ? withPhoto().and(withSize()).test(advertisement) :
                withPhoto().and(withDescription()).and(withSize()).test(advertisement);
    }

    private Predicate<Advertisement> withPhoto() {
        return (ad) -> ad.hasPhoto();
    }

    private Predicate<Advertisement> withDescription() {
        return (ad) -> ad.hasDescription();
    }

    private Predicate<Advertisement> withSize() {
        return (ad) -> sizeVerifier.verify(ad);
    }
}
