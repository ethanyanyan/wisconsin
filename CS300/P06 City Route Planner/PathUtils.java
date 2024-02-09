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

/**
 * This PathUtils class represents Utility methods for planning a trip through the streets 
 * of an urban grid city.
 * 
 * @author Ethan Yikai Yan
 */
public class PathUtils {
    /**
     * Finds the number of valid Paths between the given start and end Intersections. If it is not 
     * possible to get from the start to the end intersection by moving up or right, then 0 should 
     * be returned. For example, if start is Intersection(0, 0) and end is Intersection(2, 1), 
     * this method should return 3. If start is Intersection(1, 0) and end is Intersection(0, 0), 
     * this method should return 0. MUST be implemented recursively. If you wish, you can use a 
     * call to a private static helper method which is recursive.
     * 
     * @param start Intersection to start at
     * @param end   Intersection to end at
     * @return      the number of valid Paths which start and end at the given Intersections
     */
    public static int countPaths(Intersection start, Intersection end) {
        int count = 0;
        // Base cases
        if (start.getX() > end.getX() || start.getY() > end.getY()) {
            return 0;
        }
        if (start.equals(end)) {
            return 1;
        }

        // Check if X or Y coordinate is the same.
        if (start.getX() == end.getX() && start.getY() < end.getY()) {
            return 1;
        }
        if (start.getX() < end.getX() && start.getY() == end.getY()) {
            return 1;
        }

        if (start.getX() < end.getX() && start.getY() < end.getY()) {
            return countPaths(start.goNorth(), end) + countPaths(start.goEast(), end);
        }
        return count;
    }

    /**
     * Finds all valid Paths between the given start and end Intersections. If it is not possible 
     * to get from the start to the end intersection by moving up or right, then an empty 
     * ArrayList should be returned.
     * For example, if start is Intersection(0, 0) and end is Intersection(2, 1), this method 
     * should return an ArrayList consisting of the following Paths:
     * (0,0)->(1,0)->(2,0)->(2,1)
     * (0,0)->(1,0)->(1,1)->(2,1)
     * (0,0)->(0,1)->(1,1)->(2,1)
     * If start is Intersection(1, 0) and end is Intersection(0, 0), this method should return an 
     * empty ArrayList. MUST be implemented recursively. If you wish, you can use a call to a 
     * private static helper method which is recursive.
     * 
     * @param start Intersection to start at
     * @param end   Intersection to end at
     * @return      an ArrayList containing all valid Paths which start and end at the given 
     *              Intersections
     */
    public static ArrayList<Path> findAllPaths(Intersection start,Intersection end) {
        ArrayList<Path> allPaths = new ArrayList<Path>();
        // Base cases
        if (start.getX() > end.getX() || start.getY() > end.getY()) {
            return allPaths;
        }
        if (start.equals(end)) {
            Path currentPath = new Path();
            currentPath.addTail(start);
            allPaths.add(currentPath);
            return allPaths;
        }

        // Check if X or Y coordinate is the same.
        if (start.getX() == end.getX() && start.getY() < end.getY()) {
            if (allPaths.isEmpty()) {
                allPaths.addAll(findAllPaths(start.goNorth(), end));
                allPaths.get(allPaths.size()-1).addHead(start);
            }
            return allPaths;
        }
        if (start.getX() < end.getX() && start.getY() == end.getY()) {
            if (allPaths.isEmpty()) {
                allPaths.addAll(findAllPaths(start.goEast(), end));
                allPaths.get(allPaths.size()-1).addHead(start);
            }
            return allPaths;
        }

        if (start.getX() < end.getX() && start.getY() < end.getY()) {
                ArrayList<Path> returnedPaths = findAllPaths(start.goNorth(), end);
                for (int i = 0; i < returnedPaths.size(); i ++) {
                    if (!(returnedPaths.get(i)).getHead().equals(start)) {
                        returnedPaths.get(i).addHead(start);
                    }
                }
                allPaths.addAll(returnedPaths);
            
                ArrayList<Path> returnedPaths2 = findAllPaths(start.goEast(), end);
                for (int i = 0; i < returnedPaths2.size(); i ++) {
                    if (!(returnedPaths2.get(i)).getHead().equals(start)) {
                        returnedPaths2.get(i).addHead(start);
                    }
                }
                allPaths.addAll(returnedPaths2);                
        }
        return allPaths;
    }
}
