/*
 * Vy Truong
 * Homework 4
 * 04/25/2018
 *
 * The code presented in this file is entirely my own.
 */

/**
 * 
 * The program replicates a modified version of ArrayList.
 * The features I added to this version of MyArrayList includes:
 *  retainIf
 *  removeIf
 *  forEach
 *  replaceAll
 *  sort
 *  subList
 * 
 */


package hw4;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*; //Customer, Predicate, Function

public class MyArrayList<E> implements Iterable<E> {

    private E[] list;
    private int size;
    
    public MyArrayList() {
        list = (E[]) new Object[10];
    }
    
    /*
        Add an element to a designated position
        Increase the size of the list by 1
    */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == list.length) {
            increaseCapacity();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = e;
        size++;
    }
    
    /*
        Double the capacity of the list
    */
    private void increaseCapacity() {
        E[] newList = (E[]) new Object[2 * list.length];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    /*
        Get the element at a certain index
        Return the element wanted
    */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    /*
        Return the size of the list
    */
    public int size() {
        return size;
    }
    
    /*
        Add an element to the end of the list
        Increase the size of the list by 1
    */
    public boolean add(E e) {
        if (size == list.length) {
            increaseCapacity();
        }
        list[size++] = e;
        return true;
    }

    /*
        Check if an element is contained in the list
        Return true if the element is in the list. Otherwise, return false
    */
    public boolean contains(Object o) {
        boolean flag = false; // assume object is not in the list
        E temp = (E) o;
        int index = 0;
        while (index < size && !flag) {
            flag = equals(o, list[index++]);  // o = target, list[index] = element
        }
        return flag;
    }

    /*
        Set a new object to designated position
        Return the former element
    */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E tmp = list[index];
        list[index] = e;
        return tmp;
    }
    
    /*
        Remove the element at a certain position
        Return the removed element
    */
    public E remove(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E temp = list[index];
        System.arraycopy(list, index + 1, list, index, size - index + 1);
        return temp;

    }

    /* 
        Compare two objects
        Returns true if target equals element
    */
    private boolean equals(Object target, Object element) {
        boolean flag = false;
        if (target == null) {
            flag = element == null;
        } else {  // target isn't null
            flag = target.equals(element); // element.equals(target) can lead to runtime error
        }
        return flag;
    }

    // Code for the iterator
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;   // stop iterating when index = size
        }

        @Override
        public E next() {
            return list[index++];  // return current item; advance index
        }

    }
    
    /*
        Retain all elements of the list that satisfy the filter
        Return true if at least one element is removed. Otherwise, return false
    */
    public boolean retainIf(Predicate<E> filter) {
        boolean removed = false;
        
        int i = 0;
        
        while( i < size )
            if( !filter.test(list[i]) ) {          
                System.arraycopy(list, i+1, list, i, size - i - 1);
                size--;
                removed = true;
            }
            else
                i++;
        
        return removed;
    }
    
    /*
        Remove all elements in the list that satisfy filter
        Return true if at least one element is removed. Otherwise, return false
    */
    public boolean removeIf(Predicate<E> filter) {
        boolean removed = false;
        
        int i = 0;
        while( i < size )
            if( filter.test(list[i]) ) {        
                System.arraycopy(list, i+1, list, i, size - i - 1);
                size--;
                removed = true;  
            }
            else
                i++;
        
        return removed;
    }
    
    /*
        Perform action to each element of the list
    */
    @Override
    public void forEach(Consumer<? super E> action) {
        for( int i = 0; i < size; i++ ){
            action.accept( list[i] );
        }
    }
    
    /*
        Replace all the elements of the list with the new calculated elements
    */
    public void replaceAll(Function<E, E> operator) {
        for( int i = 0; i < size; i++ )
            list[i] = operator.apply( list[i] );
    }
    
    /*
        Sort the list in order specified by Comparator s
    */
    public void sort(Comparator<E> c) {
        E[] temp = (E[]) new Object[size];
        System.arraycopy(list, 0, temp, 0, size);
        
        Arrays.sort(temp, c);
        list = temp;
    }
    
    /*
        Create a sublist of the array from fromIndex to toIndex
    */
    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        if( fromIndex < 0 || toIndex >= size )
            throw new IndexOutOfBoundsException();
        
        if( fromIndex > toIndex )
            throw new IllegalArgumentException();
        
        MyArrayList<E> subList = new MyArrayList<>();
        for( int i = fromIndex; i <= toIndex; i++ )    
            subList.add( list[i] );
        
        
        return subList;
    }
}
