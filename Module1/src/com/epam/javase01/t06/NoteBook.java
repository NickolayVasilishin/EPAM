package com.epam.javase01.t06;

/**
 * Created by Nick on 26.02.2016.
 * Simple model of a notebook. Uses Note[] array to store notes.
 */
public class NoteBook {
    private Note[]  notes;
    private int lastIndex;

    public NoteBook() {
        notes = new Note[2];
        lastIndex = 0;
    }

    /**
     * Used to add new notes to notebook.
     * @param note - string which will be used as note.
     * @return - this instance of NoteBook.
     *
     */
    public NoteBook add(String note) {
        add(new Note(note));
        return this;
    }

    /**
     * Used to add now notes to notebook.
     * @param note
     * @return - this instance of NoteBook.
     */
    public NoteBook add(Note note) {
        if(lastIndex > notes.length/2) {
            Note[] newNotes = new Note[notes.length*2];
            System.arraycopy(notes, 0, newNotes, 0, notes.length);
            notes = newNotes;
        }
        notes[lastIndex++] = note;
        return this;
    }

    /**
     * Used to remove note by its index.
     * @param index - index of note.
     * @return - removed note.
     */
    public Note remove(int index) {
        Note removedNote = notes[index];
        System.arraycopy(notes, index+1, notes, index, lastIndex - index);
        --lastIndex;
        return removedNote;
    }

    /**
     * Used to edit notes by replacing them by index.
     * @param index - index of note to be replaced.
     * @param note - new note.
     * @return - this instance of NoteBook.
     */
    public NoteBook edit(int index, Note note) {
        notes[index] = note;
        return this;
    }

    /**
     * Used to get note by its index.
     * @param index - index of note.
     * @return - note at this index.
     */
    public Note get(int index) {
        return notes[index];
    }

    /**
     * Used to get all notes in the notebook.
     * @return - notes as ArrayList.
     */
    public Note[] getAll() {
        return notes;
    }

    /**
     *
     * @return - size of notebook.
     */
    public int size() {
        return lastIndex-1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append("[");
        for(Note note:notes){
            if(note == null) break;
            s.append(note).append(", ");
        }
        s.deleteCharAt(s.lastIndexOf(", ")).append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        NoteBook notebook = new NoteBook();
        notebook.add("First note")
                .add("Second note")
                .add("Third note")
                .add("4")
                .add("5")
                .add("Last note");
        System.out.println("Notes in notebook: " + notebook.size());
        System.out.println("The second note in notebook is: " + notebook.get(1));
        System.out.println("Now the second note in notebook is: " + notebook.edit(1, new Note("Edited second note")).get(1));
        System.out.println("Removing third note in notebook: " + notebook.remove(2));
        System.out.println("Removing last note in notebook: " + notebook.remove(notebook.size()));
        System.out.println("List all notebook: " + notebook);
        notebook.remove(notebook.size());
        notebook.remove(notebook.size());
        System.out.println("Removed 2 last notes. List all notebook: " + notebook);
        notebook.remove(0);
        System.out.println("Removing first note in notebook. List all notebook: " + notebook);
        notebook.add("Some").add("new").add("notes");
        System.out.println("Added notes. List all notebook: " + notebook);


    }
}
