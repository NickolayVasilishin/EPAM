package com.epam.javase03.t02;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Nick on 27.03.2016.
 */
public class InternationalQuiz {
    private static final String QUESTIONS = "questions";
    private static final String ANSWERS = "answers";

    private ResourceBundle questions;
    private ResourceBundle answers;

    public InternationalQuiz(Locale locale) {
        questions = ResourceBundle.getBundle(QUESTIONS, locale);
        answers =  ResourceBundle.getBundle(ANSWERS, locale);
    }

    public InternationalQuiz() {
        this(Locale.getDefault());
    }

    private void setLocale(Locale locale) {
        questions = ResourceBundle.getBundle(QUESTIONS, locale);
        answers = ResourceBundle.getBundle(ANSWERS, locale);
    }

    private String question(int number) {
        return questions.getString("" + number);
    }

    private String answer(int number) {
        return answers.getString("" + number);
    }

    private String greeting() {
        return questions.getString("greeting");
    }

    private String language() {
        return questions.getString("language");
    }

    private String exit() {
        return questions.getString("exit");
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(language());
        switch (scanner.nextLine().toLowerCase()) {
            case "1":
            case "русский": setLocale(new Locale("ru", "RU")); break;
            case "2":
            case "english":
            default: setLocale(new Locale("en", "us")); break;
        }
        System.out.println(greeting());
        String input;
        while(scanner.hasNext() && !(input = scanner.nextLine()).equals(exit())) {
            int number = Integer.valueOf(input);
            System.out.println(question(number));
            System.out.println(answer(number));
        }
    }

    public static void main(String[] args) {
        new InternationalQuiz().run();
    }


}
