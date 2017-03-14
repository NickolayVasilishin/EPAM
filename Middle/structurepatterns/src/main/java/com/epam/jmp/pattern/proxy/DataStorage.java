package com.epam.jmp.pattern.proxy;

import java.io.IOException;

public interface DataStorage {
    Person getPerson(String name, String surname) throws Exception;
    DataStorage savePerson(Person person) throws Exception;
    DataStorage setSource(String url);
}
