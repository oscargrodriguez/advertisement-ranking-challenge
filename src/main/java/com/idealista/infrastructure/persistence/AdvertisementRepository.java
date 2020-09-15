package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.Optional;

public interface AdvertisementRepository {
    Optional<Advertisement> findAdvertisement(int advertisementId);
}
