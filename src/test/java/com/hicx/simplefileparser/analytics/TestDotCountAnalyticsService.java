package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestDotCountAnalyticsService {
    @Test
    public void Given_TextWithNull_When_Parse_Then_return0() {
        final String text = null;

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWith0Words_When_Parse_Then_return0() {
        final String text = "";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWithWordAndWithoutDotsAndSpaces_When_Parse_Then_return0() {
        final String text = "  aasasas  ";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWith3Words_When_Parse_Then_return0() {
        final String text = "aa    cc    aa";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(0), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWithWordAndDotsAndSpaces_When_Parse_Then_return2() {
        final String text = "  .aasasas.  ";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(2), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWithWordAndDotsAndSpaces_When_Parse_Then_return5() {
        final String text = ".  .aasasas. .. ";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(5), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }

    @Test
    public void Given_TextWithWordAndDotsAndSpaces_When_Parse_Then_return6() {
        final String text = ".aa.    .cc.    .aa.";

        final AnalyticsService analyticsService = new DotCountAnalyticsService();
        analyticsService.analyze(text);

        Assertions.assertEquals(Long.valueOf(6), analyticsService.getResult().get(AnalyticsResultType.NUMBER_OF_DOTS));
    }


}
