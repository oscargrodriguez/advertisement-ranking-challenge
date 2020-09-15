package com.idealista.domain.model.ports.secondary;

import com.idealista.domain.model.advertisement.Advertisement;

import java.util.Optional;

public interface AdvertisementRepository {
    Optional<Advertisement> findAdvertisement(int advertisementId);
}
