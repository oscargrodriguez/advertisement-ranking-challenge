package com.idealista.domain.model.advertisement;

import java.util.ArrayList;
import java.util.List;

public class Advertisement {

    private String descriptiveText = "";
    private List<Photo> photoList = new ArrayList<>();

    public Advertisement() {
    }

    public Advertisement(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }

    public int score() {
        return descriptiveTextScore() +
                photoScore(photoList);
    }

    private int descriptiveTextScore() {
        if (descriptiveText.isEmpty()) {
            return 0;
        }
        return 5;
    }

    private int photoScore(List<Photo> photoList) {
        return photoList.stream().map(it -> it.score()).reduce(0, Integer::sum);
    }

    public void addPhoto(Photo photo) {
        photoList.add(photo);
    }
}
