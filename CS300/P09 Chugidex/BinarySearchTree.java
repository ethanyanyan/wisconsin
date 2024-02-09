

import java.util.NoSuchElementException;
import bstComplete.BinaryNode;

/**
 * CS300 - Programming II University of Wisconsin at Madison I Implementation of a generic
 * BinarySearchTree
 * 
 * @param <T> type parameter of the data field of a binary node T should be a reference type (class)
 *            that implements the comparable interface or extends a class that implements the
 *            comparable interface (compareTo method should be defined to compare to objects of type
 *            <T>
 */

public class BinarySearchTree<T extends Comparable<T>> {
  private BinaryNode<T> root; // root of the BST

  // /**
  // * Creates an empty instance of a Binary Search Tree
  // */
  // public BinarySearchTree() {
  // root = null;
  // }


  /**
   * Checks whether the BST is empty
   * 
   * @return true if the BST is empty, false otherwise
   */
  public boolean isEmpty() {
    // O(1)
    return root == null;
  }

  /**
   * print all the elements in the BST according to the pre-order traversal algorithm Time
   * Complexity: O(?) ; The problem size n represents the number of nodes in the tree
   */
  public void printPreOrder() {
    if (isEmpty()) {
      System.out.println("This BST is empty.");
    } else {
      printPreOrderHelper(root); // recursive algorithm
    }
  }

  /**
   * Recursive helper method to perform a pre-order traversal of the BST
   * 
   * @param currentNode
   */
  private void printPreOrderHelper(BinaryNode<T> currentNode) {
    // process the parent
    System.out.print(currentNode.getData() + " ");
    // recur on the left sub-tree if currentNode has a left child
    if (currentNode.getLeft() != null)
      printPreOrderHelper(currentNode.getLeft());
    // recur on the right sub-tree if the currentNode has a right child
    if (currentNode.getRight() != null)
      printPreOrderHelper(currentNode.getRight());
  }


  /**
   * Prints the Tree content in a sorted order: performs an in-order traversal of the BST
   */
  public void printTreeSortedOrder() {
    // root.printInOrder(); // if printInOrder() is defined in the Binary node
    System.out.print(printInOrderHelper(root));
  }

  /**
   * Recursive helper method to perform a in-order traversal of the BST
   * 
   * @param currentNode root of the subtree
   */
  private static <T> String printInOrderHelper(BinaryNode<T> currentNode) {
    String result = ""; // String representation of the in-order traversal of the subtree routed at
                        // currentNode, initialized to an empty String
    // Base case(s)?
    // Recursive cases?

    return result;
  }


  /**
   * Checks whether the BST contains item
   * 
   * @param item: The item we're looking for
   * @return true if item is present in the Binary Search Tree, false otherwise
   */
  public boolean contains(T item) {
    return containsHelper(item, root);
  }

  /**
   * Helper method that checks if the subtree rooted at a given node contains item
   * 
   * @param item to look for in the subtree rooted at node
   * @param node current node in a binary search tree
   * @return true if the subtree rooted at node contains item, false otherwise
   */
  private boolean containsHelper(T item, BinaryNode<T> node) {
    // Recursive algorithm (use compareTo() method to compare items)
    // Base case(s)??

    // Recursive cases??

    return false; // default return statement added to resolve compile errors
  }



  /**
   * Computes and returns the number of items stored in this BST
   * 
   * @return the number of items stored in this BST
   */
  public int size() {
    return sizeHelper(root);

  }

  /**
   * Helper method that returns the number of items stored in the subtree rooted at a given
   * BinaryNode
   * 
   * @param current BinaryNode that represents a root of a subtree
   * @return the size of the subtree rooted at current
   */
  private static <T> int sizeHelper(BinaryNode<T> current) {
    // Base case ??
    // recursive cases ??

    return 0; // default return statement added to resolve the compile error
  }



  /**
   * Adds item to this Binary Search Tree
   * 
   * @param item to insert/add to this binary search tree (BST)
   * @throws an Exception if item is already stored in the tree
   */
  public void add(T item) {
    if (isEmpty()) // tree is empty
      root = new BinaryNode<T>(item); // add item at root position of this BST
    else
      addHelper(item, root); // make call to addHelper to recursively add item to this BST
  }

  // Return the parent node of item,
  /**
   * Utility/Helper method to insert into a subtree.
   * 
   * @param newItem the item to add.
   * @param node    the node that roots the tree.
   * @return the new root.
   * @throws IllegalArgumentException if the newItem is already present.
   */
  private void addHelper(T newItem, BinaryNode<T> node) {
    // In this implementation, we won't allow duplicate items
    // Recursive algorithm (use compareTo() method to compare items)
    // Base case(s)?

    // Recursive cases?
  }

  /**
   * Removes item from this BST
   * 
   * @param item to be removed from this BinarySearchTree and
   * @throws NoSuchElementException if item is not found in the tree
   */
  public void remove(T item) {
    root = removeHelper(item, root);
  }

  /**
   * Helper method to remove from a specific item from a subtree.
   * 
   * @param item the item to remove.
   * @param node the node that roots the subtree.
   * @return the new root of the subtree.
   * @throws NoSuchElementException if item is not found in the tree
   */
  private BinaryNode<T> removeHelper(T item, BinaryNode<T> node) {
    if (node == null) // base case
      // item not found
      throw new NoSuchElementException("Error! Item not found.");
    if (item.compareTo(node.getData()) < 0)
      node.setLeft(removeHelper(item, node.getLeft())); // recur on the left subtree rooted at
                                                        // node.getLeft()
    else if (item.compareTo(node.getData()) > 0)
      node.setRight(removeHelper(item, node.getRight())); // recur on the right subtree
    else { // item found
      // TODO (Complete the missing code with respect to the following hints
      // The most difficult operation to implement for a BST is the remove operation.
      // Once you find the node to be removed, several possibilities should be considered.
      //
      // The first problem is that the removal of a node may disconnect parts of the tree.
      // If that happens, the tree should be carefully re-attached such that the binary
      // search tree property is maintained.
      //
      // The second problem to consider is that we would like to avoid making the tree
      // unnecessarily deep since the depth of the tree affects the running time of the
      // different binary search tree algorithms (except isEmpty).
      //
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
          // then
          // remove that node (successor).
          //node.setData(getMin(node.getRight()).getData()); // replace it with its successor

          // remove that binary node (the binary node holding the smallest element at the right subtree)
          // that node should be either a leaf or a node that has only one child (right child)
          node.setRight(removeHelper(node.getData(), node.getRight()));
          // or
          // replace the item in this node with the largest item in the left subtree and then
          // remove that node.
          
        }
      }

    }
    return node; // return the new root of this subtree, otherwise, the changes will be lost after
                 // the method returns
  }


}
