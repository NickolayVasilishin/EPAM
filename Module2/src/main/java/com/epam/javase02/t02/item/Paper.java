package com.epam.javase02.t02.item;

/**
 * Created by Nick on 04.03.2016.
 */
public class Paper extends CancelleryItem {
    private StringBuilder notes;

    public Paper(String manufacturer, String owner, int cost) {
        super(manufacturer, owner, cost);
        notes = new StringBuilder();
    }

    Paper write(String note) {
        notes.append(note);
        return this;
    }

    Paper erase(String note) {
        notes.delete(notes.indexOf(note), notes.indexOf(note) + note.length());
        return this;
    }

    @Override
    public String toString() {
        return notes.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Paper paper = (Paper) o;

        return notes != null ? notes.equals(paper.notes) : paper.notes == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
