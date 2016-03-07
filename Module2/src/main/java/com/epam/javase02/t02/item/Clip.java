package com.epam.javase02.t02.item;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 04.03.2016.
 */
public class Clip extends CancelleryItem{
    List<Paper> clippedPapers;

    public Clip(String manufacturer, String owner, int cost) {
        super(manufacturer, owner, cost);
    }

    public List<Paper> clipPapers(Paper ... papers) {
        if (clippedPapers != null)
            throw new IllegalStateException("This clip is already used for another papers");
        else {
            List<Paper> clippedPapers = new LinkedList<>();
            Collections.addAll(clippedPapers, papers);
        }
           return clipPapers(clippedPapers);
    }

    public List<Paper> clipPapers(List<Paper> papers) {
        if (clippedPapers == null) {
            List<Paper> clippedPapers = new LinkedList<>(papers);
            return this.clippedPapers = clippedPapers;
        } else
            throw new IllegalStateException("This clip is already used for another papers");
    }


    public List<Paper> unclipPapers() {
        List<Paper> papers = clippedPapers;
        clippedPapers = null;
        return papers;
    }

    public boolean isUnclipped() {
        return clippedPapers == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Clip clip = (Clip) o;

        return clippedPapers != null ? clippedPapers.equals(clip.clippedPapers) : clip.clippedPapers == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (clippedPapers != null ? clippedPapers.hashCode() : 0);
        return result;
    }
}
