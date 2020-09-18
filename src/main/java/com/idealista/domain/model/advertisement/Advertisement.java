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

public abstract class Advertisement {

    private Integer id;
    private Description description;
    private List<Photo> photoList = new ArrayList<>();
    private Typology typology;
    private Integer score;
    protected Integer houseSize;
    protected Integer gardenSize;
    private Date irrelevantSince;

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

    public abstract boolean hasSize();

    public abstract boolean isFull();

    public List<String> getPhotoUrls() {
        return photoList.stream().map(it -> it.getUri()).collect(Collectors.toList());
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }

    public String getDescriptionText() {
        return description.getText();
    }

    public static Comparator<Advertisement> scoreDescComparator() {
        return Comparator.comparingInt(Advertisement::getScore).reversed();
    }

    private void addPhotos(List<String> uris, PhotoQuality quality) {
        uris.stream().forEach(uri -> photoList.add(new Photo(uri, quality)));
    }
}
