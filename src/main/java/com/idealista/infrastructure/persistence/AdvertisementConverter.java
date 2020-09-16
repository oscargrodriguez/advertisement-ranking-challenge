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
        Optional<AdConverter> adConverter = adConverterFactory.getConverter(adVo);
        if (!adConverter.isPresent()) {
            return Optional.empty();
        }
        Advertisement advertisement = adConverter.get().convert(adVo, standardPictures, hdPictures);
        addPhotos(advertisement, standardPictures, hdPictures);
        return Optional.of(advertisement);
    }

    private void addPhotos(Advertisement advertisement, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        advertisement.addStandardPhotos(convertPictures(standardPictures));
        advertisement.addHighDefinitionPhotos(convertPictures(hdPictures));
    }

    private List<String> convertPictures(List<PictureVO> pictures) {
        return pictures.stream().map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
