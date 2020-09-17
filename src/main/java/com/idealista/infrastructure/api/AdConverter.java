package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdConverter {
    public QualityAd convertToQualityAd(Advertisement advertisement) {
        return new QualityAd(advertisement.getId(),
                advertisement.getTypologyName(),
                advertisement.getDescriptionText(),
                advertisement.getPhotoUrls(),
                advertisement.getHouseSize(),
                advertisement.getGardenSize(),
                advertisement.getScore(),
                advertisement.getIrrelevantSince());
    }

    public PublicAd convertToPublicAd(Advertisement advertisement) {
        return new PublicAd(advertisement.getId(),
                advertisement.getTypologyName(),
                advertisement.getDescriptionText(),
                advertisement.getPhotoUrls(),
                advertisement.getHouseSize(),
                advertisement.getGardenSize());
    }
}
