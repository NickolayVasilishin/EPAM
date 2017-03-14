package com.epam.jmp.pattern.proxy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CsvDataStorageCacheProxy implements DataStorage {
    private static final Logger LOGGER = Logger.getLogger(CsvDataStorageCacheProxy.class.getName());
    public static final String COMPOSITE_DELIMITER = "_";
    private DataStorage storage;
    private final int MAX_CAPACITY = 3;
    private Map<String, Person> lruCache = new LinkedHashMap<String, Person>(MAX_CAPACITY, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Person> eldest) {
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
    public Person getPerson(String name, String surname) throws Exception {
        LOGGER.info("Getting person: " + name + " " + surname);
        String key = generateCompositeKey(name, surname);
        if (lruCache.containsKey(key)) {
            return lruCache.get(key);
        } else {
            return loadFromStorage(name, surname);
        }
    }

    private Person loadFromStorage(String name, String surname) throws Exception {
        LOGGER.info("Cache miss. Getting " + name + " " + surname + " from storage.");
        Person person = storage.getPerson(name, surname);
        lruCache.put(generateCompositeKey(name, surname), person);
        return person;
    }

    @Override
    public DataStorage savePerson(Person person) throws Exception {
        LOGGER.info("Saving person " + person);
        String key = generateCompositeKey(person.getName(), person.getSurname());
        lruCache.put(key, person);
        storage.savePerson(person);
        return this;
    }

    @Override
    public DataStorage setSource(String url) {
        storage.setSource(url);
        return this;
    }

    private String generateCompositeKey(String name, String surname) {
        return name + COMPOSITE_DELIMITER + surname;
    }
}
