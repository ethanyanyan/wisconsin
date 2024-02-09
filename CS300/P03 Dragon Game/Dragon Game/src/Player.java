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

import java.util.ArrayList;

/**
 * This Player class represents the character that the player of the game controls.
 * This class will be responsible for keeping track of the current player 
 * location, moving the player between rooms, and determining if the player is near a room that 
 * has a special property (e.g. contains a dragon).
 * 
 * @author Ethan Yikai Yan
 */
public class Player {
    private Room currentRoom;
    
    /**
     * Constructor for the Player class when creating a new Player object
     * 
     * @param currentRoom  the current location of the player
     */
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Determines whether or not the player can move to the given destination room
     * 
     * @param destination  room the player wants to move to
     * @return  true if it is a valid movement, false otherwise
     */
    public boolean canMoveTo(Room destination) {
        if(this.currentRoom.isAdjacent(destination)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setter for this player's current room
     * 
     * @param newRoom  the location that the palyer is moving to
     */
    public void changeRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    /**
     * Gets the list of rooms adjacent to where the player is currently at
     * 
     * @return  list of rooms adjacent to current room
     */
    public ArrayList<Room> getAdjacentRoomsToPlayer() {
        return this.currentRoom.getAdjacentRooms();
    }

    /**
     * Getter for this player's current room
     * 
     * @return  current location of the player
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Determines whether or not the given dragon is in a nearby room
     * 
     * @param d the dragon to check if nearby
     * @return  true if the dragon is nearby, false otherwise
     */
    public boolean isDragonNearby(Dragon d) {
        if(this.currentRoom.isAdjacent(d.getCurrentRoom())) {
            return true;
        }
        return false;
    }

    /**
     * Determines whether or not a portal room is in a nearby room
     * 
     * @return  true if a portal room is nearby, false otherwise
     */
    public boolean isPortalNearby() {
        for(int i = 0; i < this.currentRoom.getAdjacentRooms().size(); i++) {
            if (this.currentRoom.getAdjacentRooms().get(i).getType() == RoomType.PORTAL) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether or not the treasure room is in a nearby room
     * 
     * @return  true if the treasure room is nearby, false otherwise
     */
    public boolean isTreasureNearby() {
        for(int i = 0; i < this.currentRoom.getAdjacentRooms().size(); i++) {
            if (this.currentRoom.getAdjacentRooms().get(i).getType() == RoomType.TREASURE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether or not the player needs to teleport. 
     * Players teleport when their current room is of type PORTAL
     * 
     * @return  true if they should teleport, false otherwise
     */
    public boolean shouldTeleport() {
        if(this.currentRoom.getType() == RoomType.PORTAL) {
            return true;
        }
        return false;
    }
}
