package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementConverter {
    @Autowired
    AdConverterFactory adConverterFactory;

    public Advertisement convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        Advertisement advertisement = adConverterFactory.getConverter(adVo).convert(adVo, standardPictures, hdPictures);
        advertisement.addPhotos(convertPictures(standardPictures), convertPictures(hdPictures));
        advertisement.setScore(adVo.getScore());
        return advertisement;
    }

    private List<String> convertPictures(List<PictureVO> pictures) {
        return pictures.stream().map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
