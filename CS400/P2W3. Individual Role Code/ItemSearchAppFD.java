import java.util.Scanner;

/**
 * The class to instantiate both the frontend and the backend
 */
public class ItemSearchAppFD {
  /**
   * @param args strings passed in
   */
  public static void main(String[] args) {
    // Use data wrangler's code to load post data
		ItemReader itemLoader = new ItemReader();
		// Use algorithm engineer's code to store and search for data
		RedBlackTree<Item> rbt = new RedBlackTree<>();
		MinHeap minHeap = new MinHeap();
		// Use the backend developer's code to manage all app specific processing
		ItemSearchBackendBD backend = new ItemSearchBackendBD(itemLoader, rbt, minHeap);
		// Use the frontend developer's code to drive the text-base user interface
		Scanner scanner = new Scanner(System.in);
		ItemSearchFrontendFD frontend = new ItemSearchFrontendFD(scanner, backend);
		frontend.runCommandLoop();
  }
}
