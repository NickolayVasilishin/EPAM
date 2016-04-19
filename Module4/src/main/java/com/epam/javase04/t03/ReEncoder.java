package com.epam.javase04.t03;

import java.io.*;

/**
 * Created by Nick on 29.03.2016.
 */
public class ReEncoder {
    public static final String INPUT_FILE_UTF_8 = "Module4\\src\\main\\resources\\utf-8.txt";
    public static final String OUTPUT_FILE_UTF_16 = "Module4\\src\\main\\resources\\utf-16.txt";

    public static void translate(String inputFile, String outputFile) {
        byte[] buffer = new byte[2048];
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            while (input.read(buffer) != -1) {
                String sBuffer;
                sBuffer = new String(buffer, "utf-8");
                System.out.println(sBuffer);
                sBuffer = new String(sBuffer.getBytes("utf-16"), "utf-16");
                System.out.println(sBuffer);
                buffer = sBuffer.getBytes("utf-16");
                output.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
