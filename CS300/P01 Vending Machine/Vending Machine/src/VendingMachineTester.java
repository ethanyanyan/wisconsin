//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    File to test Simple Vending Machine program
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

import java.util.Arrays;

// This class is used to test the functionalities of the vending machine
public class VendingMachineTester {

  // Checks the correctness of getIndexNextItem defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testGetIndexNextItem() {

    // 1. Next item to be dispensed is NOT found: the expected output is -1
    {
      // define the vending machine
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("candy", items, itemsCount) != -1) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did not "
                + "return -1 when no match found.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Next item to be dispensed is at index 0
    {
      String[][] items = new String[][] {{"Water", "1"}, {"Chocolate", "10"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 0) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains multiple matches "
                + "with the provided item description and the matching item with the "
                + "soonest expiration date is at index 0.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "1"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 3. Next item to be dispensed is at the end of the array
    {
      String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "20"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Chocolate", items, itemsCount) != 6) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains multiple matches "
                + "with the provided item description and the matching item with the soonest "
                + "expiration date is at the end of the array");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "20"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 4. Next item to be dispensed is at the middle of the array
    {
      String[][] items = new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"},
          {"Water", "5"}, {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 3) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains matches with "
                + "the provided item description with different expiration dates.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "5"},
              {"Candy", "30"}, {"Water", "15"}, {"Chocolate", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    return true; // No bug detected
  }

  // Checks the correctness of containsItem defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testContainsItem() {

    // 1. Successful search returning true
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      String description = "Water";
      if (!VendingMachine.containsItem(description, items, itemsCount)) {
        return false;
      }
    }

    // 2. Unsuccessful search returning false
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      String description = "Candy";
      if (VendingMachine.containsItem(description, items, itemsCount)) {
        return false;
      }
    }
    return true; 
  }

