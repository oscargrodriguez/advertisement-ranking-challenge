package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.GARAGE;

public class GarageAdvertisement extends Advertisement {

    public GarageAdvertisement() {
        super(GARAGE);
    }

    public GarageAdvertisement(Description description) {
        super(GARAGE, description);
    }

    @Override
    public boolean hasSize() {
        return true;
    }
}
