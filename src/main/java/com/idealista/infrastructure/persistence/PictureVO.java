package com.idealista.infrastructure.persistence;

public class PictureVO {

    private Integer id;
    private String url;
    private String quality;

    public PictureVO(Integer id, String url, String quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getQuality() {
        return quality;
    }
}
