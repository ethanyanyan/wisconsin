//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Walking Simulator
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
 * This class models a simple walking simulator that first generates a random number of characters
 * on screen then simulates them walking upon clicking on each character. User is able to add
 * characters or stop all moving characters by pressing 'a'/'A' and 's'/'S' respectively.
 * 
 * @author Ethan Yikai Yan
 *
 */

import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
 * @setup called automatically when the program begins. All data field initialization should
 * happen here, any program configuration actions, etc.
 * @draw runs continuously as long as the application window is open. Draws the window and
 * the current state of its contents to the screen.
 * @mousePressed called automatically whenever the mouse button is pressed.
 * @keyPressed called automatically whenever a keyboard key is pressed.
 * 
 */

public class WalkingSim {

    private static Random randGen;
    private static int bgColor;
    private static PImage[] frames;
    private static Walker[] walkers;
    public static void main(String[] args){
        Utility.runApplication(); 
    }

    /**
     * @setup called automatically when the program begins. All data field initialization should
     * happen here, any program configuration actions, etc.
     * A random number of walkers with random x and y coordinates is generated onto a random
     * background color.
     */
    public static void setup() {
        randGen = new Random();
        bgColor = randGen.nextInt();
        frames = new PImage[Walker.NUM_FRAMES];
        for (int i = 0; i < Walker.NUM_FRAMES; i++) {
            frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i +".png");
        }
        walkers = new Walker[8];
        int numWalkers = randGen.nextInt(8) + 1;
        for (int i = 0; i < numWalkers; i++) {
            int randWidth = randGen.nextInt(Utility.width());
            int randHeight = randGen.nextInt(Utility.height());
            walkers[i] = new Walker(randWidth, randHeight);
        }
    }

    public static void draw() {
        Utility.background(bgColor);
        for (int i = 0; i < walkers.length; i++) {
            if (walkers[i] != null) {
                if(walkers[i].isWalking()) {
                    float currentX = walkers[i].getPositionX();
                    if (currentX > 797) {
                        walkers[i].setPositionX(0);
                    } else {
                        walkers[i].setPositionX(currentX+3);
                    }
                }
                Utility.image(frames[walkers[i].getCurrentFrame()], walkers[i].getPositionX(), 
                        walkers[i].getPositionY());
                if(walkers[i].isWalking()) {
                    walkers[i].update();
                }
            }
        }
    }

    /**
     * @mousePressed called automatically whenever the mouse button is pressed.
     * Animates the walker that user clicks on. Would animate the walker that has a lower index in
     * the situation where two walkers are overlapped.
     */
    public static void mousePressed () {
        System.out.println("Mousepressed");
        for (int i = 0; i < walkers.length; i ++) {
            if (walkers[i] != null && !walkers[i].isWalking()) {
                System.out.println("i value: " + i);
                float walkerX = walkers[i].getPositionX();
                float walkerY = walkers[i].getPositionY();
                PImage sampleImage = Utility.loadImage("images" + File.separator + "walk-" + 0 
                        + ".png");
                float walkerWidth = sampleImage.width;
                float walkerHeight = sampleImage.height;
                System.out.println(walkerWidth + " " + walkerHeight);
                float leftBound = walkerX - (walkerWidth/2);
                float rightBound = walkerX + (walkerWidth/2);
                float bottomBound = walkerY - (walkerHeight/2);
                float topBound = walkerY + (walkerHeight/2);        
                if (Utility.mouseX() < rightBound && Utility.mouseX() > leftBound && 
                        Utility.mouseY() > bottomBound && Utility.mouseY() < topBound) {
                    System.out.println("in if " + i);
                    walkers[i].setWalking(true);
                    break;
                } 
            }
        }
    }

    /**
     * @keyPressed called automatically whenever a keyboard key is pressed.
     * Adds a new stationary walker when 'a' or 'A' key is pressed.
     * Stops all walking walkers when 's' or 'S' key is pressed.
     */
    public static void keyPressed(Character x) {
        if (x == 'a' || x == 'A') {
            int emptyIndex = 0;
            while (walkers[emptyIndex] != null && emptyIndex < 8) {
                emptyIndex++;
            }
            int randWidth = randGen.nextInt(Utility.width());
            int randHeight = randGen.nextInt(Utility.height());
            walkers[emptyIndex] = new Walker(randWidth, randHeight);
        }

        if (x == 's' || x == 'S') {
            for (int i = 0; i < walkers.length; i ++) {
                if (walkers[i] != null) {
                    walkers[i].setWalking(false);
                }
            }
        }
    }
}