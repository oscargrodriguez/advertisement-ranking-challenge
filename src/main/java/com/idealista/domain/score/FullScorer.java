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
    private static final int NON_FULL_AD_SCORE = 0;


    public int score(Advertisement advertisement) {
        return fullVerifier.verify(advertisement) ? fullScore : NON_FULL_AD_SCORE;
    }
}
