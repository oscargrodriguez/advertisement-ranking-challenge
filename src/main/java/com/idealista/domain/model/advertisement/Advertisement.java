package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Advertisement {

    public static final int NO_PHOTO_PENALTY = -10;
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
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public void addStandardPhotos(List<String> uris) {
        addPhotos(uris, Photo::new);
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        addPhotos(uris,HighDefinitionPhoto::new);
    }

    private void addPhotos(List<String> uris,Function<String,Photo> fn )
    {
        uris.stream().forEach(it -> photoList.add(fn.apply(it)));
    }

    private int photoScore(List<Photo> photoList) {
        if (photoList.isEmpty()) {
            return NO_PHOTO_PENALTY;
        }
        return photoList.stream().map(it -> it.score()).reduce(0, Integer::sum);
    }
}
