package com.idealista.domain.score.photo;

import com.idealista.domain.model.advertisement.photo.Photo;
import org.springframework.stereotype.Component;

@Component
public class PhotoByQualityScorer {

    private static final int HD_SCORE = 20;
    private static final int STANDARD_SCORE = 10;

    public int score(Photo photo) {
        return photo.isHighDefinition() ? HD_SCORE : STANDARD_SCORE;
    }
}
