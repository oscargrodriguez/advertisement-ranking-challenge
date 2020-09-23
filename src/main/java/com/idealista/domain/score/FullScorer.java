package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.FullVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FullScorer {
    @Autowired
    private FullVerifier fullVerifier;
    @Value("${score.full}")
    private int fullScore;
    @Value("${score.incomplete}")
    private int incompleteScore;


    public int score(Advertisement advertisement) {
        return fullVerifier.verify(advertisement) ? fullScore : incompleteScore;
    }
}
