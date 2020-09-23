package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
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

    public int score(Advertisement advertisement) {
        return advertisement.hasEmptyDescription() ? emptyScore : nonEmptyScore(advertisement);
    }

    private int nonEmptyScore(Advertisement advertisement) {
        return nonEmptyScore +
                lengthScorer.score(advertisement) +
                keywordScorer.score(advertisement.getDescription());
    }
}