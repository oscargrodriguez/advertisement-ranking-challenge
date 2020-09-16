package com.idealista.infrastructure.api;

import java.util.Date;
import java.util.List;

public class QualityAd {

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
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictureUrls = pictureUrls;
        this.houseSize = houseSize;
        this.gardenSize = gardenSize;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Integer getId() {
        return id;
    }

    public String getTypology() {
        return typology;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
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
