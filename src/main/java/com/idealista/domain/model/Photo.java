package com.idealista.domain.model;

public class Photo {
    String uri;
    boolean highDefinition;

    public Photo(String uri, boolean highDefinition) {
        this.uri = uri;
        this.highDefinition = highDefinition;
    }

    public int score() {
        if (highDefinition) return 20;
        return 10;
    }
}
