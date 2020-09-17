package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import com.idealista.domain.score.AdvertisementScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CalculateScoreUseCase {
    @Autowired
    private AdvertisementRepository inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;
    @Value("${score.irrelevant_threshold}")
    private int irrelevantThreashold;

    public List<Advertisement> scoreAll() {
        List<Advertisement> advertisements = inMemoryPersistence.findAll();
        advertisements.forEach(ad -> updateScore(ad));
        return inMemoryPersistence.findAll();
    }

    public Optional<Advertisement> score(int id) {
        Optional<Advertisement> advertisement = inMemoryPersistence.find(id);
        if (!advertisement.isPresent()) {
            return Optional.empty();
        }
        updateScore(advertisement.get());
        return inMemoryPersistence.find(id);
    }

    private void updateScore(Advertisement advertisement) {
        inMemoryPersistence.updateScore(advertisement.getId(), advertisementScorer.score(advertisement));
        inMemoryPersistence.updateIrrelevantDate(advertisement.getId(), irrelevantThreashold);
    }

    public List<Advertisement> getPublicAdsOrderedByScoreDesc() {
        return scoreAll().stream().filter(it -> it.getScore() > irrelevantThreashold)
                .sorted(Comparator.comparingInt(Advertisement::getScore).reversed())
                .collect(Collectors.toList());
    }

    public List<Advertisement> getIrrelevantAds() {
        return scoreAll().stream().filter(it -> it.getScore() <= irrelevantThreashold)
                .collect(Collectors.toList());
    }
}
