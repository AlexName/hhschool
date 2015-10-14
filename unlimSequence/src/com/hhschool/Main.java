package com.hhschool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        String inString = inputStream.readLine();
        System.out.println(getSubstrPosition(inString));

    }
    // Using Knuth–Morris–Pratt algorithm
    private static int getSubstrPosition(String needle) {
        int[] shift = getShift(needle);
        int curIndex = 0;
        int absoluteIndex = 0;
        int deletedSymbolsCount = 0;
        int curNumber = 1;
        String hayStack = new String();
        while(true) {
            if(hayStack.length() > absoluteIndex - deletedSymbolsCount) {
                while (needle.charAt(curIndex) != hayStack.charAt(absoluteIndex - deletedSymbolsCount) && curIndex > 0) {
                    curIndex = shift[curIndex - 1];
                }
                if (needle.charAt(curIndex) == hayStack.charAt(absoluteIndex - deletedSymbolsCount)) {
                    curIndex = curIndex + 1;
                    if (curIndex == needle.length()) return absoluteIndex + 1 - curIndex + 1;

                } else curIndex = 0;

                ++absoluteIndex;
            } else {
                hayStack += curNumber++;
                hayStack = hayStack.substring(absoluteIndex-deletedSymbolsCount);
                deletedSymbolsCount = absoluteIndex;
            }
        }

    }

    private static int[] getShift(String text) {
        int[] shift = new int[text.length()];
        shift[0] = 0;
        for(int i = 1; i < text.length(); ++i) {
            int k = shift[i - 1];
            while(text.charAt(k) != text.charAt(i) && k > 0) {
                k = shift[k - 1];
            }
            if(text.charAt(k) == text.charAt(i)) shift[i] = k + 1;
            else shift[i] = 0;
        }
        return shift;
    }
}
