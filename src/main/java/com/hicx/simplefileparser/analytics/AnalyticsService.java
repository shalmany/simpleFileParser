package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.util.Map;

public interface AnalyticsService {

    void analyze(final String textLine);

    Map<AnalyticsResultType, Object> getResult();
}
