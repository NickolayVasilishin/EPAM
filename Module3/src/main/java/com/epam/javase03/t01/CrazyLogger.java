package com.epam.javase03.t01;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nick on 18.03.2016.
 */
public class CrazyLogger {
    /*
        25-33-2016:08-33 [TAG] - Log message.

        ((?:\d{2,4}[-:]?){5}) - date
        \[(\w*)\] - tag
        (.*) - message
     */
    private static final String RE_LOG_PATTERN = "((?:\\d{2,4}[-:]?){5})\\s\\[(\\w*)\\]\\s-\\s(.*)";
    private static final String TAG = "INFO";
    private static final String LOG_DATE_FORMAT = "dd-MM-YYYY:HH-mm";
    private static final String LOG_FORMAT = "{0} [{1}] - {2}\n";
    private LoggerSearchRule searchRule;
    private StringBuilder logs;
    private String tag;

    public CrazyLogger() {
        this(TAG);
    }

    public CrazyLogger(String tag) {
        logs = new StringBuilder();
        this.tag = tag;
    }

    public void log(String message) {
        log(tag, message);
    }

    public void log(String tag, String message) {
        logs.append(logEntry(tag, message));
    }

    private String logEntry(String tag, String message) {
        return MessageFormat.format(LOG_FORMAT, date(), tag, message);
    }

    private String date() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(LOG_DATE_FORMAT));
    }

    public String getLogs() {
        return logs.toString();
    }

    private void createRule() {
        if (searchRule == null)
            searchRule = new LoggerSearchRule();
    }

    public CrazyLogger from(LocalDateTime time) {
        createRule();
        if (searchRule.getStartTime() != null)
            throw new IllegalStateException("Filter value 'From time' has already been set");
        searchRule.setStartTime(time);
        return this;
    }

    public CrazyLogger to(LocalDateTime time) {
        createRule();
        if (searchRule.getEndTime() != null)
            throw new IllegalStateException("Filter value 'End time' has already been set");
        searchRule.setEndTime(time);
        return this;
    }

    public CrazyLogger of(String tag) {
        createRule();
        if (searchRule.getTag() != null)
            throw new IllegalStateException("Filter value 'Tag' has already been set");
        searchRule.setTag(tag);
        return this;
    }

    public CrazyLogger contains(String sequence) {
        createRule();
        if (searchRule.getSequence() != null)
            throw new IllegalStateException("Filter value 'Contains sequence' has already been set");
        searchRule.setSequence(sequence);
        return this;
    }

    public String search() {
        LoggerSearchRule searchRule = this.searchRule;
        this.searchRule = null;
        if (searchRule == null)
            return logs.toString();
        Stream<String> stream = Arrays.stream(logs.toString().split("\n"));
        if (searchRule.getStartTime() != null)
            stream = stream.filter(s -> time(s).isAfter(searchRule.getStartTime()));
        if (searchRule.getEndTime() != null)
            stream = stream.filter(s -> time(s).isBefore(searchRule.getEndTime()));
        if (searchRule.getSequence() != null)
            stream = stream.filter(s -> message(s).toLowerCase().contains(searchRule.getSequence()));
        if (searchRule.getTag() != null)
            stream = stream.filter(s -> tag(s).equals(searchRule.getTag()));
        //TODO logs will be inlined?
        return stream.map(s -> s.concat("\n")).collect(Collectors.joining("\n"));
    }

    private LocalDateTime time(String logEntry) {
        return LocalDateTime.parse(match(logEntry, 1),
                DateTimeFormatter.ofPattern(LOG_DATE_FORMAT));
    }

    private String tag(String logEntry) {
        return match(logEntry, 2);
    }

    private String message(String logEntry) {
        return match(logEntry, 3);
    }

    private String match(String logEntry, int group) {
        Matcher m = Pattern.compile(RE_LOG_PATTERN).matcher(logEntry);
        return m.matches() ? m.group(group) : "";
    }
}
