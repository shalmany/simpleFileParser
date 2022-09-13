package com.hicx.simplefileparser.parser;

import com.hicx.simplefileparser.analytics.AnalyticsService;
import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface FileParserService {

    Map<AnalyticsResultType, Object> parse(File file, AnalyticsService analyticsService) throws IOException;
}
