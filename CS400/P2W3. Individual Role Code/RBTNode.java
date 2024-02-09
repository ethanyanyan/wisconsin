public class RBTNode<T> implements RBTNodeInterface {
    public Item item; // Item to be stored in the RBTNode
    public String name; // Name of the item to be stored in the RBTNode
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
    public RBTNode<T>[] context = (RBTNode<T>[])new RBTNode[3];
    
    /**
     * @return true when this node has a parent and is the right child of
     * that parent, otherwise return false
     */
    public boolean isRightChild() {
        return context[0] != null && context[0].context[2] == this;
    }

    /**
     * Constructor for a RBTNode
     * @param item Item to be stored in the RBTNode
     */
    public RBTNode(Item item) {
        this.item = item;
        if (item != null) {
            this.name = item.getName();
        }
    }
    
    /**
     * Getter method to get Item stored in the RBTNode
     * @return Item stored in the RBTNode
     */
    public Item getItem() {
        return this.item;
    }    

    /**
     * Getter method to get name of the item stored in the RBTNode
     * @return name of the item stored in the RBTNode
     */
    public String getName() {
        return this.item.getName();
    }

    /**
     * Getter method to get rating of the item stored in the RBTNode
     * @return rating of the item stored in the RBTNode
     */
    public double getRating() {
        return this.item.getRating();
    }

    public String getCategory() {
        return this.item.getCategory();
    }

    public String getDescription() {
        return this.item.getDescription();
    }

    public double getPrice() {
        return this.item.getSalePrice();
    }
    
    //public RBTNodeInterface(Item item);
}
