package com.epam.jmp.pattern.proxy;

import java.io.IOException;
import java.util.List;

public interface DataStorage {
    List<Person> getPerson(String name, String surname) throws IOException;
    DataStorage savePerson(Person person) throws DataStorageException, IOException;
    DataStorage setSource(String url);
}
