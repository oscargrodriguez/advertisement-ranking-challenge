package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoScorer {
    @Value("${score.photo.empty_penalty}")
    private int emptyPenalty;

    public int score(List<Photo> photoList) {
        return photoList.isEmpty() ? emptyPenalty :
                photoList.stream().map(Photo::score).reduce(0, Integer::sum);
    }
}
