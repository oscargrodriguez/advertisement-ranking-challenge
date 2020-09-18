package com.idealista.domain.model.ports.secondary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.AdvertisementScored;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {
    List<Advertisement> findAll();

    void updateScore(int advertisementId, Integer score);

    void updateIrrelevantDate(int advertisementId, int irrelevantThreshold);

    Optional<AdvertisementScored> findScored(Integer advertisementId);

    Optional<Advertisement> find(Integer advertisementId);

}
