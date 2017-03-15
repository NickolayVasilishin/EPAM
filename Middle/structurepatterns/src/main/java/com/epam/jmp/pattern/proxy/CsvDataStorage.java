package com.epam.jmp.pattern.proxy;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.beans.IntrospectionException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CsvDataStorage extends CsvToBean<Person> implements DataStorage {
    public static final CsvDataStorage INSTANCE = new CsvDataStorage();
    private static final char SEPARATOR = ',';
    private static final char QUOTECHAR = '\'';
    private final Logger LOGGER = Logger.getLogger(CsvDataStorage.class.getName());
    private String fileName;
    private HeaderColumnNameMappingStrategy<Person> strategy;

    public CsvDataStorage() {
        LOGGER.info("Storage instantiated");
        strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Person.class);
    }

    public List<Person> getPerson(String name, String surname) throws IOException {
        LOGGER.info("Getting person: " + name + " " + surname);
        try (CSVReader reader = new CSVReader(new FileReader(fileName), SEPARATOR, QUOTECHAR)) {
            List<String> problemLines = new LinkedList<>();
            CsvToBean<Person> csvToBean = new CsvToBean<>();

            List<Person> personList = csvToBean.parse(strategy, reader, line -> {
                try {
                    Person person = processLine(strategy, line);
                    if (person.getName().equals(name) && person.getSurname().equals(surname)) {
                        return true;
                    }
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException | IntrospectionException e) {
                    // I think, these things cannot be handled by user
                    throw new RuntimeException(e);
                } catch (CsvRequiredFieldEmptyException | CsvConstraintViolationException | CsvDataTypeMismatchException e) {
                    // These exceptions cannot be thrown from here.
                    problemLines.add(Arrays.toString(line) + " because of " + e.getMessage());
                }
                return false;
            });

            if (!problemLines.isEmpty()) {
                // Is it good idea to throw exception here if some lines were corrupted?
                // throw new DataStorageException(problemLines);

                // Or maybe better
                LOGGER.warning("Total " + problemLines.size() + " were not loaded.\n"
                        + String.join("\n", problemLines));
                // I know, the best idea to use better library
            }

            return personList;
        }
    }

    public DataStorage savePerson(Person person) throws DataStorageException, IOException {
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
        } catch (CsvRequiredFieldEmptyException e) {
            throw new DataStorageException("Field " + e.getDestinationField() + " cannot be empty. Got " + person, e);
        } catch (CsvDataTypeMismatchException e) {
            throw new DataStorageException("Types mismatch. Got " + person + ", expected" + e.getSourceObject(), e);
        }
        return this;
    }

    @Override
    public DataStorage setSource(String url) {
        fileName = url;
        return this;
    }
}
