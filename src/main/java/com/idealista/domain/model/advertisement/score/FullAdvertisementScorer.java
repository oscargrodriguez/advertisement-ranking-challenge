package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;

public class FullAdvertisementScorer {

    public static final int FULL_AD_SCORE = 40;
    public static final int NON_FULL_AD_SCORE = 0;

    public int score(Advertisement advertisement) {
        return advertisement.isFull() ? FULL_AD_SCORE : NON_FULL_AD_SCORE;
    }
}
