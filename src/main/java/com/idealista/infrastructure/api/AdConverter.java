package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.AdvertisementScored;
import org.springframework.stereotype.Component;

@Component
public class AdConverter {
    public QualityAd toQualityAd(AdvertisementScored advertisement) {
        return new QualityAd(advertisement.getAdvertisement().getId(),
                advertisement.getTypologyName(),
                advertisement.getDescriptionText(),
                advertisement.getPhotoUrls(),
                advertisement.getHouseSize(),
                advertisement.getGardenSize(),
                advertisement.getScore(),
                advertisement.getIrrelevantSince());
    }

    public PublicAd toPublicAd(AdvertisementScored advertisement) {
        return new PublicAd(advertisement.getAdvertisement().getId(),
                advertisement.getTypologyName(),
                advertisement.getDescriptionText(),
                advertisement.getPhotoUrls(),
                advertisement.getHouseSize(),
                advertisement.getGardenSize());
    }
}
