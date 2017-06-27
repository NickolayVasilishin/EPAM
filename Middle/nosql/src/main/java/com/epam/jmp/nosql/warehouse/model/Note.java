package com.epam.jmp.nosql.warehouse.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Nikolay_Vasilishin on 6/27/2017.
 */
public abstract class Note {
    public final static String TITLE_FIELD = "title";
    public final static String TEXT_FIELD = "text";
    public final static String DATE_FIELD = "date";

    protected String title;
    protected String text;
    protected LocalDateTime date;

    public abstract Map<String, Object> getContents();

    public static long dateToTimestamp(LocalDateTime date) {
        return  date.toEpochSecond(ZoneOffset.UTC);
    }

    public static LocalDateTime timestampToDate(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }
}
