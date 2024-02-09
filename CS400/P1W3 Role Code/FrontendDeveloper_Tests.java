// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Scanner;

/**
 * This class checks the correctness of the an implementation of CS400 CHSearchFrontend
 * 
 * @author Ethan Yikai Yan
 */
public class FrontendDeveloper_Tests {
    // Standard commonly printed prompts initialized here for cleaner code
    private static String prompt = "Choose an option by typing in your keyboard one of the"+ 
                " following letters in brackets:\n" + "[L]oad data from file\nSearch Post " + 
                "[T]itles\nSearch Post [B]odies\nSearch [P]ost titles and bodies \nDisplay "+ 
                "[S]tatistics for dataset\n[Q]uit"; // Main menu prompt
    private static String searchPrompt = "Type in words to be searched with a comma in between, " + 
                "in a line (e.g. \"WI,Wisconsin,Madison\")"; // searchWords prompt
    /**
     * test1 is done on runCommandLoop(), testing to Quit [Q] right away and then testing of invalid choice of character
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test1() {
        // set up UI tester and test to quit program right away
        TextUITester tester = new TextUITester("Q\n");
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 
    
            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt) || 
                    !output.contains("You have chosen to quit, goodbye!")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // set up UI tester and test an invalid input character
        TextUITester tester2 = new TextUITester("K\nQ\n");
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 
    
            front.runCommandLoop(); 
            String output = tester2.checkOutput(); 
            if (!output.startsWith(prompt) || !output.contains("Invalid input, choose again.") ||
                    !output.contains("You have chosen to quit, goodbye!")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * test2 is done to runCommandLoop() to check prompt for Load Data [L] and invalid file output
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test2() {
        // set up UI tester
        TextUITester tester = new TextUITester("L\nnil\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt) || 
                    !output.contains("Enter filename: ") || !output.contains("File not found")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * test3 is done for runCommandLoop() to test chooseSearchWordsPrompt, searchTitleCommand 
     * and searchBodyCommand
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test3() {
        // set up UI tester and test for chooseSearchWordsPrompt and searchTitleCommand
        TextUITester tester = new TextUITester("T\nWI,Wisconsin\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all posts [T]itles results: []") ||
                    !output.contains("Confirm your search words? (y/n)")) {
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // set up UI tester and test for chooseSearchWordsPrompt and searchBodyCommand
        TextUITester tester2 = new TextUITester("B\nWI,Wisconsin\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester2.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all posts [B]odies results: []") ||
                    !output.contains("Confirm your search words? (y/n)")) {
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * test4 is done for search [P]ost titles and body, including editing choice of searchWords
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test4() {
        // set up UI tester and test for chooseSearchWordsPrompt and searchTitleCommand
        TextUITester tester = new TextUITester("P\nWI,Wisconsin\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all [P]osts bodies and titles results: []") ||
                    !output.contains("Confirm your search words? (y/n)") || 
                    !output.contains("[WI, Wisconsin]")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // set up UI tester and test for chooseSearchWordsPrompt and editing choice of words
        TextUITester tester2 = new TextUITester("P\nWI,Wisconsin\nn\nPokemon\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester2.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            // Change search words from "WI,Wisconsin" to "Pokemon"
            if (!output.contains(searchPrompt) || 
                    !output.contains("Type in words to be searched with a comma in between, in " +
                    "a line (e.g. \"WI,Wisconsin,Madison\")\nConfirm your search words? (y/n) " +
                    "[WI, Wisconsin]\nType in words to be searched with a comma in between, in a " + 
                    "line (e.g. \"WI,Wisconsin,Madison\")\nConfirm your search words? (y/n) [Poke" +
                    "mon]\nSearch all [P]osts bodies and titles results: []")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * test5 is done for Display [S]tatistics for dataset
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test5() {
        // set up UI tester and test for chooseSearchWordsPrompt and searchTitleCommand
        TextUITester tester = new TextUITester("S\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendFD(null, null)); 

            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains("Statistics found are: ") || 
                    !output.contains("null")) {
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }
}
