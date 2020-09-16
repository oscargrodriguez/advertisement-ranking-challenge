package com.idealista.infrastructure.api;

import java.util.List;

public class PublicAd {

    private Integer id;
    private String typology;
    private String description;
    private List<String> pictureUrls;
    private Integer houseSize;
    private Integer gardenSize;

    public PublicAd(Integer id, String typology, String description, List<String> pictureUrls, Integer houseSize, Integer gardenSize) {
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictureUrls = pictureUrls;
        this.houseSize = houseSize;
        this.gardenSize = gardenSize;
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
}
