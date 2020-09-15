package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Typology;
import com.idealista.domain.model.advertisement.Description;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;

public class DescriptionScorer {

    public static final int NON_EMPTY_SCORE = 5;
    public static final int KEYWORD_SCORE = 5;

    public Integer score(Typology typology, Description description) {
        return description.isEmpty() ? 0 : NON_EMPTY_SCORE + scoreByLength(typology, description) + scoreByKeywords(description);
    }

    private Integer scoreByKeywords(Description description) {
        return description.getKeywords() * KEYWORD_SCORE;
    }

    private Integer scoreByLength(Typology typology, Description description) {
        Integer score = 0;
        if (FLAT.equals(typology)) {
            score =  flatLengthScore(description);
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
            score =  10;
        } else if (description.isLarge()) {
            score = 30;
        } return score;
    }
}
