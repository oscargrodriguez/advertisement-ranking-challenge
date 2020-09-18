package com.idealista.domain.model.advertisement;

import com.idealista.domain.model.advertisement.photo.Photo;
import com.idealista.domain.model.advertisement.photo.PhotoQuality;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.idealista.domain.model.advertisement.photo.PhotoQuality.HIGH_DEFINITION;
import static com.idealista.domain.model.advertisement.photo.PhotoQuality.STANDARD;

public class Advertisement {

    private Integer id;
    private Description description;
    private List<Photo> photoList = new ArrayList<>();
    private Typology typology;
    protected Integer houseSize;
    protected Integer gardenSize;

    public Advertisement(Integer id,
                         Typology typology,
                         Description description) {
        this.id = id;
        this.typology = typology;
        this.description = description == null ? new Description() : description;
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
        addPhotos(uris, STANDARD);
    }

    public void addHighDefinitionPhotos(List<String> uris) {
        addPhotos(uris, HIGH_DEFINITION);
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

    public boolean hasPhoto() {
        return !photoList.isEmpty();
    }

    public Typology getTypology() {
        return typology;
    }

    public String getTypologyName() {
        return typology.name();
    }

    public boolean hasDescription() {
        return !description.isEmpty();
    }

    public List<String> getPhotoUrls() {
        return photoList.stream().map(it -> it.getUri()).collect(Collectors.toList());
    }

    public String getDescriptionText() {
        return description.getText();
    }

    private void addPhotos(List<String> uris, PhotoQuality quality) {
        uris.stream().forEach(uri -> photoList.add(new Photo(uri, quality)));
    }

    public boolean isGarage() {
        return Typology.GARAGE.equals(typology);
    }

    public boolean isFlat() {
        return Typology.FLAT.equals(typology);
    }

    public boolean hasHouseSize() {
        return houseSize != null;
    }

    public boolean hasGardenSize() {
        return gardenSize != null;
    }
}
