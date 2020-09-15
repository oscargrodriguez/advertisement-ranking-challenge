package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class AdvertisementConverter {
    public Optional<Advertisement> convert(AdVO adVo, List<PictureVO> standardPictures, List<PictureVO> hdPictures) {
        if (adVo.isGarage()) {
            return convertGarage(adVo, standardPictures, hdPictures);
        }
        if (adVo.isFlat()) {
            return convertFlat(adVo, standardPictures, hdPictures);
        } else if (adVo.isChalet()) {
            return convertChalet(adVo, standardPictures, hdPictures);
        }
        return Optional.empty();
    }

    private Optional<Advertisement> convertChalet(AdVO adVo,
                                                  List<PictureVO> standardPictures,
                                                  List<PictureVO> hdPictures) {
        return convert(adVo, standardPictures, hdPictures, () -> getChaletAdvertisement(adVo));

    }

    private ChaletAdvertisement getChaletAdvertisement(AdVO adVo) {
        return new ChaletAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize(), adVo.getGardenSize());
    }

    private Optional<Advertisement> convertFlat(AdVO adVo,
                                                List<PictureVO> standardPictures,
                                                List<PictureVO> hdPictures) {
        return convert(adVo, standardPictures, hdPictures, () -> getFlatAdvertisement(adVo));
    }

    private FlatAdvertisement getFlatAdvertisement(AdVO adVo) {
        return new FlatAdvertisement(new Description(adVo.getDescription()), adVo.getHouseSize());
    }

    private Optional<Advertisement> convertGarage(AdVO adVo,
                                                  List<PictureVO> standardPictures,
                                                  List<PictureVO> hdPictures) {
        return convert(adVo, standardPictures, hdPictures, () -> getGarageAdvertisement(adVo));
    }

    private GarageAdvertisement getGarageAdvertisement(AdVO adVo) {
        return new GarageAdvertisement(new Description(adVo.getDescription()));
    }

    private Optional<Advertisement> convert(AdVO adVo,
                                            List<PictureVO> standardPictures,
                                            List<PictureVO> hdPictures,
                                            Supplier<Advertisement> supplier) {
        Advertisement advertisement = supplier.get();
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
