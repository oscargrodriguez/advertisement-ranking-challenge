package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;

public class Advertisement {

    private DescriptiveText descriptiveText;
    private List<Photo> photoList = new ArrayList<>();
    private AdvertisementType advertisementType;

    public Advertisement(AdvertisementType advertisementType) {
        this.descriptiveText = new DescriptiveText();
        this.advertisementType = advertisementType;
    }

    public Advertisement(DescriptiveText descriptiveText,
                         AdvertisementType advertisementType) {
        this.descriptiveText = descriptiveText;
        this.advertisementType = advertisementType;
    }

    public int score() {
        int score = descriptiveText.score(advertisementType) +
                photoScore(photoList);
        if (score < 0)
        {
            score = 0;
        }
        return score;
    }

    public void addPhotos(List<String> uris) {
        uris.stream().forEach(it->photoList.add(new Photo(it)));
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        uris.stream().forEach(it->photoList.add(new HighDefinitionPhoto(it)));
    }

    private int photoScore(List<Photo> photoList) {
        if (photoList.isEmpty())
        {
            return -10;
        }
        return photoList.stream().map(it -> it.score()).reduce(0, Integer::sum);
    }
}
