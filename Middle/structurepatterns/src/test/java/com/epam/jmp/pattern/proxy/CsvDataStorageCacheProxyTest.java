package com.epam.jmp.pattern.proxy;

public class CsvDataStorageCacheProxyTest extends StorageTest {

    @Override
    public DataStorage getDataStorage() {
        return new CsvDataStorageCacheProxy(CsvDataStorage.INSTANCE);
    }

    @Override
    protected String getPhonebookUrl() {
        return  "src/test/resources/Phonebook.pb";
    }
}