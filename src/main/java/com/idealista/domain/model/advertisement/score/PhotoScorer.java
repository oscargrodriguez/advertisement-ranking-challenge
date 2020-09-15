package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Photo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoScorer {
    public static final int NO_PHOTO_PENALTY = -10;
    public static final int MINIMAL_SCORE = 0;

    public int score(List<Photo> photoList) {
        return photoList.isEmpty() ? NO_PHOTO_PENALTY : photoList.stream().map(it -> it.score()).reduce(MINIMAL_SCORE, Integer::sum);
    }
}
