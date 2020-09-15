package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.Typology.CHALET;

public class ChaletAdvertisement extends HouseAdvertisement {

    private Integer gardenSize;

    public ChaletAdvertisement() {
        super(CHALET);
    }

    public ChaletAdvertisement(Description description) {
        super(CHALET, description);
    }

    public ChaletAdvertisement(Description description, Integer houseSize) {
        super(CHALET, description);
        this.houseSize = houseSize;
    }

    public ChaletAdvertisement(Description description, Integer houseSize, Integer gardenSize) {
        super(CHALET, description, houseSize);
        this.gardenSize = gardenSize;
    }

    @Override
    public boolean hasSize() {
        return hasGardenSize() && houseSize != null;
    }

    private boolean hasGardenSize() {
        return gardenSize != null;
    }
}
