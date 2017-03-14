package com.epam.jmp.pattern.proxy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.Assert.assertEquals;

public abstract class StorageTest {

    private String phonebookUrl;
    protected DataStorage storage;

    protected abstract DataStorage getDataStorage();
    protected abstract String getPhonebookUrl();

    @Before
    public void setUp() {
        storage = getDataStorage();
        phonebookUrl = getPhonebookUrl();
    }

    @Test
    public void getPerson() throws Exception {
        Person personOrwell = storage
                .setSource(phonebookUrl)
                .getPerson("Georg", "Orwell");
        Person expectedPerson = new Person()
                .setName("Georg")
                .setSurname("Orwell")
                .setPhoneNumber("+1-123-42-52");
        assertEquals(expectedPerson, personOrwell);
    }

    @Test
    public void getPersonFrequently() throws Exception {
        Person personOrwell = storage
                .setSource(phonebookUrl)
                .getPerson("Georg", "Orwell");
        Person expectedPerson = new Person()
                .setName("Georg")
                .setSurname("Orwell")
                .setPhoneNumber("+1-123-42-52");
        assertEquals(expectedPerson, personOrwell);
        storage.setSource(phonebookUrl).getPerson("Harry", "Harrison");
        storage.setSource(phonebookUrl).getPerson("Georg", "Orwell");
        storage.setSource(phonebookUrl).getPerson("Aldous", "Huxley");
        storage.setSource(phonebookUrl).getPerson("Georg", "Orwell");
        storage.setSource(phonebookUrl).getPerson("Arthur", "Clarke");
        storage.setSource(phonebookUrl).getPerson("Georg", "Orwell");

    }

    @Test
    public void savePerson() throws Exception {
        Person person = new Person()
                .setName("Kurt")
                .setSurname("Vonnegut")
                .setPhoneNumber("+6-987-42-52");
        storage.setSource(phonebookUrl)
                .savePerson(person);
        Person personVonnegut = storage
                .setSource(phonebookUrl)
                .getPerson("Kurt", "Vonnegut");
        assertEquals(person, personVonnegut);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        String content = "'name','phoneNumber','surname'\n" +
                "'Georg','+1-123-42-52','Orwell'\n" +
                "'Harry','+2-456-42-52','Harrison'\n" +
                "'Aldous','+3-567-42-52','Huxley'\n" +
                "'Arthur','+4-678-42-52','Clarke'\n" +
                "'Isaak','+5-789-42-52','Azimov'\n";
        Files.write(Paths.get(phonebookUrl), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

}
