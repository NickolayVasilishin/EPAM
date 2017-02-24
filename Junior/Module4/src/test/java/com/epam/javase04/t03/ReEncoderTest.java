package com.epam.javase04.t03;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by Nick on 31.03.2016.
 */
public class ReEncoderTest {

    @Test
    public void encode() {
        ReEncoder.translate(ReEncoder.INPUT_FILE_UTF_8, ReEncoder.OUTPUT_FILE_UTF_16);
    }
}