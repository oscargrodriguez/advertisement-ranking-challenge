package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;

@Component
public class LengthScorer {

    @Value("${score.description.length.flat.short}")
    private int flatShort;
    @Value("${score.description.length.flat.medium}")
    private int flatMedium;
    @Value("${score.description.length.flat.large}")
    private int flatLarge;
    @Value("${score.description.length.chalet.large}")
    private int chaletLarge;


    public int score(Typology typology, Description description) {
        int score = 0;
        if (FLAT.equals(typology)) {
            score = flatScore(description);
        } else if (CHALET.equals(typology)) {
            score = chaletScore(description);
        }
        return score;
    }

    private int chaletScore(Description description) {
        return description.isLarge() ? chaletLarge : 0;
    }

    private int flatScore(Description description) {
        Integer score = flatShort;
        if (description.isMedium()) {
            score = flatMedium;
        } else if (description.isLarge()) {
            score = flatLarge;
        }
        return score;
    }
}
