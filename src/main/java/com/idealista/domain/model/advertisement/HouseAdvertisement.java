package com.idealista.domain.model.advertisement;

public abstract class HouseAdvertisement extends Advertisement {
    public HouseAdvertisement(Typology typology) {
        super(typology);
    }

    public HouseAdvertisement(Typology typology,
                              Description description) {
        super(typology, description);
    }

    public HouseAdvertisement(Typology typology,
                              Description description,
                              Integer houseSize) {
        super(typology, description);
        this.houseSize = houseSize;
    }

    protected Integer houseSize;

    @Override
    public boolean isFull() {
        return hasPhoto() && hasDescription() && hasSize();
    }

    protected boolean hasHouseSize() {
        return houseSize != null;
    }
}
