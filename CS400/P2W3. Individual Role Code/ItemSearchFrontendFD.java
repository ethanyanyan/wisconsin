import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemSearchFrontendFD implements ItemSearchFrontendInterface {
  private Scanner userInput;
  private ItemSearchBackendInterface backend;

  /**
   * Initialize frontend to make use of a provided Scanner and ItemSearchBackend.
   *
   * @param userInput can be used to read input from use, or to read from files
   *                  for testing
   * @param backend   placeholder by me, working implementation by Backend
   *                  Developer
   */
  public ItemSearchFrontendFD(Scanner userInput, ItemSearchBackendInterface backend) {
    this.userInput = userInput;
    this.backend = backend;
  }

  /**
   * Helper method to display a 79 column wide row of dashes.
   */
  private void hr() {
    System.out.println("-".repeat(79));
  }

  /**
   * Ask users to type in the next command until "Q" is entered.
   */
  @Override public void runCommandLoop() {
    hr(); // display welcome message
    System.out.println("Welcome to the Item Search App.");
    hr();

    List<String> words;
    char command = '\0';
    while (command != 'Q') {
      command = this.mainMenuPrompt();
      switch (command) {
        case 'L':
          loadCSVCommand();
          break;
        case 'F':
          words = chooseSearchWordsPrompt();
          searchProductNameCommand(words);
          break;
        case 'W':
          findLowestRatingWrapper();
          break;
        case 'Q':
          break;
        default:
          System.out.println(
              "Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
          break;
      }
    }

    hr(); // thank user before ending this application
    System.out.println("Thank you for using the Cheap and Healthy Search App.");
    hr();
  }

  /**
   * Display the main menu of this application. Will ask the user to type in a command
   * from the list displayed.
   *
   * @return the command typed in
   */
  @Override public char mainMenuPrompt() {
    // display menu of choices
    System.out.println("Choose a command from the list below:");
    System.out.println("    [L]oad product rating data from CSV file");
    System.out.println("    [F]ind the items by product name");
    System.out.println("    Find the k lo[W]est performing items by rating");
    System.out.println("    [Q]uit");

    // read in user's choice, and trim away any leading or trailing whitespace
    System.out.print("Choose command: ");
    String input = userInput.nextLine().trim();
    if (input.length() == 0) // if user's choice is blank, return null character
      return '\0';
    // otherwise, return an uppercase version of the first character in input
    return Character.toUpperCase(input.charAt(0));
  }

  /**
   * Ask the user to type in the name of the file to read from. Will not load the
   * data if an invalid file name is entered.
   */
  @Override public void loadCSVCommand() {
    System.out.print("Enter the name of the file to load: ");
    String filename = userInput.nextLine().trim();
    try {
      backend.loadData(filename);
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
    }
  }

  /**
   * This method is the same as the chooseSearchWordsPrompt in project 1.
   * It will repeatedly ask user to type in keywords to search for. Words that are
   * typed in twice will be deleted. User can end this process by press enter without
   * typing in any new keywords to search.
   *
   * @return a list of words to search for in product names
   */
  @Override public List<String> chooseSearchWordsPrompt() {
    List<String> words = new ArrayList<>();
    while (true) { // this loop ends when the user pressed enter (without typing any words)
      System.out.println("List of words to search for: " + words);
      System.out.print("Word(s) to add-to/remove-from query, or press enter to search: ");
      String input = userInput.nextLine().replaceAll(",", "").trim();
      if (input.length() == 0) // an empty string ends this loop and method call
        return words;
      else
        // otherwise the specified word's presence in the list is toggled:
        for (String word : input.split(" "))
          if (words.contains(word))
            words.remove(word); // remove any words that were already in the list
          else
            words.add(word); // add any words that were previously missing
    }
  }

  /**
   * Print the product name, rating, brand, category, sale price, and description if
   * we find any of the keywords in this product's name.
   *
   * @param words the list of keywords to search for in product names
   */
  @Override public void searchProductNameCommand(List<String> words) {
    for (String word : words) {
      System.out.println(backend.findItemsByProductName(word));
    }
  }

  /**
   * Wrapper method of findLowestRating. Ask the user to type in the rating and
   * then call the findLowestRating method. End if invalid rarting is entered.
   */
  public void findLowestRatingWrapper() {
    System.out.println("Please enter the number of the lowest item by the rating you want to print");
    String input = userInput.nextLine().trim();
    try {
      findLowestRating(Integer.parseInt(input));
    } catch (Exception ignored) {
      System.out.println("Invalid input rating");
    }
  }

  /**
   * Display the k lowest performing items by rating
   *
   * @param numbers the number of items to print
   */
  @Override public void findLowestRating(int numbers) {
    List<Item> itemsFound = backend.findLowestItems(numbers);
    for (Item item : itemsFound) {
      System.out.println(item.getName() + " (" + "rating: " + item.getRating() + ")");
    }
  }
}
