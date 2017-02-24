package com.epam.javase02.t01;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nick on 04.03.2016.
 */
public class MyPenEqualsTest {


    private MyPen myPen;
    private MyPen sameMyPen;
    private MyPen similarMyPen;
    private MyPen anotherMyPen;
    private Object somethigElse;

    @Before
    public void setup() {
        myPen = new MyPen(Color.black, "epam", 2);
        sameMyPen = myPen;
        similarMyPen = new MyPen(Color.black, "epam", 2);
        anotherMyPen = new MyPen();
        somethigElse = new Object();

    }

    @Test
    public void equalsTest() {
        assertTrue(myPen.equals(sameMyPen));
        assertTrue(myPen.equals(similarMyPen));
        assertFalse(myPen.equals(anotherMyPen));
        assertFalse(myPen.equals(somethigElse));
    }

    @Test
    public void hashcodeTest() {
        assertTrue(myPen.hashCode() == similarMyPen.hashCode());
        assertFalse(myPen.hashCode() == anotherMyPen.hashCode());

    }
}
