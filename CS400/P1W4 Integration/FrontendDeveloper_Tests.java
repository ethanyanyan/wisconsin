// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.RunElement;

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

    /**
     * test6 is done for Integration and testing for successful [L]oading of different filenames
     * and displaying of [S]tatistics
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test6() {
        // Test if fake.txt can be successfully loaded and [Q]uit text is displayed
        TextUITester tester = new TextUITester("L\ndata/fake.txt\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains("Enter filename:") ||
                    !output.contains("File successfully loaded!") ||
                    !output.contains("You have chosen to quit, goodbye!")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test if large.txt can be succesfully loaded
        TextUITester tester2 = new TextUITester("L\ndata/large.txt\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester2.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains("Enter filename:") ||
                    !output.contains("File successfully loaded!") ||
                    !output.contains("You have chosen to quit, goodbye!")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test to see if display of statistics for fake.txt is accurate
        TextUITester tester3 = new TextUITester("L\ndata/fake.txt\nS\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester3.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains("Enter filename:") ||
                    !output.contains("File successfully loaded!") || 
                    !output.contains("Dataset contains:") ||
                    !output.contains("4 posts") || 
                    !output.contains("49 unique words") || 
                    !output.contains("159 total word-post pairs") || 
                    !output.contains("note that words in titles vs bodies are counted separately")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test to see if display of statistics for small.txt is accurate
        TextUITester tester4 = new TextUITester("L\ndata/small.txt\nS\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester4.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains("Enter filename:") ||
                    !output.contains("File successfully loaded!") || 
                    !output.contains("Dataset contains:") ||
                    !output.contains("12 posts") || 
                    !output.contains("571 unique words") || 
                    !output.contains("3185 total word-post pairs") || 
                    !output.contains("note that words in titles vs bodies are counted separately")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * test7 is done for Integration and testing for searching posts [T]itles, [B]odies and entire
     * [P]ost titles and bodies
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test7() {
        // Test searching for first 2 posts [T]itle in fake.txt with search Madison,Wisconsin,WI, with
        // Madison and WI being a hit, check if output is accurate
        TextUITester tester = new TextUITester("L\ndata/fake.txt\nT\nMadison,Wisconsin,WI\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all posts [T]itles results:") ||
                    !output.contains("Confirm your search words? (y/n)") || 
                    !output.contains("[Madison, Wisconsin, WI]") || 
                    !output.contains("Can I grow stevia in Madison?   -  https://no_real_post") || 
                    !output.contains("Consider natural sugar alternatives in WI")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test searching for posts [B]odies in small.txt with search prOtein with
        // intentional mis-capitalization of the search word
        TextUITester tester2 = new TextUITester("L\ndata/small.txt\nB\nprOtein\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester2.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all posts [B]odies results:") ||
                    !output.contains("Confirm your search words? (y/n)") || 
                    !output.contains("[prOtein]") || 
                    !output.contains("Protein snacks? - https://www.reddit.com/r/EatCheapAndHe" +
                            "althy/comments/zp6q1y/protein_snacks/") || 
                    !output.contains("Anyone try Aviate Lupini FLAKES? - https://www.reddit.com/r/"+
                            "EatCheapAndHealthy/comments/zohfum/anyone_try_aviate_lupini_flakes/")|| 
                    !output.contains("Protein overload - https://www.reddit.com/r/EatCheapAndHea" +
                            "lthy/comments/zpgx94/protein_overload/")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test searching for [P]osts title and bodies in large.txt with search "chilli", 
        // specific search word to filter large dataset
        TextUITester tester3 = new TextUITester("L\ndata/large.txt\nP\n"+
                "chilli\ny\nQ\n"); 
        try (Scanner scan = new Scanner(System.in)) {
            CHSearchFrontendFD front = new CHSearchFrontendFD(scan, new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
            front.runCommandLoop(); 
            String output = tester3.checkOutput(); 
            if (!output.startsWith(prompt)) {
                return false;
            }
            if (!output.contains(searchPrompt) || 
                    !output.contains("Search all [P]osts bodies and titles results:") ||
                    !output.contains("Confirm your search words? (y/n)") || 
                    !output.contains("[chilli]") || 
                    !output.contains("Help for cooking when I'm feeling terrible? - https://ww"+
                            "w.reddit.com/r/EatCheapAndHealthy/comments/z2xqvk/help_for_cooking_w"+
                            "hen_im_feeling_terrible/") || 
                    !output.contains("Pinto and Kidney Bean Soup? - https://www.reddit.com/r/"+
                            "EatCheapAndHealthy/comments/ynrj9y/pinto_and_kidney_bean_soup/")|| 
                    !output.contains("What's your favorite ingredients to bulk out a chilli? "+
                            "- https://www.reddit.com/r/EatCheapAndHealthy/comments/yo45hy/whats"+
                            "_your_favorite_ingredients_to_bulk_out_a/")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * test8 tests the BackendDeveloper's constructor, loadData method and getStatisticsString
     * methods
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test8() {
        // Test constructor and test loading a file that does not exist
        try {
            CHSearchBackendBD tester = new CHSearchBackendBD(new HashtableWithDuplicateKeysAE<>(), 
                    new PostReaderDW());
            tester.loadData("data/massive.txt");
        } 
        catch (FileNotFoundException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test constructor and test loading fake.txt and check if getStatisticsString display 
        // accurate information before and after loading
        try {
            CHSearchBackendBD tester = new CHSearchBackendBD(new HashtableWithDuplicateKeysAE<>(), 
                    new PostReaderDW());
            String before = tester.getStatisticsString();
            String beforeCompare = "Dataset contains:\n" +
                    "    " + 0 + " posts\n" +
                    "    " + 0 + " unique words\n" +
                    "    " + 0 + " total word-post pairs\n" +
                    "    note that words in titles vs bodies are counted separately";
            if (!before.equals(beforeCompare)) {
                return false;
            }
            tester.loadData("data/fake.txt");
            String after = tester.getStatisticsString();
            String afterCompare = "Dataset contains:\n" +
                    "    " + 4 + " posts\n" +
                    "    " + 49 + " unique words\n" +
                    "    " + 202 + " total word-post pairs\n" +
                    "    note that words in titles vs bodies are counted separately";
            if (!after.equals(afterCompare)) {
                return false;
            }

            tester.loadData("data/small.txt");
            String secondTest = tester.getStatisticsString();
            String secondCompare = "Dataset contains:\n" +
                    "    " + 12 + " posts\n" +
                    "    " + 571 + " unique words\n" +
                    "    " + 2933 + " total word-post pairs\n" +
                    "    note that words in titles vs bodies are counted separately";
            if (!secondTest.equals(secondCompare)) {
                return false;
            }

            tester.loadData("data/large.txt");
            String thirdTest = tester.getStatisticsString();
            String thirdCompare = "Dataset contains:\n" +
                    "    " + 856 + " posts\n" +
                    "    " + 57785+ " unique words\n" +
                    "    " + 152261 + " total word-post pairs\n" +
                    "    note that words in titles vs bodies are counted separately";
            if (!thirdTest.equals(thirdCompare)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * test9 tests the BackendDeveloper's findPostByTitleWords, findPostbyBodyWords, and 
     * findPostsByTitleOrBodyWords methods
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test9() {
        // Check if keys are being added accurately to the HashtableMap
        try {
            HashtableWithDuplicateKeysAE hashtableTest = new HashtableWithDuplicateKeysAE<>();
            PostReaderDW postReaderTest = new PostReaderDW();
            CHSearchBackendBD tester = new CHSearchBackendBD(hashtableTest, postReaderTest);
            tester.loadData("data/fake.txt");
            // Check for certain keys that should be in the map
            if (!hashtableTest.containsKey("T:aspartame")) {
                return false;
            }
            if (!hashtableTest.containsKey("B:climate")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        // Tests findPostsByTitleWords
        try {
            CHSearchBackendBD tester = new CHSearchBackendBD(new HashtableWithDuplicateKeysAE<>(), 
                    new PostReaderDW());
            tester.loadData("data/small.txt");
            // Check if return type of method is a list
            if(!(tester.findPostsByTitleWords("") instanceof List)) {
                return false;
            }
            List<String> returnList = tester.findPostsByTitleWords("chicken");
            if(!returnList.contains("ORANGE CHICKEN - http"+
                    "s://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/") ||
                    !returnList.contains("\"What is a good, high protein, easy to make, plant based"+
                    " food to replace chicken with?\" - https://www.reddit.com/r/EatCheapAndHealth"+
                    "y/comments/zp3qhf/what_is_a_good_high_protein_easy_to_make_plant/")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Tests findPostsByBodyWords
        try {
            CHSearchBackendBD tester = new CHSearchBackendBD(new HashtableWithDuplicateKeysAE<>(), 
                    new PostReaderDW());
            tester.loadData("data/large.txt");
            // Check if return type of method is a list
            if(!(tester.findPostsByBodyWords("") instanceof List)) {
                return false;
            }
            List<String> returnList = tester.findPostsByBodyWords("pressure");
            if(!returnList.contains("\"cheap, easy, fast Mediterranean low sodium recipes\" - h"+
                    "ttps://www.reddit.com/r/EatCheapAndHealthy/comments/y7m284/cheap_easy_fast_m"+
                    "editerranean_low_sodium_recipes/") ||
                    !returnList.contains("Why are my black beans still crunchy from the instan"+
                    "t pot? - https://www.reddit.com/r/EatCheapAndHealthy/comments/yk2sws/why_are"+
                    "_my_black_beans_still_crunchy_from_the/")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Tests findPostsByTitleOrBodyWords
        try {
            CHSearchBackendBD tester = new CHSearchBackendBD(new HashtableWithDuplicateKeysAE<>(), 
                    new PostReaderDW());
            tester.loadData("data/small.txt");
            // Check if return type of method is a list
            if(!(tester.findPostsByTitleOrBodyWords("") instanceof List)) {
                return false;
            }
            List<String> returnList = tester.findPostsByTitleOrBodyWords("sodium");
            if(!returnList.contains("Protein snacks? - https://www.reddit.com/r/EatCheapAndHea"+
                    "lthy/comments/zp6q1y/protein_snacks/")) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void test10() {
        CHSearchFrontendFD front = new CHSearchFrontendFD(new Scanner(System.in), new CHSearchBackendBD(new 
                    HashtableWithDuplicateKeysAE<>(10), new PostReaderDW())); 
        front.runCommandLoop(); 
    }
    public static void main(String[] args) {
        if (test1()) {
            System.out.println("FrontendDeveloper Individual Test 1: passed");
        } else {
            System.out.println("FrontendDeveloper Individual Test 1: failed");
        }
        if (test2()) {
            System.out.println("FrontendDeveloper Individual Test 2: passed");
        } else {
            System.out.println("FrontendDeveloper Individual Test 2: failed");
        }
        if (test3()) {
            System.out.println("FrontendDeveloper Individual Test 3: passed");
        } else {
            System.out.println("FrontendDeveloper Individual Test 3: failed");
        }
        if (test4()) {
            System.out.println("FrontendDeveloper Individual Test 4: passed");
        } else {
            System.out.println("FrontendDeveloper Individual Test 4: failed");
        }
        if (test5()) {
            System.out.println("FrontendDeveloper Individual Test 5: passed");
        } else {
            System.out.println("FrontendDeveloper Individual Test 5: failed");
        }
        if (test6()) {
            System.out.println("FrontendDeveloper Integration Test 1: passed");
        } else {
            System.out.println("FrontendDeveloper Integration Test 1: failed");
        }
        if (test7()) {
            System.out.println("FrontendDeveloper Integration Test 2: passed");
        } else {
            System.out.println("FrontendDeveloper Integration Test 2: failed");
        }
        if (test8()) {
            System.out.println("FrontendDeveloper Partner (BackendDeveloper) Test 1: passed");
        } else {
            System.out.println("FrontendDeveloper Partner (BackendDeveloper) Test 1: failed");
        }
        if (test9()) {
            System.out.println("FrontendDeveloper Partner (BackendDeveloper) Test 2: passed");
        } else {
            System.out.println("FrontendDeveloper Partner (BackendDeveloper) Test 2: failed");
        }
        test10();
    }
}
