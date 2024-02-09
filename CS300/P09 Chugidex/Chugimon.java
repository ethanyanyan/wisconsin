//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Chugidex
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
 * This class models the Chugimon data type.
 */
public class Chugimon implements Comparable<Chugimon>{
    public static final int MIN_ID = ChugidexUtility.MIN_CHUGI_ID; // The minimum ID number
    public static final int MAX_ID = ChugidexUtility.MAX_CHUGI_ID; // The maximum ID number
    private final String NAME; // The name of the Chugimon
    private final int FIRST_ID; // The first ID of the Chugimon
    private final int SECOND_ID; // The second ID of the Chugimon
    private final ChugiType PRIMARY_TYPE; // The primary type of the Chugimon; cannot be null; 
                                          // cannot be the same as the secondary type
    private final ChugiType SECONDARY_TYPE; // The secondary type of the Chugimon; may be null; 
                                            // cannot be the same as the primary type
    private final double HEIGHT; // The height of the Chugimon in meters
    private final double WEIGHT; // The weight of the Chugimon in kilograms

    /**
     * Creates a new Chugimon with specific first and second IDs and initializes all its data 
     * fields accordingly.
     * @param firstID the first ID of the Chugimon, between 1-151
     * @param secondID the second ID of the Chugimon, between 1-151
     * @throws IllegalArgumentException if the first and second ID are out of bounds or 
     *                                  equal to each other.
     */
    public Chugimon(int firstID, int secondID) {
        if (firstID == secondID) {
            throw new IllegalArgumentException("firstID cannot equal secondID");
        }
        if (firstID < 1 || firstID > 151) {
            throw new IllegalArgumentException("Invalid firstID");
        }
        if (secondID < 1 || secondID > 151) {
            throw new IllegalArgumentException("Invalid secondID");
        }

        this.FIRST_ID = firstID;
        this.SECOND_ID = secondID;
        this.NAME = ChugidexUtility.getChugimonName(firstID, secondID);
        this.WEIGHT = ChugidexUtility.getChugimonWeight(firstID, secondID);
        this.HEIGHT = ChugidexUtility.getChugimonHeight(firstID, secondID);
        ChugiType[] typeArr;
        typeArr = ChugidexUtility.getChugimonTypes(firstID, secondID);
        this.PRIMARY_TYPE = typeArr[0];
        if (typeArr[1] == null) {
            this.SECONDARY_TYPE = null;
        } else if (typeArr[1] == typeArr[0]) {
            this.SECONDARY_TYPE = null;
        } else {
            this.SECONDARY_TYPE = typeArr[1];
        }
    }

    /**
     * Gets the name of this Chugimon
     * @return the name of the Chugimon
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Gets the first ID of this Chugimon
     * @return the first ID of the Chugimon
     */
    public int getFirstID() {
        return this.FIRST_ID;
    }

    /**
     * Gets the second ID of thid Chugimon
     * @return the second ID of the Chugimon
     */
    public int getSecondID() {
        return this.SECOND_ID;
    }

    /**
     * Gets the primary type of this Chugimon
     * @return the primary type of the Chugimon
     */
    public ChugiType getPrimaryType() {
        return this.PRIMARY_TYPE;
    }

    /**
     * Gets the secondary type of this Chugimon
     * @return the secondary type of the Chugimon
     */
    public ChugiType getSecondaryType() {
        return this.SECONDARY_TYPE;
    }

    /**
     * Gets the height of this Chugimon
     * @return the height of the Chugimon
     */
    public double getHeight() {
        return this.HEIGHT;
    }

    /**
     * Gets the the weight of the Chugimon.
     * @return the weight of the Chugimon.
     */
    public double getWeight() {
        return this.WEIGHT;
    }

    /**
     * Determines the ordering of Chugimon. Chugimon are ordered by: 1) name (alphabetical) 
     * 2) the first ID (if name is equal). The one with the smaller first ID is less than the other
     *  3) the second ID (if name and first ID are equal). The one with the smaller second ID is 
     * less than the other. A Chugimon with identical #1-3 are considered equal.
     * Specified by: compareTo in interface Comparable<Chugimon>
     * @param otherChugi the other Chugimon to compare this Chugimon to.
     * @return a negative int if this Chugimon is less than other, a positive int if this Chugimon 
     * is greater than other, or 0 if this and the other Chugimon are equal.
     */
    @Override
    public int compareTo(Chugimon otherChugi) {
        if (this.getName().equals(otherChugi.getName())) {
            if (this.getFirstID() < otherChugi.getFirstID()) {
                return -1;
            }
            if (this.getFirstID() > otherChugi.getFirstID()) {
                return 1;
            }
            if (this.getFirstID() == otherChugi.getFirstID()) {
                if (this.getSecondID() < otherChugi.getSecondID()) {
                    return -1;
                }
                if (this.getSecondID() > otherChugi.getSecondID()) {
                    return 1;
                }
                if (this.getSecondID() == otherChugi.getSecondID()) {
                    return 0;
                }
            }
        }
        // if both names are not equal, use compareTo to return value
        return this.getName().compareTo(otherChugi.getName()); 
    }

    /**
     * A Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID" -- 
     * Example: "Zapchu#145.25"
     * Overrides toString in class Object
     * @return a String representation of this Chugimon
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString = returnString + this.NAME + "#" + this.FIRST_ID + "." + this.SECOND_ID;
        return returnString;
    }

    /**
     * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon with 
     * the exact same name, and their both first and second IDs match.
     * Overrides equals in class Object
     * @param other Object to determine equality against this Chugimon
     * @return true if this Chugimon and other Object are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Chugimon)) {
            return false;
        }
        if (this.compareTo((Chugimon) other) == 0) {
            return true;
        }
        return false;
    }
}