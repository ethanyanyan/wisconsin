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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This class implements testers to check the correctness of the methods implemented in p04
 * Exceptional Vending Machine
 *
 */
public class ExceptionalVendingMachineTester {
  // It is recommended but NOT required to add additional tester methods to check the correctness
  // of loadItems and saveVendingMachineSumary defined in the ExceptionalVendingMachine class.

  /**
   * Checks the correctness of the constructor of the class Item when passed invalid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorNotValidInput() {
    // Test constructor with an illegal description input
    try {
      Item invalidItem1 = new Item ("", 7);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      Item invalidItem2 = new Item (null, 7);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    // Test constructor with an illegal expirationDate input
    try {
      Item invalidItem3 = new Item ("Apple", -3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Checks the correctness of the constructor of the class Item, Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when passed valid inputs
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorGettersSetters() {
    try {
      Item testItem2 = new Item ("Apple", 5);
      
      // Test getDescription
      if (!testItem2.getDescription().equals("Apple")) {
        return false;
      }
      if (testItem2.getDescription().equals("Orange")) {
        return false;
      }

      // Test getExpirationDate
      if (testItem2.getExpirationDate() != 5) {
        return false;
      }

      // Test setDescription
      testItem2.setDescription("Orange");
      if (!testItem2.getDescription().equals("Orange")) {
        return false;
      }
      if (testItem2.getDescription().equals("Apple")) {
        return false;
      }

      // Test toString
      if (!testItem2.toString().equals("Orange: 5")) {
        return false;
      }
    }

    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Checks the correctness of the Item.equals() method. You should consider at least the following
   * four scenarios. (1) Create an item with valid description and expiration date, comparing it to
   * itself should return true. (2) Two items having the same description but different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals() method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance
   * a string object.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemEquals() {
    try {
      Item testItem1 = new Item ("Apple", 5);
      Item testItem2 = new Item ("Apple", 8);

      // (1) Create an item with valid description and expiration date, comparing it to itself 
      // should return true.
      if (!testItem1.equals(testItem1)) {
        return false;
      }

      // (2) Two items having the same description but different expiration dates should 
      // be equal.
      if (!testItem1.equals(testItem2)) {
        return false;
      }

      // (3) Passing a null reference to the Item.equals() method should return false.
      if (testItem1.equals(null)) {
        return false;
      }

      // (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance 
      // a string object.
      if (testItem1.equals("Apple")) {
        return false;
      }
    }

    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }


  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine when passed invalid
   * input
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor() {
    // Test for invalid input (when capacity is zero or negative)
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(0);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      ExceptionalVendingMachine testMachine2 = new ExceptionalVendingMachine(-3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Checks the correctness of the following methods defined in the ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   * 
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(), getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() {
    // Test addItem and containsItem
    // Test addItem when illegal expiration description is passed as an input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 5);
      if (!testMachine1.containsItem("Apple")) {
        return false;
      }
      if (testMachine1.containsItem("Pineapple")) {
        return false;
      }
      testMachine1.addItem(null, 5);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("", 5);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Test addItem when illegal expiration date is passed as an input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", -3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test false inputs for containsItem
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 5);
      try {
        testMachine1.containsItem(null);
        return false;
      }
      catch (IllegalArgumentException e) {}

      try {
        testMachine1.containsItem("");
        return false;
      }
      catch (IllegalArgumentException e) {}
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test addItem when the exceptional vending machine is full
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Orange", 5);
      testMachine1.addItem("Banana", 7);
      testMachine1.addItem("Banana", 7);
      return false;
    }
    catch (IllegalStateException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test getIndexNextItem
    // Test getIndexNextItem when an item not in the exceptional vending machine is passed as an
    // input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Orange", 5);
      testMachine1.addItem("Banana", 7);
      if (testMachine1.getIndexNextItem("Banana") != 2) {
        return false;
      }
      int nonExistentItem = testMachine1.getIndexNextItem("Grape");
      return false;
    }
    catch (NoSuchElementException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Test getIndexNextItemwhen illegal description is passed as an input
    try{
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      int wrongInput = testMachine1.getIndexNextItem("");
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try{
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      int wrongInput = testMachine1.getIndexNextItem(null);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test getItemAtIndex
    // Test getItemAtIndex when an index more than the size of vending machine or less than 0 is
    // passed as an input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Orange", 5);
      testMachine1.addItem("Banana", 7);
      Item testOrange = new Item("Orange", 4);
      if (!testMachine1.getItemAtIndex(1).equals("Orange: 5")) {
        return false;
      }
      if (testMachine1.getItemAtIndex(2).equals("Orange: 5")) {
        return false;
      }
      String outOfBounds = testMachine1.getItemAtIndex(3);
      return false;
    }
    catch (IndexOutOfBoundsException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      String outOfBounds = testMachine1.getItemAtIndex(-5);
      return false;
    }
    catch (IndexOutOfBoundsException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test getItemOccurences
    // Test getItemOccurences when illegal description is passed as input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Apple", 5);
      if (testMachine1.getItemOccurrences("Apple") != 2) {
        return false;
      }
      int invalidInput = testMachine1.getItemOccurrences("");
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      int invalidInput = testMachine1.getItemOccurrences(null);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test getItemOccurencesByExpirationDate
    // Test getItemOccurencesByExpirationDate when illegal description is passed as input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Apple", 5);
      testMachine1.addItem("Apple", 7);
      if (testMachine1.getItemOccurrencesByExpirationDate("Apple", 5) != 2) {
        return false;
      }
      int invalidInput = testMachine1.getItemOccurrencesByExpirationDate("", 3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      int invalidInput = testMachine1.getItemOccurrencesByExpirationDate(null, 3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Test getItemOccurencesByExpirationDate when illegal expirationDate is passed as input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      int invalidInput = testMachine1.getItemOccurrencesByExpirationDate("Apple", -3);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test removeNextItem
    // Test removeNextItem when no match is found for the desired Item
    try{
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(4);
      testMachine1.addItem("Apple", 7);
      testMachine1.addItem("Chocolate", 5);
      testMachine1.addItem("Apple", 3);
      testMachine1.addItem("Banana", 6);
      Item testApple = new Item("Apple", 3);
      testMachine1.removeNextItem("Apple");

      // Check that items have been shifted correctly
      if (!testMachine1.getItemAtIndex(0).equals("Apple: 7") || 
              !testMachine1.getItemAtIndex(1).equals("Chocolate: 5") || 
                    !testMachine1.getItemAtIndex(2).equals("Banana: 6")) {
                      return false;
                    }
      Item unmatchedItem = testMachine1.removeNextItem("Pineapple");
      return false;
    }
    catch (NoSuchElementException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // Test removeNextItem when illegal description is passed as input
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      Item invalidInput = testMachine1.removeNextItem(null);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.addItem("Apple", 3);
      Item invalidInput = testMachine1.removeNextItem("");
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in the
   * ExceptionalVendingMachine class
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() {
    // Test isEmpty, size and isFull methods
    try{
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      ExceptionalVendingMachine testMachine2 = new ExceptionalVendingMachine(1);
      if (!testMachine1.isEmpty()) {
        return false;
      }
      testMachine1.addItem("Apple", 3);
      if (testMachine1.isEmpty()) {
        return false;
      }
      if (testMachine1.isFull()) {
        return false;
      }

      if (testMachine1.size() == 3) {
        return false;
      }
      if (testMachine1.size() != 1) {
        return false;
      }

      testMachine1.addItem("Banana", 5);
      testMachine1.addItem("Pear", 7);
      if (!testMachine1.isFull()) {
        return false;
      }

      if (testMachine2.isFull()) {
        return false;
      }
    }

    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful scenario for passing null
   * or a blank string (for instance one space or empty string) to the loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   * 
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadOneItem() {
    // (1) Successful scenario for loading one item with a valid string representation to a 
    // non-full vending machine.
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("Apple: 5");
      if (!testMachine1.getItemAtIndex(0).equals("Apple: 5")) {
        return false;
      }
      testMachine1.loadOneItem("    Banana: 7           ");
      if (!testMachine1.getItemAtIndex(1).equals("Banana: 7")) {
        return false;
      }
    }
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (2) Unsuccessful scenario for passing null or a blank string (for instance one space or 
    // empty string) to the loadOneItem() method call, an IllegalArgumentEXception is 
    // expected to be thrown.
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("   ");
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem(null);
      return false;
    }
    catch (IllegalArgumentException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (3) Unsuccessful scenario for passing a badly formatted string to the loadOneItem method. 
    // A DataFormatException is expected to be thrown.
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("5: Apple");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("Apple:   ");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("       : 77");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("Apple:Banana:321");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("Apple: -2");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(3);
      testMachine1.loadOneItem("Apple 5");
      return false;
    }
    catch (DataFormatException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (4) Unsuccessful scenario for trying to load an item with a valid representation to a
    // full vending machine. An IllegalStateException is expected to be thrown.
    try {
      ExceptionalVendingMachine testMachine1 = new ExceptionalVendingMachine(1);
      testMachine1.addItem("Apple", 5);
      testMachine1.loadOneItem("Banana: 7");
      return false;
    }
    catch (IllegalStateException e) {}
    // Catch any unexpected exceptions
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bugs detected
  }

  /**
   * Invokes all the public tester methods implemented in this class
   * 
   * @return true if all testers pass with no errors, and false if any of the tester fails.
   */
  public static boolean runAllTests() {
    if (!testEmptySizeFullExceptionalVendingMachine() || !testLoadOneItem() || !testItemEquals() ||
            !testItemConstructorNotValidInput() || !testItemConstructorGettersSetters() || 
                !testExceptionalVendingMachineAddContainsRemoveGetters() ||
                    !testExceptionalVendingMachineConstructor()) {
                    return false;
                  }
    return true; // No bugs detected, all tests passed
  }

  /**
   * Main method for the tester class
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testConstructorNotValidInput: " + testItemConstructorNotValidInput());
    System.out.println("testConstructorGettersSetters: " + testItemConstructorGettersSetters());
    System.out.println("testItemEquals: " + testItemEquals());
    System.out.println("testExceptionalVendingMachineConstructor: " +
        testExceptionalVendingMachineConstructor());
    System.out.println("testEmptySizeFullExceptionalVendingMachine: " + 
        testEmptySizeFullExceptionalVendingMachine());
    System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters: " +
        testExceptionalVendingMachineAddContainsRemoveGetters());
    System.out.println("testLoadOneItem: " + testLoadOneItem());
    System.out.println("runAllTests: " + runAllTests());

    ExceptionalVendingMachine test = new ExceptionalVendingMachine(20);
    File file = new File("/Users/ethanyan01/Desktop/P04 Exceptional Vending Machine/items.txt");
    try {
      System.out.println(test.loadItems(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

}
