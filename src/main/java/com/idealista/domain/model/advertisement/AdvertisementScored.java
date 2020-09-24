package com.idealista.domain.model.advertisement;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AdvertisementScored {

    private Advertisement advertisement;
    private Integer score;
    private Date irrelevantSince;

    public AdvertisementScored(Advertisement advertisement, Integer score, Date irrelevantSince) {
        this.advertisement = advertisement;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public Integer getScore() {
        return score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public static Comparator<AdvertisementScored> scoreDescComparator() {
        return Comparator.comparingInt(AdvertisementScored::getScore).reversed();
    }

    public Integer getId() {
        return advertisement.getId();
    }

    public Integer getGardenSize() {
        return advertisement.getGardenSize();
    }

    public Integer getHouseSize() {
        return advertisement.getHouseSize();
    }

    public Typology getTypology() {
        return advertisement.getTypology();
    }

    public String getDescriptionText() {
        return advertisement.getDescription().getText();
    }

    public String getTypologyName() {
        return advertisement.getTypologyName();
    }

    public List<String> getPhotoUrls() {
        return advertisement.getPhotoUrls();
    }
}
