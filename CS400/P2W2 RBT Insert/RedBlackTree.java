// --== CS400 Spring 2023 File Header Information ==--
// Name: Ethan Yikai Yan
// Email: eyyan@wisc.edu
// Team: BY
// TA: HAFEEZ ALI ANEES ALI
// Lecturer: Gary Dahl
// Notes to Grader: Added a complicated test case to each JUnit test on top of the 3 cases that were
//                  taught in lecture

import java.util.LinkedList;
import java.util.Stack;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        public int blackHeight = 0; // color of the node (0: red, 1: black, 2: double black)
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { this.data = data; }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; 
            size++; 
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (child == null || parent == null || child.context[0] == null) {
            throw new IllegalArgumentException("Illegal node");
        }
        if (!child.context[0].equals(parent)) {
            throw new IllegalArgumentException("Nodes not related!");
        }
        // Right Rotation
        if (child.equals(parent.context[1])) {
            Node<T> leftChild = child;
            
            parent.context[1] = child.context[2];
            
            if (leftChild.context[2] != null) {
                leftChild.context[2].context[0] = parent;
            }
            
            leftChild.context[2] = parent;
            replaceNode(parent, leftChild);
            parent.context[0] = leftChild;
        }
        // Left Rotation
        if (child.equals(parent.context[2])) {
            Node<T> rightChild = child;
            
            parent.context[2] = child.context[1];
            
            if (rightChild.context[1] != null) {
                rightChild.context[1].context[0] = parent;
            }
            
            rightChild.context[1] = parent;
            replaceNode(parent, rightChild);
            parent.context[0] = rightChild;
        }

    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }  
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // ensure new root node is black
                nodeWithData.blackHeight = 1;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    if (successorNode.blackHeight != 0) {
                        // Ensure fix double black problem
                        Node<T> doubleBlack = new Node(null);
                        doubleBlack.blackHeight = 2;
                        this.replaceNode(successorNode, doubleBlack);
                        enforceRBTreePropertiesAfterDelete(doubleBlack);
                        this.replaceNode(doubleBlack, null);
                    }
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child and change
                    // child to black
                    if (successorNode.blackHeight == 1 && successorNode.context[2].blackHeight == 0) {
                        successorNode.context[2].blackHeight = 1;
                    }
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                if (nodeWithData.blackHeight == 1 && nodeWithData.context[2].blackHeight == 0) {
                    nodeWithData.context[2].blackHeight = 1;
                }
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                if (nodeWithData.blackHeight == 1 && nodeWithData.context[1].blackHeight == 0) {
                    nodeWithData.context[1].blackHeight = 1;
                }
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                if (nodeWithData.blackHeight != 0) {
                    // Ensure fix double black problem
                    Node<T> doubleBlack = new Node(null);
                    doubleBlack.blackHeight = 2;
                    this.replaceNode(nodeWithData, doubleBlack);
                    enforceRBTreePropertiesAfterDelete(doubleBlack);
                    this.replaceNode(doubleBlack, null);
                }
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            return true;
        } 
    }

    private void enforceRBTreePropertiesAfterDelete(Node<T> node) {
        while (true) {
            if (node == root) {
                break;
            }
            else {
                Node<T> parent = node.context[0];

                Node<T> sibling;
                if (parent.context[1] == node) {
                    sibling = parent.context[2];
                    // Case 3: Sibling black and sibling both child black
                    if ((sibling == null || sibling.blackHeight == 1) && (sibling.context[1] == null || sibling.context[1].blackHeight == 1) 
                            && (sibling.context[2] == null || sibling.context[2].blackHeight == 1)) {
                        node.blackHeight--;
                        sibling.blackHeight--;
                        parent.blackHeight++;
                        if (parent.blackHeight == 2) {
                            enforceRBTreePropertiesAfterDelete(parent);
                        }
                        else {
                            break;
                        }
                    }

                    // Case 2: Sibling is red
                    else if (sibling.blackHeight == 0) {
                        rotate(sibling, parent);
                        sibling.blackHeight = 1;
                        parent.blackHeight = 0;
                        enforceRBTreePropertiesAfterDelete(node);
                    }

                    // Case 1: Sibling is black and has one red child
                    else if ((sibling == null || sibling.blackHeight == 1) && sibling.context[1].blackHeight == 0
                            || sibling.context[2].blackHeight == 0) {
                        int colorX = parent.blackHeight;
                        Node<T> siblingLeft = sibling.context[1];
                        Node<T> siblingRight = sibling.context[2];
                        if (siblingLeft.blackHeight == 0) {
                            rotate(siblingLeft, sibling);
                            siblingLeft.blackHeight = 1;
                            sibling.blackHeight = 0;
                            rotate(siblingLeft, parent);
                            parent.blackHeight = siblingLeft.blackHeight;
                            siblingLeft.blackHeight = colorX;
                            siblingLeft.context[2].blackHeight++;
                            node.blackHeight--;
                            break;
                        }
                        else if (siblingRight.blackHeight == 0) {
                            rotate(sibling, parent);
                            parent.blackHeight = sibling.blackHeight;
                            sibling.blackHeight = colorX;
                            siblingRight.blackHeight++;
                            node.blackHeight--;
                            break;
                        }
                    }
                } else if (parent.context[2] == node) {
                    sibling = parent.context[1];
                    // Case 3: Sibling black and sibling both child black
                    if ((sibling == null || sibling.blackHeight == 1) && (sibling.context[1] == null || sibling.context[1].blackHeight == 1) 
                            && (sibling.context[2] == null || sibling.context[2].blackHeight == 1)) {
                        node.blackHeight--;
                        sibling.blackHeight--;
                        parent.blackHeight++;
                        if (parent.blackHeight == 2) {
                            enforceRBTreePropertiesAfterDelete(parent);
                        }
                        else {
                            break;
                        }
                    }

                    // Case 2: Sibling is red
                    else if (sibling.blackHeight == 0) {
                        rotate(sibling, parent);
                        sibling.blackHeight = 1;
                        parent.blackHeight = 0;
                        enforceRBTreePropertiesAfterDelete(node);
                    }

                    // Case 1: Sibling is black and has one red child
                    else if ((sibling == null || sibling.blackHeight == 1) && sibling.context[1].blackHeight == 0
                            || sibling.context[2].blackHeight == 0) {
                        int colorX = parent.blackHeight;
                        Node<T> siblingLeft = sibling.context[1];
                        Node<T> siblingRight = sibling.context[2];
                        if (siblingLeft.blackHeight == 0) {
                            rotate(sibling, parent);
                            parent.blackHeight = sibling.blackHeight;
                            sibling.blackHeight = colorX;
                            siblingLeft.blackHeight++;
                            node.blackHeight--;
                            break;
                        }
                        else if (siblingRight.blackHeight == 0) {
                            rotate(siblingRight, sibling);
                            siblingRight.blackHeight = 1;
                            sibling.blackHeight = 0;
                            rotate(siblingRight, parent);
                            parent.blackHeight = siblingRight.blackHeight;
                            siblingRight.blackHeight = colorX;
                            siblingRight.context[1].blackHeight++;
                            node.blackHeight--;
                            break;
                        }
                    }
                } 
            }
        }
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The replacement
     * node may be null to remove the node from the tree.
     * @param nodeToReplace the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a node with two children.
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null; 
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.data.toString() + "("+Integer.toString(next.blackHeight)+ ")");
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * Helper method to enforce RBT properties after insertion. Uses a recursive loop to ensure that
     * the nodes being analyzed move upwards towards the root node before being terminated. The root
     * node is always being set back to black.
     * @param newNode the child/newNode that is being analyzed if there are any RBT violations and 
     *                the fixes to make based on the current node being analyzed.
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
        boolean loop = true;
        while (loop) {
            // Check if newNode has a parent. If node has no parent, set root to black and exit loop
            if (newNode.context[0] != null) {
                Node<T> parent = newNode.context[0];
                // Check if newNode and parent are both red. If not, no RBT violation on this node
                // and its parent. Also check if there is grandparent.
                if (newNode.blackHeight == 0 && parent.blackHeight == 0 && parent.context[0] != null) {
                    Node<T> grandparent = parent.context[0];
                    // parent is on grandparent's left and parent has a non-null sibling
                    if (grandparent.context[1] == parent && grandparent.context[2] != null) {
                        // newNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Left (Case 1)
                        if (parent.context[1] == newNode && parent.blackHeight == 0 && 
                                grandparent.context[2].blackHeight == 1) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Right (Case 2)
                        if (parent.context[2] == newNode && parent.blackHeight == 0 && 
                                grandparent.context[2].blackHeight == 1) {
                            // Rotate newNode and parent
                            rotate(newNode,parent);
                            // Rotate newNode and grandparent and colorswap
                            rotate(newNode, grandparent);
                            grandparent.blackHeight = 0;
                            newNode.blackHeight = 1;
                        }

                        // Parent is red and Parent's sibling is red (Case 3)
                        if (grandparent.blackHeight == 1 && grandparent.context[2].blackHeight == 0 
                                && parent.blackHeight == 0) {
                            // Re-coloring
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                            grandparent.context[2].blackHeight = 1;
                        }
                    }
                    // parent is on grandparent's left and parent has a null sibling
                    if (grandparent.context[1] == parent && grandparent.context[2] == null) {
                        // newNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Left (Case 1)
                        if (parent.context[1] == newNode && parent.blackHeight == 0) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Right (Case 2)
                        if (parent.context[2] == newNode && parent.blackHeight == 0) {
                            // Rotate newNode and parent
                            rotate(newNode,parent);
                            // Rotate newNode and grandparent and colorswap
                            rotate(newNode, grandparent);
                            grandparent.blackHeight = 0;
                            newNode.blackHeight = 1;
                        }
                    }
                    // parent is on grandparent's right and parent has a non-null sibling
                    if (grandparent.context[2] == parent && grandparent.context[1] != null) {
                        // newNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on right (Case 1)
                        if (parent.context[2] == newNode && parent.blackHeight == 0 && 
                                grandparent.context[1].blackHeight == 1) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on left (Case 2)
                        if (parent.context[1] == newNode && parent.blackHeight == 0 && 
                                grandparent.context[1].blackHeight == 1) {
                            // Rotate newNode and parent
                            rotate(newNode,parent);
                            // Rotate newNode and grandparent and colorswap
                            rotate(newNode, grandparent);
                            grandparent.blackHeight = 0;
                            newNode.blackHeight = 1;
                        }

                        // Parent is red and Parent's sibling is red (Case 3)
                        if (grandparent.blackHeight == 1 && grandparent.context[1].blackHeight == 0 
                                && parent.blackHeight == 0) {
                            // Re-coloring
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                            grandparent.context[1].blackHeight = 1;
                        }
                    }
                    // parent is on grandparent's right and parent has a null sibling
                    if (grandparent.context[2] == parent && grandparent.context[1] == null) {
                        // newNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on right (Case 1)
                        if (parent.context[2] == newNode && parent.blackHeight == 0) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on left (Case 2)
                        if (parent.context[1] == newNode && parent.blackHeight == 0) {
                            // Rotate newNode and parent
                            rotate(newNode,parent);
                            // Rotate newNode and grandparent and colorswap
                            rotate(newNode, grandparent);
                            grandparent.blackHeight = 0;
                            newNode.blackHeight = 1;
                        }
                    }
                }
                enforceRBTreePropertiesAfterInsert(parent); // recurse on parent node, go up tree
            }
            root.blackHeight = 1; // set root to black
            break; // exit loop if current newNode has no parent
        }
    }

    /**
     * JUnit test1
     * Case 1: Parent is red, Parent's sibling is black, newNode is on outside
     * This test covers cases when parent is on the left of grandparent and newNode is on the left
     * of the parent and when parent is on the right of grandparent and newNode is on the right of
     * the parent. There is an additional test to test overall functionalities of RBT insert.
     */
    @Test
    public void test1() {
        // parent is on left of grandparent, newNode on left of parent
        RedBlackTree<Integer> tester = new RedBlackTree<>();
        tester.insert(10);
        tester.insert(6);
        tester.insert(4);
        assertEquals(3, tester.size);
        assertEquals(0, tester.findNodeWithData(4).blackHeight);
        assertEquals(0, tester.findNodeWithData(10).blackHeight);
        assertEquals(1, tester.findNodeWithData(6).blackHeight);
        assertEquals(tester.findNodeWithData(6), tester.root);

        // parent is on right of grandparent, newNode on left of parent
        RedBlackTree<Integer> tester1 = new RedBlackTree<>();
        tester1.insert(10);
        tester1.insert(15);
        tester1.insert(20);
        assertEquals(3, tester1.size);
        assertEquals(0, tester1.findNodeWithData(20).blackHeight);
        assertEquals(0, tester1.findNodeWithData(10).blackHeight);
        assertEquals(1, tester1.findNodeWithData(15).blackHeight);
        assertEquals(tester1.findNodeWithData(15), tester1.root);
        
        // insert 6,7,8,9,10,5,4,3,2,1
        RedBlackTree<Integer> tester2 = new RedBlackTree<>();
        tester2.insert(6);
        tester2.insert(7);
        tester2.insert(8);
        tester2.insert(9);
        tester2.insert(10);
        assertEquals(5, tester2.size);
        assertEquals(0, tester2.findNodeWithData(8).blackHeight);
        assertEquals(0, tester2.findNodeWithData(10).blackHeight);
        assertEquals(1, tester2.findNodeWithData(6).blackHeight);
        assertEquals(1, tester2.findNodeWithData(7).blackHeight);
        assertEquals(1, tester2.findNodeWithData(9).blackHeight);
        assertEquals(tester2.findNodeWithData(7), tester2.root);

        tester2.insert(5);
        tester2.insert(4);
        tester2.insert(3);
        tester2.insert(2);
        tester2.insert(1);
        assertEquals(10, tester2.size);
        assertEquals(1, tester2.findNodeWithData(2).blackHeight);
        assertEquals(1, tester2.findNodeWithData(4).blackHeight);
        assertEquals(1, tester2.findNodeWithData(5).blackHeight);
        assertEquals(1, tester2.findNodeWithData(6).blackHeight);
        assertEquals(1, tester2.findNodeWithData(9).blackHeight);
        assertEquals(0, tester2.findNodeWithData(1).blackHeight);
        assertEquals(0, tester2.findNodeWithData(3).blackHeight);
        assertEquals(0, tester2.findNodeWithData(7).blackHeight);
        assertEquals(0, tester2.findNodeWithData(8).blackHeight);
        assertEquals(0, tester2.findNodeWithData(10).blackHeight);
        assertEquals(tester2.findNodeWithData(5), tester2.root);
    }

    /**
     * JUnit test2 
     * Case 2: Parent is red, Parent's sibling is black, newNode is on inside
     * This test covers cases when parent is on the left of grandparent and newNode is on the right
     * of the parent and when parent is on the right of grandparent and newNode is on the left of
     * the parent. There is an additional test to test overall functionalities of RBT insert.
     */
    @Test
    public void test2() {
        // parent is on left of grandparent, newNode on right of parent
        RedBlackTree<Integer> tester = new RedBlackTree<>();
        tester.insert(10);
        tester.insert(6);
        tester.insert(8);
        assertEquals(3, tester.size);
        assertEquals(0, tester.findNodeWithData(6).blackHeight);
        assertEquals(0, tester.findNodeWithData(10).blackHeight);
        assertEquals(1, tester.findNodeWithData(8).blackHeight);
        assertEquals(tester.findNodeWithData(8), tester.root);

        // parent is on right of grandparent, newNode on left of parent
        RedBlackTree<Integer> tester1 = new RedBlackTree<>();
        tester1.insert(10);
        tester1.insert(15);
        tester1.insert(12);
        assertEquals(3, tester1.size);
        assertEquals(0, tester1.findNodeWithData(15).blackHeight);
        assertEquals(0, tester1.findNodeWithData(10).blackHeight);
        assertEquals(1, tester1.findNodeWithData(12).blackHeight);
        assertEquals(tester1.findNodeWithData(12), tester1.root);
        
        // insert 1 to 10 test
        RedBlackTree<Integer> tester2 = new RedBlackTree<>();
        tester2.insert(1);
        tester2.insert(2);
        tester2.insert(3);
        tester2.insert(4);
        tester2.insert(5);
        assertEquals(5, tester2.size);
        assertEquals(0, tester2.findNodeWithData(5).blackHeight);
        assertEquals(0, tester2.findNodeWithData(3).blackHeight);
        assertEquals(1, tester2.findNodeWithData(1).blackHeight);
        assertEquals(1, tester2.findNodeWithData(2).blackHeight);
        assertEquals(1, tester2.findNodeWithData(4).blackHeight);
        assertEquals(tester2.findNodeWithData(2), tester2.root);

        tester2.insert(6);
        tester2.insert(7);
        tester2.insert(8);
        assertEquals(8, tester2.size);
        assertEquals(0, tester2.findNodeWithData(2).blackHeight);
        assertEquals(0, tester2.findNodeWithData(6).blackHeight);
        assertEquals(0, tester2.findNodeWithData(8).blackHeight);
        assertEquals(1, tester2.findNodeWithData(1).blackHeight);
        assertEquals(1, tester2.findNodeWithData(3).blackHeight);
        assertEquals(1, tester2.findNodeWithData(5).blackHeight);
        assertEquals(1, tester2.findNodeWithData(7).blackHeight);
        assertEquals(1, tester2.findNodeWithData(4).blackHeight);
        assertEquals(tester2.findNodeWithData(4), tester2.root);

        tester2.insert(9);
        tester2.insert(10);
        assertEquals(10, tester2.size);
        assertEquals(0, tester2.findNodeWithData(8).blackHeight);
        assertEquals(0, tester2.findNodeWithData(10).blackHeight);
        assertEquals(1, tester2.findNodeWithData(1).blackHeight);
        assertEquals(1, tester2.findNodeWithData(2).blackHeight);
        assertEquals(1, tester2.findNodeWithData(3).blackHeight);
        assertEquals(1, tester2.findNodeWithData(4).blackHeight);
        assertEquals(1, tester2.findNodeWithData(5).blackHeight);
        assertEquals(1, tester2.findNodeWithData(6).blackHeight);
        assertEquals(1, tester2.findNodeWithData(7).blackHeight);
        assertEquals(1, tester2.findNodeWithData(9).blackHeight);
        assertEquals(tester2.findNodeWithData(4), tester2.root);
    }

    /**
     * JUnit test3 
     * Case 3: Parent is red, Parent's sibling is red
     * This test covers cases when both the parent and its sibling are red. It covers cases where 
     * the newNode is both on the inside and outside of the parent. There is also an additional 
     * test to test overall functionalities of RBT insert.
     */
    @Test
    public void test3() {
        try {
            // parent is on left of grandparent, newNode on left of parent
            RedBlackTree<Integer> tester = new RedBlackTree<>();
            tester.insert(5);
            tester.insert(7);
            tester.insert(3);
            tester.insert(2);
            assertEquals(4, tester.size);
            assertEquals(0, tester.findNodeWithData(2).blackHeight);
            assertEquals(1, tester.findNodeWithData(3).blackHeight);
            assertEquals(1, tester.findNodeWithData(5).blackHeight);
            assertEquals(1, tester.findNodeWithData(7).blackHeight);
            assertEquals(tester.findNodeWithData(5), tester.root);

            // parent is on left of grandparent, newNode on right of parent
            RedBlackTree<Integer> tester1 = new RedBlackTree<>();
            tester1.insert(5);
            tester1.insert(7);
            tester1.insert(3);
            tester1.insert(4);
            assertEquals(4, tester1.size);
            assertEquals(0, tester1.findNodeWithData(4).blackHeight);
            assertEquals(1, tester1.findNodeWithData(3).blackHeight);
            assertEquals(1, tester1.findNodeWithData(5).blackHeight);
            assertEquals(1, tester1.findNodeWithData(7).blackHeight);
            assertEquals(tester1.findNodeWithData(5), tester1.root);

            // parent is on right of grandparent, newNode on left of parent
            RedBlackTree<Integer> tester2 = new RedBlackTree<>();
            tester2.insert(5);
            tester2.insert(7);
            tester2.insert(3);
            tester2.insert(6);
            assertEquals(4, tester2.size);
            assertEquals(0, tester2.findNodeWithData(6).blackHeight);
            assertEquals(1, tester2.findNodeWithData(3).blackHeight);
            assertEquals(1, tester2.findNodeWithData(5).blackHeight);
            assertEquals(1, tester2.findNodeWithData(7).blackHeight);
            assertEquals(tester2.findNodeWithData(5), tester2.root);

            // parent is on right of grandparent, newNode on right of parent
            RedBlackTree<Integer> tester3 = new RedBlackTree<>();
            tester3.insert(5);
            tester3.insert(7);
            tester3.insert(3);
            tester3.insert(8);
            assertEquals(4, tester3.size);
            assertEquals(0, tester3.findNodeWithData(8).blackHeight);
            assertEquals(1, tester3.findNodeWithData(3).blackHeight);
            assertEquals(1, tester3.findNodeWithData(5).blackHeight);
            assertEquals(1, tester3.findNodeWithData(7).blackHeight);
            assertEquals(tester3.findNodeWithData(5), tester3.root);

            // Insert in order: 5,7,3,1,4,2
            // Tests Case 3 when at a larger height of tree, ensures that recursive method goes up
            // the tree to enforce RBT properties.
            RedBlackTree<Integer> tester4 = new RedBlackTree<>();
            tester4.insert(5);
            tester4.insert(7);
            tester4.insert(3);
            tester4.insert(1);
            tester4.insert(4);
            tester4.insert(2);
            assertEquals(6, tester4.size);
            assertEquals(0, tester4.findNodeWithData(2).blackHeight);
            assertEquals(0, tester4.findNodeWithData(3).blackHeight);
            assertEquals(1, tester4.findNodeWithData(1).blackHeight);
            assertEquals(1, tester4.findNodeWithData(4).blackHeight);
            assertEquals(1, tester4.findNodeWithData(5).blackHeight);
            assertEquals(1, tester4.findNodeWithData(7).blackHeight);
            assertEquals(tester4.findNodeWithData(5), tester4.root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}