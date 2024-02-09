// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class is the interface for placeholder backend class
 */
public interface CHSearchBackendInterface {    
    // public CHSearchBackendInterface(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader);
    public void loadData(String filename) throws FileNotFoundException;
    public List<String> findPostsByTitleWords(String words);
    public List<String> findPostsByBodyWords(String words);
    public List<String> findPostsByTitleOrBodyWords(String words);
    public String getStatisticsString();
}