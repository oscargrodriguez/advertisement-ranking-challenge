package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.idealista.domain.score.description.Length.*;

@Component
public class LengthCalculator {

    @Value("${score.description.length.threshold.short}")
    private int shortThreshold;
    @Value("${score.description.length.threshold.large}")
    private int largeThreshold;

    public Length get(Description description) {
        if (description.length() >= largeThreshold) {
            return LARGE;
        } else if (description.length() >= shortThreshold) {
            return MEDIUM;
        }
        return SHORT;
    }
}
