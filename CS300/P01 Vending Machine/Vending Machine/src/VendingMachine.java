//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Simple Vending Machine
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
 * This class models a simple vending machine that stores items with a corresponding
 * expiration date. The requested item that expires the soonest would be dispensed first.
 * 
 * @author Ethan Yikai Yan
 *
 */
public class VendingMachine {

  /**
   * Adds/appends an item defined by its description and expirationDate to a vending machine
   * represented by an oversize array of strings defined by the two-dimensional array items and its
   * size itemsCount. The item will be added to the end of the array. If the vending machine is
   * full, the new item won't be added and the method returns the items count passed as input
   * without making any changes to the contents of the array items.
   * 
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a string parsable to a positive integer which represents the expiration
   *                       date of the item. The date "0" represents January 1st 2023.
   * @param items          a two-dimensional of strings storing items. items[i][0] and items[i][1]
   *                       respectively represent the description and the expiration date of the
   *                       item stored at index i
   * @param itemsCount     number of items in the vending machine
   * @return the size of the vending machine after trying to add the new item
   */
  public static int addItem(String description, String expirationDate, String[][] items,
      int itemsCount) {
    // Check if the vending machine is full, else assign description and expirationDate to new item
    if (itemsCount < items.length) {
      items[itemsCount] = new String[] {description, expirationDate};
      itemsCount ++;
    }
    return itemsCount;
  }

  /**
   * Returns without removing a string representation of the item at the given index within the
   * vending machine defined by the array items and its size itemsCount. This method does not make
   * any changes to the contents of the vending machine.
   * 
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @param index      index of an item within the provided vending machine
   * @return a string representation of the item stored at the given index within the vending
   *         machine defined by items and itemsCount. The returned string must have the following
   *         format: "description (expiration date)". If the provided index is out of the range of
   *         indexes 0..itemsCount-1, the method returns "ERROR INVALID INDEX"
   */
  public static String getItemAtIndex(int index, String[][] items, int itemsCount) {
    String itemDetails = "";
    String itemDescription = "";
    String itemExpiryDate = "";
    // Check index validity
    if (index < 0 || (index > itemsCount - 1)) {
      itemDetails = "ERROR INVALID INDEX";
    } else {
      itemDescription = items[index][0];
      itemExpiryDate = items[index][1];
      itemDetails = itemDescription + " (" + itemExpiryDate + ")";
    }
    return itemDetails;
  }

  /**
   * Returns without removing the index of the item having the provided description and the smallest
   * expiration date within the vending machine defined by the array items and its size itemsCount.
   * 
   * @param description description of the item to get its index
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the index of the next item, meaning the item with the given description and the
   *         smallest expiration date. If no match found, return -1.
   */
  public static int getIndexNextItem(String description, String[][] items, int itemsCount) {
    // If the vending machine contains more than one item with the given description,
    // return the index of the one with the smallest expiration date.
    // Set nextItemIndex to -1 to be returned if no match
    int nextItemIndex = -1;
    // Set smallestExpiryDate to -1 as smallest date starts at 0
    int smallestExpiryDate = -1;
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0])) {
        // Assign matched item expiry date to smallestExpiryDate and store respective index
        if (smallestExpiryDate == -1) {
          smallestExpiryDate = Integer.parseInt(items[i][1]);
          nextItemIndex = i;
        // Already has one match, check for other matches that has smaller expiry date
        } else if (Integer.parseInt(items[i][1]) < smallestExpiryDate) {
          smallestExpiryDate = Integer.parseInt(items[i][1]);
          nextItemIndex = i;
        }
      }
    }
    return nextItemIndex;
  }

  /**
   * Removes the item having the provided description with the smallest expiration date within the
   * vending machine defined by the array items and its size itemsCount. This method should maintain
   * the order of precedence of items in the vending machine. This means that the remove operation
   * involves a shift operation.
   * 
   * @param description description of the item to remove or dispense
   * @param items       array storing items within a vending machine
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return size of the vending machine after removing the item with the given description and the
   *         smallest expiration date. If no match found, this method must return the provided 
   *         itemsCount without making any change to the contents of the vending machine.
   */
  public static int removeNextItem(String description, String[][] items, int itemsCount) {
    // Get the index for the item requested with smallest expiry date
    int nextItemIndex = getIndexNextItem(description, items, itemsCount);
    if (nextItemIndex != -1) {
      // Shift items down by one starting from index+1 to index to remove indexed item
      for (int i = nextItemIndex; i < itemsCount - 1; i++) {
        items[i][0] = items[i+1][0];
        items[i][1] = items[i+1][1];
      }
      items[itemsCount-1] = null;
      itemsCount--;
    }
    return itemsCount;
  }

  /**
   * Returns the number of occurrences of an item with a given description within the vending
   * machine defined by items and itemsCount
   * 
   * @param description description (name) of an item
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the number of occurrences of an item with a given description within the vending
   *         machine
   */
  public static int getItemOccurrences(String description, String[][] items, int itemsCount) {
    int itemOccurences = 0;
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0])) {
        itemOccurences++;
      }
    }
    return itemOccurences;
  }

  /**
   * Checks whether a vending machine defined by the array items and its size itemsCount contains at
   * least an item with the provided description
   * 
   * @param description description (name) of an item to search
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return true if there is a match with description in the vending machine, false otherwise
   */
  public static boolean containsItem(String description, String[][] items, int itemsCount) {
    boolean hasItem = false;
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0])) {
        hasItem = true;
      }
    }
    return hasItem;
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   * 
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @param items          two dimensional array storing items within a vending machine where
   *                       items[i][0] represents the description of the item at index i and
   *                       items[i][1] stores its expiration date.
   * @param itemsCount     (size) number of items stored in the vending machine
   * @return the number of items with a specific description and whose expiration date is greater or
   *         equal to the given one
   */
  public static int getItemsOccurrencesByExpirationDate(String description, String expirationDate,
      String[][] items, int itemsCount) {
    int itemOccurences = 0;
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0]) && Integer.parseInt(items[i][1]) >= 
          Integer.parseInt(expirationDate)) {
        itemOccurences++;
      }
    }
    return itemOccurences;
  }

  /**
   * Returns a summary of the contents of a vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. "water (5)\nchocolate (10)\nsnack (7)"
   * If the vending machine is empty, this method returns an empty string ""
   * 
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @return a descriptive summary of the contents of a vending machine
   */
  public static String getItemsSummary(String[][] items, int itemsCount) {
    String summary = "";
    String [][] summaryArray = new String[itemsCount][2];
    int summaryArrayCount = 0;
    if (itemsCount != 0) {
      for (int i = 0; i < itemsCount; i++) {
        if (!VendingMachine.containsItem(items[i][0], summaryArray, summaryArrayCount)) {
          summaryArray[summaryArrayCount][0] = items[i][0];
          summaryArray[summaryArrayCount][1] = 
              String.valueOf(getItemOccurrences(items[i][0], items, itemsCount));
          summaryArrayCount++;
        }
      }
      summary = summary + summaryArray[0][0] + " (" + summaryArray[0][1] + ")";
      for (int i = 1; i < summaryArrayCount; i++) {
        summary = summary + "\n" + summaryArray[i][0] + " (" + summaryArray[i][1] + ")";
      }
    }
    return summary; 
  }

}
