package com.idealista.domain.model.advertisement;

import java.text.Normalizer;
import java.util.Arrays;

public class Description {
    public static final int SHORT_THRESHOLD = 20;
    public static final int LARGE_THRESHOLD = 50;
    private String text = "";

    public Description() {
    }

    public Description(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isEmpty() {
        return text.isEmpty();
    }

    public boolean isMedium() {
        return text.length() >= SHORT_THRESHOLD && text.length() < LARGE_THRESHOLD;
    }

    public boolean isLarge() {
        return text.length() >= LARGE_THRESHOLD;
    }

    public Integer getKeywords() {
        return Math.toIntExact(Arrays.stream(text.split(" "))
                .map(it -> it.toLowerCase())
                .filter(it -> isKeyword(it))
                .distinct()
                .count());
    }

    private boolean isKeyword(String word) {
        return word.equals("luminoso") ||
                word.equals("nuevo") ||
                word.equals("centrico") ||
                word.equals("céntrico") ||
                word.equals("reformado") ||
                word.equals("ático") ||
                word.equals("atico");
    }
}
