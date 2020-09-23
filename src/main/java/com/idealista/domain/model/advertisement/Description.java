package com.idealista.domain.model.advertisement;

import java.text.Normalizer;
import java.util.Arrays;

public class Description {

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

    public int length()
    {
        return text.length();
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
