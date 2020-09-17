package com.idealista.domain.model.advertisement;

public class Photo {

    private String uri;

    private String quality;

    public Photo(String uri, String quality) {
        this.uri = uri;
        this.quality = quality;
    }

    public boolean isHighDefinition() {
        return "HD".equals(quality);
    }

    public String getUri() {
        return uri;
    }
}
