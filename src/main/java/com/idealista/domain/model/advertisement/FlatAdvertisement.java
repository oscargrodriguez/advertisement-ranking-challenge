package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class FlatAdvertisement extends HouseAdvertisement {
    public FlatAdvertisement() {
        super(FLAT);
    }

    public FlatAdvertisement(Description description) {
        super(FLAT, description);
    }

    public FlatAdvertisement(Description description, Integer houseSize) {
        super(FLAT, description);
        this.houseSize = houseSize;
    }

    @Override
    public boolean hasSize() {
        return houseSize!=null;
    }
}
