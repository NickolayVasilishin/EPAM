package com.epam.javase01.t05;

/**
 * Created by Nick on 26.02.2016.
 */
public class MultiArray {
    private int[][] array;

    public MultiArray(int n, int m) {
        array = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == j || i == m - j - 1)
                    array[i][j] = 1;
                else
                    array[i][j] = 0;
            }
        }
    }


    public String getAsStringTable() {
        StringBuilder s = new StringBuilder();
        for(int[] row:array) {
            for (int element : row) {
                s.append(element).append(",");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MultiArray(10,15).getAsStringTable());
    }

}
