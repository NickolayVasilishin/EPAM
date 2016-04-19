package com.epam.javase04.t04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 30.03.2016.
 */
public class FilmCollection implements Serializable{
    private List<Film> collection;

    public FilmCollection() {
        collection = new ArrayList<>();
    }

    public FilmCollection add(Film film) {
        collection.add(film);
        return this;
    }

    public Film remove(String filmName) {
        return collection.remove(collection.indexOf(new Film(filmName)));
    }

    public List<Film> getFilms() {
        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmCollection that = (FilmCollection) o;

        return collection != null ? collection.equals(that.collection) : that.collection == null;

    }

    @Override
    public int hashCode() {
        return collection != null ? collection.hashCode() : 0;
    }
}
