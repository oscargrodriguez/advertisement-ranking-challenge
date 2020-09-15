package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Advertisement;

public class FullAdScorer {
    public int score(Advertisement advertisement) {
        if (advertisement.isGarage()) {
            if (advertisement.hasPhoto()) {
                return 40;
            }
        }
        else if (advertisement.isFlat())
        {
            if (advertisement.hasPhoto() && advertisement.hasDescription() && advertisement.hasHouseSize()) return 40;
        }
        else if (advertisement.isChalet())
        {
            if (advertisement.hasPhoto() && advertisement.hasDescription() && advertisement.hasHouseSize() && advertisement.hasGardenSize()) return 40;
        }
        return 0;
    }
}
