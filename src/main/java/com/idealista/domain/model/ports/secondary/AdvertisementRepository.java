package com.idealista.domain.model.ports.secondary;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {
    List<Advertisement> findAll();

    void updateScore(int advertisementId, Integer score);

    void updateIrrelevantDate(int advertisementId);

    Optional<Advertisement> find(Integer advertisementId);
}
