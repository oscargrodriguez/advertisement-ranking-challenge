package com.idealista.domain.model.advertisement;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.idealista.domain.model.advertisement.Advertisement.*;

@Component
public class SizeVerifier {
    public boolean verify(Advertisement ad) {
        return garageSized().or(flatSized()).or(chaletSized()).test(ad);
    }

    private Predicate<Advertisement> garageSized() {
        return ad -> garage().test(ad);
    }

    private Predicate<Advertisement> flatSized() {
        return ad -> flat().and(withHouseSize()).test(ad);
    }

    private Predicate<Advertisement> chaletSized() {
        return ad -> chalet().and(withGardenSize()).and(withHouseSize()).test(ad);
    }
}
