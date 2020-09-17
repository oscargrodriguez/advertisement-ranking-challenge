package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.score.description.DescriptionScorer;
import com.idealista.domain.score.photo.PhotoScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementScorer {
    @Autowired
    private PhotoScorer photoScorer;
    @Autowired
    private DescriptionScorer descriptionScorer;
    @Autowired
    private FullAdvertisementScorer fullAdvertisementScorer;
    @Value("${score.max}")
    private int maxValue;
    @Value("${score.min}")
    private int minValue;

    public int score(Advertisement advertisement) {
        return checkLimits(calculateScore(advertisement));
    }

    private int calculateScore(Advertisement advertisement) {
        return photoScorer.score(advertisement.getPhotoList()) +
                descriptionScorer.score(advertisement.getTypology(), advertisement.getDescription()) +
                fullAdvertisementScorer.score(advertisement);
    }

    private int checkLimits(Integer score) {
        if (score < minValue) score = minValue;
        else if (score > maxValue) score = maxValue;
        return score;
    }
}
