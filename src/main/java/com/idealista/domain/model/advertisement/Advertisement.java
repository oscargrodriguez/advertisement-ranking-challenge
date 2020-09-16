package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Advertisement {

    private Integer id;
    private Description description;
    private List<Photo> photoList = new ArrayList<>();
    private Typology typology;
    private Integer score;
    protected Integer houseSize;
    protected Integer gardenSize;

    public Advertisement(Integer id,
                         Typology typology) {
        this.id = id;
        this.description = new Description();
        this.typology = typology;
    }

    public Advertisement(Integer id,
                         Typology typology,
                         Description description) {
        this.id = id;
        this.typology = typology;
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void addPhotos(List<String> standardPictures, List<String> hdPictures) {
        addStandardPhotos(standardPictures);
        addHighDefinitionPhotos(hdPictures);
    }

    public void addStandardPhotos(List<String> uris) {
        addPhotos(uris, Photo::new);
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        addPhotos(uris, HighDefinitionPhoto::new);
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    private void addPhotos(List<String> uris, Function<String, Photo> fn) {
        uris.stream().forEach(it -> photoList.add(fn.apply(it)));
    }

    public boolean hasPhoto() {
        return !photoList.isEmpty();
    }

    public Typology getTypology() {
        return typology;
    }

    public boolean hasDescription() {
        return !description.isEmpty();
    }

    public abstract boolean hasSize();

    public abstract boolean isFull();

    public List<String> getPhotoUrls() {
        return photoList.stream().map(it -> it.getUri()).collect(Collectors.toList());
    }
}
