package com.epam.javase01.t01;

import com.epam.javase01.t01.logic.Logic;

/**
 * Created by Nick on 25.02.2016.
 *
 * Move to project root directory.
 * Compile project with:
 *  javac -d bin src\com\epam\javase01\t01\logic\*.java
 * Run program with:
 *  java -cp . com.epam.javase01.t01.Main
 *
 */
public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();
        System.out.println(logic.method());
    }
}
