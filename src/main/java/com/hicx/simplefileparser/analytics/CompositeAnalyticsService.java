package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CompositeAnalyticsService implements AnalyticsService {

    private final List<AnalyticsService> analyticsServiceList = new ArrayList<AnalyticsService>();

    @Override
    public void analyze(String textLine) {
        for (AnalyticsService analyticsService : analyticsServiceList) {
            analyticsService.analyze(textLine);
        }
    }

    @Override
    public Map<AnalyticsResultType, Object> getResult() {
        final Map<AnalyticsResultType, Object> mapResult = new ConcurrentHashMap<>();
        for (AnalyticsService analyticsService : analyticsServiceList) {
            Map<AnalyticsResultType, Object> mapResultChild = analyticsService.getResult();
            for (AnalyticsResultType analyticsResultType : mapResultChild.keySet()) {
                mapResult.put(analyticsResultType, mapResultChild.get(analyticsResultType));
            }


        }
        return mapResult;
    }

    public void addStatisticsService(AnalyticsService emp) {
        analyticsServiceList.add(emp);
    }

    public void removeStatisticsService(AnalyticsService emp) {
        analyticsServiceList.remove(emp);
    }
}
