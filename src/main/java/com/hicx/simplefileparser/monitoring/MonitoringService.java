package com.hicx.simplefileparser.monitoring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;

public class MonitoringService {

    public void start(  final BlockingQueue<File> blockingQueue , final File folder ) throws IOException, InterruptedException {

        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                blockingQueue.put(fileEntry);
            }
        }
        startMonitoringNewFilesInDirectory( folder, blockingQueue);
    }

    private void startMonitoringNewFilesInDirectory(File folder, BlockingQueue<File> blockingQueue) throws IOException, InterruptedException {
        Path path = Paths.get(folder.getAbsolutePath());
        WatchService watchService = path.getFileSystem().newWatchService();

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();
                File newFile = Paths.get(folder.getAbsolutePath() + File.separator + filename.toString()).toFile();

                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + "");
                blockingQueue.put(newFile);


            }
            key.reset();
        }
    }
}
