package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.description.DescriptionScorer;

public class AdvertisementScorer {

    private PhotoScorer photoScorer;
    private DescriptionScorer descriptionScorer;

    private static final int MINIMAL_SCORE = 0;
    private static final int MAXIMAL_SCORE = 100;

    public AdvertisementScorer(PhotoScorer photoScorer, DescriptionScorer descriptionScorer) {
        this.photoScorer = photoScorer;
        this.descriptionScorer = descriptionScorer;
    }

    public Integer score(Advertisement advertisement) {
        Integer score = photoScorer.score(advertisement.getPhotoList()) +
                descriptionScorer.score(advertisement.getTypology(), advertisement.getDescription());
        return checkLimits(score);
    }

    private Integer checkLimits(Integer score) {
        if (score < MINIMAL_SCORE) score = MINIMAL_SCORE;
        else if (score > MAXIMAL_SCORE) score = MAXIMAL_SCORE;
        return score;
    }

}
