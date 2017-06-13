package com.epam.jmp.trademonitor.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVWriter;
import com.epam.jmp.trademonitor.controllers.PackageExplorer;
import com.epam.jmp.trademonitor.controllers.PackageProcessor;
import com.epam.jmp.trademonitor.controllers.PackageQueue;
import com.epam.jmp.trademonitor.data.IncomingPackage;

public final class TradeMonitor {

    //Uses for waiting for files, delegating them to queue and removing from incoming directory
    private PackageExplorer explorer;
    //Stores incoming files
    private PackageQueue queue;
    //Uses for calculating dividends
    private PackageProcessor processor;
    private Properties properties;

    private volatile boolean stop = false;
    private boolean debug = false;
    private boolean toGenerateFiles = false;

    //Constructor initializing main members and loads properties
    TradeMonitor() {
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Provide own files supplier (it's expected to get files from external source in realtime)
        toGenerateFiles = Boolean.valueOf((String) properties.getOrDefault("GenerateFile", "true"));

        processor = new PackageProcessor((String) properties.getOrDefault("OutputFolder",
                PackageProcessor.DEFAULT_OUTPUT_PATH));
        queue = new PackageQueue(processor);
        explorer = new PackageExplorer(queue,
                (String) properties.getOrDefault("InputFolder", PackageExplorer.DEFAULT_INCOMING_PATH),
                (String) properties.getOrDefault("OldFolder", PackageExplorer.DEFAULT_OLD_PATH),
                PackageExplorer.DEFAULT_REFRESH_RATE);

        processor.setPackageQueue(queue);

        debug = Boolean.valueOf((String) properties.getOrDefault("Debug", "true"));
        if (debug) {
            setAllLogging();
        }
    }


    private void loadProperties() throws IOException {
        properties = new Properties();
        File file = Paths.get("config.ini").toFile();
        if (file.exists()) {

            FileInputStream input = new FileInputStream(file);
            properties.load(input);
        }
        System.out.println("File shouldn't exist");
    }

    public void addPackages(List<IncomingPackage> packages) {
        queue.addPackages(packages);
    }

    public void setAllLogging() {
        explorer.setLogging();
        queue.setLogging();
        processor.setLogging();
    }

    private void stop() {
        stop = true;
        System.out.println("Stop functionality...");
    }

    //Every second generates the same file
    private void generateFiles(final long numberOfFiles) {
        File parent = new File("concurrency");
        File file = new File(parent.getAbsoluteFile(), "input/");
        new Thread(() -> {
            long count = numberOfFiles;
            file.getAbsoluteFile().mkdirs();
            while ((count--) != 0) {
                try (CSVWriter writer = new CSVWriter(new FileWriter(new File(
                        file.getAbsoluteFile(),
                        "GV123_01082013_02082013_5.csv")))) {
                    String[] s = {"11111111", "00000001", "00000002", "7",
                            "01082013", "10082013"};
                    writer.writeNext(s);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void start() {
        if (toGenerateFiles) {
            generateFiles(10000);
        }

        Thread processorThread = new Thread(processor);
        processorThread.start();

        //Stop functionality may be here
        Thread inputHandlerThread = new Thread(() -> {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try {
                if (in.readLine().equals("exit")) {
                    stop();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        try {
            explorer.inspectIncoming();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        TradeMonitor monitor = new TradeMonitor();
        monitor.start();
    }

}
