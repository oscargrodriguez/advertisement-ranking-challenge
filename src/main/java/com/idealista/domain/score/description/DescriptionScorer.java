package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DescriptionScorer {
    @Autowired
    private KeywordScorer keywordScorer;
    @Autowired
    private LengthScorer lengthScorer;
    @Value("${score.description.empty}")
    private int emptyScore;
    @Value("${score.description.non_empty}")
    private int nonEmptyScore;

    public int score(Typology typology, Description description) {
        return description.isEmpty() ? emptyScore :
                nonEmptyScore(typology, description);
    }

    private int nonEmptyScore(Typology typology, Description description) {
        return nonEmptyScore +
                lengthScorer.score(typology, description) +
                keywordScorer.score(description);
    }
}