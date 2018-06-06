import java.util.Scanner;

class Palindromes{
    public static boolean pal(String text){
        // if the lenght is 0 or 1 then 
        // the string is a palindrome
        if(text.length()<=1){
            return true;
        }
        // check for first and last char in the string
        // if they are the same then do the same for a substring
        // with first and last char removed
        if(text.charAt(0) == text.charAt(text.length()-1)){
           return pal(text.substring(1,text.length()-1));
        }
        return false;
    }
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a string");
        String text = input.nextLine();
        System.out.println("Palindrome: " + pal(text) );
    }
}	
