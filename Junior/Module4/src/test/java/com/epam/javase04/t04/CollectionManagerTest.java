package com.epam.javase04.t04;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Nick on 31.03.2016.
 */
public class CollectionManagerTest {
    CollectionManager managerOld;
    CollectionManager manager;
    Film[] films;
    Actor[] actors;
    @Before
    public void setup() throws Exception {
        actors = new Actor[] {
                new Actor("Alex", "Nevskiy"),
                new Actor("Michael", "Boyarsky"),
                new Actor("Modern", "Nagiev"),
                new Actor("Teemah", "Tee")
        };
        films = new Film[] {
                new Film("Bull Hit", Arrays.asList(actors[0], actors[1])),
                new Film("Hot water", Arrays.asList(actors[2], actors[3]))
        };

        managerOld = new CollectionManager(new FilmCollection().add(films[0]).add(films[1]));
        manager = new CollectionManager(new FilmCollection().add(films[0]).add(films[1]));
    }

    @Test
    public void modifySaveLoad() throws IOException, ClassNotFoundException {
        manager.getCollection().add(new Film("Top film", Arrays.asList(actors)));
        manager.save("my.collection");
        assertNotEquals(manager, managerOld);
        assertEquals(manager, new CollectionManager().load("my.collection"));
    }
}