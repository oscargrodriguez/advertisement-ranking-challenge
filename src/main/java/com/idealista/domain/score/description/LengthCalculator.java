package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.springframework.stereotype.Component;

import static com.idealista.domain.score.description.Length.*;

@Component
public class LengthCalculator {
    private static final int SHORT_THRESHOLD = 20;
    private static final int LARGE_THRESHOLD = 50;

    public Length get(Description description) {
        if (description.getText().length() >= LARGE_THRESHOLD) {
            return LARGE;
        } else if (description.getText().length() >= SHORT_THRESHOLD) {
            return MEDIUM;
        }
        return SHORT;
    }
}
