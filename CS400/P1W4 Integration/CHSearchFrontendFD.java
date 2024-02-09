// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the CHSearchFrontendInterface that drives an interactive loop of prompting 
 * the user to select a command, then provide any needed details about that command (like any 
 * keywords to search for), and then display the results of such searches with the help of 
 * computations performed by the BD's code.  When the user enters invalid input, instructive 
 * feedback about what they should enter is displayed.
 * 
 * @author Ethan Yikai Yan
 */
public class CHSearchFrontendFD implements CHSearchFrontendInterface {
    Scanner sc = null; // scanner to be used, that will be initialized in constructor
    CHSearchBackendInterface testerBackend = null; // backend placeholder
    /**
     * Constructor for the CHSearchFrontend class
     * @param userInput scanner to be used while loop is running
     * @param backend placeholder backend
     */
    public CHSearchFrontendFD(Scanner userInput, CHSearchBackendInterface backend) {
        this.sc = userInput;
        this.testerBackend = backend;
    }

    /**
     * This method is the main interactive loop that determines what commands to run
     */
    public void runCommandLoop() {
        char input = '\0';
        while (input != 'Q') {
            input = mainMenuPrompt();
            if (input == 'L') {
                loadDataCommand();
            } 
            else if (input == 'T') {
                List<String> searchList = chooseSearchWordsPrompt();
                searchTitleCommand(searchList);
            } 
            else if (input == 'B') {
                List<String> searchList = chooseSearchWordsPrompt();
                searchBodyCommand(searchList);
            } 
            else if (input == 'P') {
                List<String> searchList = chooseSearchWordsPrompt();
                searchPostCommand(searchList);
            } 
            else if (input == 'S') {
                displayStatsCommand();
            }
        }
        System.out.println("You have chosen to quit, goodbye!");
    }

    /**
     * This method prints the main menu prompt and receives user input (a char) that will be passed
     * to the main commandLoop to determine which subsequent commands to run
     * @returns char to decide which command to run next
     */
    public char mainMenuPrompt() {
        String promptBody = "[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\n" + 
                "Search [P]ost titles and bodies \nDisplay [S]tatistics for dataset\n[Q]uit";
        String prompt = "Choose an option by typing in your keyboard one of the"+ 
                " following letters in brackets:\n" + promptBody;
        System.out.println(prompt);
        char input = sc.nextLine().charAt(0);
        if (input == 'L' || input == 'l') {
            return 'L';
        } else if (input == 'T' || input == 't') {
            return 'T';
        } else if (input == 'B' || input == 'b') {
            return 'B';
        } else if (input == 'P' || input == 'p') {
            return 'P';
        } else if (input == 'S' || input == 's') {
            return 'S';
        } else if (input == 'Q' || input == 'q') {
            return 'Q';
        } else {
            System.out.println("Invalid input, choose again.");
            return '\0';
        }
    }

    /**
     * receives input of filename and runs loadData from backend
     */
    public void loadDataCommand() {
        System.out.println("Enter filename: ");
        String fileToRead = sc.nextLine().trim();
        System.out.println(fileToRead);
        try {
            testerBackend.loadData(fileToRead);
            System.out.println("File successfully loaded!");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Standard prompt to allow users to select words to be searched and gives user the
     * opportunity to edit their search words
     * @returns List of words to be searched
     */
    public List<String> chooseSearchWordsPrompt() {
        boolean confirmSearch = false;
        List<String> returnList = null;
        while (!confirmSearch) {
            String prompt = "Type in words to be searched with a comma in between, in a line " + 
                "(e.g. \"WI,Wisconsin,Madison\")";
            System.out.println(prompt);
            String input = sc.nextLine();
            String[] stringSplit = input.trim().split(",", 0);
            for (int i = 0; i < stringSplit.length; i ++) {
                stringSplit[i] = stringSplit[i].trim();
            }
            returnList = Arrays.asList(stringSplit);
            System.out.println("Confirm your search words? (y/n) " + returnList);
            char confirmation = sc.nextLine().charAt(0);
            if (confirmation == 'y' || confirmation == 'Y') {
                confirmSearch = true;
            }

        }
        return returnList;
    }

    /**
     * Searches title of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchTitleCommand(List<String> words) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < words.size(); i ++) {
            if (testerBackend.findPostsByTitleWords(words.get(i)) != null) {
                returnList.addAll(testerBackend.findPostsByTitleWords(words.get(i)));
            }
        }
        System.out.println("Search all posts [T]itles results: " + returnList);
    }

    /**
     * Searches body of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchBodyCommand(List<String> words) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < words.size(); i ++) {
            if (testerBackend.findPostsByBodyWords(words.get(i)) != null) {
                returnList.addAll(testerBackend.findPostsByBodyWords(words.get(i)));
            }
        }
        System.out.println("Search all posts [B]odies results: " + returnList);
    }

    /**
     * Searches both body and title of posts for a list of searchwords
     * @param words Words to be searched
     */
    public void searchPostCommand(List<String> words) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < words.size(); i ++) {
            if (testerBackend.findPostsByTitleOrBodyWords(words.get(i)) != null) {
                returnList.addAll(testerBackend.findPostsByTitleOrBodyWords(words.get(i)));
            }
        }
        System.out.println("Search all [P]osts bodies and titles results: " + returnList);
    }

    /**
     * Displays statistics of the dataset being searched
     */
    public void displayStatsCommand() {
        System.out.println("Statistics found are: ");
        System.out.println(testerBackend.getStatisticsString());
    }
}
