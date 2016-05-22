package com.epam.javase05.t02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nick on 21.04.2016.
 */
public class Properties {
    private Map<String, String> properties;
    private String file;

    public Properties() {
        properties = new HashMap<>();
    }

    public Properties(String file) {
        properties = new HashMap<>();
        this.file = file;
    }

    public static Properties fromFile(String file) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(file));
        Properties properties = new Properties(file);
        lines.forEach(line -> properties.properties.put(checkLine(line)[0], checkLine(line)[1]));
        properties.properties.remove(null);
        return properties;
    }

    private static String[] checkLine(String line) {
        if (line.startsWith("!") || line.startsWith("#") || line.isEmpty() || !line.contains("="))
            return new String[] {null, null};
        return new String[] {line.split("=")[0].trim(), line.split("=")[1]};
    }

    public Optional<String> get(String key) {
        return Optional.of(properties.get(key));
    }

    public String remove(String key) {
        return properties.remove(key);
    }

    public Properties set(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public Properties save() throws IOException {
        save(file);
        return this;
    }

    public Properties save(String file) throws IOException {
        List<String> list = properties.keySet()
                .stream().map(key -> key + "=" + properties.get(key))
                .collect(Collectors.toCollection(LinkedList::new));
        Files.write(Paths.get(file), list, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        return this;
    }

}
