package com.epam.javase04.t01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Nick on 29.03.2016.
 */
public class KeywordCounter {
    private static final String JAVA_KEYWORDS_PATH = "src\\main\\resources\\java-keywords.txt";

    private static Set<String> keyWords;
    private Map<String, Long> statistics;

    public KeywordCounter() {
        statistics = new HashMap<>();
        initKeyWords();
    }

    private void initKeyWords() {
        keyWords = new HashSet<>();
        try {
            Files.lines(Paths.get(JAVA_KEYWORDS_PATH)).forEach(keyWords::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public KeywordCounter parse(String file)  {
        String source = readSources(file);
        Stream.of(source.split("\\s")).filter(s -> keyWords.contains(s.trim())).forEach(t -> {
            if(statistics.containsKey(t)) statistics.put(t, statistics.get(t) + 1);
            else statistics.put(t, 1L);
        });
        return this;
    }

    protected String readSources(String file) {
        byte[] buffer = new byte[2048];
        StringBuilder builder = new StringBuilder();
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            while(in.read(buffer) != -1) {
                builder.append(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public String getStatistics() {
        return statistics.toString();
    }
}
