package com.idealista.domain.model.advertisement;

public abstract class HouseAdvertisement extends Advertisement {

    public HouseAdvertisement(Integer id,
                              Typology typology,
                              Description description,
                              Integer houseSize) {
        super(id, typology, description);
        this.houseSize = houseSize;
    }

    @Override
    public boolean isFull() {
        return hasPhoto() && hasDescription() && hasSize();
    }

    protected boolean hasHouseSize() {
        return houseSize != null;
    }
}
