// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class is placeholder backend class
 */
public class CHSearchBackendFD implements CHSearchBackendInterface {    
    public CHSearchBackendFD(Runnable func, Runnable func2) {

    }
    public void loadData(String filename) throws FileNotFoundException {
        if (filename.equals("nil")) {
            throw new FileNotFoundException();
        }
    }
    public List<String> findPostsByTitleWords(String words) {
        return null;
    }
    public List<String> findPostsByBodyWords(String words) {
        return null;
    }
    public List<String> findPostsByTitleOrBodyWords(String words) {
        return null;
    }
    public String getStatisticsString() {
        return null;
    }
}