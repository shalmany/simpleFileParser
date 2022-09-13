package com.hicx.simplefileparser;

import com.hicx.simplefileparser.monitoring.MonitoringService;
import com.hicx.simplefileparser.parallel.consumer.FileConsumer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleFileParserApplication {

    public static final int TOTAL_CONSUMERS = 2;
    public static final int QUEUE_SIZE = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleFileParserApplication simpleFileParserApplication = new SimpleFileParserApplication();
        simpleFileParserApplication.start();
    }

    public void start() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String directory = getDirectory(scanner);
        final File folder = new File(directory);
        System.out.println("start scan : " + directory);
        Files.createDirectories(Paths.get(folder.getAbsolutePath() + File.separator + "processed"));

        final BlockingQueue<File> blockingQueue = new ArrayBlockingQueue<File>(QUEUE_SIZE);
        createConsumers(blockingQueue, TOTAL_CONSUMERS);

        MonitoringService monitoringService = new MonitoringService();
        monitoringService.start(blockingQueue,folder);
    }

    private String getDirectory(Scanner scanner) {
        String directory = null;
        File folder;
        while (directory == null || directory.isBlank()) {
            System.out.println("Enter directory:");
            directory = scanner.nextLine().strip();
            folder = new File(directory);
            if (!folder.isDirectory()) {
                directory = null;
                System.out.println("directory not found!");
            }
        }
        return directory;
    }

    private void createConsumers(BlockingQueue<File> blockingQueue, int totalConsumers) {
        for (int i = 0; i < totalConsumers; i++) {
            new Thread(new FileConsumer(blockingQueue)).start();
        }
    }

}
