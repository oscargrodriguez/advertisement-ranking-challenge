package com.idealista.domain.model.advertisement;

import static com.idealista.domain.model.advertisement.AdvertisementType.CHALET;
import static com.idealista.domain.model.advertisement.AdvertisementType.HOUSE;

public class DescriptiveText {
    public static final int SHORT_THRESHOLD = 20;
    public static final int LARGE_THRESHOLD = 50;
    private String text = "";


    public DescriptiveText() {
    }

    public DescriptiveText(String text) {
        this.text = text;
    }

    public int score(AdvertisementType advertisementType) {
        if (text.isEmpty()) {
            return 0;
        }
        return 5 + scoreByLength(advertisementType);
    }

    private int scoreByLength(AdvertisementType advertisementType) {
        if (HOUSE.equals(advertisementType)) {
            if (isMedium()) {
                return 10;
            } else if (isLarge()) {
                return 30;
            }
        } else if (CHALET.equals(advertisementType)) {
            if (isLarge()) {
                return 20;
            }
        }
        return 0;
    }

    private boolean isMedium() {
        return text.length() >= SHORT_THRESHOLD && text.length() < LARGE_THRESHOLD;
    }

    private boolean isLarge() {
        return text.length() >= LARGE_THRESHOLD;
    }
}
