package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdvertisementConverter {
    @Autowired
    AdConverterFactory adConverterFactory;

    public Optional<Advertisement> convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        return adConverterFactory.getConverter(adVo).map(converter -> getAdvertisement(adVo, standardPictures, hdPictures, converter));
    }

    private Advertisement getAdvertisement(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures, AdConverter adConverter) {
        Advertisement advertisement = adConverter.convert(adVo, standardPictures, hdPictures);
        advertisement.addPhotos(convertPictures(standardPictures), convertPictures(hdPictures));
        return advertisement;
    }

    private List<String> convertPictures(List<PictureVO> pictures) {
        return pictures.stream().map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
