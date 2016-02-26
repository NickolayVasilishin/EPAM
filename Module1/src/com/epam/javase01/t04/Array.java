package com.epam.javase01.t04;

/**
 * Created by Nick on 26.02.2016.
 */
public class Array {

    public static double max(double[] array) {
        //Elvis just divides int there properly.
        double[] transformedArray = new double[array.length % 2 == 1 ? array.length/2 + 1 : array.length/2];
        for(int i=0, j=array.length-1; i <= j; i++, j--)
            transformedArray[i] = array[i] + array[j];
        double max = transformedArray[0];
        for(double d:transformedArray)
            if(d > max)
                max = d;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(Array.max(new double[] {13.2, 14.2, 0.3, 15.13, 2.3}));
    }
}
