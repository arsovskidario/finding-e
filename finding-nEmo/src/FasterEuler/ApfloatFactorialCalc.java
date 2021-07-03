package FasterEuler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ApfloatFactorialCalc {
    private final int STEP = 10;
    private final BigDecimal APFLOAT_ZERO = BigDecimal.ZERO;
    private List<BigDecimal> values;

    public ApfloatFactorialCalc(int maxSize) {
        this.values = Collections.synchronizedList(new ArrayList<BigDecimal>(maxSize));

        // adding 0!
        this.values.add(new BigDecimal(1));

        // adding 1!
        this.values.add(new BigDecimal(1));
    }

    public BigDecimal getFact(int n) {
        if(n == 0 || n == 1) {
            return new BigDecimal(1L);
        }
        resize(n);
        if(n % STEP == 0) {
            if(values.get(n).equals(APFLOAT_ZERO)) {
                values.set(n, new BigDecimal(n).multiply(getFact(n-1)));
            }
            return values.get(n);
        } else {
            int iters = n % STEP;
            BigDecimal result = getFact(n - iters);
            for(int i = iters; i > 0; i--) {
                result = result.multiply(new BigDecimal(i));
            }
            return result;
        }
    }

    private void resize(int n) {
        while(this.values.size() <= n + 1) {
            this.values.add(APFLOAT_ZERO);
        }
    }
}
