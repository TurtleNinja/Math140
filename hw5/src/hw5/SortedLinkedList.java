/*
 * Vy Truong
 * Homework 4
 * 05/14/2018
 *
 * The code presented in this file is entirely my own.
 */

/**
 * 
 * The program features operations with a sorted linked list
 *  inserts new items in specified order
 *  remove requested items
 *  produces lists of items less than or greater than a specific value
 * 
 */

package hw5;
        
import java.util.Comparator;
import java.util.Iterator;

public class SortedLinkedList<E> implements Iterable<E>{

    private Node head;
    private Node last;
    private int size;
    private Comparator<E> c;

    private class Node {

        private E item;
        private Node next;
        private Node prev;

        private Node(E item) {
            this.item = item;
        }
    }
    
    
    //constructor
    public SortedLinkedList(Comparator<E> c){
        this.c = c;  
    }
    
    /*
        Insert a new node to the list in sorted order
        Return true after adding
    */
    public boolean add(E item) {
        Node node = new Node(item);
        if (size == 0) {
            head = last = node;
        }
        else{
            Node ptr = head;    
            while (ptr != null && c.compare(ptr.item, item) < 0) {
                ptr = ptr.next;
            }

            //add item
            if (ptr == head){   //add the new node at the begining of the list
                node.next = head;
                head.prev = node;
                head = node;
            }
            else if (ptr == last.next) {   //at the new node at the end of the list
                last.next = node;
                node.prev = last;
                last = node;
            }
            else {          //add the new node in the middle of the list
                node.next = ptr;
                node.prev = ptr.prev;
                ptr.prev.next = node;
                ptr.prev = node;
            }
        }
        size++;
        
        return true;
    }
    
    /*
        Returns a SortedLinkedList that contains items less than the parameter
    */
    public SortedLinkedList<E> lessThan(E item){
        SortedLinkedList<E> temp = new SortedLinkedList(c);
        Node ptr = head;
        
        for (int i = 0; i < size; i++) {
            if (c.compare(ptr.item, item) < 0)
                temp.add(ptr.item);
            ptr = ptr.next;        
        }
        return temp;
    }
    
    /*
        Returns a SortedLinkedList that contains items greater than the parameter
    */
    public SortedLinkedList<E> greaterThan(E item){
        SortedLinkedList<E> temp = new SortedLinkedList(c);
        Node ptr = head;
        
        for (int i = 0; i < size; i++) {
            if (c.compare(ptr.item, item) > 0)
                temp.add(ptr.item);
            
            ptr = ptr.next;
                
        }
        return temp;
    }
    
    public int size(){
        return size;
    }
    
    public E remove(int i){
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        E tmp = null;
        if (i==0){
            tmp = head.item;
            if (size == 1){
                head = last = null;
            }
            else{
                head = head.next;
                head.prev = null;
            }
        }
        else if (i == size  - 1){
            tmp = last.item;
            last = last.prev;
            last.next = null;
        }
        else{
            Node ptr = head;
            for(int index = 0; index < i; index++){
                ptr = ptr.next;
            }
            tmp = ptr.item;
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
        }
        size--;
        return tmp;
    }

    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node ptr = head;
        for (int index = 0; index < i; index++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    
    
    public Iterator<E> iterator(){
        return new SortedLinkedListIterator();
    }
    
    
    
    private class SortedLinkedListIterator implements Iterator<E>{
        Node ptr = head;
        
        public boolean hasNext(){
            return ptr != null;
        }
        
        public E next(){
            E tmp = ptr.item;
            ptr = ptr.next;
            return tmp;
        }
    }

}
