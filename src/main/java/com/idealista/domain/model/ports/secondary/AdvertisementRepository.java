package com.idealista.domain.model.ports.secondary;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.List;

public interface AdvertisementRepository {
    List<Advertisement> findAll();

    void updateScore(int advertisementId, Integer score);
}
