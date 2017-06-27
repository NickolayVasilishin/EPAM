package com.epam.jmp.nosql.warehouse.utils;

import com.epam.jmp.nosql.warehouse.model.SimpleNotebook;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public class MongoNotebookManager implements NotebookManager {
    MongoClient mongoClient;
    private final MongoDatabase database;


    MongoNotebookManager(String host, int port, String databaseName) {
        mongoClient = new MongoClient(host, port);
        database = mongoClient.getDatabase(databaseName);
    }

    public SimpleNotebook getNotebook(String user) {
        return new SimpleNotebook(database.getCollection(user));
    }

    public void close() {
        mongoClient.close();
    }
}
