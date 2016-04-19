package com.epam.javase05.t01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by Nick on 31.03.2016.
 */
public class Explorer {
    private Path currentDirectory;

    public Explorer() {
        currentDirectory = Paths.get(".").toAbsolutePath().normalize();
    }

    public static void main(String[] args) {
        System.out.println(args[0]);
    }

    public void ls(String s) throws IOException {
        Path path = Paths.get(s);
        if(!path.isAbsolute())
            path = currentDirectory.resolve(path);
        Files.list(path).map(this::getInfo).forEach(System.out::println);
    }

    //TODO Replace with modern date
    private String getInfo(Path path) {
        return getInfo(path.toFile());
    }

    private String getInfo(File file) {
        return new StringBuilder()
                .append(file.isDirectory() ? "d" : "-")
                .append(file.canRead() ? "r" : "-")
                .append(file.canWrite() ? "w" : "-")
                .append(file.canExecute() ? "x" : "-")
                .append(" ")
                .append(new Date(file.lastModified()))
                .append(" ")
                .append(file.getName()).toString();

    }

    public void write(String text, boolean append) {

    }

    public void rm(String file) {

    }

    public void cat(String file) throws IOException {
        Path f = Paths.get(file);
        if(!f.isAbsolute())
            f = currentDirectory.resolve(f);
        Files.lines(f).limit(30).forEach(System.out::println);
    }

    public void touch(String file) throws IOException {
        Path f = Paths.get(file);
        if(!f.isAbsolute())
            f = currentDirectory.resolve(f);

        Files.createFile(f);
    }

    public void cd(String to) throws IOException {
        if(!Files.isDirectory(Paths.get(to)))
            throw new IOException("Wrong argument: " + to + " is not a directory");

        currentDirectory = currentDirectory.resolve(to).normalize();
    }

    public void pwd() {
        System.out.println(currentDirectory.toAbsolutePath());
    }
}
