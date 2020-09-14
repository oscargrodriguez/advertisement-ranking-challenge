package com.idealista.domain.model;

public class Photo {
    String uri;
    boolean hq;

    public Photo(String uri, boolean hq) {
        this.uri = uri;
        this.hq = hq;
    }

    public int score()
    {
        if (hq) return 20;
        return 10;
    }
}
