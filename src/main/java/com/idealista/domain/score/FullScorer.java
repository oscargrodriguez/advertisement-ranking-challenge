package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FullScorer {

    @Value("${score.full}")
    private int fullScore;
    public static final int NON_FULL_AD_SCORE = 0;

    public int score(Advertisement advertisement) {
        return advertisement.isFull() ? fullScore : NON_FULL_AD_SCORE;
    }
}
