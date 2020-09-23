package com.idealista.domain.model.ports.primary;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.AdvertisementScored;
import com.idealista.domain.model.ports.secondary.AdvertisementRepository;
import com.idealista.domain.score.AdvertisementScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.idealista.domain.model.advertisement.AdvertisementScored.scoreDescComparator;

@Component
public class CalculateScoreUseCase {
    @Autowired
    private AdvertisementRepository inMemoryPersistence;
    @Autowired
    private AdvertisementScorer advertisementScorer;
    @Value("${score.irrelevant_threshold}")
    private int irrelevantThreashold;

    public List<AdvertisementScored> scoreAll() {
        return inMemoryPersistence.findAll().stream()
                .map(ad -> updateScoreValue(ad).get())
                .collect(Collectors.toList());
    }

    public Optional<AdvertisementScored> score(int id) {
        return inMemoryPersistence.find(id).flatMap(this::updateScoreValue);
    }

    public List<AdvertisementScored> getPublicAdsOrderedByScoreDesc() {
        return scoreAll().stream()
                .filter(relevant())
                .sorted(scoreDescComparator())
                .collect(Collectors.toList());
    }

    public List<AdvertisementScored> getIrrelevantAds() {
        return scoreAll().stream()
                .filter(irrelevant())
                .collect(Collectors.toList());
    }

    private Optional<AdvertisementScored> updateScoreValue(Advertisement advertisement) {
        updateScoreValue().andThen(updateIrrelevantDate()).accept(advertisement);
        return inMemoryPersistence.findScored(advertisement.getId());
    }

    private Consumer<Advertisement> updateScoreValue() {
        return ad -> inMemoryPersistence.updateScore(ad.getId(), advertisementScorer.score(ad));
    }

    private Consumer<Advertisement> updateIrrelevantDate() {
        return ad -> inMemoryPersistence.updateIrrelevantDate(ad.getId(), irrelevantThreashold);
    }

    private Predicate<AdvertisementScored> irrelevant() {
        return ad -> ad.getScore() <= irrelevantThreashold;
    }

    private Predicate<AdvertisementScored> relevant() {
        return ad -> ad.getScore() > irrelevantThreashold;
    }
}
