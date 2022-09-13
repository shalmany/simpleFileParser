package com.hicx.simplefileparser.parallel.consumer;

import com.hicx.simplefileparser.parser.FileParserContext;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class FileConsumer implements Runnable {

    private final BlockingQueue<File> blockingQueue;

    public FileConsumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                File file = blockingQueue.take();
                FileParserContext fileParserContext = new FileParserContext();
                fileParserContext.analyze(file);
            } catch (InterruptedException | IOException e) {
                System.out.println("Error processing file: " + e.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Error processing file: " + ex.getMessage());
            }
        }

    }
}
