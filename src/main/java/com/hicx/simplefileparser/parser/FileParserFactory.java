package com.hicx.simplefileparser.parser;

public class FileParserFactory {

    public FileParserService createParser(String contentType) {
        {
            if (contentType == null || contentType.isBlank())
                return null;
            switch (contentType) {
                case "text/plain":
                    return new PlainTextFileParserService();
                default:
                    throw new IllegalArgumentException("not supported contentType " + contentType);
            }
        }
    }
}
