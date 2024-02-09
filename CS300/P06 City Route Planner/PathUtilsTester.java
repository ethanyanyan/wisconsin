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
import java.util.Scanner;

/**
 * This PathUtilsTester class is the Tester class for PathUtils.
 * 
 * @author Ethan Yikai Yan
 */
public class PathUtilsTester {
    /**
     * Tests the case of countPaths() when there are no valid Paths. For example, when the start 
     * position is Intersection(1, 1) and the ending position is Intersection(0, 1), there should 
     * be no valid Paths, so countPaths() should return 0.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testCountPathsNoPath() {
        // Test case when end is west of start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(0, 1)) != 0) {
            return false;
        }
        // Test case when end is south of start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(1, 0)) != 0) {
            return false;
        }
        // Test case when end is south-west of start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(0, 0)) != 0) {
            return false;
        }
        return true;
    }

    /**
     * Tests the case of countPaths() when there is a single valid Path. For example, when the 
     * start position is Intersection(1, 1) and the ending position is Intersection(1, 2), there 
     * should be a single Path, so countPaths() should return 1.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testCountPathsOnePath() {
        // Test case when end is directly east of start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(2, 1)) != 1) {
            return false;
        }
        // Test case when end is directly north of start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(1, 2)) != 1) {
            return false;
        }
        // Test case when end is two steps away north from start
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(1, 3)) != 1) {
            return false;
        }
        // Test case when end is two steps east from start, and testing negative coordinates
        if (PathUtils.countPaths(new Intersection(-1, 1), new Intersection(1, 1)) != 1) {
            return false;
        }
        return true;
    }

    /**
     * Tests the case of countPaths() when there are multiple possible paths. For example, when 
     * the start position is Intersection(0, 0) and the ending position is Intersection(1, 2), 
     * there should be three possible Paths, so countPaths() should return 3.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testCountPathsRecursive() {
        if (PathUtils.countPaths(new Intersection(1, 1), new Intersection(2, 3)) != 3) {
            return false;
        }
        // Test negative coordinate input for both x and y
        if (PathUtils.countPaths(new Intersection(-1, 1), new Intersection(1, 3)) != 6) {
            return false;
        }
        if (PathUtils.countPaths(new Intersection(1, -1), new Intersection(3, 1)) != 6) {
            return false;
        }
        // Test cases with larger outputs
        if (PathUtils.countPaths(new Intersection(0, 0), new Intersection(10, 5)) != 3003) {
            return false;
        }
        if (PathUtils.countPaths(new Intersection(0, 0), 
                new Intersection(15, 15)) != 155117520) {
            return false;
        }
        return true;
    }

    /**
     * Tests the case of findAllPaths() when there are no valid Paths. For example, when the 
     * start position is Intersection(1, 1) and the ending position is Intersection(0, 1), there 
     * should be no valid Paths, so findAllPaths() should return an empty ArrayList.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testFindAllPathsNoPath() {
        // Test case when end is west of start
        if (!PathUtils.findAllPaths(new Intersection(1, 1), new Intersection(0,1)).isEmpty()) {
            return false;
        }
        // Test case when end is south of start
        if (!PathUtils.findAllPaths(new Intersection(1, 1), new Intersection(1,0)).isEmpty()) {
            return false;
        }
        // Test case when end is west of start with negative coordinate input
        if (!PathUtils.findAllPaths(new Intersection(1, 1), new Intersection(-1,1)).isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Tests the case of findAllPaths() when there is a single valid Path. For example, when the 
     * start position is Intersection(1, 1) and the ending position is Intersection(1, 2), there 
     * should be a single Path. For each of your cases, ensure that there is only a single path, 
     * and that the Path exactly matches what you expect to see.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testFindAllPathsOnePath() {
        // Test case of start (0,1) and end (0,1), check for size and correct output
        if (PathUtils.findAllPaths(new Intersection(0, 1), new Intersection(0,1)).size() != 1) {
            return false;
        }
        for (Path p : PathUtils.findAllPaths(new Intersection(0, 1), new Intersection(0,1))) {
            if (!p.toString().equals("(0,1)")) {
                return false;
            }
        }
        // Test case of start (0,1) and end (0,2), check for size and correct output
        if (PathUtils.findAllPaths(new Intersection(0, 1), new Intersection(0,2)).size() != 1) {
            return false;
        }
        for (Path p : PathUtils.findAllPaths(new Intersection(0, 1), new Intersection(0,2))) {
            if (!p.toString().equals("(0,1)->(0,2)")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests the case of findAllPaths() when there are multiple possible paths. For example, when 
     * the start position is Intersection(0, 0) and the ending position is Intersection(1, 2), 
     * there should be three possible Paths. For each of your cases, ensure that there is both the 
     * correct number of Paths, and that the returned Paths exactly match what you expect to see.
     * Remember: The order the Paths appear in the output of findAllPaths() will not necessarily 
     * match your own implementation.
     * 
     * @return true if all test cases are passed
     */
    public static boolean testFindAllPathsRecursive() {
        // Check for correct number of Paths generated for start(0,0) and end(1,1)
        if (PathUtils.findAllPaths(new Intersection(0, 0), new Intersection(1,1)).size() != 2) {
            return false;
        }
        // Check for uniqueness and correctness
        ArrayList<Path> checker = PathUtils.findAllPaths(new Intersection(0, 0), new Intersection(1,1));
        for (int i = 0; i < checker.size(); i++) {
            if (checker.get(i).toString().equals("(0,0)->(0,1)->(1,1)")) {
                checker.remove(i);
            }
            if (checker.get(i).toString().equals("(0,0)->(1,0)->(1,1)")) {
                checker.remove(i);
            }
        }
        if (!checker.isEmpty()) {
            return false;
        }

        // Check for correct number of Paths generated for start(0,0) and end(2,2)
        if (PathUtils.findAllPaths(new Intersection(0, 0), new Intersection(2,2)).size() != 6) {
            return false;
        }
        // Check for uniqueness and correctness
        ArrayList<Path> checker2 = PathUtils.findAllPaths(new Intersection(0, 0), new Intersection(2,2));
        for (int i = 0; i < checker2.size(); i++) {
            if (checker2.get(i).toString().equals("(0,0)->(0,1)->(0,2)->(1,2)->(2,2)")) {
                checker2.remove(i);
            }
            if (checker2.get(i).toString().equals("(0,0)->(0,1)->(1,1)->(1,2)->(2,2)")) {
                checker2.remove(i);
            }
            if (checker2.get(i).toString().equals("(0,0)->(0,1)->(1,1)->(2,1)->(2,2)")) {
                checker2.remove(i);
            }
            if (checker2.get(i).toString().equals("(0,0)->(1,0)->(1,1)->(1,2)->(2,2)")) {
                checker2.remove(i);
            }
            if (checker2.get(i).toString().equals("(0,0)->(1,0)->(1,1)->(2,1)->(2,2)")) {
                checker2.remove(i);
            }
            if (checker2.get(i).toString().equals("(0,0)->(1,0)->(2,0)->(2,1)->(2,2)")) {
                checker2.remove(i);
            }
        }
        if (!checker2.isEmpty()) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        // Driver code provided in P06 write-up
        /*try (Scanner keyboard = new Scanner(System.in)) {
            int startX, startY, endX, endY;
            String input = "Y";
            while (input.equalsIgnoreCase("Y")) {
            System.out.print("Enter starting X coordinate: ");
            startX = keyboard.nextInt();
            System.out.print("Enter starting Y coordinate: ");
            startY = keyboard.nextInt();
            System.out.print("Enter ending X coordinate: ");
            endX = keyboard.nextInt();
            System.out.print("Enter ending Y coordinate: ");
            endY = keyboard.nextInt();
            Intersection start = new Intersection(startX, startY);
            Intersection end = new Intersection(endX, endY);
            System.out.println("Number of paths from start to end: "
            + PathUtils.countPaths(start, end));
            System.out.println("List of possible paths:");
            for (Path p : PathUtils.findAllPaths(start, end)) {
                System.out.println(p);
            }
        do {
            System.out.print("Try another route? (Y/N): ");
            input = keyboard.next();
        } while (!input.equalsIgnoreCase("Y")
        && !input.equalsIgnoreCase("N"));
        }
        }*/
        System.out.println("testCountPathsNoPath: " + testCountPathsNoPath());
        System.out.println("testCountPathsOnePath: " + testCountPathsOnePath());
        System.out.println("testCountPathsRecursive: " + testCountPathsRecursive());
        System.out.println("testFindAllPathsNoPath: " + testFindAllPathsNoPath());
        System.out.println("testFindAllPathsOnePath: " + testFindAllPathsOnePath());
        System.out.println("testFindAllPathsRecursive: " + testFindAllPathsRecursive());
        
    }
}
