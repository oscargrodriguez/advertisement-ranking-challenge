package com.idealista.domain.score.photo;

import com.idealista.domain.model.advertisement.photo.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoScorer {
    @Value("${score.photo.empty_penalty}")
    private int emptyPenalty;
    @Autowired
    private PhotoByQualityScorer photoByQualityScorer;

    public int score(List<Photo> photoList) {
        return photoList.isEmpty() ? emptyPenalty :
                photoList.stream().map(photo -> photoByQualityScorer.score(photo)).reduce(0, Integer::sum);
    }
}
