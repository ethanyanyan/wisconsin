import java.io.FileNotFoundException;
import java.util.List;

public interface ItemReaderInterface {
    // public ItemReaderInterface();
    public List<Item> readItemsFromFile(String filename) throws FileNotFoundException;
 }
 