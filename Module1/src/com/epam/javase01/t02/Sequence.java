package com.epam.javase01.t02;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 25.02.2016.
 */
public class Sequence {
    /**
     * Method calculates elements of sequence a(n) = 1/(x+1)^2
     * until a(n) < condition.
     * a(n) belongs (0;1].
     * Method prints all elements from 1 to n and
     *
     * @param condition - value until sequence will be calculated.
     *                  Must be greater than 0;
     * @return - List of elements, where last element is the first element less than condition value.
     *
     */
    public static strictfp List<Double> calculate(double condition) {
        if (condition <= 0)
            throw new IllegalArgumentException("Condition can not be less than 0");

        double a = 1;
        List<Double> elements = new LinkedList<>();
        elements.add(a);
        for (int i = 1; a > condition; i++) {
            a = 1/Math.pow((i+1), 2);
            elements.add(a);
        }
        return elements;
    }

    public static void main(String[] args) {
        List <Double> elements = calculate(0.039);
        System.out.println("Index is: " + elements.size());
        System.out.println("Elements are: \n" + elements);
    }
}