  // Checks the correctness of getItemAtIndex defined in the VendingMachine class. This method
  // returns true if the test verifies a correct functionality and false if any bug is detected
  public static boolean testGetItemAtIndex() {

    // 1. Index out of range
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      int testIndex = 5;
      if (!(VendingMachine.getItemAtIndex(testIndex, items, itemsCount)).equals
          ("ERROR INVALID INDEX")) {
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetItemAtIndex-scenario 1. Problem detected: Your testGetItemAtIndex did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Index in bounds
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      int testIndex = 2;
      if (!(VendingMachine.getItemAtIndex(testIndex, items, itemsCount)).equals
          ("Juice (20)")) {
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetItemAtIndex-scenario 2. Problem detected: Your testGetItemAtIndex did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    return true;
  }

  // Checks the correctness of getItemOccurrences defined in the VendingMachine class.
  public static boolean testGetItemsOccurrences() {

    // 1. No match found
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      String testItem = "Banana";
      if (VendingMachine.getItemOccurrences(testItem, items, itemsCount) != 0) {
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetItemOccurrences-scenario 1. Problem detected: Your testGetItemsOccurrences "
                + "did make changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Multiple Occurences
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "25"}, 
              {"Water", "45"}, null};
      int itemsCount = 5;
      String testItem = "Water";
      if (VendingMachine.getItemOccurrences(testItem, items, itemsCount) != 3) {
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "25"}, 
              {"Water", "45"}, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetItemOccurrences-scenario 2. Problem detected: Your testGetItemsOccurrences "
                + "did make changes to the content of the array passed as input.");
        return false;
      }
    }
    return true; 
  }

  // Checks the correctness of addItem defined in the VendingMachine class.
  public static boolean testAddItem() {
  
    // 1. Add one new item to empty vending machine
    {
      String[][] items =
          new String[][] {null, null, null, null, null, null};
      int itemsCount = 0;
      String testItem = "Water";
      String testExpirationDate = "15";
      int newCount = VendingMachine.addItem(testItem, testExpirationDate, items, itemsCount);
      if (newCount != 1) {
        return false;
      }
      // Check that the method made the right changes
      String[][] newItems =
          new String[][] {{"Water", "15"}, null, null, null, null, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testAddItem-scenario 1. Problem detected: Your testAddItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    
    // 2. Add one new item to nonempty, nonfull vending machine
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null, null, null};
      int itemsCount = 3;
      String testItem = "Water";
      String testExpirationDate = "35";
      int newCount = VendingMachine.addItem(testItem, testExpirationDate, items, itemsCount);
      if (newCount != 4) {
        return false;
      }
      // Check that the method made the right changes
      String[][] newItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, {"Water", "35"}, 
              null, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testAddItem-scenario 2. Problem detected: Your testAddItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 3. Add one new item to full vending machine
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}};
      int itemsCount = 6;
      String testItem = "Water";
      String testExpirationDate = "35";
      int newCount = VendingMachine.addItem(testItem, testExpirationDate, items, itemsCount);
      if (newCount != 6) {
        return false;
      }
      // Check that the method made no changes
      String[][] newItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testAddItem-scenario 3. Problem detected: Your testAddItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    return true;
  }

  // Checks the correctness of removeNextItem defined in the VendingMachine class.
  public static boolean testRemoveNextItem() {

    // 1. Remove nonexistent item from vending machine
    {
      String[][] items =
          new String[][] {null, null, null, null, null, null};
      int itemsCount = 0;
      String testItem = "Water";
      int newCount = VendingMachine.removeNextItem(testItem,  items, itemsCount);
      if (newCount != 0) {
        return false;
      }
      // Check that the method made the right changes
      String[][] newItems =
          new String[][] {null, null, null, null, null, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testRemoveNextItem-scenario 1. Problem detected: Your testRemoveNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Remove item at index 0
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}};
      int itemsCount = 6;
      String testItem = "Water";
      int newCount = VendingMachine.removeNextItem(testItem,  items, itemsCount);
      if (newCount != 5) {
        return false;
      }
      // Check that the method made no changes
      String[][] newItems =
          new String[][] {{"Chocolate", "10"}, {"Juice", "20"}, 
              {"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testRemoveNextItem-scenario 2. Problem detected: Your testRemoveNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 3. Index of removed item = itemsCount
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Candy", "23"}, {"Chocolate", "18"}, {"Juice", "17"}};
      int itemsCount = 6;
      String testItem = "Juice";
      int newCount = VendingMachine.removeNextItem(testItem,  items, itemsCount);
      if (newCount != 5) {
        return false;
      }
      // Check that the method made no changes
      String[][] newItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Candy", "23"}, {"Chocolate", "18"}, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testRemoveNextItem-scenario 3. Problem detected: Your testRemoveNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 4. Removed item is in the middle of array
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Candy", "23"}, {"Chocolate", "18"}, {"Juice", "17"}};
      int itemsCount = 6;
      String testItem = "Candy";
      int newCount = VendingMachine.removeNextItem(testItem,  items, itemsCount);
      if (newCount != 5) {
        return false;
      }
      // Check that the method made no changes
      String[][] newItems =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
              {"Chocolate", "18"}, {"Juice", "17"}, null};
      if (!Arrays.deepEquals(items, newItems)) {
        System.out.println(
            "testRemoveNextItem-scenario 4. Problem detected: Your testRemoveNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    return true; 
  }

  // Checks the correctness of getItemsSummary defined in the VendingMachine class.
  public static boolean testGetItemsSummary() {

    // 1. Vending Machine is empty
    {
      String[][] items =
          new String[][] {null, null, null, null, null, null};
      int itemsCount = 0;
      String summary = VendingMachine.getItemsSummary(items, itemsCount);
      if (!summary.equals("")) {
        return false;
      }
    }

    // 2. Vending Machine contains non duplicate items
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
          {"Candy", "23"}, null, null};
      int itemsCount = 4;
      String summary = VendingMachine.getItemsSummary(items, itemsCount);
      if (!summary.equals("Water (1)\nChocolate (1)\nJuice (1)\nCandy (1)")) {
        return false;
      }
    }

    // 3. Vending Machine contains multiple items with the same description at various 
    //    index locations
    {
      String[][] items =
          new String[][] {{"Water", "15"}, {"Chocolate", "10"}, {"Juice", "20"}, 
          {"Candy", "23"}, {"Water", "15"}, {"Water", "15"}};
      int itemsCount = 6;
      String summary = VendingMachine.getItemsSummary(items, itemsCount);
      if (!summary.equals("Water (3)\nChocolate (1)\nJuice (1)\nCandy (1)")) {
        return false;
      }
    }
    
    return true; 
  }

  // This method returns false if any of the tester methods defined in this class fails, and true
  // if no bug detected.
  public static boolean runAllTests() {
    if (!(testAddItem() && testContainsItem() && testGetIndexNextItem() && testGetItemAtIndex() && 
        testGetItemsOccurrences() && testGetItemsSummary() && testRemoveNextItem())) {
      return false;
    }
    return true;
  }

  // main method to call the tester methods defined in this class
  public static void main(String[] args) {
    System.out.println("testGetIndexNextItem(): " + testGetIndexNextItem());
    System.out.println("testContainsItem(): " + testContainsItem());
    System.out.println("testGetItemAtIndex(): " + testGetItemAtIndex());
    System.out.println("testGetItemsOccurrences(): " + testGetItemsOccurrences());
    System.out.println("testAddItem(): " + testAddItem());
    System.out.println("testRemoveNextItem(): " + testRemoveNextItem());
    System.out.println("testGetItemsSummary(): " + testGetItemsSummary());
    System.out.println("runAllTests(): " + runAllTests());
  }

}
