package com.idealista.domain.model.advertisement;

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

    public int length() {
        return text.length();
    }
}
