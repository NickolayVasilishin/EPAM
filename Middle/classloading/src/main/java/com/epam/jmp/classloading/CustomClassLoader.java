package com.epam.jmp.classloading;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.logging.Logger;

public class CustomClassLoader extends URLClassLoader {
    private static final String CLASSNAME = "com.epam.jmp.classloading.Semaphore";

    public CustomClassLoader(String path) throws MalformedURLException {
        this(new URL[]{new File(path).toURI().toURL()});
    }

    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    public Class<?> loadSemaphoreClass() throws ClassNotFoundException {
        Logger.getLogger(this.getClass().getName()).info("Loading class: " + CLASSNAME +
                " from " + Arrays.toString(getURLs()));
        return loadClass(CLASSNAME);
    }

    // This method is overrode to avoid loading existing Semaphore class
    // It loads either Semaphore class from URL or other classes.
    // It should fail if existing Semaphore class tries to be loaded.
    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // Check if it's loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                // Try to load it
                c = findClass(name);
            } catch (ClassNotFoundException e) {
                // If fails, delegate to parent
                if (!name.equals(CLASSNAME)) {
                    c = super.loadClass(name, resolve);
                } else {
                    throw e;
                }
                return c;
            }
        }

        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

}


