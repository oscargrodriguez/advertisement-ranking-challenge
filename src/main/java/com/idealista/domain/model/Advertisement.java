package com.idealista.domain.model;

public class Advertisement {

    private String descriptiveText = "";

    public Advertisement() {
    }

    public Advertisement(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }

    public int score() {
        if (descriptiveText.isEmpty())
        {
            return 0;
        }
        return 5;
    }
}
