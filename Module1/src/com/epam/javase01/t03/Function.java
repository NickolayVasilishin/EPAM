package com.epam.javase01.t03;

/**
 * Created by Nick on 26.02.2016.
 * It just simply prints f(x) as table.
 */
public class Function {
    Function(int from, int to, int step) {
        System.out.println("x\ty");
        for (int i = from; i <= to; i += step) {
            System.out.println("" + i + "\t" + (Math.tan(2*i) - 3));
        }
    }

    public static void main(String[] args) {
        new Function(2, 10, 2);
    }
}
