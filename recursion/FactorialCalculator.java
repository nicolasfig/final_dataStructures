class FactorialCalculator{
    public static long factorial(long number){
        if(number <= 1){
            return 1; 
        }else{
            return number * factorial(number - 1); 
        } 
    }
    public static void main(String[]args){
        for(int i = 0; i < 21; i++){
            System.out.printf("local = %d\trecursive param = %d\nfactorial = %d\n",i,(i-1),factorial(i));
        }    
    }
}	
