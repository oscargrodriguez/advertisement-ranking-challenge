package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.GARAGE;

public class GarageAdvertisement extends Advertisement {

    public GarageAdvertisement(Integer id, Description description) {
        super(id, GARAGE, description);
    }

    @Override
    public boolean hasSize() {
        return true;
    }
}
