//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Music Player 300
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
 * This class represents a singly-linked node for our linked queue, which contains a Song object
 * 
 * @author Ethan Yikai Yan
 */
public class SongNode {
    private Song song; // The Song object in this node
    private SongNode next; // The next SongNode in this queue

    /**
     * Constructs a single SongNode containing the given data, not linked to any other SongNodes
     * @param data the Song for this node
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data is null!");
        }
        this.song = data;
        this.next = null;
    }

    /**
     * Constructs a single SongNode containing the given data, linked to the specified SongNode
     * @param data the Song for this node
     * @param next the next node in the queue
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data, SongNode next) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("data is null!");
        }
        this.song = data;
        this.next = next;
    }

    /**
     * Accessor method for this node's data
     * @return the Song in this node
     */
    public Song getSong() {
        return this.song;
    }

    /**
     * Accessor method for the next node in the queue
     * @return the SongNode following this one, if any
     */
    public SongNode getNext() {
        if (this.next == null) {
            return null;
        }
        return this.next;
    }

    /**
     * Changes the value of this SongNode's next data field to the given value
     * @param next next - the SongNode to follow this one; may be null
     */
    public void setNext(SongNode next) {
        if (next == null) {
            this.next = null;
        }
        this.next = next;
    }
}
