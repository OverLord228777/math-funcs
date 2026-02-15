package org.example;

import java.util.Random;
import java.util.Arrays;

public class TriTask {
     public static void TriangleTask() {

         /*
          На окружности 3 точки, какова вероятность того,
          что 3 случайные точки образуют тупоугольный треугольник,
          найти абсолютную и относительную погрешности
          при 500, 1000 и 1500 вариантах.
         */

         int[] sampleSizes = {500, 1000, 1500};
         Random rand = new Random();

         for (int N : sampleSizes) {
             double[] results = new double[N];

             for (int i = 0; i < N; i++) {
                 // Генерация трёх случайных углов
                 double[] angles = new double[3];
                 for (int j = 0; j < 3; j++) {
                     angles[j] = rand.nextDouble() * 2 * Math.PI;
                 }
                 Arrays.sort(angles);

                 // Вычисление дуг между соседними точками
                 double d1 = angles[1] - angles[0];
                 double d2 = angles[2] - angles[1];
                 double d3 = 2 * Math.PI - (angles[2] - angles[0]); // замыкающая дуга

                 double maxGap = Math.max(Math.max(d1, d2), d3);
                 // Треугольник тупоугольный, если максимальная дуга > π
                 results[i] = (maxGap > Math.PI) ? 1.0 : 0.0;
             }

             // Статистическая обработка
             double avg = StatisticsCalculator.calculateAverage(results);
             double stdDev = StatisticsCalculator.calculateStandardDeviation(results, avg);
             double t = StatisticsCalculator.getStudentCoefficient(N);
             double absError = StatisticsCalculator.calculateAbsoluteError(t, stdDev, N);
             double relError = StatisticsCalculator.calculateRelativeError(absError, avg);

             // Вывод результатов
             System.out.printf("N = %d%n", N);
             System.out.printf("Оценка вероятности: %.6f%n", avg);
             System.out.printf("Стандартное отклонение: %.6f%n", stdDev);
             System.out.printf("Абсолютная погрешность (p=0.95): %.6f%n", absError);
             System.out.printf("Относительная погрешность: %.2f%%%n", relError);
             System.out.println();
         }
     }
}
