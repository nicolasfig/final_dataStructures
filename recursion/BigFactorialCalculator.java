import java.math.BigInteger;

class BigFactorialCalculator{
   public static BigInteger factorial(BigInteger number){
        if(number.compareTo(BigInteger.ONE) <= 0){
            return BigInteger.ONE;
        }
        else{
            return number.multiply(factorial(number.subtract(BigInteger.ONE)));
        }
   } 
   public static void main(String[]args){
       for(int i = 0; i <= 50; i++){
            System.out.printf("%d! = %d\n",i,factorial(BigInteger.valueOf(i)));
       }
   }
}	
