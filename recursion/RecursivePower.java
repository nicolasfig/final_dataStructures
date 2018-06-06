import java.util.Scanner;

class RecursivePower{
    public static int recursivePow(int num, int exp){
        if(exp == 1){
            return num;
        }else{
            return num * recursivePow(num,exp-1);
        }
    }
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = input.nextInt();
        System.out.println("Enter the exponent");
        int exp = input.nextInt();
        System.out.printf("%d to the power %d is %d\n",num,exp,recursivePow(num,exp));
    }
}	
