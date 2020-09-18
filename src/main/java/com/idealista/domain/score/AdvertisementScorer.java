package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.score.description.DescriptionScorer;
import com.idealista.domain.score.photo.PhotoScorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AdvertisementScorer {
    @Autowired
    private PhotoScorer photoScorer;
    @Autowired
    private DescriptionScorer descriptionScorer;
    @Autowired
    private FullScorer fullScorer;
    @Value("${score.max}")
    private int maxValue;
    @Value("${score.min}")
    private int minValue;


    public int score(Advertisement advertisement) {
        return calculateScore().andThen(checkLimits()).apply(advertisement);
    }

    private Function<Advertisement, Integer> calculateScore() {
        return ad -> photoScorer.score(ad.getPhotoList()) +
                descriptionScorer.score(ad.getTypology(), ad.getDescription()) +
                fullScorer.score(ad);
    }

    private Function<Integer, Integer> checkLimits() {
        return score -> {
            if (score < minValue) score = minValue;
            else if (score > maxValue) score = maxValue;
            return score;
        };
    }
}
