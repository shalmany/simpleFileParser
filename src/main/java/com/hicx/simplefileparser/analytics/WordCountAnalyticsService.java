package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.util.Map;

public class WordCountAnalyticsService implements AnalyticsService {

    private Long totalWords = 0l;

    @Override
    public void analyze(final String textLine) {
        if (textLine != null && !textLine.isBlank()) {
            totalWords += textLine.strip().split("\\s+").length;
        }

    }

    @Override
    public Map<AnalyticsResultType, Object> getResult() {
        return Map.of(
                AnalyticsResultType.NUMBER_OF_WORDS, totalWords
        );
    }
}
