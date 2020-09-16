package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class FlatAdvertisement extends HouseAdvertisement {
    public FlatAdvertisement(Integer id) {
        super(id, FLAT);
    }

    public FlatAdvertisement(Integer id, Description description) {
        super(id, FLAT, description);
    }

    public FlatAdvertisement(Integer id, Description description, Integer houseSize) {
        super(id, FLAT, description);
        this.houseSize = houseSize;
    }

    @Override
    public boolean hasSize() {
        return hasHouseSize();
    }
}
