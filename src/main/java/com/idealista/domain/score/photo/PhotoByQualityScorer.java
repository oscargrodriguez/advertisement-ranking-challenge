package com.idealista.domain.score.photo;

import com.idealista.domain.model.advertisement.photo.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PhotoByQualityScorer {

    @Value("${score.photo.quality.high_definition}")
    private int hdScore;
    @Value("${score.photo.quality.standard}")
    private int standardScore;

    public int score(Photo photo) {
        return photo.isHighDefinition() ? hdScore : standardScore;
    }
}
