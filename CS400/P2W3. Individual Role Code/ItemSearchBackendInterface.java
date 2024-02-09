import java.io.FileNotFoundException;
import java.util.List;

public interface ItemSearchBackendInterface {   
    // public ItemSearchBackendInterface(RedBlackTreeInterface rbtree, MinHeapInterface heap);
    public void loadData(String filename) throws FileNotFoundException;
    public String findItemsByProductName(String words);
    public List<Item> findLowestItems(int num);
}
