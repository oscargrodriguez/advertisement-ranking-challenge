package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;

public class AdvertisementScorer {

    private PhotoScorer photoScorer;
    private DescriptiveTextScorer textScorer;

    private static final int MINIMAL_SCORE = 0;

    public AdvertisementScorer(PhotoScorer photoScorer, DescriptiveTextScorer textScorer) {
        this.photoScorer = photoScorer;
        this.textScorer = textScorer;
    }

    public int score(Advertisement advertisement) {
        int score = photoScorer.score(advertisement.getPhotoList()) +
                textScorer.score(advertisement.getAdvertisementType(), advertisement.getDescriptiveText());
        return score < MINIMAL_SCORE ? MINIMAL_SCORE : score;
    }
}
