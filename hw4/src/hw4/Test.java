/*
 * Vy Truong
 * Homework 4
 * 04/25/2018
 *
 * The code presented in this file is entirely my own.
 */

/**
 * 
 * This program tests features of MyArrayList using lambda expressions
 * 
 */

package hw4;


public class Test {

    
    public static void main(String[] args) {

        int[] width = {9, 11, 23, 43, 101, 98, 101, 232, 55, 1000};
        int[] length = {6, 29, 31, 887, 8, 7, 8, 65, 5, 981};

        MyArrayList<Rectangle> arr = new MyArrayList<>();
        for (int i = 0; i < width.length; i++) {
            arr.add(new Rectangle(width[i], length[i]));
        }
        
        
        System.out.println("Display contents of the list ");
        // The input argument is an example of a lambda expression.
        arr.forEach( x -> System.out.println(x) );  
        
        /* Step 1: Insert a lambda expression that sorts 
           the list by length is ascending order
        */
        arr.sort( (r1, r2) -> r1.getLength() - r2.getLength() );
        System.out.println("\nDisplay contents of the list sorted by length ");
        arr.forEach( x -> System.out.println(x) );
        
        /* Step 2: Insert a lambda expression that sorts 
           the list by width is ascending order
        */
        arr.sort( (r1, r2) -> r1.getWidth() - r2.getWidth() );
        System.out.println("\nDisplay contents of the list sorted by width ");
        arr.forEach( x -> System.out.println(x) );

        MyArrayList<Rectangle> subList = arr.subList(1, 8);
        System.out.println("\nDisplay contents of the sublist (1 - 7) ");
        subList.forEach( x -> System.out.println(x) );
        
        /* Step 3: Insert a lambda expression that increases the width of
           each object in the list by 1.  Hint:  You need the Rectangle
           constructor, the getWidth, and the getLength methods.
        */
        subList.replaceAll( r -> new Rectangle(r.getWidth()+1, r.getLength()) );
        System.out.println("\nDisplay contents of the sublist after adding 1 to width");
        subList.forEach( x -> System.out.println(x) );
        
        /* Step 4: Insert a lambda expression that removes Rectangles
         whose length is a multiple of 4 + 1.  Example: remove a Rectangle with
         length = 9 since 9 = 2*4 + 1. 
         
        */
        subList.removeIf( r -> (r.getLength()-1)%4 == 0);
        System.out.println("\nDisplay contents of the sublist after removing " +
                             "lengths = multiples of 4 + 1");
        subList.forEach( x -> System.out.println(x) );

        /* Step 5: Insert a lambda expression that retains Rectangles
         whose width is in the open interval (40, 100).
        */
        subList.retainIf( r -> (r.getWidth() > 40 && r.getWidth() < 100) );
        System.out.println("\nDisplay contents of the sublist after removing " +
                             "widths >= 100 or widths <= 40");
        subList.forEach( x -> System.out.println(x) );
        
        try{
            subList = arr.subList(3, 2);
        }
        catch(IllegalArgumentException e){
            System.out.println("\nERROR: to_index < from_index");
        }
        try{
            subList = arr.subList(-1, 2);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("\nERROR: index error");
        }
        try{
            subList = arr.subList(0, arr.size() + 1);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("\nERROR: index error");
        }
        subList = arr.subList(0, 0);
        System.out.println("List size when from_index = to_index: " 
                          + subList.size());
    }

}
