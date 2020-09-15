package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdvertisementConverter {
    public Optional<Advertisement> convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        if (isGarage(adVo)) {
            Optional<Advertisement> advertisement = Optional.of(new GarageAdvertisement(new Description(adVo.getDescription())));
            advertisement.get().addStandardPhotos(convertPictures(standardPictures));
            advertisement.get().addHighDefinitionPhotos(convertPictures(hdPictures));
            return advertisement;
        }
        if (isFlat(adVo)) {
            Optional<Advertisement> advertisement = Optional.of(new FlatAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize()));
            advertisement.get().addStandardPhotos(convertPictures(standardPictures));
            advertisement.get().addHighDefinitionPhotos(convertPictures(hdPictures));
            return advertisement;
        } else if (isChalet(adVo)) {
            Optional<Advertisement> advertisement = Optional.of(new ChaletAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize(), adVo.getGardenSize()));
            advertisement.get().addStandardPhotos(convertPictures(standardPictures));
            advertisement.get().addHighDefinitionPhotos(convertPictures(hdPictures));
            return advertisement;
        }
        return Optional.empty();
    }

    private List<String> convertPictures(List<PictureVO> pictures) {
        return pictures.stream().map(PictureVO::getUrl).collect(Collectors.toList());
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
