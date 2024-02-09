// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
/**
 * Generic class implementing a Binary Node of a Binary Search Tree (BST)
 *
 * @param <T> type of the data carried by this binary node
 */
public class BSTNode<T extends Comparable<T>> {
  private T data; // data in the node field
  private BSTNode<T> left; // reference to the left child
  private BSTNode<T> right; // reference to the right child

// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
  
  /**
   * Creates a BSTNode with a given data value
   * 
   * @param data data carried by this binary node
   * @throws NullPointerException if data is null
   */
  public BSTNode(T data) {
    if (data == null)
      throw new NullPointerException("Invalid data: CANNOT be null.");
    this.data = data;
  }

  /**
   * Creates a BSTNode with given data value, a reference to a left child (left
   * BST subtree) and a reference to a right child (right BST sub-tree).
   * 
   * @param data  element held by this binary node
   * @param left  reference to the left child
   * @param right reference to the right child
   * @throws NullPointerException if data is null
   */
  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
    if (data == null)
      throw new NullPointerException("Invalid data: CANNOT be null.");
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Getter of the data field
   * 
   * @return the data stored in this node
   */
  public T getData() {
    return data;
  }

  /**
   * Getter of the left child
   * 
   * @return the left child of this node
   */
  public BSTNode<T> getLeft() {
    return left;
  }

  /**
   * Setter for the left child
   * 
   * @param left the left to set
   */
  public void setLeft(BSTNode<T> left) {
    this.left = left;
  }

  /**
   * Getter of the right child
   * 
   * @return the right child of this node
   */
  public BSTNode<T> getRight() {
    return right;
  }

  /**
   * Setter for the right child
   * 
   * @param right the right to set
   */
  public void setRight(BSTNode<T> right) {
    this.right = right;
  }

}
