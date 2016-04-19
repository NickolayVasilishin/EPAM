package com.epam.javase04.t04;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 30.03.2016.
 */
public class Actor implements Serializable{
    private String firstname;
    private String lastname;
    private List<Film> films;

    public Actor(String firstname, String lastname) {
        films = new LinkedList<>();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public List<Film> getFilms() {
        return films;
    }

    public Actor addFilm(Film film) {
        films.add(film);
        return this;
    }
}
