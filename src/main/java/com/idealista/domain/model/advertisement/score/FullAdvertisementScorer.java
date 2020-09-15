package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;

public class FullAdvertisementScorer {
    public int score(Advertisement advertisement) {
        if (advertisement.isFull()) {
                return 40;
        }
        return 0;
    }
}
