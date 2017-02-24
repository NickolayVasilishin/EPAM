package com.epam.javase01.t06;

/**
 *  Represents single note in notebook.
 *
 */
public class Note {
    private String text;

    public Note(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}
