package org.example;

public class StatisticsCalculator {

    // Расчет среднего арифметического
    public static double calculateAverage(double[] data) {
        double sum = 0;
        for (double val : data) sum += val;
        return sum / data.length;
    }

    // Расчет стандартного отклонения (S)
    public static double calculateStandardDeviation(double[] data, double avg) {
        double sumSq = 0;
        for (double val : data) {
            sumSq += Math.pow(val - avg, 2);
        }
        return Math.sqrt(sumSq / (data.length - 1));
    }

    // Получение коэффициента Стьюдента (табличные значения для p=0.95)
    public static double getStudentCoefficient(int n) {
        double[] tTable = {0, 12.71, 4.30, 3.18, 2.78, 2.57, 2.45, 2.36, 2.31, 2.26, 2.23};
        int df = n - 1;
        if (df < 1) return 0;
        return (df < tTable.length) ? tTable[df] : 1.96;
    }

    // Расчет абсолютной погрешности
    public static double calculateAbsoluteError(double t, double stdDev, int n) {
        return t * (stdDev / Math.sqrt(n));
    }

    // Расчет относительной погрешности
    public static double calculateRelativeError(double absError, double avg) {
        return (absError / avg) * 100;
    }
}
