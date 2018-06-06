import java.math.BigInteger;
/*
 * This class calculates the fibonacci series up to 
 * the number 40
 * Example from the book java how to program
 */
public class FibonacciSeries{
    // create a bigInteger variable from the number 2
    private static BigInteger TWO = BigInteger.valueOf(2); 
    /*
     * Returns the fibonacci of a given number
     */
    public static BigInteger fibonacci(BigInteger number){
        
        if(number.equals(BigInteger.ZERO)|| number.equals(BigInteger.ONE))
            return number;
        else
            return fibonacci(number.subtract(BigInteger.ONE)).add(fibonacci(number.subtract(TWO)));
    }

    public static void main(String[]args){
        for(int i = 0; i <= 40; i++){
            System.out.printf("Fibonacci of %d is: %d\n",i,fibonacci(BigInteger.valueOf(i)));
        }
    }
}	
