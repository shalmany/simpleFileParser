package com.hicx.simplefileparser.parser;

import com.hicx.simplefileparser.analytics.AnalyticsService;
import com.hicx.simplefileparser.type.AnalyticsResultType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PlainTextFileParserService implements FileParserService {
    @Override
    public Map<AnalyticsResultType, Object> parse(File file, AnalyticsService analyticsService) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(file.getAbsoluteFile());
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                analyticsService.analyze(line);
            }

            return analyticsService.getResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return null;
    }
}
