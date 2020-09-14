package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    public DescriptiveText getDescriptiveText() {
        return descriptiveText;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public AdvertisementType getAdvertisementType() {
        return advertisementType;
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
}
