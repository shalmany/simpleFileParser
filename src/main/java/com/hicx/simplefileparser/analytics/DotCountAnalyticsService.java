package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.util.Map;

public class DotCountAnalyticsService implements AnalyticsService {
    private Long count = 0l;

    @Override
    public void analyze(final String textLine) {
        if (textLine != null && !textLine.isBlank()) {
            count += textLine.chars().filter(ch -> ch == '.').count();
        }
    }


    @Override
    public Map<AnalyticsResultType, Object> getResult() {
        return Map.of(
                AnalyticsResultType.NUMBER_OF_DOTS, count
        );
    }
}
