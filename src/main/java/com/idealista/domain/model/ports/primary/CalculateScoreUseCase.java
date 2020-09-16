package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.AdvertisementScorer;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculateScoreUseCase {
    @Autowired
    private AdvertisementRepository inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;

    public List<Advertisement> scoreAll() {
        List<Advertisement> advertisements = inMemoryPersistence.findAll();
        advertisements.forEach(ad -> inMemoryPersistence.updateScore(ad.getId(), advertisementScorer.score(ad)));
        return inMemoryPersistence.findAll();
    }
}
