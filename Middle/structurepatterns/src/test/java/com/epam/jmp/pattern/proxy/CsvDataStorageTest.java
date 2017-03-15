package com.epam.jmp.pattern.proxy;


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