package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;

public class DescriptionScorer {

    private static final int NON_EMPTY_SCORE = 5;
    private static final int EMPTY_SCORE = 0;

    private KeywordScorer keywordScorer;
    private LengthScorer lengthScorer;

    public DescriptionScorer(KeywordScorer keywordScorer,
                             LengthScorer lengthScorer) {
        this.keywordScorer = keywordScorer;
        this.lengthScorer = lengthScorer;
    }

    public Integer score(Typology typology, Description description) {
        return description.isEmpty() ? EMPTY_SCORE :
                nonEmptyScore(typology, description);
    }

    private int nonEmptyScore(Typology typology, Description description) {
        return NON_EMPTY_SCORE +
                lengthScorer.score(typology, description) +
                keywordScorer.score(description);
    }
}
