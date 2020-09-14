package com.idealista.domain.model.advertisement;

import java.net.URI;

public class HighDefinitionPhoto extends Photo {

    public HighDefinitionPhoto(URI uri) {
        super(uri);
    }

    public int score() {
        return 20;
    }
}
