package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class NumericalMethods {

    public record Diapason(double a, double b){}

    public static ArrayList<Double> bisertion(String expr, double epsilon){
        ArrayList<Double> roots = new ArrayList<>();
        double step = 0.5;
        double start = -50.0;
        double end = 50.0;

        // Поиск интервалов, где функция меняет знак
        ArrayList<Diapason> intervals = new ArrayList<>();
        double prevX = start;
        double prevF = evaluate(expr, prevX);

        for (double x = start + step; x <= end; x += step) {
            double currF = evaluate(expr, x);
            // Если значения на концах имеют разные знаки (или одно из них равно нулю)
            if (prevF * currF <= 0) {
                intervals.add(new Diapason(prevX, x));
            }
            prevX = x;
            prevF = currF;
        }

        // Уточнение корней на каждом найденном интервале
        for (Diapason interval : intervals) {
            double a = interval.a;
            double b = interval.b;
            double fa = evaluate(expr, a);
            double fb = evaluate(expr, b);

            // Проверка, что на концах действительно разные знаки (или один из них ноль)
            if (fa == 0) {
                roots.add(a);
                continue;
            }
            if (fb == 0) {
                roots.add(b);
                continue;
            }
            if (fa * fb > 0) {
                continue; // нет гарантии корня, пропускаем (на всякий случай)
            }

            // Метод бисекции
            double c;
            double fc;
            do {
                c = (a + b) / 2.0;
                fc = evaluate(expr, c);

                if (fc == 0) {
                    break;
                }
                // Сравниваем знак fc со знаком fa
                if (fa * fc > 0) {
                    a = c;      // корень слева от c, двигаем левую границу
                    fa = fc;
                } else {
                    b = c;      // корень справа от c, двигаем правую границу
                    fb = fc;
                }
            } while (Math.abs(b - a) > epsilon && Math.abs(fc) > epsilon);

            roots.add(c);
        }

        return roots;
    }

    // Вспомогательный метод для вычисления значения выражения в точке x
    private static double evaluate(String expr, double x) {
        return new ExpressionBuilder(expr)
                .variable("x")
                .build()
                .setVariable("x", x)
                .evaluate();
    }
}
