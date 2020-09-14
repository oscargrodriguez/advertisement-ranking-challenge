package com.idealista.domain.model.advertisement;

import java.net.URI;

public  class Photo {
    URI uri;

    public Photo(URI uri) {
        this.uri = uri;
    }

    public int score() {
        return 10;
    }
}
