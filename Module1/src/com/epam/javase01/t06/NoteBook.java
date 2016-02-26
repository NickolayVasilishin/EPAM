package com.epam.javase01.t06;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 26.02.2016.
 * Simple model of a notebook. Uses ArrayList to store notes.
 */
public class NoteBook {
    List<Note> notes;

    public NoteBook() {
        notes = new ArrayList<>();
    }

    /**
     * Used to add new notes to notebook.
     * @param note - string which will be used as note.
     * @return - this instance of NoteBook.
     *
     */
    public NoteBook add(String note) {
        notes.add(new Note(note));
        return this;
    }

    /**
     * Used to add now notes to notebook.
     * @param note
     * @return - this instance of NoteBook.
     */
    public NoteBook add(Note note) {
        notes.add(note);
        return this;
    }

    /**
     * Used to remove note by its index.
     * @param index - index of note.
     * @return - removed note.
     */
    public Note remove(int index) {
        return notes.remove(index);
    }

    /**
     * Used to edit notes by replacing them by index.
     * @param index - index of note to be replaced.
     * @param note - new note.
     * @return - this instance of NoteBook.
     */
    public NoteBook edit(int index, Note note) {
        notes.set(index, note);
        return this;
    }

    /**
     * Used to get note by its index.
     * @param index - index of note.
     * @return - note at this index.
     */
    public Note get(int index) {
        return notes.get(index);
    }

    /**
     * Used to get all notes in the notebook.
     * @return - notes as ArrayList.
     */
    public List<Note> getAll() {
        return notes;
    }

    /**
     *
     * @return - size of notebook.
     */
    public int size() {
        return notes.size();
    }

    @Override
    public String toString() {
        return notes.toString();
    }

    public static void main(String[] args) {
        NoteBook notebook = new NoteBook();
        notebook.add("First note").add("Second note").add("Last note");
        System.out.println("Notes in notebook: " + notebook.size());
        System.out.println("The second note in notebook is: " + notebook.get(1));
        System.out.println("Now the second note in notebook is: " + notebook.edit(1, new Note("Edited second note")).get(1));
        System.out.println("Removing last note in notebook: " + notebook.remove(2));
        System.out.println("List all notebook: " + notebook);



    }
}
