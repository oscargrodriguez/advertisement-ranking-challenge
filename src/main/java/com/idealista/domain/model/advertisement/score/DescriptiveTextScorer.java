package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.AdvertisementType;
import com.idealista.domain.model.advertisement.DescriptiveText;

import static com.idealista.domain.model.advertisement.AdvertisementType.CHALET;
import static com.idealista.domain.model.advertisement.AdvertisementType.HOUSE;

public class DescriptiveTextScorer {

    public static final int NON_EMPTY_SCORE = 5;

    public int score(AdvertisementType advertisementType, DescriptiveText descriptiveText) {
        if (descriptiveText.isEmpty()) {
            return 0;
        }
        return NON_EMPTY_SCORE + scoreByLength(advertisementType, descriptiveText);
    }

    private int scoreByLength(AdvertisementType advertisementType, DescriptiveText descriptiveText) {
        if (HOUSE.equals(advertisementType)) {
            return calculateHouseLengthScore(descriptiveText);
        } else if (CHALET.equals(advertisementType)) {
            return calculateChaletLengthScore(descriptiveText);
        }
        return 0;
    }

    private int calculateChaletLengthScore( DescriptiveText descriptiveText) {
        if (descriptiveText.isLarge()) {
            return 20;
        }
        return 0;
    }

    private int calculateHouseLengthScore( DescriptiveText descriptiveText) {
        if (descriptiveText.isMedium()) {
            return 10;
        } else if (descriptiveText.isLarge()) {
            return 30;
        } else {
            return 0;
        }
    }
}
