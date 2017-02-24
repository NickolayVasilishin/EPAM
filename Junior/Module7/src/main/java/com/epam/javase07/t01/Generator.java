package com.epam.javase07.t01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

/**
 * Created by Nick on 09.05.2016.
 */
public class Generator {
    public static void main(String[] args) throws IOException {
        StringBuilder events = new StringBuilder();
        for(int i = 0; i < 1000; i++)
            events.append(new Random().nextInt(100)+1).append("_").append(new Random().nextInt(100)+1).append("_").append(new Random().nextInt(1000)+1).append("\n");
        Files.write(Paths.get("bills"), events.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }
}
