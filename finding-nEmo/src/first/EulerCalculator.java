package first;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class EulerCalculator implements Callable<BigDecimal> {
    private int start;
    private int end;

    public EulerCalculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public BigDecimal call() {
        long numerator = 3 - 4 * start * start;
        BigDecimal denominator = factorial(2 * start + 1);
        BigDecimal partialSum = BigDecimal.valueOf(numerator)
                .divide(denominator, 1000, RoundingMode.HALF_EVEN);
        for (int i = start + 1 ; i < end; i++) {
            numerator = 3 - 4 * i * i;
            denominator = denominator.multiply(BigDecimal.valueOf(2 * i * (2*i + 1)));
            partialSum = partialSum.add(BigDecimal.valueOf(numerator)
                    .divide(denominator, 1000, RoundingMode.HALF_EVEN));
        }

        return partialSum;
    }

    private BigDecimal factorial(int cur) {
        BigDecimal fact = BigDecimal.ONE;
        for (int i = 2; i <= cur; i++) {
            fact = fact.multiply(BigDecimal.valueOf(i));
        }

        return fact;
    }
}
