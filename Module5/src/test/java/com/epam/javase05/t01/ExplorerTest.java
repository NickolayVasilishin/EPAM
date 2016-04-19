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

    @Test
    public void test() throws IOException {
//        explorer.cd("");
        explorer.pwd();
        explorer.cat("pom.xml");
        explorer.touch("NEWFILE");
        explorer.ls(".");
        explorer.cd("C:/");
        explorer.pwd();
        explorer.ls(".");
        explorer.cd("pom.xml");
        //UNIMPLEMENTED
        explorer.rm("");
        explorer.write("", true);
    }

}