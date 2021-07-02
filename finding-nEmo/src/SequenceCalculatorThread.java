import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class SequenceCalculatorThread implements Callable<BigDecimal> {
	
	private int startingIndex;
	private int targetIndex;
	private int scale;
	
	

	public SequenceCalculatorThread(int startingIndex, int targetIndex, int scale) {
		super();
		this.startingIndex = startingIndex;
		this.targetIndex = targetIndex;
		this.scale = scale;
	}



	@Override
	public BigDecimal call() throws Exception {
		// Setup
		long numerator = 2 * startingIndex + 1;
		BigDecimal denominator =  calculateFactorial(2 * startingIndex);
		BigDecimal sum = BigDecimal.valueOf(numerator).divide(denominator, scale, RoundingMode.HALF_UP);
		for(int i = startingIndex+1; i <= targetIndex; i++) {
			numerator = 2*i + 1;
			denominator = denominator.multiply(BigDecimal.valueOf(2*i));
			sum = sum.add(BigDecimal.valueOf(numerator).divide(denominator, scale, RoundingMode.HALF_UP));
		}
		
		return sum;
	}
	
	private BigDecimal calculateFactorial(int end) {
		BigDecimal result = BigDecimal.ONE;
		for(int i = 2; i <= end; i++) {
			result = result.multiply(BigDecimal.valueOf(i));
		}
		return result;
	}

}
