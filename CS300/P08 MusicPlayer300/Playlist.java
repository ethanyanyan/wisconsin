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
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 * 
 * @author Ethan Yikai Yan
 */
public class Playlist implements QueueADT<Song> {
    private SongNode first; // The current first song in the queue; the next one up to play 
                            // (front of the queue)
    private SongNode last; // The current last song in the queue; the most-recently added one 
                           // (back of the queue)
    private int numSongs; // The number of songs currently in the queue

    /**
     * Constructs a new, empty playlist queue
     */
    public Playlist() {
        this.first = null;
        this.last = null;
        this.numSongs = 0;
    }

    /**
     * Adds a new song to the end of the queue
     * Specified by: enqueue in interface QueueADT<Song>
     * @param element the song to add to the Playlist
     */
    public void enqueue(Song element) {
        SongNode newNode = new SongNode(element);
        if (this.numSongs == 0) {
            this.first = newNode;
            this.last = newNode;
            numSongs++;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
            numSongs++;
        }
    }

    /**
     * Removes the song from the beginning of the queue
     * Specified by: dequeue in interface QueueADT<Song>
     * @return the song that was removed from the queue, or null if the queue is empty
     */
    public Song dequeue() {
        Song returnSong = null;
        if (numSongs == 0) {
            return null;
        }
        if (numSongs == 1) {
            returnSong = this.first.getSong();
            this.first = null;
            this.last = null;
            numSongs--;
            return returnSong;
        }
        if (numSongs > 1) {
            returnSong = this.first.getSong();
            this.first = this.first.getNext();
            numSongs--;
            return returnSong;
        }
        return returnSong;
    }

    /**
     * Returns the song at the front of the queue without removing it
     * Specified by: peek in interface QueueADT<Song>
     * @return the song that is at the front of the queue, or null if the queue is empty
     */
    public Song peek() {
        Song returnSong = null;
        if (numSongs == 0) {
            return null;
        }
        if (numSongs > 0) {
            returnSong = this.first.getSong();
            return returnSong;
        }
        return returnSong;
    }

    /**
     * Returns true if and only if there are no songs in this queue
     * Specified by: isEmpty in interface QueueADT<Song>
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.numSongs == 0) {
            return true;
        } 
        return false;
    }

    /**
     * Returns the number of songs in this queue
     * Specified by: size in interface QueueADT<Song>
     * @return the number of songs in this queue
     */
    public int size() {
        return this.numSongs;
    }

    /**
     * Creates and returns a formatted string representation of this playlist, with the string 
     * version of each song in the list on a separate line. For example:
     * "He's A Pirate" (1:21) by Klaus Badelt
     * "Africa" (4:16) by Toto
     * "Waterloo" (2:45) by ABBA
     * "All I Want For Christmas Is You" (4:10) by Mariah Carey
     * "Sandstorm" (5:41) by Darude
     * "Never Gonna Give You Up" (3:40) by Rick Astley
     * Specified by: toString in interface QueueADT<Song>
     * @return the string representation of this playlist
     */
    @Override
    public String toString() {
        String returnString = "";
        if (this.first == null) {
            return returnString;
        }
        if (this.first.getNext() == null) {
            return returnString + first.getSong().toString();
        }
        while (this.first.getNext() != null) {
            returnString = returnString + first.getSong().toString() + "\n";
            first = first.getNext();
        }
        returnString = returnString + first.getSong().toString();
        return returnString;
    }
}
