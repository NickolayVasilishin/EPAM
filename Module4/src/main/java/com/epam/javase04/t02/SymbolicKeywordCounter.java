package com.epam.javase04.t02;

import com.epam.javase04.t01.KeywordCounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Nick on 29.03.2016.
 */
public class SymbolicKeywordCounter extends KeywordCounter {
    @Override
    protected String readSources(String file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String input;
            while((input = reader.readLine()) != null)
                builder.append(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
