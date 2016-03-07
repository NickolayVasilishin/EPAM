package com.epam.javase02.t02;

import com.epam.javase02.t02.item.Clip;
import com.epam.javase02.t02.item.Eraser;
import com.epam.javase02.t02.item.Paper;
import com.epam.javase02.t02.item.Pen;
import com.epam.javase02.t02.preson.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nick on 04.03.2016.
 */
public class CancelleryItemsFeatureTest {
    private Clip clip;
    private Pen pen;
    private Eraser eraser;
    private List<Paper> papers;


    @Before
    public void setup() {
        clip = new Clip("man", "me", 2);
        pen = new Pen("me", 2);
        eraser = new Eraser("man", "me", 2);
        papers = new ArrayList<>();
        papers.add(new Paper("man", "me", 1));
        papers.add(new Paper("man", "me", 1));
    }

    @Test
    public void penWritesToPaper() {
        pen.writeTo(papers.get(0), "First note");
        assertEquals(papers.get(0).toString(), "First note");
    }

    @Test
    public void eraseNoteFromPaper() throws NoSuchFieldException, IllegalAccessException {
        //Are there any other ways? Only pen can changes paper, but
        //i don't want to overlap tests: if pen doesn't work properly
        //this test will will fail even if eraser works fine.
        Field field = papers.get(0).getClass().getDeclaredField("notes");
        field.setAccessible(true);
        field.set(papers.get(0), new StringBuilder().append("First note"));

        eraser.erase(papers.get(0), "note");
        assertEquals(papers.get(0).toString(), "First ");
    }

    @Test
    public void clipPapers() {
        assertEquals(papers, clip.clipPapers(papers.toArray(new Paper[0])));
    }

    @Test
    public void clipPersonPapers() {
        Person p = new Person("me").addItem(papers.get(0)).addItem(papers.get(1)).addItem(pen).addItem(eraser).addItem(papers.get(1));
//TODO
//        clip.clipPapers(p.getAllOf(Paper.class));
    }


}
