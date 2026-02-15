package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        String expr = "x * x * x + 0.1 * x * x - 16.69 * x + 23.171";
        ArrayList<Double> Korni = NumericalMethods.bisertion(expr, 0.005);
        System.out.println(Korni);

        TriTask.TriangleTask();
    }
}
