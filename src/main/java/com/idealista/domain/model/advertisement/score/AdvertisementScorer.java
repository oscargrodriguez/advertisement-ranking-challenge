package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.score.description.DescriptionScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementScorer {
    @Autowired
    private PhotoScorer photoScorer;
    @Autowired
    private DescriptionScorer descriptionScorer;
    @Autowired
    private FullAdvertisementScorer fullAdvertisementScorer;

    private static final int MINIMAL_SCORE = 0;
    private static final int MAXIMAL_SCORE = 100;


    public int score(Advertisement advertisement) {
        return checkLimits(getScore(advertisement));
    }

    private int getScore(Advertisement advertisement) {
        return photoScorer.score(advertisement.getPhotoList()) +
                descriptionScorer.score(advertisement.getTypology(), advertisement.getDescription()) +
                fullAdvertisementScorer.score(advertisement);
    }

    private int checkLimits(Integer score) {
        if (score < MINIMAL_SCORE) score = MINIMAL_SCORE;
        else if (score > MAXIMAL_SCORE) score = MAXIMAL_SCORE;
        return score;
    }
}
