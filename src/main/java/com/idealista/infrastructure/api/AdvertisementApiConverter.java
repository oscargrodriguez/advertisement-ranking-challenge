package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementApiConverter {
    public QualityAd convert(Advertisement advertisement) {
        return new QualityAd(advertisement.getId(),
                advertisement.getTypology().name(),
                null,
                null,
                null,
                null,
                advertisement.getScore());
    }
}
