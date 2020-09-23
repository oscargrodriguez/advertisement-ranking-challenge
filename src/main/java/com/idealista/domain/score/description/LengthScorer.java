package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.idealista.domain.model.advertisement.Advertisement.isChalet;
import static com.idealista.domain.model.advertisement.Advertisement.isFlat;

@Component
public class LengthScorer {

    @Value("${score.description.length.default}")
    private int defaultScore;
    @Value("${score.description.length.flat.short}")
    private int flatShort;
    @Value("${score.description.length.flat.medium}")
    private int flatMedium;
    @Value("${score.description.length.flat.large}")
    private int flatLarge;
    @Value("${score.description.length.chalet.large}")
    private int chaletLarge;

    public int score(Advertisement advertisement) {
        if (isFlat().test(advertisement)) {
            return flatScore(advertisement.getDescription());
        } else if (isChalet().test(advertisement)) {
            return chaletScore(advertisement.getDescription());
        }
        return defaultScore;
    }

    private int chaletScore(Description description) {
        return description.isLarge() ? chaletLarge : defaultScore;
    }

    private int flatScore(Description description) {
        if (description.isMedium()) {
            return flatMedium;
        } else if (description.isLarge()) {
            return flatLarge;
        }
        return flatShort;
    }
}
