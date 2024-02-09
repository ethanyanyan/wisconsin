import java.util.List;

public interface ItemSearchFrontendInterface {
  //public ItemSearchFrontend(Scanner userInput, ItemSearchBackendInterface backend);
  public void runCommandLoop();
  public char mainMenuPrompt();
  public void loadCSVCommand();
  public List<String> chooseSearchWordsPrompt();
  public void searchProductNameCommand(List<String> words);
  public void findLowestRating(int numbers);
}
