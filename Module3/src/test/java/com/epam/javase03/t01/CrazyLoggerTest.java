package com.epam.javase03.t01;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Nick on 26.03.2016.
 */
public class CrazyLoggerTest {

    String[] logEntries = {"22-03-2016:08-33 [ERROR] - System failure: network error.",
            "23-03-2016:11-20 [ERROR] - Some nodes have been lost.",
            "23-03-2016:12-30 [WARNING] - Less than 30% free space on hdfs.",
            "23-03-2016:19-48 [INFO] - All network communications have been restored.",
            "24-03-2016:05-13 [INFO] - Scheduled restart.",
            "24-03-2016:08-11 [ERROR] - Some nodes haven't been started.",
            "24-03-2016:09-58 [ERROR] - Some web services may work not properly.",
            "24-03-2016:09-59 [WARNING] - Less than 25% free space on hdfs.",
            "24-03-2016:15-44 [ERROR] - Less than 20% free space on hdfs: Stopping optional services.",
            "25-03-2016:18-20 [INFO] - Added 15 new nodes. ",
            "25-03-2016:18-20 [ERROR] - Some nodes haven't been started.",
            "25-03-2016:18-21 [INFO] - Scheduled restart."
    };
    LocalDateTime startTime23;
    LocalDateTime startTime24;
    LocalDateTime endTime;
    CrazyLogger logger;

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        logger = new CrazyLogger("INFO");
        Field logs = logger.getClass().getDeclaredField("logs");
        logs.setAccessible(true);
        StringBuilder builder = (StringBuilder) logs.get(logger);
        for(String log:logEntries) {
            builder.append(log).append("\n");
        }
        builder.deleteCharAt(builder.lastIndexOf("\n"));

        startTime23 = LocalDateTime.of(2016, 3, 23, 12, 0);
        startTime24 = LocalDateTime.of(2016, 3, 24, 15, 0);
        endTime = LocalDateTime.of(2016, 3, 23, 20, 0);
    }

    @Test
    public void dateSearch() {
        assertEquals("23-03-2016:12-30 [WARNING] - Less than 30% free space on hdfs.\n" +
                        "23-03-2016:19-48 [INFO] - All network communications have been restored.",
                logger.from(startTime23).to(endTime).search());

        assertEquals("24-03-2016:15-44 [ERROR] - Less than 20% free space on hdfs: Stopping optional services.\n" +
                "25-03-2016:18-20 [INFO] - Added 15 new nodes. \n" +
                "25-03-2016:18-20 [ERROR] - Some nodes haven't been started.\n" +
                "25-03-2016:18-21 [INFO] - Scheduled restart.",
                logger.from(startTime24).search());
    }

    @Test
    public void tagSearch() {
        assertEquals("22-03-2016:08-33 [ERROR] - System failure: network error.\n" +
                "23-03-2016:11-20 [ERROR] - Some nodes have been lost.\n" +
                "24-03-2016:08-11 [ERROR] - Some nodes haven't been started.\n" +
                "24-03-2016:09-58 [ERROR] - Some web services may work not properly.\n" +
                "24-03-2016:15-44 [ERROR] - Less than 20% free space on hdfs: Stopping optional services.\n" +
                "25-03-2016:18-20 [ERROR] - Some nodes haven't been started.",
                logger.of("ERROR").search());
    }

    @Test
    public void messageSearch() {
        assertEquals("23-03-2016:12-30 [WARNING] - Less than 30% free space on hdfs.\n" +
                "24-03-2016:09-59 [WARNING] - Less than 25% free space on hdfs.\n" +
                "24-03-2016:15-44 [ERROR] - Less than 20% free space on hdfs: Stopping optional services.",
                logger.contains("hdfs").search());
    }

    @Test(expected = IllegalStateException.class)
    public void incorrectUsage() {
        logger.from(startTime23).from(startTime24).search();
    }
}