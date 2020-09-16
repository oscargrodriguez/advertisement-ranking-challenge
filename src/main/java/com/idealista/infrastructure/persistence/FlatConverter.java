package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("flatConverter")
public class FlatConverter implements AdConverter {
    @Override
    public Advertisement convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        return new FlatAdvertisement(adVo.getId(), new Description(adVo.getDescription()), adVo.getHouseSize());
    }
}
