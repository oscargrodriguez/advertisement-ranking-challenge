package com.idealista.domain.score.description;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KeywordRetriever {

    public int getNumberOfKeywords(String text) {
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
