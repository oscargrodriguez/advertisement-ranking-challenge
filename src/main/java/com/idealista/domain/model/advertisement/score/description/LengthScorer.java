package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class LengthScorer {

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
        return description.isLarge() ? 20 : 0;
    }

    private int flatScore(Description description) {
        Integer score = 0;
        if (description.isMedium()) {
            score = 10;
        } else if (description.isLarge()) {
            score = 30;
        }
        return score;
    }
}
