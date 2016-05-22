package com.epam.javase07.t02;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nick on 09.05.2016.
 */
public class SynchronizedProperties {
    private static final Cache<String, SynchronizedProperties> instances = CacheBuilder.newBuilder().weakKeys().build();

    private Map<String, String> properties;
    private String file;

    private SynchronizedProperties(String file) {
        properties = new ConcurrentHashMap<>();
        this.file = Paths.get(file).toAbsolutePath().toString();
    }

    public static SynchronizedProperties empty(String file) throws ExecutionException {
        Path path = Paths.get(file).toAbsolutePath();
        return instances.get(path.toString(), () -> {
            SynchronizedProperties props = new SynchronizedProperties(file);
            instances.put(path.toString(), props);
            return props;
        });
    }

    public static SynchronizedProperties fromFile(String file) throws IOException, ExecutionException {
        Path path = Paths.get(file).toAbsolutePath();
            return instances.get(path.toString(), () -> {
                Stream<String> lines = Files.lines(path);
                SynchronizedProperties props = new SynchronizedProperties(file);
                lines.forEach(line -> props.properties.put(checkLine(line)[0], checkLine(line)[1]));
                props.properties.remove(null);
                instances.put(path.toString(), props);
                return props;
            });
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

    public SynchronizedProperties set(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public SynchronizedProperties save() throws IOException {
        return save(file);
    }

    private SynchronizedProperties save(String file) throws IOException {
        List<String> list = properties.keySet()
                .stream().map(key -> key + "=" + properties.get(key))
                .collect(Collectors.toCollection(LinkedList::new));
        //Not sure
        synchronized(this) {
            Files.write(Paths.get(file), list, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        return this;
    }

}
