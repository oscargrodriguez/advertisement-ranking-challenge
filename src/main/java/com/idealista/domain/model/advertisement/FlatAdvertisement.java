package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class FlatAdvertisement extends HouseAdvertisement {

    public FlatAdvertisement(Integer id, Description description, Integer houseSize) {
        super(id, FLAT, description, houseSize);
    }
}
