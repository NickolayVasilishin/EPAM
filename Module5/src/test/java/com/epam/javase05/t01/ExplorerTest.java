package com.epam.javase05.t01;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Nick on 31.03.2016.
 */
public class ExplorerTest {
    Explorer explorer;

    @Before
    public void setUp() {
        explorer = new Explorer();
    }

    @Test(expected = IOException.class)
    public void test() throws IOException {
        explorer.pwd();
        explorer.cat("pom.xml");
        explorer.touch("NEWFILE");
        explorer.ls(".");
        //Exception here
        explorer.cd("pom.xml");
        explorer.write("NEWFILE", "new test", true);
        explorer.write("NEWFILE", "\nanother new test", true);
        explorer.cat("NEWFILE");
        explorer.rm("NEWFILE");
        //This will fail on linux-based systems
        explorer.cd("C:/");
        explorer.pwd();
        explorer.ls(".");
    }

}