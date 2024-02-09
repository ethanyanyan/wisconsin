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
 * This Room class represents a room in the cave and implement some of their basic functionalities.
 * Rooms keep track of a couple of pieces of information like their unique ID, a description, 
 * type and a list of rooms adjacent to it.
 * 
 * @author Ethan Yikai Yan
 */
public class Room {
    private RoomType type; //one of the four types a room could be
    private String roomDescription; //a brief description of the room
    private ArrayList<Room> adjRooms = new ArrayList<>(); //arraylist that holds the rooms adjacent
    private final int ID; //unique ID for each room to identify it
    private static int teleportLocationID; //place where all portal rooms will go to
    private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
    private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";

    /** Determines if the given object is equal to this room.
    * They are equal if other is a Room and their IDs are the same.
    * @param other, another object to check if it is equal to this
    * @return true if the two rooms are equal, false otherwise
    * @author Michelle */
    @Override
    public boolean equals(Object other){
        if(other instanceof Room) {
            Room otherRoom = (Room)other;
            return this.ID == otherRoom.ID;
        }
        return false;
    }
    /** Returns a String representation of this room.
    * @return the string representation of this room and
    * it’s object data field values
    * @author Michelle*/
    @Override
    public String toString(){
        String s = this.ID +": " + this.roomDescription+ " (" + type +")\n Adjacent Rooms: ";
        for(int i = 0; i<adjRooms.size(); i++){
            s+= adjRooms.get(i).ID +" ";
    }
    return s;
    }
    
    /**
     * Constructor for the room object. Assigns values to the non-static fields. 
     * The default type should be RoomType.NORMAL
     * 
     * @param id               the unique id number for this room
     * @param roomDescription  a brief description of this room
     */
    public Room(int id, String roomDescription) {
        this.ID = id;
        this.roomDescription = roomDescription;
        this.type = RoomType.NORMAL;
        adjRooms = new ArrayList<Room>();
    }

    /**
     * Getter for the adjRooms instance field
     * 
     * @return this object's list of rooms adjacent to it
     */
    public void addToAdjacentRooms(Room toAdd) {
        this.adjRooms.add(toAdd);
    }

    /**
     * Getter for the id instance field
     * 
     * @return this object's id
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Getter for the description instance field
     * 
     * @return this object's description
     */
    public String getRoomDescription() {
        return this.roomDescription;
    }

    /**
     * Getter for the type instance field
     * 
     * @return this object's roomtype
     */
    public RoomType getType() {
        return this.type;
    }

    /**
     * Changes this objects type to the roomtype given
     * 
     * @param newType   The new roomtype of this Room object
     */
    public void setRoomType(RoomType newType) {
        this.type = newType;
    }

    /**
     * Checks whether this given room is adjacent to this one or not
     * 
     * @param r   The room that you are seeing if it is adjacent to this
     * @return true if they are adjacent, false otherwise
     */
    public boolean isAdjacent(Room r) {
        if(this.adjRooms.contains(r)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for the adjRooms instance field
     * 
     * @return this object's list of rooms adjacent to it
     */
    public ArrayList<Room> getAdjacentRooms() {
        return this.adjRooms;
    }

    /**
     * Sets the class field teleportLocation to the int given
     * 
     * @param newType   the id of the room where all portals should teleport to
     */
    public static void assignTeleportLocation(int teleportID) {
        teleportLocationID = teleportID;
    }

    /**
     * Returns the string that is the room class's portal warning, 
     * indicating that there is one nearby
     * 
     * @return The portal warning message string
     */
    public static String getPortalWarning() {
        return PORTAL_WARNING;
    }

    /**
     * Returns the string that is the room class's treasure warning, 
     * indicating that the treasure room is nearby
     * 
     * @return The treasure warning message string
     */
    public static String getTreasureWarning() {
        return TREASURE_WARNING;
    }

    /**
     * Returns the id of the room where all portals will teleport to
     * 
     * @return the id of the teleportLocation room
     */
    public static int getTeleportationRoom() {
        return teleportLocationID;
    }
}
