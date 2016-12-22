package com.okunev;

import com.okunev.algolib.AlgorithmTools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            final String originalText = reader.readLine();
            AlgorithmTools.test(originalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}