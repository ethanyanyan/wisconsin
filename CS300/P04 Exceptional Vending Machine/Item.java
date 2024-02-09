//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Vending Machine
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
 * This class models an item defined by its description and expiration date.
 * 
 * @author Ethan Yikai Yan
 */
public class Item {
    private String description ="";
    private int expirationDate;

    /**
     * Constructor for the Item object. Creates a new Item Object with a specific expiration 
     * date and description
     * 
     * @param description     a human readable description of this item
     * @param expirationDate  a positive integer starting at day 0, which represents Jan 1, 2023
     * @throws IllegalArgumentException  with a descriptive error message if expirationDate is
     *                                   negative (less than zero) or description is null or blank
     */
    public Item (String description, int expirationDate) {
        if (expirationDate < 0) {
            throw new IllegalArgumentException("Invalid expirationDate! expirationDate must be > " 
                    + "0");
        }
        if (description == null || description.equals("") || 
                description.trim().equals("")) {
            throw new IllegalArgumentException("Invalid description!");
        }
        this.description = description;
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the description of this item
     * 
     * @return the description of this item
     */
    public String getDescription () {
        return this.description;
    }

    /**
     * Changes the description of this item
     * 
     * @param description new description of the item
     * @throws IllegalArgumentException  with a descriptive error message if expirationDate is
     *                                   negative (less than zero) or description is null or blank
     */
    public void setDescription (String description) {
        if (description == null || description.equals("")) {
            throw new IllegalArgumentException("Invalid description!");
        }
        this.description = description;
    }

    /**
     * Gets the expiration date of this item
     * 
     * @return the expiration date of this item
     */
    public int getExpirationDate () {
        return this.expirationDate;
    }

    /** 
     * Returns a String representation of this item formatted as "description: expirationDate"
     * 
     * @return a String representation of this item
     */
    @Override
    public String toString() {
        return description + ": " + expirationDate;
    }

    /** 
     * Checks whether this item equals another object passed as input.
     * They are equal if other is a Item and their descriptions are the same.
     * 
     * @param other, another object to check if it is equal to this
     * @return true if other is instance of Item and has the same description as this item, 
     *         false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Item) {
            Item obj1 = ((Item) other);
            if (obj1.getDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }
}
