package com.epam.javase03.t01;

import java.time.LocalDateTime;

/**
 * Created by Nick on 25.03.2016.
 */
public class LoggerSearchRule {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tag;
    private String sequence;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
