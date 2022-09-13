package com.hicx.simplefileparser.parser;

import com.hicx.simplefileparser.analytics.CompositeAnalyticsService;
import com.hicx.simplefileparser.analytics.DotCountAnalyticsService;
import com.hicx.simplefileparser.analytics.MostUsedAnalyticsService;
import com.hicx.simplefileparser.analytics.WordCountAnalyticsService;
import com.hicx.simplefileparser.type.AnalyticsResultType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TestFileParserService {


    @Test
    public void Given_TextWithWords_When_Parse_Then_returnSucess() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("demo1.txt").getPath());

        String contentType = Files.probeContentType(Path.of(file.getAbsolutePath()));
        FileParserService fileParserService = new FileParserFactory().createParser(contentType);
        CompositeAnalyticsService compositeAnalyticsService = createCompositeAnalyticsService();

        fileParserService.parse(file, compositeAnalyticsService);
        Map<AnalyticsResultType, Object> mapResult = compositeAnalyticsService.getResult();

        Assertions.assertEquals(12l, mapResult.get(AnalyticsResultType.NUMBER_OF_WORDS));
        Assertions.assertEquals(0l, mapResult.get(AnalyticsResultType.NUMBER_OF_DOTS));
        Assertions.assertEquals("house", mapResult.get(AnalyticsResultType.MOST_USED_WORDS));
    }

    @Test
    public void Given_TextEmpty_When_Parse_Then_return0() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("demo2.txt").getPath());

        String contentType = Files.probeContentType(Path.of(file.getAbsolutePath()));
        FileParserService fileParserService = new FileParserFactory().createParser(contentType);
        CompositeAnalyticsService compositeAnalyticsService = createCompositeAnalyticsService();

        fileParserService.parse(file, compositeAnalyticsService);
        Map<AnalyticsResultType, Object> mapResult = compositeAnalyticsService.getResult();

        Assertions.assertEquals(0l, mapResult.get(AnalyticsResultType.NUMBER_OF_WORDS));
        Assertions.assertEquals(0l, mapResult.get(AnalyticsResultType.NUMBER_OF_DOTS));
        Assertions.assertEquals("", mapResult.get(AnalyticsResultType.MOST_USED_WORDS));
    }

    @Test
    public void Given_TextWithWordsDots_When_Parse_Then_returnSucess() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("demo3.txt").getPath());

        String contentType = Files.probeContentType(Path.of(file.getAbsolutePath()));
        FileParserService fileParserService = new FileParserFactory().createParser(contentType);
        CompositeAnalyticsService compositeAnalyticsService = createCompositeAnalyticsService();

        fileParserService.parse(file, compositeAnalyticsService);
        Map<AnalyticsResultType, Object> mapResult = compositeAnalyticsService.getResult();

        Assertions.assertEquals(15l, mapResult.get(AnalyticsResultType.NUMBER_OF_WORDS));
        Assertions.assertEquals(28l, mapResult.get(AnalyticsResultType.NUMBER_OF_DOTS));
        Assertions.assertEquals("house", mapResult.get(AnalyticsResultType.MOST_USED_WORDS));
    }


    private CompositeAnalyticsService createCompositeAnalyticsService() {
        CompositeAnalyticsService compositeAnalyticsService = new CompositeAnalyticsService();
        compositeAnalyticsService.addStatisticsService(new WordCountAnalyticsService());
        compositeAnalyticsService.addStatisticsService(new DotCountAnalyticsService());
        compositeAnalyticsService.addStatisticsService(new MostUsedAnalyticsService());

        return compositeAnalyticsService;
    }
}
