package com.idealista.infrastructure.api;

import java.util.Date;
import java.util.List;

public class QualityAd extends PublicAd {

    private Integer id;
    private String typology;
    private String description;
    private List<String> pictureUrls;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public QualityAd(Integer id,
                     String typology,
                     String description,
                     List<String> pictureUrls,
                     Integer houseSize,
                     Integer gardenSize,
                     Integer score,
                     Date irrelevantSince) {
        super(id, typology, description, pictureUrls, houseSize, gardenSize);
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }
}
