import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Future;

public class ExecuterClass {

	public static void main(String[] args) throws Exception {
		int scale = 1000;		
		SequenceCalculatorThread t1 = new SequenceCalculatorThread(0, 10, scale);
		BigDecimal result = t1.call();
		Thread.sleep(5000);
		System.out.println(result);
		System.out.println("AFTER");
		System.out.println(test(0,10,1000));
	}
	
	public static BigDecimal test(int startingIndex, int targetIndex, int scale){
		// Setup
		long numerator = 2 * startingIndex + 1;
		BigDecimal denominator =  calculateFactorial(2 * startingIndex);
		BigDecimal sum = BigDecimal.valueOf(numerator).divide(denominator, scale, RoundingMode.HALF_UP);
		for(int i = startingIndex+1; i <= targetIndex; i++) {
			numerator = 2*i + 1;
			denominator=denominator.multiply(BigDecimal.valueOf(2*i));
			sum=sum.add(BigDecimal.valueOf(numerator).divide(denominator, scale, RoundingMode.HALF_UP));
		}
		
		return sum;
	}
	
	private static BigDecimal calculateFactorial(int end) {
		BigDecimal result = BigDecimal.ONE;
		for(int i = 2; i <= end; i++) {
			result = result.multiply(BigDecimal.valueOf(i));
		}
		return result;
	}

}
