package com.epam.javase04.t04;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nick on 30.03.2016.
 */
public class Film implements Serializable{
    private String name;

    private List<Actor> actors;

    public Film(String name, List<Actor> actors) {
        this.name = name;
        this.actors = actors;
    }

    public Film(String filmName) {
        name = filmName;
    }

    public Film addActor(Actor actor) {
        actors.add(actor);
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActors() {
        return actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        return name.equals(film.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
