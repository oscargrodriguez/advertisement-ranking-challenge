package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Advertisement {

    private Description description;
    private List<Photo> photoList = new ArrayList<>();
    private Typology typology;

    public Advertisement(Typology typology) {
        this.description = new Description();
        this.typology = typology;
    }

    public Advertisement(Description description,
                         Typology typology) {
        this.description = description;
        this.typology = typology;
    }

    public Description getDescription() {
        return description;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public Typology getTypology() {
        return typology;
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

    public boolean hasPhoto() {
        return !photoList.isEmpty();
    }
}
