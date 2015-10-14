package com.hhschool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) {

        System.out.println("Enter arrays");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            double[] arr1 = convertToDouble(reader.readLine().split(" "));
            double[] arr2 = convertToDouble(reader.readLine().split(" "));

            System.out.println("Median: " + findMedian(arr1,arr2));

        }
        catch(NumberFormatException e)
        {
            System.out.println("Incorrect number in input string");
        }
        catch(IOException e)
        {
            System.out.println("Some problems with I/O");
        }

    }


    private static double findMedian (double[] arr1, double[] arr2) {
        int length = arr1.length + arr2.length;
        double temp = 0;
        double curElem = 0;
        boolean even = isEven(length);

        for (int i = 0, j = 0; i + j < length / 2 + 1 ; ) {
            if (even && i + j == length / 2) temp = curElem;
            if (i < arr1.length && j < arr2.length) {
                if(arr1[i] <= arr2[j]) {
                    curElem = arr1[i];
                    i++;
                }
                else {
                    curElem = arr2[j];
                    j++;
                }
            }
            else if(i == arr1.length) {
                curElem = arr2[j];
                j++;
            }
            else if (j == arr2.length) {
                curElem = arr1[i];
                i++;
            }
        }
        return (even) ? average(temp,curElem) : curElem;
    }
    private static double average(double x, double y) {
        return (x + y) / 2.0;
    }
    private static boolean isEven (int length) {
        return (length % 2 == 0) ? true : false;
    }
    private static double[] convertToDouble (String[] arr) {
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Double.parseDouble(arr[i]);
        }
        return result;
    }
}
