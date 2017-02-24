package com.epam.javase05.t01;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Nick on 31.03.2016.
 */
public class Explorer {
    private Path currentDirectory;
    private final static String LS_COMMAND = "ls";
    private final static String RM_COMMAND = "rm";
    private final static String CAT_COMMAND = "cat";
    private final static String WRITE_TRUNC_COMMAND = ">";
    private final static String WRITE_APPEND_COMMAND = ">>";
    private final static String TOUCH_COMMAND = "touch";
    private final static String CD_COMMAND = "cd";
    private final static String PWD_COMMAND = "pwd";

    public Explorer() {
        currentDirectory = Paths.get(".").toAbsolutePath().normalize();
    }

    public static void main(String[] args) {
        Explorer explorer = new Explorer();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("%>");
            String[] commands = getArguments(scanner);
            if(commands[0].toLowerCase().equals("exit"))
                break;
            try {

                switch (commands[0]) {
                    case LS_COMMAND:
                        try {
                            explorer.ls(commands[1]);
                        } catch (IOException e) {
                        }
                        break;
                    case RM_COMMAND:
                        try {
                            explorer.rm(commands[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case CAT_COMMAND:
                        try {
                            explorer.cat(commands[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case TOUCH_COMMAND:
                        try {
                            explorer.touch(commands[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case CD_COMMAND:
                        try {
                            explorer.cd(commands[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case PWD_COMMAND:
                        explorer.pwd();
                        break;
                    default:
                        System.out.println(Arrays.asList(commands));
                        switch (commands[1]) {
                            case WRITE_APPEND_COMMAND:
                                try {
                                    explorer.write(commands[2], commands[0], true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case WRITE_TRUNC_COMMAND:
                                try {
                                    explorer.write(commands[2], commands[0], false);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                System.out.println("Unknown command.");
                        }

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("You have to provide more arguments.");
            }
        }

    }

    public static String[] getArguments(Scanner scanner) {
        CommandLine cm = new CommandLine("sh");
        cm.addArguments(scanner.nextLine(), false);
        return cm.getArguments();
    }

    public void ls(String s) throws IOException {
        Path path = resolvePath(s);
        Files.list(path).map(this::getInfo).forEach(System.out::println);
    }

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

    public void write(String file, String text, boolean append) throws IOException {
        Path f = resolvePath(file);
        Files.write(f, text.getBytes(), append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

    public Path resolvePath(String file) {
        Path f = Paths.get(file);
        if(!f.isAbsolute())
            f = currentDirectory.resolve(f);
        return f;
    }

    public void rm(String file) throws IOException {
        Path f = resolvePath(file);
        Files.delete(f);
    }

    // Files.lines must be wrapped by auto-closeable. Quite strange and unexpected behavior.
    public void cat(String file) throws IOException {
        Path f = resolvePath(file);
        try(Stream<String> stream = Files.lines(f).limit(30)) {
            stream.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void touch(String file) throws IOException {
        Path f = resolvePath(file);
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
