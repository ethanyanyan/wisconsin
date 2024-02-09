//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Quizzer
// Course:   CS 300 Fall 2022
//
// Author:   Ethan Yikai Yan
// Email:    eyyan@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NONE
// Partner Email:   NONE
// Partner Lecturer's Name: NONE
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An instance of this class represents a single node within a singly linked list.
 * 
 * @author Ethan Yikai Yan
 */
public class LinkedNode<T> {
    private T data; // data carried by this linked node
    private LinkedNode<T> next; // reference to the next linked node

    /**
     * Constructs a new node with the provided information.
     * @param data - to be stored within this node
     * @param next - next node, which comes after this node in a singly linked list
     * @throws NullPointerException - with a descriptive error message if data is null
     */
    public LinkedNode(T data, LinkedNode<T> next) {
        if (data == null) {
            throw new NullPointerException("data is null!");
        }
        this.data = data;
        this.next = next;
    }

    /**
     * Constructs a new node with a specific data and NO next node in the list.
     * @param data - to be stored within this node
     * @param next - next node, which comes after this node in a singly linked list
     * @throws NullPointerException - with a descriptive error message if data is null
     */
    public LinkedNode(T data) {
        if (data == null) {
            throw new NullPointerException("data is null!");
        }
        this.data = data;
        this.next = null;
    }

    /**
     * Accessor method for this node's next node reference.
     * @returns the next reference to the node that comes after this one in the list, 
     * or null when this is the last node in that list
     */
    public LinkedNode<T> getNext() {
        if (this.next == null) {
            return null;
        }
        return this.next;
    }

    /**
     * Mutator method for this node's next node reference.
     * @param next - node, which comes after this node in a doubly linked list
     */
    public void setNext(LinkedNode<T> next) {
        if (next == null) {
            this.next = null;
        }
        this.next = next;
    }

    /**
     * Accessor method for this node's data.
     * @returns the data stored within this node.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Returns a string representation of this linked node formatted as follows:
     * data.toString() if this node does NOT have a next node in the list
     * data.toString() + "->" if this node has a next node in the list
     * @returns a String representation of this node in the list
     */
    @Override
    public String toString() {
        if (this.next == null) {
            return data.toString();
        }
        return data.toString() + "->";
    }
}
