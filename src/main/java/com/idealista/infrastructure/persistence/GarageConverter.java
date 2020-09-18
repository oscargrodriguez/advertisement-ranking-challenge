package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.GarageAdvertisement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("garageConverter")
public class GarageConverter implements AdConverter {
    @Override
    public Advertisement convert(AdVO adVo) {
        return new GarageAdvertisement(adVo.getId(), new Description(adVo.getDescription()));
    }
}
