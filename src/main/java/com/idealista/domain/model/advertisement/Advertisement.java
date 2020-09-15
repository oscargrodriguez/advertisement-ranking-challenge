package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.idealista.domain.model.advertisement.Typology.*;

public abstract class Advertisement {

    private Description description;
    private List<Photo> photoList = new ArrayList<>();
    private Typology typology;

    public Advertisement(Typology typology) {
        this.description = new Description();
        this.typology = typology;
    }

    public Advertisement(Typology typology,
                         Description description) {
        this.typology = typology;
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void addStandardPhotos(List<String> uris) {
        addPhotos(uris, Photo::new);
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        addPhotos(uris, HighDefinitionPhoto::new);
    }

    private void addPhotos(List<String> uris, Function<String, Photo> fn) {
        uris.stream().forEach(it -> photoList.add(fn.apply(it)));
    }

    public boolean hasPhoto() {
        return !photoList.isEmpty();
    }

    public boolean isGarage() {
        return GARAGE.equals(typology);
    }

    public boolean isFlat() {
        return FLAT.equals(typology);
    }

    public boolean isChalet() {
        return CHALET.equals(typology);
    }

    public Typology getTypology() {
        return typology;
    }

    public boolean hasDescription() {
        return !description.isEmpty();
    }

    public abstract boolean hasSize();

    public abstract boolean isFull();
}
