package com.idealista.domain.model.advertisement;

import java.net.URI;

public  class Photo {
    String uri;

    public Photo(String uri) {
        this.uri = uri;
    }

    public int score() {
        return 10;
    }
}
