package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import com.idealista.domain.score.AdvertisementScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
        List<Advertisement> scoredAdvertisements = new ArrayList<>();
        inMemoryPersistence.findAll().forEach(ad -> scoredAdvertisements.add(updateScore(ad).get()));
        return scoredAdvertisements;
    }

    public Optional<Advertisement> score(int id) {
        return inMemoryPersistence.find(id).map(ad -> updateScore(ad)).orElse(Optional.empty());
    }

    public List<Advertisement> getPublicAdsOrderedByScoreDesc() {
        return scoreAll().stream().filter(relevant())
                .sorted(Comparator.comparingInt(Advertisement::getScore).reversed())
                .collect(Collectors.toList());
    }

    public List<Advertisement> getIrrelevantAds() {
        return scoreAll().stream().filter(irrelevant())
                .collect(Collectors.toList());
    }

    private Optional<Advertisement> updateScore(Advertisement advertisement) {
        inMemoryPersistence.updateScore(advertisement.getId(), advertisementScorer.score(advertisement));
        inMemoryPersistence.updateIrrelevantDate(advertisement.getId(), irrelevantThreashold);
        return inMemoryPersistence.find(advertisement.getId());
    }

    private Predicate<Advertisement> irrelevant() {
        return ad -> ad.getScore() <= irrelevantThreashold;
    }

    private Predicate<Advertisement> relevant() {
        return ad -> ad.getScore() > irrelevantThreashold;
    }
}
