package com.epam.javase02.t02;

import com.epam.javase02.t02.cancellery.Cancellery;
import com.epam.javase02.t02.item.Clip;
import com.epam.javase02.t02.item.Eraser;
import com.epam.javase02.t02.item.Paper;
import com.epam.javase02.t02.item.Pen;
import com.epam.javase02.t02.preson.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nick on 04.03.2016.
 */
public class CancelleryTest {
    private Cancellery cancellery;
    private Person personAnn;
    private Person personDavid;


    @Before
    public void setup() {
        cancellery = new Cancellery();
        personAnn = new Person("Ann S.");
        personAnn.addItem(new Clip("clipCo", personAnn.getName(), 2), 3);
        personAnn.addItem(new Paper("papersCo", personAnn.getName(), 1), 30);
        personAnn.addItem(new Pen(personAnn.getName(), 20), 2);
        personAnn.addItem(new Eraser("Erasers", personAnn.getName(), 12));


        personDavid = new Person("David P.");
        personDavid.addItem(new Clip("RussianClips", personDavid.getName(), 4), 5);
        personDavid.addItem(new Paper("SnowWhite", personDavid.getName(), 1), 50);
        personDavid.addItem(new Pen(personDavid.getName(), 50), 1);
        personDavid.addItem(new Eraser("Erasure", personDavid.getName(), 6), 4);

        cancellery.registerEmployee(personAnn).registerEmployee(personDavid);

    }

    @Test
    public void calculateCostTest() {
        assertEquals(cancellery.calculateCost(personAnn), 88);
        assertEquals(cancellery.calculateCost(personDavid), 144);
    }
}
