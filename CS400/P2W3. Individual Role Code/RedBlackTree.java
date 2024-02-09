import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RedBlackTree<T extends Comparable<T>> implements RedBlackTreeInterface<T> {
    protected RBTNode<T> root; // reference to root RBTNode of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new RBTNode in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree, or argument is
     *                                  not of type Item
     */
    public boolean insert(T item) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(item == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        if(!(item instanceof Item)) {
            throw new IllegalArgumentException("Insert function only takes arguments of type Item");
        }
        Item insertItem = (Item) item;
        RBTNode<T> newRBTNode = new RBTNode<>(insertItem);

        if (this.root == null) {
            // add first RBTNode to an empty tree
            root = newRBTNode; 
            size++; 
            enforceRBTreePropertiesAfterInsert(newRBTNode);
            return true;
        } else {
            // insert into subtree
            RBTNode<T> current = this.root;
            while (true) {
                int compare = newRBTNode.getName().compareTo(current.getName());
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + insertItem.getName());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newRBTNode;
                        newRBTNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newRBTNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newRBTNode;
                        newRBTNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newRBTNode);
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
     * Performs the rotation operation on the provided RBTNodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided RBTNodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the RBTNode being rotated from child to parent position
     *      (between these two RBTNode arguments)
     * @param parent is the RBTNode being rotated from parent to child position
     *      (between these two RBTNode arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      RBTNode references are not initially (pre-rotation) related that way
     */
    private void rotate(RBTNode<T> child, RBTNode<T> parent) throws IllegalArgumentException {
        if (child == null || parent == null || child.context[0] == null) {
            throw new IllegalArgumentException("Illegal RBTNode");
        }
        if (!child.context[0].equals(parent)) {
            throw new IllegalArgumentException("RBTNodes not related!");
        }
        // Right Rotation
        if (child.equals(parent.context[1])) {
            RBTNode<T> leftChild = child;
            
            parent.context[1] = child.context[2];
            
            if (leftChild.context[2] != null) {
                leftChild.context[2].context[0] = parent;
            }
            
            leftChild.context[2] = parent;
            replaceRBTNode(parent, leftChild);
            parent.context[0] = leftChild;
        }
        // Left Rotation
        if (child.equals(parent.context[2])) {
            RBTNode<T> rightChild = child;
            
            parent.context[2] = child.context[1];
            
            if (rightChild.context[1] != null) {
                rightChild.context[1].context[0] = parent;
            }
            
            rightChild.context[1] = parent;
            replaceRBTNode(parent, rightChild);
            parent.context[0] = rightChild;
        }

    }

    /**
     * Get the size of the tree (its number of RBTNodes).
     * @return the number of RBTNodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any RBTNode).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Method to remove all nodes in the RBT
     */
    public void removeAll() {
        this.size = 0;
        this.root = null;
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
            RBTNode<T> nodeWithData = this.findRBTNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }  
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                RBTNode<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.name = successorNode.name;
                nodeWithData.item = successorNode.item;
                // ensure new root node is black
                nodeWithData.blackHeight = 1;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    if (successorNode.blackHeight != 0) {
                        // Ensure fix double black problem
                        RBTNode<T> doubleBlack = new RBTNode(null);
                        doubleBlack.blackHeight = 2;
                        this.replaceRBTNode(successorNode, doubleBlack);
                        enforceRBTreePropertiesAfterDelete(doubleBlack);
                        this.replaceRBTNode(doubleBlack, null);
                    }
                    this.replaceRBTNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child and change
                    // child to black
                    if (successorNode.blackHeight == 1 && successorNode.context[2].blackHeight == 0) {
                        successorNode.context[2].blackHeight = 1;
                    }
                    this.replaceRBTNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                if (nodeWithData.blackHeight == 1 && nodeWithData.context[2].blackHeight == 0) {
                    nodeWithData.context[2].blackHeight = 1;
                }
                this.replaceRBTNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                if (nodeWithData.blackHeight == 1 && nodeWithData.context[1].blackHeight == 0) {
                    nodeWithData.context[1].blackHeight = 1;
                }
                this.replaceRBTNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                if (nodeWithData.blackHeight != 0) {
                    // Ensure fix double black problem
                    RBTNode<T> doubleBlack = new RBTNode(null);
                    doubleBlack.blackHeight = 2;
                    this.replaceRBTNode(nodeWithData, doubleBlack);
                    enforceRBTreePropertiesAfterDelete(doubleBlack);
                    this.replaceRBTNode(doubleBlack, null);
                } else {
                    this.replaceRBTNode(nodeWithData, null);
                }
            }
            this.size--;
            return true;
        } 
    }

    /**
     * Helper method to fix double black nodes after remove operation
     * @param node Node with the double black count
     */
    private void enforceRBTreePropertiesAfterDelete(RBTNode<T> node) {
        while (true) {
            if (node == root) {
                if (node.blackHeight == 2){
                    node.blackHeight--;
                }
                break;
            }
            else {
                RBTNode<T> parent = node.context[0];

                RBTNode<T> sibling;
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
                            break;
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
                        break;
                    }

                    // Case 1: Sibling is black and has one red child
                    else if ((sibling == null || sibling.blackHeight == 1) && sibling.context[1].blackHeight == 0
                            || sibling.context[2].blackHeight == 0) {
                        int colorX = parent.blackHeight;
                        RBTNode<T> siblingLeft = sibling.context[1];
                        RBTNode<T> siblingRight = sibling.context[2];
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
                            break;
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
                        break;
                    }

                    // Case 1: Sibling is black and has one red child
                    else if ((sibling == null || sibling.blackHeight == 1) && sibling.context[1].blackHeight == 0
                            || sibling.context[2].blackHeight == 0) {
                        int colorX = parent.blackHeight;
                        RBTNode<T> siblingLeft = sibling.context[1];
                        RBTNode<T> siblingRight = sibling.context[2];
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
        } else if (data instanceof Item) {
            RBTNode<T> RBTNodeWithData = this.findRBTNodeWithData(data);
            // return false if the RBTNode is null, true otherwise
            return (RBTNodeWithData != null);
        }
        else {
            return false;
        }
    }

    /**
     * Helper method that will replace a RBTNode with a replacement RBTNode. The replacement
     * RBTNode may be null to remove the RBTNode from the tree.
     * @param RBTNodeToReplace the RBTNode to replace
     * @param replacementRBTNode the replacement for the RBTNode (may be null)
     */
    protected void replaceRBTNode(RBTNode<T> RBTNodeToReplace, RBTNode<T> replacementRBTNode) {
        if (RBTNodeToReplace == null) {
            throw new NullPointerException("Cannot replace null RBTNode.");
        }
        if (RBTNodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementRBTNode != null)
                replacementRBTNode.context[0] = null;
            this.root = replacementRBTNode;
        } else {
            // set the parent of the replacement RBTNode
            if (replacementRBTNode != null)
                replacementRBTNode.context[0] = RBTNodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (RBTNodeToReplace.isRightChild()) {
                RBTNodeToReplace.context[0].context[2] = replacementRBTNode;
            } else {
                RBTNodeToReplace.context[0].context[1] = replacementRBTNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a RBTNode with two children.
     * @param RBTNode the RBTNode to find the successor for
     * @return the RBTNode that is the inorder successor of RBTNode
     */
    protected RBTNode<T> findMinOfRightSubtree(RBTNode<T> RBTNode) {
        if (RBTNode.context[1] == null && RBTNode.context[2] == null) {
            throw new IllegalArgumentException("RBTNode must have two children");
        }
        // take a step to the right
        RBTNode<T> current = RBTNode.context[2];
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
     * Helper method that will return the RBTNode in the tree that contains a specific
     * value. Returns null if there is no RBTNode that contains the value.
     * @return the RBTNode that contains the data, or null of no such RBTNode exists
     */
    protected RBTNode<T> findRBTNodeWithData(T data) {
        RBTNode<T> current = this.root;
        if (!(data instanceof Item)) {
            throw new IllegalArgumentException("data must be of type Item");
        }
        Item searchData = (Item) data;
        while (current != null) {
            int compare = searchData.getName().compareTo(current.getName());
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
        // we're at a null RBTNode and did not find data, so it's not in the tree
        return null; 
    }

    /**
     * Method that will return the RBTNode in the tree that contains a specific
     * value. Returns null if there is no RBTNode that contains the value.
     * @return the RBTNode that contains the data, or null of no such RBTNode exists
     */
    public Item findByName(String name) {
        RBTNode<T> current = this.root;
        Item searchData = new Item(0, name, "", 0, "", 0, "");
        while (current != null) {
            int compare = searchData.getName().compareTo(current.getName());
            if (compare == 0) {
                // we found our value
                return current.getItem();
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null RBTNode and did not find data, so it's not in the tree
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
            Stack<RBTNode<T>> RBTNodeStack = new Stack<>();
            RBTNode<T> current = this.root;
            while (!RBTNodeStack.isEmpty() || current != null) {
                if (current == null) {
                    RBTNode<T> popped = RBTNodeStack.pop();
                    sb.append(popped.getName());
                    if(!RBTNodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    RBTNodeStack.add(current);
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
            LinkedList<RBTNode<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                RBTNode<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.getName() + "(" +Integer.toString(next.blackHeight)+ ")");
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
     * the RBTNodes being analyzed move upwards towards the root RBTNode before being terminated. The root
     * RBTNode is always being set back to black.
     * @param newRBTNode the child/newRBTNode that is being analyzed if there are any RBT violations and 
     *                the fixes to make based on the current RBTNode being analyzed.
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newRBTNode) {
        boolean loop = true;
        while (loop) {
            // Check if newRBTNode has a parent. If RBTNode has no parent, set root to black and exit loop
            if (newRBTNode.context[0] != null) {
                RBTNode<T> parent = newRBTNode.context[0];
                // Check if newRBTNode and parent are both red. If not, no RBT violation on this RBTNode
                // and its parent. Also check if there is grandparent.
                if (newRBTNode.blackHeight == 0 && parent.blackHeight == 0 && parent.context[0] != null) {
                    RBTNode<T> grandparent = parent.context[0];
                    // parent is on grandparent's left and parent has a non-null sibling
                    if (grandparent.context[1] == parent && grandparent.context[2] != null) {
                        // newRBTNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Left (Case 1)
                        if (parent.context[1] == newRBTNode && parent.blackHeight == 0 && 
                                grandparent.context[2].blackHeight == 1) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newRBTNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Right (Case 2)
                        if (parent.context[2] == newRBTNode && parent.blackHeight == 0 && 
                                grandparent.context[2].blackHeight == 1) {
                            // Rotate newRBTNode and parent
                            rotate(newRBTNode,parent);
                            // Rotate newRBTNode and grandparent and colorswap
                            rotate(newRBTNode, grandparent);
                            grandparent.blackHeight = 0;
                            newRBTNode.blackHeight = 1;
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
                        // newRBTNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Left (Case 1)
                        if (parent.context[1] == newRBTNode && parent.blackHeight == 0) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newRBTNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on left, Child on Right (Case 2)
                        if (parent.context[2] == newRBTNode && parent.blackHeight == 0) {
                            // Rotate newRBTNode and parent
                            rotate(newRBTNode,parent);
                            // Rotate newRBTNode and grandparent and colorswap
                            rotate(newRBTNode, grandparent);
                            grandparent.blackHeight = 0;
                            newRBTNode.blackHeight = 1;
                        }
                    }
                    // parent is on grandparent's right and parent has a non-null sibling
                    if (grandparent.context[2] == parent && grandparent.context[1] != null) {
                        // newRBTNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on right (Case 1)
                        if (parent.context[2] == newRBTNode && parent.blackHeight == 0 && 
                                grandparent.context[1].blackHeight == 1) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newRBTNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on left (Case 2)
                        if (parent.context[1] == newRBTNode && parent.blackHeight == 0 && 
                                grandparent.context[1].blackHeight == 1) {
                            // Rotate newRBTNode and parent
                            rotate(newRBTNode,parent);
                            // Rotate newRBTNode and grandparent and colorswap
                            rotate(newRBTNode, grandparent);
                            grandparent.blackHeight = 0;
                            newRBTNode.blackHeight = 1;
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
                        // newRBTNode is on outside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on right (Case 1)
                        if (parent.context[2] == newRBTNode && parent.blackHeight == 0) {
                            // Rotate and color swap
                            rotate(parent, grandparent);
                            grandparent.blackHeight = 0;
                            parent.blackHeight = 1;
                        }

                        // newRBTNode is on inside. Parent is red and Parent's sibling is black.
                        // Parent on right, Child on left (Case 2)
                        if (parent.context[1] == newRBTNode && parent.blackHeight == 0) {
                            // Rotate newRBTNode and parent
                            rotate(newRBTNode,parent);
                            // Rotate newRBTNode and grandparent and colorswap
                            rotate(newRBTNode, grandparent);
                            grandparent.blackHeight = 0;
                            newRBTNode.blackHeight = 1;
                        }
                    }
                }
                enforceRBTreePropertiesAfterInsert(parent); // recurse on parent RBTNode, go up tree
            }
            root.blackHeight = 1; // set root to black
            break; // exit loop if current newRBTNode has no parent
        }
    }
}