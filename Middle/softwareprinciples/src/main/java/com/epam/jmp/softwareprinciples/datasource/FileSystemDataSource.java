package com.epam.jmp.softwareprinciples.datasource;

import com.epam.jmp.softwareprinciples.model.ClientCard;
import com.epam.jmp.softwareprinciples.model.ClientData;

import java.io.*;

/**
 * Created by User on 3/8/2017.
 */
public enum FileSystemDataSource implements DataSource {
    /**
     * FileSystemDataSource should be singleton because files do not support concurrent access: if there will be
     * tries to write to one file from multiple threads in parallel, result information in it will be inconsistent.
     */
    
    INSTANCE;

    private File file;
    private static final String SEPARATOR = ",";

    FileSystemDataSource() {
    }

    public FileSystemDataSource open(File file) {
        this.file = file;
        return this;
    }


    @Override
    public ClientData getCard() {
        ClientCard card = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            card = new ClientCard(reader.readLine(), SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public void saveCard(ClientData card) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(card.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

