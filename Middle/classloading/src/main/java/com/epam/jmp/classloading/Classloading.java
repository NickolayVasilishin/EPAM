package com.epam.jmp.classloading;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 1. Custom classloader should be able to load compiled Semaphore.class file.
 * Class path is passed via console on program start.
 * Status progress/success/error should be output to console.
 * <p>
 * 2. Semaphore object should be instantiated.
 * Method lever should be invoked.
 * Use reflection. Google exmples on reflection API.
 * <p>
 * 3. Expected to see "It works!" message on the console.
 * <p>
 * // soure code, compile it
 * <p>
 * package com.epam.mentoring.lessone;
 * <p>
 * public class Semaphore {
 * <p>
 * public void lever() {
 * System.out.println("It works!");
 * }
 * <p>
 * }
 * <p>
 * Use custom class files from previous task.
 * Extend classloader by adding ability to reload class at runtime.
 * Program should ask continuously for a class path.
 * When a path is entered to console - class should be reloaded.
 * <p>
 * 1. Prepare several versions of Semaphor class - change message string for output.
 * <p>
 * 2. Expected: after entering class path status messages and correct message are displayed in console.
 */
public class Classloading {

    public static void main(String[] args) throws ClassNotFoundException {

        // If we have specified args from command line, use them.
        // Else we will use inputs from System.in
        InputStream source = getInputStream(args);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {
            String path;
            while ((path = reader.readLine()) != null && !path.equals("exit")) {
                CustomClassLoader classLoader = new CustomClassLoader(path);
                Class<?> semaphoreClass = classLoader.loadSemaphoreClass();
                Constructor<?> semaphoreConstructor = semaphoreClass.getConstructor();
                Object semaphore = semaphoreConstructor.newInstance();
                Method method = semaphoreClass.getMethod("lever");
                method.invoke(semaphore);
            }
        } catch (InstantiationException | InvocationTargetException
                | IllegalAccessException | NoSuchMethodException | IOException e) {
            e.printStackTrace();
        }
    }

    private static InputStream getInputStream(String[] args) {
        InputStream source = System.in;
        if (args.length != 0) {
            String s  = String.join("\n", args) + "\n";
            source = new ByteArrayInputStream(s.getBytes());
            System.out.println(s);
        }
        return source;
    }
}