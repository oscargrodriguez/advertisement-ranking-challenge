package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Advertisement;

public class FullAdScorer {
    public int score(Advertisement advertisement) {
        if (advertisement.isGarage()) {
            if (advertisement.hasPhoto()) {
                return 40;
            }
        }
        return 0;
    }
}
