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
 * This DragonTreasureTester class consists of tester methods to test the functionalities
 * of the other classes: Room, Player and Dragon. 
 * 
 * @author Ethan Yikai Yan
 */
public class DragonTreasureTester {
    //Define 3 testRooms, 1 testPlayer and 1 testDragon
    private static Room testRoom1 = new Room(97, "Tester Room 1");
    private static Room testRoom2 = new Room(96, "Tester Room 2");
    private static Room testRoom3 = new Room(95, "Tester Room 3");
    private static Player testPlayer = new Player(testRoom1);
    private static Dragon testDragon = new Dragon(testRoom3);
    private static Player testPlayer2 = new Player(testRoom3);
    private static Player testPlayer3 = new Player(testRoom2);

    /**
     * Checks the correctness of the 8 instance methods defined in the Room class
     * 
     * @return true if testRoomInstanceMethods passes all the test scenarios defined in this 
     *         tester method, and false otherwise
     */
    public static boolean testRoomInstanceMethods() {
        try {
            // testRoom1 is set to be adjacent to testRooms 2 and 3, testRoom3 is set to be 
            // adjacent to testRoom1. testRoom1, testRoom2, and testRoom3 are set to have RoomType
            // of NORMAL, PORTAL and TREASURE respectively.
            testRoom1.setRoomType(RoomType.NORMAL);
            testRoom2.setRoomType(RoomType.PORTAL);
            testRoom3.setRoomType(RoomType.TREASURE);
            testRoom1.addToAdjacentRooms(testRoom2);
            testRoom1.addToAdjacentRooms(testRoom3);
            testRoom3.addToAdjacentRooms(testRoom1);

            // Check that the correctness of the output of getID and constructor method 
            if(testRoom1.getID() != 97) {
                System.out.println("getID() or constructor does not work!");
                return false;
            }

            // Check that the correctness of the output of getRoomDescription and constructor 
            // method 
            if(!(testRoom1.getRoomDescription().equals("Tester Room 1"))) {
                System.out.println("getRoomDescription() or constructor does not work!");
                return false;
            }

            // Check the correctness of addToAdjacentRooms and isAdjacent method
            // Case where testRoom2 isAdjacent to testRoom1
            if(!testRoom1.isAdjacent(testRoom2)) {
                System.out.println("addToAdjacentRooms() or isAdjacent() does not work!");
                return false;
            }
            // Case where testRoom3 is not Adjacent to testRoom2
            if(testRoom2.isAdjacent(testRoom3)) {
                System.out.println("addToAdjacentRooms() or isAdjacent() does not work!");
                return false;
            }
            
            // Check that the correctness of the output of getType and setRoomType method 
            if(testRoom2.getType() != RoomType.PORTAL) {
                System.out.println("setRoomType() or getType() does not work!");
                return false;
            }

            // Define the expected output of testList and check correctness of output of 
            // getAdjacentRooms method
            ArrayList<Room> testList = new ArrayList<Room>();
            testList.add(testRoom2);
            testList.add(testRoom3);
            if(!testRoom1.getAdjacentRooms().equals(testList)) {
                System.out.println("getAdjacentRooms() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of the 4 static methods defined in the Room class
     * 
     * @return true if testRoomStaticMethods passes all the test scenarios defined in this 
     *         tester method, and false otherwise
     */
    public static boolean testRoomStaticMethods() {
        try {
            // testRoom1 is set to be adjacent to testRooms 2 and 3. testRoom1, testRoom2, 
            // and testRoom3 are set to have RoomType of NORMAL, PORTAL and TREASURE respectively.
            testRoom1.setRoomType(RoomType.NORMAL);
            testRoom2.setRoomType(RoomType.PORTAL);
            testRoom3.setRoomType(RoomType.TREASURE);
            testRoom1.addToAdjacentRooms(testRoom2);
            testRoom1.addToAdjacentRooms(testRoom3);
            Room.assignTeleportLocation(97);

            // Check that the correctness of the output of assignTeleportLocation and 
            // getTeleportationRoom methods
            if(Room.getTeleportationRoom() != 97) {
                System.out.println("assignTeleportLocation() or getTeleportationRoom()" 
                        + "does not work!");
                return false;
            }

            // Check that the correctness of the output of getTreasureWarning and 
            // getPortal warning methods
            if(!Room.getTreasureWarning().equals("You sense that there is" 
                    + " treasure nearby.\n")) {
                System.out.println("getTreasureWarning() does not work!");
                return false;
            }

            if(!Room.getPortalWarning().equals("You feel a distortion "
                    + "in space nearby.\n")) {
                System.out.println("getPortalWarning() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of the canMoveTo method defined in the Player class
     * 
     * @return true if canMoveTo passes all the test scenarios defined in this 
     *         tester method, and false otherwise
     */
    public static boolean testPlayerCanMoveTo() {
        try {
            // Case where testPlayer can move to testRoom2
            if(!testPlayer.canMoveTo(testRoom2)) {
                System.out.println("canMoveTo() does not work!");
                return false;
            }
            // Case where testPlayer2 cannot move to testRoom2
            if(testPlayer2.canMoveTo(testRoom2)) {
                System.out.println("canMoveTo() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of the shouldTeleport method defined in the Player class
     * 
     * @return true if shouldTeleport passes all the test scenarios defined in this 
     *         tester method, and false otherwise
     */
    public static boolean testPlayerShouldTeleport() {
        try {
            // Case where testPlayer should not teleport
            if(testPlayer.shouldTeleport()) {
                System.out.println("shouldTeleport() does not work!");
                return false;
            }
            // Case where testPlayer3 should teleport
            if(!testPlayer3.shouldTeleport()) {
                System.out.println("shouldTeleport() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of the isPortalNearby and isTreasureNearby methods defined in the 
     * Player class
     * 
     * @return true if both isPortalNearby and isTreasureNearby passes all the test scenarios
     *         defined in this tester method, and false otherwise
     */
    public static boolean testPlayerDetectNearbyRooms() {
        try {
            if(!testPlayer.isPortalNearby()) {
                System.out.println("isPortalNearby() does not work!");
                return false;
            } else if(!testPlayer.isTreasureNearby()) {
                System.out.println("isTreasureNearby() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of the changeRoom method defined in the Dragon class
     * 
     * @return true if changeRooms passes all the test scenarios defined in this tester method, 
     *         and false otherwise
     */
    public static boolean testDragonChangeRooms() {
        try {
            testDragon.changeRooms();
            if(!testDragon.getCurrentRoom().equals(testRoom1)) {
                System.out.println("changeRooms() or getCurrentRoom() does not work!");
                return false;
            }
        }
        // Catch any unexpected exceptions
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true; // No bugs detected
    }

    // main method to call the tester methods defined in this class
    public static void main(String[] args) {
        if(testRoomInstanceMethods() && testRoomStaticMethods() && testPlayerCanMoveTo() &&
                testPlayerDetectNearbyRooms() && testPlayerShouldTeleport() 
                        && testDragonChangeRooms()) {
                            System.out.println("All tests passed.");
        } else {
        System.out.println("testRoomInstanceMethods: " + testRoomInstanceMethods());
        System.out.println("testRoomStaticMethods: " + testRoomStaticMethods());
        System.out.println("testPlayerCanMoveTo: " + testPlayerCanMoveTo());
        System.out.println("testPlayerShouldTeleport: " + testPlayerShouldTeleport());
        System.out.println("testPlayerDetectNearbyRooms: " + testPlayerDetectNearbyRooms());
        System.out.println("testDragonChangeRooms: " + testDragonChangeRooms());
        }
    }
}

