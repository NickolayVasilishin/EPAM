package com.epam.jmp.classloading;

import org.junit.Test;

public class ClassloadingTest {
    @Test
    public void testLoadingSemaphoreClassesFromURL() throws ClassNotFoundException {
        String[] args = new String[]{"src\\main\\resources\\compiled\\SemaphoreHardlyWorks.jar",
                "src\\main\\resources\\compiled\\SemaphoreProcrastinates.jar",
                "src\\main\\resources\\compiled\\SemaphoreWorks.jar",
                "src\\main\\resources\\compiled\\SemaphoreHardlyWorks.jar"};
        Classloading.main(args);
    }

    @Test(expected = ClassNotFoundException.class)
    public void testLoadingSemaphoreClassFromSources() throws ClassNotFoundException {
        String[] args = new String[]{""};
        Classloading.main(args);
    }
}