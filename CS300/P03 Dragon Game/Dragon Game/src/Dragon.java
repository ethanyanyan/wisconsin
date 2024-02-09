//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Adventure
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

import java.util.Random;

/**
 * This Dragon class represents the dragon lurking in the caves, protecting the treasure, in the game.
 * This class will be responsible for keeping track of the dragon’s current location and 
 * picking a room to move to and then moving to it. 
 * 
 * @author Ethan Yikai Yan
 */
public class Dragon {
    private Room currentRoom; //current location of the dragon
    private Random randGen; //random num generator used for moving
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";

    /**
     * Constructor for the Dragon class when creating a new Dragon object
     * 
     * @param currentRoom   the current location of the dragon
     */
    public Dragon(Room currentRoom) {
        this.currentRoom = currentRoom;
        randGen = new Random();
    }

    /**
     * A getter method to obtain the current room of the dragon
     * 
     * @return the current location of the dragon of type Room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * A getter method to obtain the String for the Dragon Warning
     * 
     * @return the String of the Dragon Warning message
     */
    public static String getDragonWarning() {
        return DRAGON_WARNING;
    }

    /**
     * A method to change the room the dragon is in at random. The Dragon can only move to an 
     * adjacent room that is not of PORTAL RoomType
     * 
     */
    public void changeRooms() {
        int numOfAdjRooms = currentRoom.getAdjacentRooms().size();
        int newRoom = this.randGen.nextInt(numOfAdjRooms); // Select random adjacent room

        // Keep selecting a random room until RoomType is not of PORTAL RoomType
        while(this.currentRoom.getAdjacentRooms().get(newRoom).getType() == RoomType.PORTAL) {
            newRoom = randGen.nextInt(numOfAdjRooms);
        }
        this.currentRoom = currentRoom.getAdjacentRooms().get(newRoom);
    }
}
