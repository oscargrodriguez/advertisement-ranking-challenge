package com.idealista.domain.model.advertisement;

import java.net.URI;

public class HighDefinitionPhoto extends Photo {

    private static final int HD_SCORE = 20;

    public HighDefinitionPhoto(String uri) {
        super(uri);
    }

    public int score() {
        return HD_SCORE;
    }
}
