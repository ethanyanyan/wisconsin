// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;

/**
 * This class is an interface to be implemented to drive an interactive loop
 * 
 * @author Ethan Yikai Yan
 */
public interface CHSearchFrontendInterface {
    //public CHSearchFrontendXX(Scanner userInput, CHSearchBackendInterface backend);

    /**
     * This method is the main interactive loop that determines what commands to run
     */
    public void runCommandLoop();

    /**
     * This method prints the main menu prompt and receives user input (a char) that will be passed
     * to the main commandLoop to determine which subsequent commands to run
     * @returns char to decide which command to run next
     */
    public char mainMenuPrompt();

    /**
     * receives input of filename and runs loadData from backend
     */
    public void loadDataCommand();

    /**
     * Standard prompt to allow users to select words to be searched and gives user the
     * opportunity to edit their search words
     * @returns List of words to be searched
     */
    public List<String> chooseSearchWordsPrompt();

    /**
     * Searches title of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchTitleCommand(List<String> words);

    /**
     * Searches body of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchBodyCommand(List<String> words);

    /**
     * Searches both body and title of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchPostCommand(List<String> words);

    /**
     * Displays statistics of the dataset being searched
     */
    public void displayStatsCommand();
}