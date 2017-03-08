package com.epam.jmp.softwareprinciples.datasource;

import com.epam.jmp.softwareprinciples.model.ClientCard;
import com.epam.jmp.softwareprinciples.model.ClientData;

import java.io.*;

/**
 * Created by User on 3/8/2017.
 */
public class FileSystemDataSource implements DataSource {

    private static volatile FileSystemDataSource instance;
    private File file;
    private static final String SEPARATOR = ",";
    private FileSystemDataSource(File file) {
        this.file = file;
    }

    public static FileSystemDataSource open(File file) {
        if (instance == null) {
            synchronized (FileSystemDataSource.class) {
                if (instance == null) {
                    instance = new FileSystemDataSource(file);
                }
            }
        }
        return instance;
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

