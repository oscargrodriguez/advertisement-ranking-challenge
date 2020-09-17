package com.idealista.domain.model.advertisement.photo;

public class Photo {

    private String uri;

    private PhotoQuality quality;

    public Photo(String uri, PhotoQuality quality) {
        this.uri = uri;
        this.quality = quality;
    }

    public boolean isHighDefinition() {
        return PhotoQuality.HIGH_DEFINITION.equals(quality);
    }

    public String getUri() {
        return uri;
    }
}
