package com.epam.javase04.t04;

import java.io.*;

/**
 * Created by Nick on 31.03.2016.
 */
public class CollectionManager {
    private FilmCollection collection;

    public CollectionManager(FilmCollection collection) {
        this.collection = collection;
    }
    public CollectionManager() {}

    public CollectionManager load(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        collection = (FilmCollection) in.readObject();
        return this;
    }

    public CollectionManager save(String file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(collection);
        return this;
    }

    public FilmCollection getCollection() {
        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionManager that = (CollectionManager) o;

        return collection != null ? collection.equals(that.collection) : that.collection == null;

    }

    @Override
    public int hashCode() {
        return collection != null ? collection.hashCode() : 0;
    }
}
