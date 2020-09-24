package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeywordScorer {
    @Value("${score.keyword}")
    private int keywordScore;
    @Autowired
    private KeywordRetriever keywordRetriever;

    public int score(Description description) {
        return keywordRetriever.getNumberOfKeywords(description.getText()) * keywordScore;
    }
}
