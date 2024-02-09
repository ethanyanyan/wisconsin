// --== CS400 Project One File Header ==--
// Name: Chenzhe Xu
// CSL Username: chenzhe
// Email: cxu296@wisc.edu
// Lecture #: 002
// Notes to Grader: N/A
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 * Performs all application specific computation for the ItemSearchApp. This
 * primarily involved loading item data, efficiently searching through that
 * data, and reporting statistics about the current dataset.
 * 
 */
public class ItemSearchBackendBD implements ItemSearchBackendInterface{
    private ItemReader itemReader;
    private RedBlackTree<Item> rbtree;
    private MinHeap heap;
    private int count;
	/**
	 * Initialize backend to make use of the provided Minheap/RedBlackTree and
	 * ItemReader
	 * 
	 * @param rbtree  placeholder by me, working implementation by
	 *                   AlgorithmEngineer
	 * @param itemReader placeholder by me, working implementation by DataWrangler
	 * @param heap  placeholder by me, working implementation by
	 *                   AlgorithmEngineer
	 */
    public ItemSearchBackendBD(ItemReader itemReader, RedBlackTree<Item> rbtree, MinHeap heap){
        this.itemReader = itemReader;
        this.rbtree = rbtree;
        this.heap = heap;
        count = 0;
    }
	/**
	 * Use DataWrangler's code to load posts from the specified file, and then add
	 * those posts one a time into the minheap/RedBlackTree
	 * 
	 * @param filename the path and name of the file to load post data from
	 */
    @Override
    public void loadData(String filename) throws FileNotFoundException {
		List<Item> items = itemReader.readItemsFromFile(filename);
		for (Item item : items){
            rbtree.insert(item);
            heap.insert(item);
            count++;
        }
    }
    /*
     * This method is to search the product by its name in the red black tree and
     * the mthod will return the details about it.
     */
    @Override
    public String findItemsByProductName(String words) {
        Item item = rbtree.findByName(words);
        String returnVal = "Name: "+ item.getName() + " Category: " + item.getCategory() + " Price: "+ item.getSalePrice() + " Rating: " + item.getRating() + " Description: " + item.getDescription();
        return returnVal;
    }

    /*
     * This method is to find the num of lowest items by ratings and return the list of Items
     */
    @Override
    public List<Item> findLowestItems(int num) {
        List<Item> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            Item min = heap.getMin();
            list.add(min);
        }
        return list;
    }
    public MinHeap getMinHeap(){
        return heap;
    }
    public RedBlackTree getRBTree(){
        return rbtree;
    }
    public ItemReader getReader(){
        return itemReader;
    }
    
}
