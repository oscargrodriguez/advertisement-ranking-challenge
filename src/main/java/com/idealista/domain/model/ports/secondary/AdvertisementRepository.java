package com.idealista.domain.model.ports.secondary;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {
    List<Advertisement> findAll();

    void updateScore(int advertisementId, Integer score);

    Optional<Advertisement> find(Integer advertisementId);
}
