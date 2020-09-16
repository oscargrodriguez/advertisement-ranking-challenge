package com.idealista.domain.model.advertisement;

public abstract class HouseAdvertisement extends Advertisement {
    public HouseAdvertisement(Integer id,
                              Typology typology) {
        super(id, typology);
    }

    public HouseAdvertisement(Integer id,
                              Typology typology,
                              Description description) {
        super(id, typology, description);
    }

    public HouseAdvertisement(Integer id,
                              Typology typology,
                              Description description,
                              Integer houseSize) {
        super(id, typology, description);
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
