package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMostUsedAnalyticsService {

    @Test
    public void Given_TextWithNull_When_Parse_Then_returnEmpty() {
        final String text = null;

        final AnalyticsService analyticsService = new MostUsedAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals("", analyticsService.getResult().get(AnalyticsResultType.MOST_USED_WORDS));
    }

    @Test
    public void Given_TextWith1WordAndSpaces_When_Parse_Then_returnMostUserWord() {
        final String text = "  aasasas  ";

        final AnalyticsService analyticsService = new MostUsedAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals("aasasas", analyticsService.getResult().get(AnalyticsResultType.MOST_USED_WORDS));
    }

    @Test
    public void Given_TextWith3Words_When_Parse_Then_returnMostUserWord() {
        final String text = "aa    cc    aa";

        final AnalyticsService analyticsService = new MostUsedAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals("aa", analyticsService.getResult().get(AnalyticsResultType.MOST_USED_WORDS));
    }

    @Test
    public void Given_TextWith2SameusedWords_When_Parse_Then_return2MostUserWords() {
        final String text = "aa    cc   cc aa";

        final AnalyticsService analyticsService = new MostUsedAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals("cc aa", analyticsService.getResult().get(AnalyticsResultType.MOST_USED_WORDS));
    }


}
