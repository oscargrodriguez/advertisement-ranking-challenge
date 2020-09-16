package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.CHALET;

public class ChaletAdvertisement extends HouseAdvertisement {

    public ChaletAdvertisement(Integer id) {
        super(id, CHALET);
    }

    public ChaletAdvertisement(Integer id,
                               Description description,
                               Integer houseSize,
                               Integer gardenSize) {
        super(id, CHALET, description, houseSize);
        this.gardenSize = gardenSize;
    }

    @Override
    public boolean hasSize() {
        return hasGardenSize() && hasHouseSize();
    }

    private boolean hasGardenSize() {
        return gardenSize != null;
    }
}
