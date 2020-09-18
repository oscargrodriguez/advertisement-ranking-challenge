package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.ChaletAdvertisement;
import com.idealista.domain.model.advertisement.Description;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("chaletConverter")
public class ChaletConverter implements AdConverter {
    @Override
    public Advertisement convert(AdVO adVo) {
        return new ChaletAdvertisement(adVo.getId(), new Description(adVo.getDescription()), adVo.getHouseSize(), adVo.getGardenSize());
    }
}
