package com.idealista.domain.model.advertisement;

public class DescriptiveText {
    public static final int SHORT_THRESHOLD = 20;
    public static final int LARGE_THRESHOLD = 50;
    private String text = "";


    public DescriptiveText() {
    }

    public DescriptiveText(String text) {
        this.text = text;
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
}
