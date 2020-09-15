package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.AdvertisementType;
import com.idealista.domain.model.advertisement.DescriptiveText;

import static com.idealista.domain.model.advertisement.AdvertisementType.CHALET;
import static com.idealista.domain.model.advertisement.AdvertisementType.HOUSE;

public class DescriptiveTextScorer {

    public static final int NON_EMPTY_SCORE = 5;

    public int score(AdvertisementType advertisementType, DescriptiveText descriptiveText) {
        return descriptiveText.isEmpty() ? 0 : NON_EMPTY_SCORE + scoreByLength(advertisementType, descriptiveText);
    }

    private int scoreByLength(AdvertisementType advertisementType, DescriptiveText descriptiveText) {
        int score = 0;
        if (HOUSE.equals(advertisementType)) {
            score =  calculateHouseLengthScore(descriptiveText);
        } else if (CHALET.equals(advertisementType)) {
            score = calculateChaletLengthScore(descriptiveText);
        }
        return score;
    }

    private int calculateChaletLengthScore(DescriptiveText descriptiveText) {
        return descriptiveText.isLarge() ? 20 : 0;
    }

    private int calculateHouseLengthScore(DescriptiveText descriptiveText) {
        int score = 0;
        if (descriptiveText.isMedium()) {
            score =  10;
        } else if (descriptiveText.isLarge()) {
            score = 30;
        } return score;
    }
}
