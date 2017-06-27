package com.epam.jmp.nosql.warehouse.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public class SimpleNote extends Note {

    public SimpleNote(String title, String text) {
        this.title = title;
        this.text = text;
        this.date = LocalDateTime.now();
    }


    public SimpleNote(String title, String text, LocalDateTime date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    @Override
    public Map<String, Object> getContents() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Note.TITLE_FIELD, title);
        map.put(Note.TEXT_FIELD, text);
        map.put(Note.DATE_FIELD, Note.dateToTimestamp(date));

        return map;
    }

    @Override
    public String toString() {
        return "SimpleNote{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
