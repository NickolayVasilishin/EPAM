package com.epam.javase04.t01;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nick on 31.03.2016.
 */
public class KeywordCounterTest {

    @Test
    public void countKeyWords() {
        assertEquals("{new=6, private=4, package=1, static=2, void=1, import=5, public=4, protected=1, throw=1, else=1, final=1, try=2, catch=3, class=1, return=3}",
                new KeywordCounter().parse("src\\main\\java\\com\\epam\\javase04\\t01\\KeywordCounter.java").getStatistics());
    }

}