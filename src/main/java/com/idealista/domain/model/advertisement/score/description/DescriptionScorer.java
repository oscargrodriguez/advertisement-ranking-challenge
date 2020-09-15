package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class DescriptionScorer {

    public static final int NON_EMPTY_SCORE = 5;

    private KeywordScorer keywordScorer;

    public DescriptionScorer(KeywordScorer keywordScorer) {
        this.keywordScorer = keywordScorer;
    }

    public Integer score(Typology typology, Description description) {
        return description.isEmpty() ? 0 :
                NON_EMPTY_SCORE +
                        scoreByLength(typology, description) +
                        keywordScorer.score(description);
    }

    private Integer scoreByLength(Typology typology, Description description) {
        Integer score = 0;
        if (FLAT.equals(typology)) {
            score = flatLengthScore(description);
        } else if (CHALET.equals(typology)) {
            score = chaletLengthScore(description);
        }
        return score;
    }

    private Integer chaletLengthScore(Description description) {
        return description.isLarge() ? 20 : 0;
    }

    private Integer flatLengthScore(Description description) {
        Integer score = 0;
        if (description.isMedium()) {
            score = 10;
        } else if (description.isLarge()) {
            score = 30;
        }
        return score;
    }
}
