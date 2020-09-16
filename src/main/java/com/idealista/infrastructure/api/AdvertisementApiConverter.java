package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AdvertisementApiConverter {
    public QualityAd convertToQualityAd(Advertisement advertisement) {
        return new QualityAd(advertisement.getId(),
                advertisement.getTypology().name(),
                advertisement.getDescription().getText(),
                advertisement.getPhotoList().stream().map(it -> it.getUri()).collect(Collectors.toList()),
                advertisement.getHouseSize(),
                advertisement.getGardenSize(),
                advertisement.getScore());
    }

    public PublicAd convertToPublicAd(Advertisement advertisement) {
        return new PublicAd(advertisement.getId(),
                advertisement.getTypology().name(),
                advertisement.getDescription().getText(),
                advertisement.getPhotoList().stream().map(it -> it.getUri()).collect(Collectors.toList()),
                advertisement.getHouseSize(),
                advertisement.getScore());
    }
}
