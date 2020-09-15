package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.description.DescriptionScorer;

public class AdvertisementScorer {

    private PhotoScorer photoScorer;
    private DescriptionScorer descriptionScorer;

    private static final int MINIMAL_SCORE = 0;

    public AdvertisementScorer(PhotoScorer photoScorer, DescriptionScorer descriptionScorer) {
        this.photoScorer = photoScorer;
        this.descriptionScorer = descriptionScorer;
    }

    public Integer score(Advertisement advertisement) {
        Integer score = photoScorer.score(advertisement.getPhotoList()) +
                descriptionScorer.score(advertisement.getTypology(), advertisement.getDescription());
        return score < MINIMAL_SCORE ? MINIMAL_SCORE : score;
    }
}
