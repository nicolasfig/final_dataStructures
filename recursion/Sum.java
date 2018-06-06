class Sum{
    public static int sum(int num){
        if(num == 0)
            return 0;
        else
            return num + sum(num - 1);
    }
    public static void main(String[]args){
        for(int i = 0; i <= 10; i++){
            System.out.printf("num = %d  sum = %d\n",i,sum(i));
        }
    }
}	
