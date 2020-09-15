package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;

public class DescriptionScorer {

    public static final int NON_EMPTY_SCORE = 5;

    private KeywordScorer keywordScorer;
    private LengthScorer lengthScorer;

    public DescriptionScorer(KeywordScorer keywordScorer,
                             LengthScorer lengthScorer) {
        this.keywordScorer = keywordScorer;
        this.lengthScorer = lengthScorer;
    }

    public Integer score(Typology typology, Description description) {
        return description.isEmpty() ? 0 :
                NON_EMPTY_SCORE +
                        lengthScorer.score(typology, description) +
                        keywordScorer.score(description);
    }
}
