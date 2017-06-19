package com.epam.jmp.memorymanagement;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Server {

    /**
     * Write a simple program that repeatedly creates some objects structure (for example ArrayList collection) and then release memory (set reference to null).
     * Please note that in order to avoid JVM optimizations use some kind of randomness.
     * Then try to run this program using different types of GC (listed below) and monitor memory utilization using Java VisualVm.
     *
     * Use the following GC configurations:
     *      1) Serial Collector with the following parameters
     *          the 6m initial heap size for when the JVM starts
     *          the 18m maximum heap size
     *          the 2m size of the Young Generation
     *          the 20m starting size of the Permanent Generation
     *          the 30 maximum size of the Permanent Generation
     *
     *      2) Parallel Collector with the following parameters
     *          the 3m initial heap size for when the JVM starts
     *          the 12m maximum heap size
     *          the 1m size of the Young Generation
     *          the 20m starting size of the Permanent Generation
     *          the 20 maximum size of the Permanent Generation
     *
     *      3) Parallel Old Collector with the following parameters
     *          the 9m initial heap size for when the JVM starts
     *          the 18m maximum heap size
     *          the 3m size of the Young Generation
     *          the 40m starting size of the Permanent Generation
     *          the 40 maximum size of the Permanent Generation
     *
     *      4) Concurrent Mark Sweep (CMS) Collector with 2 Parallel CMS Threads with the following parameters
     *          the 6m initial heap size for when the JVM starts
     *          the 18m maximum heap size
     *          the 2m size of the Young Generation
     *          the 20m starting size of the Permanent Generation
     *          the 30 maximum size of the Permanent Generation
     *
     *      5) G1 Garbage Collector with the following parameters
     *          the 4m initial heap size for when the JVM starts
     *          the 16m maximum heap size
     *          the 2m size of the Young Generation
     *          the 12m starting size of the Permanent Generation
     *          the 18 maximum size of the Permanent Generation
     *
     *      What should we get in result in repository: Source code of the
     *      program README file with all GC command line configuration parameters Screenshots from VisualVM for all types of GC running for 5 mins.
     */


    /**
     * 1) -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:+UseSerialGC
     -Xms6m
     -Xmx18m
     -XX:SurvivorRatio=9
     -XX:PermSize=20m
     -XX:MaxPermSize=20m
     https://monosnap.com/file/rlrGSt2ioNBGhKXzekSI6QAIEmOImo.png


     2) -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:+UseParallelGC
     -Xms3m
     -Xmx12m
     -XX:SurvivorRatio=12
     -XX:PermSize=20m
     -XX:MaxPermSize=20m
     https://monosnap.com/file/dw5cLofE5kuT0NRUKRQx3fsHG4ltR0.png

     3) -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:+UseParallelGC
     -XX:+UseParallelOldGC
     -Xms9m
     -Xmx18m
     -XX:SurvivorRatio=6
     -XX:PermSize=40m
     -XX:MaxPermSize=40m
     https://monosnap.com/file/NBgTQqSTkue6rNKEbbjbVPOVUDi6dc.png

     4) -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:+UseParNewGC
     -XX:+UseConcMarkSweepGC
     -XX:ConcGCThreads=2
     -Xms6m
     -Xmx18m
     -XX:SurvivorRatio=9
     -XX:PermSize=20m
     -XX:MaxPermSize=30m
     https://monosnap.com/file/e5j4Zrmw5zL43IYIa3RYaGNAzd1riu.png

     5) -XX:+PrintGCDetails
     -XX:+PrintGCDateStamps
     -XX:+PrintGCTimeStamps
     -XX:+UseG1GC
     -XX:MaxGCPauseMillis=200
     -Xms4m
     -Xmx16m
     -XX:SurvivorRatio=8
     -XX:PermSize=12m
     -XX:MaxPermSize=18m
     https://monosnap.com/file/RMzRjkPmEyds0YrWITV7H7WUDzjcux.png
     */



    public static final int COUNT = 2000;
    public static void main(String[] args) {

        for (Integer k : IntStream.range(0, 20000).toArray()) {
            ArrayList<String> strings = new ArrayList<>();
            int counter = 0;
            for (Integer i : IntStream.range(0, 20).toArray()) {
                System.out.println("========================" + i);
                for (int j = 0; j < COUNT; j++) {
                    strings.add("a " + (i + RandomUtils.nextInt()));
                }
                strings.sort(StringUtils::compare);
                for (int ch = 0; ch < 2; ch++) {
                    for (String item : strings) {
                        if (item.contains("" + ch)) {
                            counter++;
                        }
                    }
                }

            }
            strings = new ArrayList<>();
        }
    }

}
