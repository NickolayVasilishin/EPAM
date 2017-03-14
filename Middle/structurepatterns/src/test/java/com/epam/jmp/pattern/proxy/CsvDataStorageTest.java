package com.epam.jmp.pattern.proxy;

import org.junit.Before;

import javax.xml.crypto.Data;


public class CsvDataStorageTest extends StorageTest {

    @Override
    protected DataStorage getDataStorage() {
        return CsvDataStorage.INSTANCE;
    }

    @Override
    protected String getPhonebookUrl() {
        return  "src/test/resources/Phonebook.pb";
    }

}