package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DescriptionScorer {

    private static final int NON_EMPTY_SCORE = 5;
    private static final int EMPTY_SCORE = 0;

    @Autowired
    private KeywordScorer keywordScorer;
    @Autowired
    private LengthScorer lengthScorer;


    public int score(Typology typology, Description description) {
        return description.isEmpty() ? EMPTY_SCORE :
                nonEmptyScore(typology, description);
    }

    private int nonEmptyScore(Typology typology, Description description) {
        return NON_EMPTY_SCORE +
                lengthScorer.score(typology, description) +
                keywordScorer.score(description);
    }
}