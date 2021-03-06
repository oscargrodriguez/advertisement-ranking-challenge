package com.idealista.infrastructure.persistence;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.AdvertisementScored;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryPersistence implements AdvertisementRepository {

    private static final String STANDARD_QUALITY = "SD";
    private static final String HIGH_DEFINITION_QUALITY = "HD";
    private List<AdVO> ads;
    private List<PictureVO> pictures;

    @Autowired
    private AdvertisementConverter advertisementConverter;

    public InMemoryPersistence() {
        ads = new ArrayList<>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Arrays.asList(4), 300, null, null, null));
        ads.add(new AdVO(3, "CHALET", "", Arrays.asList(2), 300, null, null, null));
        ads.add(new AdVO(4, "FLAT", "Ático céntrico muy luminoso y recién reformado, parece nuevo", Arrays.asList(5), 300, null, null, null));
        ads.add(new AdVO(5, "FLAT", "Pisazo,", Arrays.asList(3, 8), 300, null, null, null));
        ads.add(new AdVO(6, "GARAGE", "", Arrays.asList(6), 300, null, null, null));
        ads.add(new AdVO(7, "GARAGE", "Garaje en el centro de Albacete", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(8, "CHALET", "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Arrays.asList(1, 7), 300, null, null, null));

        pictures = new ArrayList<>();
        pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", STANDARD_QUALITY));
        pictures.add(new PictureVO(2, "http://www.idealista.com/pictures/2", HIGH_DEFINITION_QUALITY));
        pictures.add(new PictureVO(3, "http://www.idealista.com/pictures/3", STANDARD_QUALITY));
        pictures.add(new PictureVO(4, "http://www.idealista.com/pictures/4", HIGH_DEFINITION_QUALITY));
        pictures.add(new PictureVO(5, "http://www.idealista.com/pictures/5", STANDARD_QUALITY));
        pictures.add(new PictureVO(6, "http://www.idealista.com/pictures/6", STANDARD_QUALITY));
        pictures.add(new PictureVO(7, "http://www.idealista.com/pictures/7", STANDARD_QUALITY));
        pictures.add(new PictureVO(8, "http://www.idealista.com/pictures/8", HIGH_DEFINITION_QUALITY));
    }

    @Override
    public List<Advertisement> findAll() {
        List<Advertisement> advertisements = new ArrayList<>();
        ads.stream().forEach(ad -> advertisements.add(advertisementConverter.convert(ad, findStandardPictures(ad), findHdPictures(ad))));
        return advertisements;
    }

    @Override
    public void updateScore(int advertisementId, Integer score) {
        findById(advertisementId).ifPresent(it -> it.setScore(score));
    }

    @Override
    public void updateIrrelevantDate(int advertisementId, int irrelevantThreshold) {
        findById(advertisementId).ifPresent(adVo -> {
            if (adVo.getScore() < irrelevantThreshold) {
                adVo.setIrrelevantSince(new Date());
            }
        });
    }

    @Override
    public Optional<Advertisement> find(Integer advertisementId) {
        return findById(advertisementId)
                .map(ad -> Optional.of(advertisementConverter.convert(ad, findStandardPictures(ad), findHdPictures(ad))))
                .orElse(Optional.empty());

    }

    @Override
    public Optional<AdvertisementScored> findScored(Integer advertisementId) {
        return findById(advertisementId)
                .map(ad -> Optional.of(advertisementConverter.convertScored(ad, findStandardPictures(ad), findHdPictures(ad))))
                .orElse(Optional.empty());

    }

    private List<PictureVO> findStandardPictures(AdVO adVO) {
        return findPictures(adVO, STANDARD_QUALITY);
    }

    private List<PictureVO> findHdPictures(AdVO adVO) {
        return findPictures(adVO, HIGH_DEFINITION_QUALITY);
    }

    private List<PictureVO> findPictures(AdVO adVO, String quality) {
        return pictures.stream()
                .filter(adVO.pictureContained().and(adVO.hasPhotoQuality(quality)))
                .collect(Collectors.toList());
    }

    private Optional<AdVO> findById(int id) {
        return ads.stream().filter(it -> it.getId() == id).findFirst();
    }
}
