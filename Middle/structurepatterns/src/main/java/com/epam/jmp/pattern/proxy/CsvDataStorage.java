package com.epam.jmp.pattern.proxy;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

public class CsvDataStorage extends CsvToBean<Person> implements DataStorage {
    private static final Logger LOGGER = Logger.getLogger(CsvDataStorage.class.getName());
    public final static CsvDataStorage INSTANCE = new CsvDataStorage();
    private static final char SEPARATOR = ',';
    private static final char QUOTECHAR = '\'';
    private String fileName;
    private HeaderColumnNameMappingStrategy<Person> strategy;

    public CsvDataStorage() {
        LOGGER.info("Storage instantiated");
        strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Person.class);
    }

    public Person getPerson(String name, String surname) throws Exception {
        LOGGER.info("Getting person: " + name + " " + surname);
        // Rethrow exception
        try (CSVReader reader = new CSVReader(new FileReader(fileName), SEPARATOR, QUOTECHAR)) {
            CsvToBean<Person> csvToBean = new CsvToBean<>();
            List<Person> personList = csvToBean.parse(strategy, reader, line -> {
                try {
                    Person person = CsvDataStorage.this.processLine(strategy, line);
                    if (person.getName().equals(name) && person.getSurname().equals(surname)) {
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
                return false;
            });

            return personList.isEmpty() ? null : personList.get(0);
        }
    }

    public DataStorage savePerson(Person person) throws Exception {
        LOGGER.info("Saving person " + person);
        // Rethrow exception
        try (StringWriter stringWriter = new StringWriter();
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            StatefulBeanToCsv<Person> beanToCsv =
                    new StatefulBeanToCsv<>('\\', "\n",
                            strategy, QUOTECHAR, SEPARATOR, false, stringWriter);
            beanToCsv.write(person);
            // This weird library always wants to write header and only then the bean
            writer.append(stringWriter.getBuffer().toString().split("\n")[1]);
            writer.append(("\n"));
        }
        return this;
    }

    @Override
    public DataStorage setSource(String url) {
        fileName = url;
        return this;
    }
}
