package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.score.AdvertisementScorer;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculateScoreUseCase {
    @Autowired
    private AdvertisementRepository inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;

    public Optional<Integer> score(int advertisementId) {
        return inMemoryPersistence.findAdvertisement(advertisementId)
                .map(it -> Optional.of(advertisementScorer.score(it)))
                .orElse(Optional.empty());
    }
}
