package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeywordScorer {
    @Value("${score.keyword}")
    private int keywordScore;

    public int score(Description description) {
        return description.getKeywords() * keywordScore;
    }
}
