package com.idealista.domain.score.description;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class KeywordRetriever {

    private List<String> KEYWORDS = Arrays.asList("luminoso", "nuevo", "centrico", "céntrico", "reformado", "ático", "atico");

    public int getNumberOfKeywords(String text) {
        return Math.toIntExact(Arrays.stream(text.split(" "))
                .map(it -> it.toLowerCase())
                .filter(it -> isKeyword().test(it))
                .distinct()
                .count());
    }

    private Predicate<String> isKeyword() {
        return wo -> KEYWORDS.contains(wo);
    }
}
