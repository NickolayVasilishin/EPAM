package com.epam.javase02.t06;

import org.junit.Test;

/**
 * Created by Nick on 07.03.2016.
 */
public class NuclearSubmarineTest {

    @Test
    public void submarine() throws InterruptedException {
        NuclearSubmarine submarine = new NuclearSubmarine();
        Thread.sleep(3000);
        submarine.moveTo("Canada");
        Thread.sleep(3000);
    }
}
