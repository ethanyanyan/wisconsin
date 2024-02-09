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
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc)
 * in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive
 * strategies only.
 *
 */
public class ChugiTree implements ChugidexStorage {

  /**
   * The root of this ChugiTree. Set to null when tree is empty.
   */
  private BSTNode<Chugimon> root;

  /**
   * The size of this ChugiTree (total number of Chugimon stored in this BST)
   */
  private int size;

  /**
   * Constructor for Chugitree. Should set size to 0 and root to null.
   */
  public ChugiTree() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Getter method for the Chugimon at the root of this BST.
   * 
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    return this.root.getData(); 
  }

  /**
   * A method for determining whether this ChugiTree is a valid BST. In
   * order to be a valid BST, the following must be true: For every internal
   * (non-leaf) node X of a binary tree, all the values in the node's left subtree
   * are less than the value in X, and all the values in the node's right subtree
   * are greater than the value in X.
   * 
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In
   * order to be a valid BST, the following must be true: For every internal
   * (non-leaf) node X of a binary tree, all the values in the node's left subtree
   * are less than the value in X, and all the values in the node's right subtree
   * are greater than the value in X.
   * 
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    if (node == null) {
      return true;
    }
    if (node.getLeft() == null && node.getRight() == null) {
      return true;
    }
    if (node.getLeft() != null && node.getRight() == null) {
      BSTNode<Chugimon> maxLeft = isMax(node.getLeft()); // Get greatest node on left
      if (maxLeft.getData().compareTo(node.getData()) >= 0) {
        return false;
      }
      return isValidBSTHelper(node.getLeft());
    }
    if (node.getLeft() == null && node.getRight() != null) {
      BSTNode<Chugimon> leastRight = isMin(node.getRight()); // Get least node on right
      if (leastRight.getData().compareTo(node.getData()) <= 0) {
        return false;
      }
      return isValidBSTHelper(node.getRight());
    }
    if (node.getLeft() != null && node.getRight() != null) {
      BSTNode<Chugimon> maxLeft2 = isMax(node.getLeft()); // Get greatest node on left
      BSTNode<Chugimon> leastRight2 = isMin(node.getRight()); // Get least node on right
      if (leastRight2.getData().compareTo(node.getData()) <= 0 || 
          maxLeft2.getData().compareTo(node.getData()) >= 0) {
        return false;
      }
      return isValidBSTHelper(node.getLeft()) && isValidBSTHelper(node.getRight());
    }

    return false;
  }

  /**
   * A helper method to find maximum element in the left side of a BST
   * @param node root of the BST
   * @return maximum element in a BST
   */
  private static BSTNode<Chugimon> isMax (BSTNode<Chugimon> node) {
    BSTNode<Chugimon> leftMax;  
    BSTNode<Chugimon> rightMax;  
    //Max will store temp's data  
    BSTNode<Chugimon> max = node;  
      
    //It will find largest element in left subtree  
    if(node.getLeft() != null){  
        leftMax = isMax(node.getLeft());  
        //Compare max with leftMax and store greater value into max  
        if (max.getData().compareTo(leftMax.getData()) > 0) {
          max = max;
        } 
        if (max.getData().compareTo(leftMax.getData()) < 0) {
          max = leftMax;
        } 
    }  
      
    //It will find largest element in right subtree  
    if(node.getRight() != null){  
        rightMax = isMax(node.getRight());  
        //Compare max with rightMax and store greater value into max  
        if (max.getData().compareTo(rightMax.getData()) > 0) {
          max = max;
        } 
        if (max.getData().compareTo(rightMax.getData()) < 0) {
          max = rightMax;
        } 
    }  
    return max;
  }

  /**
   * A helper method to find minimum element in the right side of a BST
   * @param node root of the BST
   * @return minimum element in a BST
   */
  private static BSTNode<Chugimon> isMin (BSTNode<Chugimon> node) {
    BSTNode<Chugimon> leftMin;  
    BSTNode<Chugimon> rightMin;  
    //Max will store temp's data  
    BSTNode<Chugimon> min = node;  
      
    //It will find minimum element in left subtree  
    if(node.getLeft() != null){  
        leftMin = isMin(node.getLeft());  
        //Compare max with leftMax and store greater value into max  
        if (min.getData().compareTo(leftMin.getData()) > 0) {
          min = leftMin;
        } 
        if (min.getData().compareTo(leftMin.getData()) < 0) {
          min = min;
        } 
    }  
      
    //It will find minimum element in right subtree  
    if(node.getRight() != null){  
        rightMin = isMin(node.getRight());  
        //Compare max with rightMax and store greater value into max  
        if (min.getData().compareTo(rightMin.getData()) > 0) {
          min = rightMin;
        } 
        if (min.getData().compareTo(rightMin.getData()) < 0) {
          min = min;
        } 
    }  
    return min;

  }

  /**
   * Checks whether this ChugiTree is empty or not
   * 
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    }
    return false; 
  }

  /**
   * Gets the size of this ChugiTree
   * 
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    return this.size; 
  }

  /**
   * Returns a String representation of all the Chugimons stored within this
   * ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method.
   * The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces
   * are expected to be
   * in the resulting string. No comma should be at the end of the resulting
   * string. For instance,
   * 
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   * 
   * @return a string containing all of the Chugimon, in the increasing order.
   *         Returns an empty
   *         string "" if this BST is empty.
   * 
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the
   * ChugiTree rooted at node. An example of the String representation of the
   * contents of a ChugiTree storing three Chugimons is provided in the
   * description of the above toString() method.
   * 
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree
   *         rooted at node in
   *         increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    String returnString = "";
    if (node == null) {
      return "";
    }
    if (node.getLeft() != null) {
      returnString = toStringHelper(node.getLeft()) + "," + node.getData().toString();
    }
    if (node.getRight() != null) {
      if (node.getLeft() != null) {
        returnString = returnString + "," + toStringHelper(node.getRight());
      }
      if (node.getLeft() == null) {
        returnString = returnString + node.getData().toString() + "," + toStringHelper(node.getRight());
      }
    }
    if (node.getRight() == null && node.getLeft() == null) {
      returnString = returnString + node.getData().toString();
    }
    return returnString; 
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   * 
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if if the newChugimon was successfully added to the ChugiTree,
   *         false if a match with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) {
    if (newChugimon == null) {
      throw new IllegalArgumentException("newChugimon cannot be null");
    }

    if (this.size == 0 && this.root == null) {
      this.root = new BSTNode<Chugimon>(newChugimon, null, null);
      this.size++;
      return true;
    }

    if (addHelper(newChugimon, this.root)) {
      this.size++;
      return true;
    }

    return false; 
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   * 
   * @param node        The "root" of the subtree we are inserting the new
   *                    Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We
   *                    assume that newChugimon is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree,
   *         false if a match with
   *         newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    if (newChugimon.compareTo(node.getData()) < 0) {
      if (node.getLeft() == null) {
        BSTNode<Chugimon> newNode = new BSTNode<Chugimon>(newChugimon);
        node.setLeft(newNode);
        return true;
      }
      return addHelper(newChugimon, node.getLeft());
    }
    if (newChugimon.compareTo(node.getData()) > 0) {
      if (node.getRight() == null) {
        BSTNode<Chugimon> newNode2 = new BSTNode<Chugimon>(newChugimon);
        node.setRight(newNode2);
        return true;
      }
      return addHelper(newChugimon, node.getRight());
    }
    if (newChugimon.compareTo(node.getData()) == 0) {
      return false;
    }

    return false; 
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   * 
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    Chugimon findChugi = new Chugimon(firstId, secondId);
    return lookupHelper(findChugi, root); 
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in
   * the subtree rooted at node, if present.
   * 
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume
   *               that toFind is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a
   *               match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    if (node == null) {
      return null;
    }
    if (toFind.compareTo(node.getData()) < 0) {
      if (node.getLeft() != null) {
        return lookupHelper(toFind, node.getLeft());
      }
    }
    if (toFind.compareTo(node.getData()) > 0) {
      if (node.getRight() != null) {
        return lookupHelper(toFind, node.getRight());
      }
    }
    if (toFind.compareTo(node.getData()) == 0) {
      return node.getData();
    }
    return null;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES
   * from root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    
    return heightHelper(this.root); 
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at
   * node counting the number of nodes and NOT the number of edges from node to
   * the deepest leaf
   * 
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    int leftHeight = 0;
    int rightHeight = 0;
    if (node == null) {
      return 0;
    }
    if (node.getLeft() != null) {
      leftHeight = heightHelper(node.getLeft());
    }
    if (node.getRight() != null) {
      rightHeight = heightHelper(node.getRight());
    }
    return Math.max(leftHeight, rightHeight) + 1; 
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing
   * order, within this ChugiTree (meaning the smallest element stored in the
   * tree).
   * 
   * @return the first element in the increasing order of this BST, and null if
   *         the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    Chugimon returnChugi = null;
    if (this.isValidBST()) {
      returnChugi = getFirstHelper(this.root);
    }
    return returnChugi;
  }

  /**
   * Recursive helper method for getFirst().
   * 
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    Chugimon firstChugi = null;
    if (root == null) {
      return null;
    }
    if (root != null) {
      firstChugi = root.getData();
      if (root.getLeft() != null) {
        firstChugi = getFirstHelper(root.getLeft());
      }
    }
    return firstChugi;
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing
   * order, within this ChugiTree (meaning the greatest element stored in the
   * tree).
   * 
   * @return the last element in the increasing order of this BST, and null if the
   *         tree is empty.
   */
  @Override
  public Chugimon getLast() {
    Chugimon returnChugi = null;
    if (this.isValidBST()) {
      returnChugi = getLastHelper(this.root);
    }
    return returnChugi;
  }

  /**
   * Recursive helper method for getLast().
   * 
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    Chugimon lastChugi = null;
    if (root == null) {
      return null;
    }
    if (root != null) {
      lastChugi = root.getData();
      if (root.getRight() != null) {
        lastChugi = getLastHelper(root.getRight());
      }
    }

    return lastChugi; // default return statement
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary
   * type of the specified type, stored in this ChugiTree.
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is
   *                  NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary
   *         type of the
   *         specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    return countTypeHelper(this.root, chugiType);
  }

  /**
   * Recursive helper method for countType(ChugiType chugiType).
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @param root the root node to begin count
   * @return the number of Chugimons with type of chugiType in the BST with the given root node
   */
  protected static int countTypeHelper (BSTNode<Chugimon> root, ChugiType chugiType) {
    int count = 0;
    if (root == null) {
      return 0;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      if (root.getData().getPrimaryType() == chugiType || 
          root.getData().getSecondaryType() == chugiType) {
      count = count + 1;
      }
    }
    if (root.getLeft() != null && root.getRight() == null) {
      count = countTypeHelper(root.getLeft(), chugiType);
      if (root.getData().getPrimaryType() == chugiType || 
          root.getData().getSecondaryType() == chugiType) {
      count = count + 1;
      }
    }
    if (root.getLeft() == null && root.getRight() != null) {
      count = countTypeHelper(root.getRight(), chugiType);
      if (root.getData().getPrimaryType() == chugiType || 
          root.getData().getSecondaryType() == chugiType) {
      count = count + 1;
      }
    }
    if (root.getLeft() != null && root.getRight() != null) {
      count = countTypeHelper(root.getLeft(),chugiType)+countTypeHelper(root.getRight(),chugiType);
      if (root.getData().getPrimaryType() == chugiType || 
          root.getData().getSecondaryType() == chugiType) {
      count = count + 1;
      }
    }
    return count;
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this
   * ChugiTree
   * 
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the
   *                                  Chugimon provided as input has no in-order
   *                                  successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("param Chugi cannot be null!");
    }
    Chugimon returnChugi = null;
    if (this.isValidBST()) {
      returnChugi = nextHelper(chugi, root, null);
    }
    return returnChugi;
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree
   * rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order successor. We assume that
   *              <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potentional candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the
   *                                Chugimon provided as input has no in-order
   *                                successor in the subtree
   *                                rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node, BSTNode next) {
    // base case: node is null
    if (node == null) {
      throw new NoSuchElementException("No successor found");
    }

    // recursive cases:
    // (1) if chugi is found and if the right child is not null, use getFirstHelper
    // to find and
    // return the next chugimon. It should be the left most child of the right
    // subtree
    if (chugi.equals(node.getData()) && node.getRight() != null) {
      return getFirstHelper(node.getRight());
    }

    // (2) if chugi is less than the Chugimon at node, set next as the root node and
    // search
    // recursively into the left subtree
    if (chugi.compareTo(node.getData()) < 0) {
      return nextHelper(chugi, node.getLeft(), node);
    }

    // (3) if chugi is found and its right child is null, if next is not null, it should contain 
    // the next data (that's the potential next), return it. If next is null, this means that this 
    // node does not have a next element.
    if (chugi.equals(node.getData()) && node.getRight() == null) {
      if (next != null) {
        return (Chugimon) next.getData();
      }
      if (next == null) {
        throw new NoSuchElementException("No successor found");
      }
    }

    // (4) if chugi is greater than the Chugimon at node, recurse right 
    if (chugi.compareTo(node.getData()) > 0) {
      return nextHelper(chugi, node.getRight(), next);
    }
    return null;
  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this
   * ChugiTree
   * 
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * 
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the
   *                                  provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree
   * rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order predecessor. We assume that
   *              <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the
   *                                Chugimon provided as input has no in-order
   *                                predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) {
    // base case: node is null
    if (node == null) {
      throw new NoSuchElementException("No predecessor found");
    }
    // recursive cases:
    // (1) if chugi is found and if the left child is not null, use getLastHelper to
    // find and return
    // the previous chugimon. It should be the right most child of the left subtree
    if (chugi.equals(node.getData()) && node.getLeft() != null) {
      return getLastHelper(node.getLeft());
    }

    // (2) if chugi is greater than the Chugimon at node, set prev as the root node
    // and search
    // recursively into the right subtree
    if (chugi.compareTo(node.getData()) > 0) {
      return previousHelper(chugi, node.getRight(), node);
    }

    // (3) if chugi is found and its left child is null, if next is not null, it should contain 
    // the next data (that's the potential next), return it. If next is null, this means that this 
    // node does not have a previous element.
    if (chugi.equals(node.getData()) && node.getLeft() == null) {
      if (prev != null) {
        return (Chugimon) prev.getData();
      }
      if (prev == null) {
        throw new NoSuchElementException("No predecessor found");
      }
    }

    // (4) if chugi is lesser than the Chugimon at node, recurse left 
    if (chugi.compareTo(node.getData()) < 0) {
      return previousHelper(chugi, node.getLeft(), prev);
    }

    return null;
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   * 
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no
   *         match found with any
   *         Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("Param Chugi cannot be null");
    }
    if (isEmpty()) {
      return false;
    }
    try {
      if (this.size == 1) {
        this.root = null;
        this.size--;
      }
      if (this.root.getData().compareTo(chugi) == 0) {
        this.root = deleteChugimonHelper(chugi, root);
        this.size--;
      }
      if (this.root.getData().compareTo(chugi) != 0) {
        deleteChugimonHelper(chugi, root);
        this.size--;
      }
    }
    catch (NoSuchElementException e) {
      return false;
    }

    return true; 
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST
   * rooted at node
   * 
   * @param target a reference to a Chugimon to delete from the BST rooted at
   *               node. We assume that target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a
   *               match with the target Chugimon.
   * 
   * 
   * @return the new "root" of the subtree we are checking after trying to remove
   *         target
   * @throws NoSuchElementException with a descriptive error message if there is
   *                                no Chugimon matching target in the BST rooted
   *                                at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) {
    // if node == null (empty subtree rooted at node), no match found, throw an
    // exception
    if (node == null) {
      throw new NoSuchElementException("No Chugimon matching target");
    }

    if (target.compareTo(node.getData()) < 0)
    // recur on the left subtree rooted at node.getLeft()
      node.setLeft(deleteChugimonHelper(target, node.getLeft())); 
    else if (target.compareTo(node.getData()) > 0)
    // recur on the right subtree
      node.setRight(deleteChugimonHelper(target, node.getRight()));
    else { // item found
      // Case 1: if the node is a leaf, its removal does not disconnect the tree,
      // So, we can remove it immediately
      if (node.getLeft() == null && node.getRight() == null)
        return null;
      // Case 2: if the node has only one child, we can remove the node after adjusting its parent's
      // child link to bypass the node.
      else {
        if (node.getLeft() == null) { // node.getRight() != null
          return node.getRight();
        } else if (node.getRight() == null) { // node.getLeft() != null
          return node.getLeft();
        } else { // both children are NOT null
          // Case 3: The complicated case: the node has two children
          // either replace the item in this node with the smallest item in the right subtree and
          // then remove that node (successor).
          BSTNode<Chugimon> successor = new BSTNode<Chugimon> (getFirstHelper(node.getRight()));
          // remove that binary node (the binary node holding the smallest element at the right subtree)
          // that node should be either a leaf or a node that has only one child (right child)
          successor.setRight(deleteChugimonHelper(successor.getData(), node.getRight()));
          successor.setLeft(node.getLeft());
          node = successor;
        }
      }
    }

    return node; 
  }
}
