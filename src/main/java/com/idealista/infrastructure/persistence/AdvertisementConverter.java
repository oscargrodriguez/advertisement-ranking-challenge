package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdvertisementConverter {
    @Autowired
    AdConverterFactory adConverterFactory;
    @Autowired
    PictureConverter pictureConverter;

    public Advertisement convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        Advertisement advertisement = adConverterFactory.getConverter(adVo).convert(adVo);
        advertisement.addPhotos(pictureConverter.convert(standardPictures), pictureConverter.convert(hdPictures));
        advertisement.setScore(adVo.getScore());
        advertisement.setIrrelevantSince(adVo.getIrrelevantSince());
        return advertisement;
    }
}
