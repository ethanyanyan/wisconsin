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
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 * 
 * @author Ethan Yikai Yan
 *
 */
public class ChugidexTester {
  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() {
    // Ensure constructor is working correctly
    try {
      Chugimon test1 = new Chugimon(0, 10);
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    try {
      Chugimon test1 = new Chugimon(10, 0);
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Chugimon test1 = new Chugimon(10, 10);
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test compareTo methods
    try {
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test8 = new Chugimon(1, 2);
      if (test1.compareTo(test2) >= 0) {
        return false;
      }
      if (test2.compareTo(test3) >= 0) {
        return false;
      }
      if (test1.compareTo(test3) >= 0) {
        return false;
      }
      if (test1.compareTo(test4) >= 0) {
        return false;
      }
      if (test4.compareTo(test5) >= 0) {
        return false;
      }
      if (test5.compareTo(test6) >= 0) {
        return false;
      }
      if (test1.compareTo(test5) >= 0) {
        return false;
      }
      if (test1.compareTo(test7) <= 0) {
        return false;
      }
      if (test7.compareTo(test8) != 0) {
        return false;
      }

      // test equals methods
      if (test1.equals(test2)) {
        return false;
      }
      if (test1.equals(test4)) {
        return false;
      }
      if (test1.equals(test7)) {
        return false;
      }
      if (!test7.equals(test8)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Chugimon test1 = new Chugimon(11, 10);
      if (test1.compareTo(null) == 0) {
        return false;
      }
    }
    catch (NullPointerException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Chugimon test1 = new Chugimon(11, 10);
      if (test1.equals(null)) {
        return false;
      }
    }
    catch (NullPointerException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() {
    try {
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      if (!test1.toString().equals("Nidoeon#29.134")) {
        return false;
      }
      if (!test2.toString().equals("Nidoeon#29.135")) {
        return false;
      }
      if (!test3.toString().equals("Nidoeon#30.134")) {
        return false;
      }
      if (!test7.toString().equals("Bulbysaur#1.2")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should be
   * a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() {
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(150, 151)); // Nidoeon#29.134
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (1) An empty tree whose root is null should be a valid BST.
    try {
      if (!ChugiTree.isValidBSTHelper(null)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (2) Consider a valid BST whose height is at least 3. Create the tree using the constructors 
    //     of the BSTNode and its setters methods
    // Valid BST: test3
    //        test2   test5
    //    test1     test4 test6
    //                       test7
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(30, 137)); // Nidoeon#30.137
      test3.setLeft(test2);
      test3.setRight(test5);
      test2.setLeft(test1);
      test5.setLeft(test4);
      test5.setRight(test6);
      test6.setRight(test7);
      if (!ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Valid BST: test3
    //        test2   
    //    test1     
    // test4
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(1, 2)); // Bulbysaur#1.2
      test3.setLeft(test2);
      test2.setLeft(test1);
      test1.setLeft(test4);
      if (!ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Valid BST: test3
    //                test5
    //                    test6
    //                       test7
    try {
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(30, 137)); // Nidoeon#30.137
      test3.setRight(test5);
      test5.setRight(test6);
      test6.setRight(test7);
      if (!ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (3) Consider a NON-valid BST where the search order property is violated at at least one 
    //     internal node.
    // Invalid BST: test3
    //          test2    test5
    //      test1  test4    test6  where test4 > test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(30, 137)); // Nidoeon#30.137
      test3.setLeft(test2);
      test3.setRight(test5);
      test2.setLeft(test1);
      test2.setRight(test4);
      test5.setRight(test6);
      test6.setRight(test7);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Invalid BST: test3
    //       test1       test5
    //          test2  test6 
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      test3.setLeft(test1);
      test3.setRight(test5);
      test1.setRight(test2);
      test5.setLeft(test6);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    } 

    // Invalid BST: test3
    //          test2    test5
    //      test1      test4    
    //   test7 test6             where test6 > test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(1, 2)); // Bulbysaur#1.2
      test3.setLeft(test2);
      test3.setRight(test5);
      test2.setLeft(test1);
      test1.setRight(test6);
      test5.setLeft(test4);
      test1.setLeft(test7);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
     
    // Invalid BST: test3
    //          test2    test5
    //       test7    test4  test6  
    //             test1              where test1 < test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(1, 2)); // Bulbysaur#1.2
      test3.setLeft(test2);
      test3.setRight(test5);
      test5.setLeft(test4);
      test4.setLeft(test1);
      test5.setRight(test6);
      test2.setLeft(test7);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
     
    // Invalid BST: test3
    //          test2    test5
    //       test7    test4   test6  
    //                      test1      where test1 < test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      BSTNode<Chugimon> test7 = new BSTNode<Chugimon>(new Chugimon(1, 2)); // Bulbysaur#1.2
      test3.setLeft(test2);
      test3.setRight(test5);
      test5.setLeft(test4);
      test5.setRight(test6);
      test6.setLeft(test1);
      test2.setLeft(test7);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Invalid BST: test3
    //                   test5
    //                test4  test6  
    //             test1              where test1 < test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test4 = new BSTNode<Chugimon>(new Chugimon(30, 134)); // Nidoeon#30.134
      BSTNode<Chugimon> test5 = new BSTNode<Chugimon>(new Chugimon(30, 135)); // Nidoeon#30.135
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      test3.setRight(test5);
      test5.setLeft(test4);
      test4.setLeft(test1);
      test5.setRight(test6);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Invalid BST: test3
    //          test2    
    //      test1          
    //         test6             where test6 > test3
    try {
      BSTNode<Chugimon> test1 = new BSTNode<Chugimon>(new Chugimon(29, 134)); // Nidoeon#29.134
      BSTNode<Chugimon> test2 = new BSTNode<Chugimon>(new Chugimon(29, 135)); // Nidoeon#29.135
      BSTNode<Chugimon> test3 = new BSTNode<Chugimon>(new Chugimon(29, 136)); // Nidoeon#29.136
      BSTNode<Chugimon> test6 = new BSTNode<Chugimon>(new Chugimon(30, 136)); // Nidoeon#30.136
      test3.setLeft(test2);
      test2.setLeft(test1);
      test1.setRight(test6);
      if (ChugiTree.isValidBSTHelper(test3)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create a
   * new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the add()
   * method call returns true, the tree is not empty, its size is 1, and the toString() called on
   * the tree returns the expected output. (3) Try adding another Chugimon which is less than the
   * Chugimon at the root, (4) Try adding a third Chugimon which is greater than the one at the
   * root, (5) Try adding at least two further Chugimons such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
   * string representation of the contents of the binary search tree in an increasing order from the
   * smallest to the greatest Chugimon. (6) Try adding a Chugimon already stored in the tree. Make
   * sure that the add() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() {
    // (1) Create a new empty ChugiTree, and check that its size is 0, it is empty, and that its
    // string representation is an empty string "".
    try {
      ChugiTree newTree = new ChugiTree();
      if (newTree.size() != 0) {
        return false;
      }
      if (!newTree.isEmpty()) {
        return false;
      }
      if (!newTree.toString().equals("")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (2) try adding one Chugimon and then check that the add() method call returns true, the 
     * tree is not empty, its size is 1, and the toString() called on the tree returns the 
     * expected output.
     */ 
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      if(!newTree.add(test1)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 1) {
        return false;
      }
      if (!newTree.toString().equals("Nidoeon#29.134")) {
        return false;
      }
      if (!newTree.getRoot().equals(test1)) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (3) Try adding another Chugimon which is less than the Chugimon at the root
     */
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      if(!newTree.add(test1)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 1) {
        return false;
      }
      if (!newTree.toString().equals("Nidoeon#29.134")) {
        return false;
      }

      if (!newTree.add(test2)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 2) {
        return false;
      }
      if (!newTree.getRoot().equals(test1)) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbysaur#1.2,Nidoeon#29.134")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (4) Try adding a third Chugimon which is greater than the one at the root
     */
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      newTree.add(test1);
      newTree.add(test2);

      if (!newTree.add(test3)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 3) {
        return false;
      }
      if (!newTree.getRoot().equals(test1)) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbysaur#1.2,Nidoeon#29.134,Nidoeon#29.136")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (5) Try adding at least two further Chugimons such that one must be added at the left
     * subtree, and the other at the right subtree. For all the above scenarios, and more, double
     * check each time that size() method returns the expected value, the add method call returns
     * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
     * string representation of the contents of the binary search tree in an increasing order from the
     * smallest to the greatest Chugimon.
     */
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);

      if (!newTree.add(test4)) {
        return false;
      }
      if (!newTree.add(test5)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 5) {
        return false;
      }
      if (!newTree.getRoot().equals(test1)) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbmander#1.4,Bulbysaur#1.2,Nidoeon#29.134," +
          "Nidoeon#29.136,Nidoeon#30.135")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (6) Try adding a Chugimon already stored in the tree. Make
     * sure that the add() method call returned false, and that the size of the tree did not change.
     */
    try {
      // Valid BST:  test1
      //        test2      test3
      //       test4         test5
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 135); // Nidoeon#30.135
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);

      if (newTree.add(test6)) {
        return false;
      }
      if (newTree.isEmpty()) {
        return false;
      }
      if (newTree.size() != 5) {
        return false;
      }
      if (!newTree.getRoot().equals(test1)) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbmander#1.4,Bulbysaur#1.2,Nidoeon#29.134," +
          "Nidoeon#29.136,Nidoeon#30.135")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for a
   * Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the right
   * and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    /**
     * (1) Create a new ChugiTree. Then, check that calling the lookup() method on an empty 
     * ChugiTree returns false.
     */
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon foundChugimon = newTree.lookup(2, 5);
      if (foundChugimon != null) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ChugiTree newTree = new ChugiTree();
      Chugimon foundChugimon2 = newTree.lookup(0, 0);
      if (foundChugimon2 != null) {
        return false;
      }
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (2) Consider a ChugiTree of height 3 which contains at least 5 Chugimons. Then, try to call 
     * lookup() method to search for a Chugimon having a match at the root of the tree.
     */
    try {
      // Valid BST:  test1
      //        test2      test3
      //      test4          test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test7);
      Chugimon foundChugimon1 = newTree.lookup(29, 134);
      if (!foundChugimon1.equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (3) Then, search for a Chugimon at the right
     * and left subtrees at different levels considering successful and unsuccessful search
     * operations. Make sure that the lookup() method returns the expected output for every method
     * call.
     */
    try {
      // Valid BST:  test1
      //        test2      test3
      //      test4           test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test7);
      // find test2 - test5
      Chugimon foundChugimon1 = newTree.lookup(1, 2);
      if (!foundChugimon1.equals(test2)) {
        return false;
      }
      Chugimon foundChugimon3 = newTree.lookup(1, 4);
      if (!foundChugimon3.equals(test4)) {
        return false;
      }
      Chugimon foundChugimon4 = newTree.lookup(29, 136);
      if (!foundChugimon4.equals(test3)) {
        return false;
      }
      Chugimon foundChugimon5 = newTree.lookup(30, 135);
      if (!foundChugimon5.equals(test5)) {
        return false;
      }
      Chugimon foundChugimon7 = newTree.lookup(30, 137);
      if (!foundChugimon7.equals(test7)) {
        return false;
      }
      // Unsuccessful searches
      Chugimon foundChugimon2 = newTree.lookup(1, 3);
      if (foundChugimon2 != null) {
        return false;
      }
      Chugimon foundChugimon6 = newTree.lookup(29, 135);
      if (foundChugimon6 != null) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() {
    try {
      ChugiTree newTree = new ChugiTree();
      if (newTree.countType(ChugiType.GRASS) != 0) {
        return false;
      }
      if (newTree.countType(ChugiType.POISON) != 0) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test1
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134 poi, water
      newTree.add(test1);
      if (newTree.countType(ChugiType.WATER) != 1) {
        return false;
      }
      if (newTree.countType(ChugiType.POISON) != 1) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test1
      //        test4        test5
      //           test2  test3 
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134 poi, water
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2 grass, poi
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136 poi, fire
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4 grass, fire
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135 poi, elec
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test2);
      newTree.add(test3);
      if (newTree.countType(ChugiType.GRASS) != 2) {
        return false;
      }
      if (newTree.countType(ChugiType.POISON) != 4) {
        return false;
      }
      if (newTree.countType(ChugiType.ELECTRIC) != 1) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; 
  }
  
  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ChugiTree with four levels for instance, is 4.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    /**
     * (1) ensures that the height of an empty Chugimon tree is zero.
     */
    try {
      ChugiTree newTree = new ChugiTree();
      if (newTree.height() != 0) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (2) ensures that the height of a tree which consists of only one node is 1.
     */
    try {
      // Valid BST:  test1
      //        test2      test3
      //           test4       test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      newTree.add(test1);
      
      if (newTree.height() != 1) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    /**
     * (3) ensures that the height of a ChugiTree with four levels for instance, is 4.
     */
    try {
      // Valid BST:  test1
      //        test2      test3
      //      test4            test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test7);
      
      if (newTree.height() != 4) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() {
    try {
      ChugiTree newTree = new ChugiTree();
      if (newTree.getFirst() != null) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      newTree.add(test1);
      if (!newTree.getFirst().equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //                 test3
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      newTree.add(test1);
      newTree.add(test3);
      if (!newTree.getFirst().equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test2      test3
      //      test4            test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test7);
      if (!newTree.getFirst().equals(test4)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test4      test3
      //           test2      test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test7);
      if (!newTree.getFirst().equals(test4)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; 
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() {
    try {
      ChugiTree newTree = new ChugiTree();
      if (newTree.getLast() != null) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      newTree.add(test1);
      if (!newTree.getLast().equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //                 test3
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      newTree.add(test1);
      newTree.add(test2);
      if (!newTree.getLast().equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test2      test3
      //      test4            test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test2);
      newTree.add(test3);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test7);
      if (!newTree.getLast().equals(test7)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test1
      //        test4        test5
      //           test2  test3 

      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test5);
      newTree.add(test2);
      newTree.add(test3);
      if (!newTree.getLast().equals(test5)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() {
    // (1) Remove a Chugimon that is at leaf node
    try {
      // Valid BST:    test4
      //                   test6    
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      ChugiTree newTree = new ChugiTree();
      newTree.add(test1);
      newTree.add(test6);
      newTree.delete(test1);
      if (newTree.getFirst().compareTo(test6) != 0) {
        return false;
      }
      if (newTree.size() != 1) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Nidoeon#30.136")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      newTree.delete(test7);
      if (newTree.getFirst().compareTo(test1) != 0) {
        return false;
      }
      if (newTree.size() != 6) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // (2) Remove a Chugimon at non-leaf node. For each of these scenarios, check that the size of 
    // the tree was decremented by one and that the resulting ChugiTree is a valid BST
    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      newTree.delete(test1);
      if (newTree.getFirst().compareTo(test7) != 0) {
        return false;
      }
      if (newTree.size() != 6) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbysaur#1.2,Nidoeon#29.135,Nidoeon#29.136,"+
          "Nidoeon#30.134,Nidoeon#30.135,Nidoeon#30.136")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      newTree.delete(test2);
      if (newTree.getFirst().compareTo(test7) != 0) {
        return false;
      }
      if (newTree.size() != 6) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbysaur#1.2,Nidoeon#29.134,Nidoeon#29.136,"+
          "Nidoeon#30.134,Nidoeon#30.135,Nidoeon#30.136")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test3
      //        test2         test5
      //    test1          test4  test6
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test4);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.delete(test5);
      if (newTree.getFirst().compareTo(test7) != 0) {
        return false;
      }
      if (newTree.size() != 6) {
        return false;
      }
      if (!newTree.isValidBST()) {
        return false;
      }
      if (!newTree.toString().equals("Bulbysaur#1.2,Nidoeon#29.134,Nidoeon#29.135,"+
          "Nidoeon#29.136,Nidoeon#30.134,Nidoeon#30.136")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() {
    try {
      ChugiTree newTree = new ChugiTree();
      newTree.next(null);
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      newTree.add(test1);
      System.out.println(newTree.next(test1));
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test4      test3
      //           test2      test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test7);
      if (!newTree.next(test2).equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.next(test5).equals(test6)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                  
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.next(test1).equals(test2)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.next(test3).equals(test4)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                  
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.next(test7).equals(test1)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test7   test3   test5
      //       test1                  
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test1);
      newTree.add(test5);
      if (!newTree.next(test1).equals(test2)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test4      test3
      //           test2      test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test7);
      System.out.println(newTree.next(test7));
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() {
    try {
      ChugiTree newTree = new ChugiTree();
      newTree.next(null);
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      newTree.add(test1);
      System.out.println(newTree.previous(test1));
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test4      test3
      //           test2      test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test7);
      if (!newTree.previous(test2).equals(test4)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                  
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.previous(test3).equals(test2)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.previous(test5).equals(test4)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                  
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.previous(test6).equals(test5)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:    test4
      //        test2         test6
      //    test1   test3   test5
      // test7                 
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(29, 135); // Nidoeon#29.135
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(30, 134); // Nidoeon#30.134
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test6 = new Chugimon(30, 136); // Nidoeon#30.136
      Chugimon test7 = new Chugimon(1, 2); // Bulbysaur#1.2
      ChugiTree newTree = new ChugiTree();
      newTree.add(test4);
      newTree.add(test2);
      newTree.add(test6);
      newTree.add(test1);
      newTree.add(test7);
      newTree.add(test3);
      newTree.add(test5);
      if (!newTree.previous(test1).equals(test7)) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      // Valid BST:  test1
      //        test4      test3
      //           test2      test5
      //                          test7
      ChugiTree newTree = new ChugiTree();
      Chugimon test1 = new Chugimon(29, 134); // Nidoeon#29.134
      Chugimon test2 = new Chugimon(1, 2); // Bulbysaur#1.2
      Chugimon test3 = new Chugimon(29, 136); // Nidoeon#29.136
      Chugimon test4 = new Chugimon(1, 4); // Bulbmander#1.4
      Chugimon test5 = new Chugimon(30, 135); // Nidoeon#30.135
      Chugimon test7 = new Chugimon(30, 137); // Nidoeon#30.137
      newTree.add(test1);
      newTree.add(test4);
      newTree.add(test3);
      newTree.add(test2);
      newTree.add(test5);
      newTree.add(test7);
      System.out.println(newTree.previous(test4));
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testChugimonCompareToEquals: " + testChugimonCompareToEquals());
    System.out.println("testChugimonToString(): " + testChugimonToString());
    System.out.println("testIsValidBSTHelper(): " + testIsValidBSTHelper());
    System.out.println("testAddToStringSize(): " + testAddToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testCountType(): " + testCountType());
    System.out.println("testGetFirst(): " + testGetFirst());
    System.out.println("testGetLast(): " + testGetLast());
    System.out.println("testDelete(): " + testDelete());
    System.out.println("testNext(): " + testNext());
    System.out.println("testPrevious(): " + testPrevious());
  }

}
