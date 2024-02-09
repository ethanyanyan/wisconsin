import java.util.Scanner;

/**
 * Main entry point for starting and running the CHSearch App.
 * 
 * @author AlgorithmEngineer, courtesy of the CS400 course staff.
 */
public class CHSearchApp {
	public static void main(String[] args) {
		// Use data wrangler's code to load post data
		PostReaderInterface postLoader = new PostReaderDW();
		// Use algorithm engineer's code to store and search for data
		HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
		hashtable = new HashtableWithDuplicateKeysAE<>();
		// Use the backend developer's code to manage all app specific processing
		CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);
		// Use the frontend developer's code to drive the text-base user interface
		Scanner scanner = new Scanner(System.in);
		CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
		frontend.runCommandLoop();
	}
}
