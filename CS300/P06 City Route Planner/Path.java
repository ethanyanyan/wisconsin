//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    City Route Planner
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
import java.util.NoSuchElementException;

/**
 * This Path class represents a valid path through a grid of city blocks surrounded by streets. 
 * A path consists of the list of intersection objects from one crossing point (intersection) 
 * to another.
 * 
 * @author Ethan Yikai Yan
 */
public class Path {
    private ArrayList<Intersection> intersections; // List of Intersections followed in this Path

    /**
     * Initializes this Path to start as empty
     */
    public Path() {
        intersections = new ArrayList<Intersection>();
    }

    /**
     * Returns the number of Intersections in this Path
     * 
     * @return the number of Intersections in this Path
     */
    public int length() {
        return intersections.size();
    }

    /**
     * Returns the first Intersection in this Path, if it is not empty. 
     * Otherwise, throws a NoSuchElementException.
     * 
     * @return                        the first Intersection in this Path, if it is not empty
     * @throws NoSuchElementException if this Path is empty
     */
    public Intersection getHead() {
        if (!intersections.isEmpty()) {
            return intersections.get(0);
        } else {
            throw new NoSuchElementException("Path is empty!");
        }
    }

    /**
     * Returns the last Intersection in this Path, if it is not empty. 
     * Otherwise, throws a NoSuchElementException.
     * 
     * @return                        the last Intersection in this Path, if it is not empty
     * @throws NoSuchElementException if this Path is empty
     */
    public Intersection getTail() {
        if (!intersections.isEmpty()) {
            return intersections.get(intersections.size() - 1);
        } else {
            throw new NoSuchElementException("Path is empty!");
        }
    }

    /**
     * Adds the given Intersection to the end of this Path if it is a valid addition. 
     * A Intersection is a valid addition if the current Path is empty, or the Intersection to 
     * add is one step directly east, or one step directly north of the current tail Intersection 
     * in this Path. Should throw an IllegalArgumentException if the given Intersection is not 
     * a valid addition.
     * 
     * @param toAdd                     Intersection to add to the end of this Path
     * @throws IllegalArgumentException if the Intersection to add is not valid
     */
    public void addTail(Intersection toAdd) {
        if (this.intersections.isEmpty()) {
            this.intersections.add(toAdd);
        } else {
            int toAddX = toAdd.getX();
            int toAddY = toAdd.getY();
            int tailX = this.getTail().getX();
            int tailY = this.getTail().getY();

            // Check if directly to east
            boolean isEast = (toAddX == tailX + 1) && (toAddY == tailY);
            // Check if directly to north
            boolean isNorth = (toAddY == tailY + 1) && (toAddX == tailX);
            if (isEast || isNorth) {
                this.intersections.add(toAdd);
            }
            if ((!isEast && !isNorth)) {
                throw new IllegalArgumentException("Not a valid addition!");
            }
        }
    }

    /**
     * Adds the given Intersection to the front of this Path if it is a valid addition. 
     * A Intersection is a valid addition if the current Path is empty, or the Intersection to 
     * add is one step directly west, or one step directly south of the current head Intersection 
     * in this Path. Should throw an IllegalArgumentException if the given Intersection is not 
     * a valid addition.
     * 
     * @param toAdd                     Intersection to add to the beginning of this Path
     * @throws IllegalArgumentException if the Intersection to add is not valid
     */
    public void addHead(Intersection toAdd) {
        if (this.intersections.isEmpty()) {
            this.intersections.add(toAdd);
        } else {
            int toAddX = toAdd.getX();
            int toAddY = toAdd.getY();
            int tailX = this.getHead().getX();
            int tailY = this.getHead().getY();

            // Check if directly to west
            boolean isWest = ((toAddX == (tailX - 1)) && (toAddY == tailY));
            // Check if directly to north
            boolean isSouth = ((toAddY == (tailY - 1)) && (toAddX == tailX));
            if (isWest || isSouth) {
                this.intersections.add(0, toAdd);
            } 
            if ((!isWest && !isSouth)) {
                throw new IllegalArgumentException("Not a valid addition!");
            }
        }
    }

    /**
     * Returns a String representing the coordinates taken in this Path. 
     * An empty Path should return the String "Empty", while a non-empty Path should return 
     * the coordinates of the Intersections it visits separated by a "->". For example:
     * (0,0)->(1,0)->(1,1)->(1,2)
     * 
     * @return a String representing the coordinates followed by this Path
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < intersections.size() - 1; i++) {
            returnString = returnString + intersections.get(i).toString() + "->";
        }
        returnString = returnString + intersections.get(intersections.size() - 1).toString();
        return returnString;
    }
}

