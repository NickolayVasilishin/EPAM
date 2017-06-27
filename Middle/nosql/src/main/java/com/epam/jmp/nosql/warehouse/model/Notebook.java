package com.epam.jmp.nosql.warehouse.model;

import java.util.List;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public interface Notebook {
    long count();
    void saveNote(Note note);
    List<Note> findByTitle(String title);
    List<Note> find(String anyText);
    boolean delete(Note note);
}
