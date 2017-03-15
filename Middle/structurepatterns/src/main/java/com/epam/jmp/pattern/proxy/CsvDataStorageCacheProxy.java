package com.epam.jmp.pattern.proxy;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CsvDataStorageCacheProxy implements DataStorage {
    private static final String COMPOSITE_DELIMITER = "_";
    private static final Logger LOGGER = Logger.getLogger(CsvDataStorageCacheProxy.class.getName());
    private DataStorage storage;
    private final int MAX_CAPACITY = 3;
    private Map<String, List<Person>> lruCache = new LinkedHashMap<String, List<Person>>(MAX_CAPACITY, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, List<Person>> eldest) {
            if (size() > MAX_CAPACITY) {
                LOGGER.info("Removing eldest entry.");
            }
            return size() > MAX_CAPACITY;
        }
    };

    public CsvDataStorageCacheProxy(DataStorage storage) {
        LOGGER.info("Cache Storage instantiated");
        this.storage = storage;
    }

    @Override
    public List<Person> getPerson(String name, String surname) throws IOException {
        LOGGER.info("Getting person: " + name + " " + surname);
        String key = generateCompositeKey(name, surname);
        List<Person> persons = lruCache.get(key);
        if (persons == null) {
            persons = loadFromStorage(name, surname);
        }
        return persons;

    }

    @Override
    public DataStorage savePerson(Person person) throws DataStorageException, IOException {
        LOGGER.info("Saving person " + person);
        String key = generateCompositeKey(person.getName(), person.getSurname());
        storage.savePerson(person);
        putToCache(key, Collections.singletonList(person));
        return this;
    }

    @Override
    public DataStorage setSource(String url) {
        storage.setSource(url);
        return this;
    }

    private List<Person> loadFromStorage(String name, String surname) throws IOException {
        LOGGER.info("Cache miss. Getting " + name + " " + surname + " from storage.");
        List<Person> persons = storage.getPerson(name, surname);
        String key = generateCompositeKey(name, surname);
        putToCache(key, persons);
        return persons;
    }

    private void putToCache(String key, List<Person> persons) {
        if (lruCache.containsKey(key)) {
            lruCache.get(key).addAll(persons);
        } else {
            lruCache.put(key, persons);
        }
    }

    private String generateCompositeKey(String name, String surname) {
        return name + COMPOSITE_DELIMITER + surname;
    }



}
