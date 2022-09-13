package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWordCountAnalyticsService {

    @Test
    public void Given_TextWithNull_When_Parse_Then_return0() {
        final String text = null;

        final AnalyticsService analyticsService = new WordCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_WORDS));
    }

    @Test
    public void Given_TextWith0Words_When_Parse_Then_return0() {
        final String text = "";

        final AnalyticsService analyticsService = new WordCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_WORDS));
    }

    @Test
    public void Given_TextWith1WordAndSpaces_When_Parse_Then_return1() {
        final String text = "  aasasas  ";

        final AnalyticsService analyticsService = new WordCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(1), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_WORDS));
    }

    @Test
    public void Given_TextWith3Words_When_Parse_Then_return3() {
        final String text = "aa    cc    aa";

        final AnalyticsService analyticsService = new WordCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(3), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_WORDS));
    }

}
