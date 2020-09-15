package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;

public class KeywordScorer {
    private static final int KEYWORD_SCORE = 5;

    public Integer score(Description description) {
        return description.getKeywords() * KEYWORD_SCORE;
    }

}
