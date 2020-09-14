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
        return descriptiveText.score(advertisementType) +
                photoScore(photoList);
    }

    public void addPhotos(List<String> uris) {
        uris.stream().forEach(it->photoList.add(new Photo(it)));
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        uris.stream().forEach(it->photoList.add(new HighDefinitionPhoto(it)));
    }

    private int photoScore(List<Photo> photoList) {
        return photoList.stream().map(it -> it.score()).reduce(0, Integer::sum);
    }
}
