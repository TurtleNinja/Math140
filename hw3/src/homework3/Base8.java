/*
 * Vy Truong
 * Homework 3
 * 03/23/2018
 *
 * The code presented in this file is entirely my own.
 */

/**
 * 
 * The program calculates the sum of two octal numbers. If the sum is more
 * than 30 digit long, state 'overflow', otherwise display the result.
 * After displaying the result, the program prompts user input
 * of continuing with another pairs, or stopping. 
 * 
 */
package homework3;

import java.util.Scanner;
        
public class Base8 {
    
    public static void main( String[] args ){
        boolean stop = false;
        Scanner s = new Scanner(System.in);
        
        while(!stop) {
            //get user inputs
            System.out.print("First number: ");
            String firstNum = s.nextLine();
            System.out.print("Second number: ");
            String secondNum = s.nextLine();
            
            //calculate the sum
            int sum[] = add( firstNum, secondNum );
            
            //display the result
            System.out.print( firstNum + " + " + secondNum );
            if( sum.length > 30 )
                System.out.println(": overflow");
            else{
                System.out.print(" = ");
                for( int i = sum.length-1; i >= 0; i--)
                    System.out.print(sum[i]);
            }
            //prompt for more calculations
            System.out.println();
            System.out.println();
            System.out.print("Do you want to continue (y or n)? ");            
            stop = !isContinue( s.nextLine() );
            System.out.println();
        }
        
        
    }
    
    /*
        Convert two strings into array of digits. Add corresponding digits
        Return an array containing the digits of the result
    */
    public static int[] add( String n1, String n2 ){
        
        int length;
        if( n1.length() > n2.length() )
            length = n1.length();
        else
            length = n2.length();
        
        int[] num1= new int[length];
        int[] num2= new int[length];
        int[] sum = new int[length];
        int carry = 0;
        
        //convert numbers into arrays of digits
        for(int i = 0; i < n1.length(); i++)
            num1[i] = n1.charAt(n1.length()-1 - i) - '0';
        for(int i = 0; i < n2.length(); i++)
            num2[i] = n2.charAt(n2.length()-1 - i) - '0';
        
        //calculate the sum
        for(int i = 0; i < length; i++){
            sum[i] = (num1[i] + num2[i] + carry) % 8;
            carry = (num1[i] + num2[i] + carry) / 8;
        }
        
        //in case the sum has more digits than the numbers, increase the size of the sum array
        if(carry != 0){
            int[] temp = new int[length + 1];
            System.arraycopy(sum, 0, temp, 0, length);
            temp[length] = carry;
            sum = temp;
        }
        
        return sum;
    }
    
    /*
        Check if user want to do another addition
        Return true if the user types 'y' or 'Y'. Otherwise, return false.
    */
    public static boolean isContinue( String reply ){
        return reply.compareToIgnoreCase("y") == 0;
    }
     
}
