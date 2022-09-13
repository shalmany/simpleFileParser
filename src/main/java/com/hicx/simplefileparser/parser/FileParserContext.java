package com.hicx.simplefileparser.parser;


import com.hicx.simplefileparser.analytics.CompositeAnalyticsService;
import com.hicx.simplefileparser.analytics.DotCountAnalyticsService;
import com.hicx.simplefileparser.analytics.MostUsedAnalyticsService;
import com.hicx.simplefileparser.analytics.WordCountAnalyticsService;
import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileParserContext {

    public void analyze(File file) throws IOException {


        String contentType = Files.probeContentType(Path.of(file.getAbsolutePath()));
        FileParserService fileParserService = new FileParserFactory().createParser(contentType);
        CompositeAnalyticsService compositeAnalyticsService = createCompositeAnalyticsService();

        fileParserService.parse(file, compositeAnalyticsService);
        String movieFile = file.getParentFile().getAbsolutePath() + File.separator + "processed" + File.separator + file.getName();
        file.renameTo(new File(movieFile));

        Map<AnalyticsResultType, Object> mapResult = compositeAnalyticsService.getResult();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" file result: " + file.getAbsolutePath() + " Thread:" + Thread.currentThread().getName());
        stringBuilder.append(System.getProperty("line.separator"));
        for (AnalyticsResultType analyticsResultType : mapResult.keySet()) {
            stringBuilder.append(analyticsResultType.name() + ":" + mapResult.get(analyticsResultType));
            stringBuilder.append(System.getProperty("line.separator"));
        }

        System.out.println(stringBuilder.toString());

    }

    private CompositeAnalyticsService createCompositeAnalyticsService() {
        CompositeAnalyticsService compositeAnalyticsService = new CompositeAnalyticsService();
        compositeAnalyticsService.addStatisticsService(new WordCountAnalyticsService());
        compositeAnalyticsService.addStatisticsService(new DotCountAnalyticsService());
        compositeAnalyticsService.addStatisticsService(new MostUsedAnalyticsService());

        return compositeAnalyticsService;
    }

}
