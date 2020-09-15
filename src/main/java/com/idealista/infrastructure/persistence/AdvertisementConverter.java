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
            return convertGarage(adVo, standardPictures, hdPictures);
        }
        if (isFlat(adVo)) {
            return convertFlat(adVo, standardPictures, hdPictures);
        } else if (isChalet(adVo)) {
            return convertChalet(adVo, standardPictures, hdPictures);
        }
        return Optional.empty();
    }

    private Optional<Advertisement> convertChalet(AdVO adVo,
                                                  List<PictureVO> standardPictures,
                                                  List<PictureVO> hdPictures) {
        Optional<Advertisement> advertisement = Optional.of(new ChaletAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize(), adVo.getGardenSize()));
        addPhotos(advertisement.get(), standardPictures, hdPictures);
        return advertisement;
    }

    private Optional<Advertisement> convertFlat(AdVO adVo,
                                                List<PictureVO> standardPictures,
                                                List<PictureVO> hdPictures) {
        Optional<Advertisement> advertisement = Optional.of(new FlatAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize()));
        addPhotos(advertisement.get(), standardPictures, hdPictures);
        return advertisement;
    }

    private Optional<Advertisement> convertGarage(AdVO adVo,
                                                  List<PictureVO> standardPictures,
                                                  List<PictureVO> hdPictures) {
        Optional<Advertisement> advertisement = Optional.of(new GarageAdvertisement(new Description(adVo.getDescription())));
        addPhotos(advertisement.get(), standardPictures, hdPictures);
        return advertisement;
    }

    private void addPhotos(Advertisement advertisement, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        advertisement.addStandardPhotos(convertPictures(standardPictures));
        advertisement.addHighDefinitionPhotos(convertPictures(hdPictures));
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
