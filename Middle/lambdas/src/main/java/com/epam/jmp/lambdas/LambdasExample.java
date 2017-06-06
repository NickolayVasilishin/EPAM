package com.epam.jmp.lambdas;

import com.epam.jmp.jdbc.model.User;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class LambdasExample {

    /**
     * Created by nikolay on 6/6/17.
     */

    public static void main(String[] args) throws IOException, ScriptException {
        /**
         * Изучите следующий фичи java 8 и напишите примеры, которые демонстрируют их исользование:
         *  - _Streams, _Lambdas, use _filter, _sort, _forEach, _map, reduce, _collect, _peek, min, _max, ...
         *  - Сделайте стрим параллельным
         *  - Comparator - создание компараторов в коде
         *  - JavaScript engine - напишите простенький javascript-код и запустите его в JVM
         *  - Optional + ifPresent
         */

        /*
         * 1
         */
        Double max = IntStream
                .range(0, 100)
                .filter(i -> i % 3 == 0)
                .asDoubleStream()
                .map(i -> Math.pow(i, 2))
                .max()
                .orElse(-1);
        System.out.println(max);

        StringBuilder logger = new StringBuilder();

        ArrayList<Integer> collected = new Random()
                .ints(10, 0, 10)
                .peek(i -> logger.append(i).append(" "))
                .distinct()
                .sorted()
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        System.out.println(logger.toString());
        System.out.println(collected);
        /*
         * 2, 3
         */
        Files.lines(Paths.get("lambdas/src/main/resources/users.csv"))
                // it's obvious here, if stream is in parallel mode: results are sorted partially
                //.parallel()
                .skip(1)
                .map(s -> s.split(","))
                .map(data -> {
                    try {
                        return new User(Integer.valueOf(data[0]), data[1], data[2], new SimpleDateFormat("YYYY-MM-DD").parse(data[3]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(User::getSurname))
                .forEach(System.out::println);

        /*
         * 4
         */
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('JavaJavaScript')");

        /*
         * 5
         */
        Optional.ofNullable(null).ifPresent(System.out::println);
        Optional.of("Optional String").ifPresent(System.out::println);


    }
}
