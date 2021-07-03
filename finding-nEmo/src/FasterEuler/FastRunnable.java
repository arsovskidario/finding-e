package FasterEuler;

import FasterEuler.ApfloatFactorialCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FastRunnable implements Runnable {
    private int from;
    private int to;
    private int step;
    private BigDecimal[] results;
    private int resultsNumber;
    private ApfloatFactorialCalc calc;
    private int floatPrecision;
    private Boolean isQuiet;
    private BigDecimal currFactorial;

    public FastRunnable(int from, int to, BigDecimal currFactorial, BigDecimal[] results, int resultsNumber, int floatPrecision, Boolean isQuiet) {
        //System.out.println(String.format("Thread #%s from %s to %s step %s", resultsNumber, from, to, step));
        this.from = from;
        this.to = to;
        this.results = results;
        this.resultsNumber = resultsNumber;
        this.floatPrecision = floatPrecision;
        this.isQuiet = isQuiet;
        this.currFactorial = currFactorial;
    }

    @Override
    public void run() {
        results[resultsNumber] = BigDecimal.ZERO;

        for (int i = from; i < to; i++) {
            results[resultsNumber] = results[resultsNumber].add(new BigDecimal(2 * i + 1).divide(currFactorial, floatPrecision, RoundingMode.HALF_UP));
            currFactorial = currFactorial.multiply(new BigDecimal(from + i * 2 + 1)).multiply(new BigDecimal(from + i * 2 + 2));
        }
    }

}
