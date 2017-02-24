package com.epam.javase05.t02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by Nick on 21.04.2016.
 */
public class PropertiesTest {
    Properties properties;

    @Before
    public void setUp() throws Exception {
        properties = Properties.fromFile("src/test/testResources/test.properties");
    }

    @Test
    public void readProperties() {
        assertEquals("property", properties.get("first").get());
        assertEquals("another property", properties.get("second").get());
        assertEquals("the last property", properties.get("third").get());

    }

}