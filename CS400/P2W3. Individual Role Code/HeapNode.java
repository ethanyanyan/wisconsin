/**
 * This class represents a HeapNode and implements the HeapNodeInterface
 */
public class HeapNode implements HeapNodeInterface {

    public double key; // Key value of each node. It is the rating of products
    public Item item; // Item contained within the HeapNode
    public RBTNode rbNode; // Pointer to the corresponding node in the Red-Black tree data structure

    /**
     * Constructor for a HeapNode
     * @param item item to be stored in the HeapNode
     */
    public HeapNode(Item item){
        this.key = item.getRating();
        this.item = item;
        this.rbNode = new RBTNode<>(item);
    }

    /**
     * Getter to get the item stored in HeapNode
     * @return Item stored in HeapNode
     */
    public Item getItem() {
        return this.item;
    }

}