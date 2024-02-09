import java.io.FileNotFoundException;
import java.util.List;

public interface PostReaderInterface {
    // public PostReaderInterface();
    public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException;
}