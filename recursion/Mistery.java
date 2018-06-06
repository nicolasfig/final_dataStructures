public class Mistery{
    public static int mistery(int a, int b){
        if(b == 1)
            return a;
        else
            return a+mistery(a,b-1);
    }
    public static void main(String[]args){
        for(int i = 1; i< 10; i++){
            for(int j = 1; j < 10; j++){
                System.out.printf("\na = %d b = %d mistery = %d",i,j,mistery(i,j));
            }
        }
    }
}
