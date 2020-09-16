package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.AdvertisementScorer;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import com.idealista.infrastructure.api.QualityAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CalculateScoreUseCase {
    @Autowired
    private AdvertisementRepository inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;

    public Optional<Integer> score(int advertisementId) {
        return inMemoryPersistence.findAdvertisement(advertisementId)
                .map(ad -> Optional.of(advertisementScorer.score(ad)))
                .orElse(Optional.empty());
    }

    public List<Advertisement> scoreAll() {
        //inMemoryPersistence.findAll().stream().forEach(ad->inMemoryPersistence.updateScore(ad.get));
        return Arrays.asList();
    }
}
