package com.hicx.simplefileparser.analytics;

import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostUsedAnalyticsService implements AnalyticsService {
    final List<String> mostUsedWordsList = new ArrayList<>();
    Map<String, Long> mapWordFrequency = new HashMap<String, Long>();
    long highestFrequency;

    @Override
    public void analyze(String textLine) {
        if (textLine != null && !textLine.isBlank()) {
            String[] arrWords = textLine.strip().split("\\s+");
            String currentWord = null;
            long currentFrequency = 0;

            for (String word : arrWords) {
                currentWord = word;

                if (mapWordFrequency.containsKey(currentWord)) {
                    currentFrequency = mapWordFrequency.get(currentWord) + 1;
                } else {
                    currentFrequency = 1;
                }
                mapWordFrequency.put(currentWord, currentFrequency);

                checkMostUsedWords(currentWord, currentFrequency);
            }
        }

    }

    private void checkMostUsedWords(String currentWord, long currentFrequency) {
        if (currentFrequency > highestFrequency) {
            highestFrequency = currentFrequency;
            mostUsedWordsList.clear();
            mostUsedWordsList.add(currentWord);
        } else if (currentFrequency == highestFrequency) {
            mostUsedWordsList.add(currentWord);
        }
    }


    @Override
    public Map<AnalyticsResultType, Object> getResult() {
        return Map.of(
                AnalyticsResultType.MOST_USED_WORDS, String.join(" ", mostUsedWordsList)
        );
    }
}
