package com.idealista.infrastructure.persistence;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class AdVO {

    private Integer id;
    private String typology;
    private String description;
    private List<Integer> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public AdVO(Integer id, String typology, String description, List<Integer> pictures, Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictures = pictures;
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

    public List<Integer> getPictures() {
        return pictures;
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

    public boolean isFlat() {
        return isTypology("FLAT");
    }

    public boolean isGarage() {
        return isTypology("GARAGE");
    }

    public Predicate<PictureVO> pictureContained() {
        return pictureVO -> pictures.contains(pictureVO.getId());
    }

    public Predicate<PictureVO> hasPhotoQuality(String quality) {
        return it -> it.getQuality().equals(quality);
    }

    private boolean isTypology(String otherTypology)
    {
        return otherTypology.equals(typology);
    }
}
