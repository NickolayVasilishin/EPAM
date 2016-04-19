package com.epam.javase04.t02;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nick on 31.03.2016.
 */
public class SymbolicKeywordCounterTest {

    @Test
    public void countKeyWords() {
        assertEquals("{new=2, package=1, static=1, void=1, extends=1, protected=1, public=1, try=1, catch=2, class=1, return=1}",
                new SymbolicKeywordCounter().parse("src\\main\\java\\com\\epam\\javase04\\t02\\SymbolicKeywordCounter.java").getStatistics());
    }

}