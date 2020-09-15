package com.idealista.infrastructure.api;

import com.idealista.domain.model.advertisement.*;
import com.idealista.infrastructure.persistence.AdVO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdvertisementConverter {
    public Optional<Advertisement> convert(AdVO adVo) {
        if (isGarage(adVo)) {
            return Optional.of(new GarageAdvertisement(new Description(adVo.getDescription())));
        }
        if (isFlat(adVo)) {
            return Optional.of(new FlatAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize()));
        } else if (isChalet(adVo)) {
            return Optional.of(new ChaletAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize(), adVo.getGardenSize()));
        }
        return Optional.empty();
    }

    private boolean isFlat(AdVO adVo) {
        return "FLAT".equals(adVo.getTypology());
    }

    private boolean isGarage(AdVO adVo) {
        return "GARAGE".equals(adVo.getTypology());
    }

    private boolean isChalet(AdVO adVo) {
        return "CHALET".equals(adVo.getTypology());
    }
}
