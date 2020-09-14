package com.idealista.domain.model.advertisement;

import java.net.URI;

public class Photo {
    private static final int SCORE = 10;

    private String uri;

    public Photo(String uri) {
        this.uri = uri;
    }

    public int score() {
        return SCORE;
    }
}
