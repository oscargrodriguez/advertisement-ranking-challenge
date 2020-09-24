package com.idealista.domain.score.description;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@ConfigurationProperties(prefix = "score.keywordconfig")
public class KeywordRetriever {
    private List<String> keywords;

    public int getNumberOfKeywords(String text) {
        return Math.toIntExact(Arrays.stream(text.split(" "))
                .map(it -> it.toLowerCase())
                .filter(it -> isKeyword().test(it))
                .distinct()
                .count());
    }

    private Predicate<String> isKeyword() {
        return wo -> keywords.contains(wo);
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
