//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Quizzer
// Course:   CS 300 Fall 2022
//
// Author:   Ethan Yikai Yan
// Email:    eyyan@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NONE
// Partner Email:   NONE
// Partner Lecturer's Name: NONE
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the an implementation of cs300 Fall 2022 p07 Quizzer
 * 
 * @author Ethan Yikai Yan
 */
public class QuizzerTester {
    /**
     * This method test and make use of the MultipleChoiceQuestion constructor, an accessor 
     * (getter) method, overridden method toString() and equal() method defined in the 
     * MultipleChoiceQuestion class.
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testMultipleChoiceQuestion() {
        try {
            // Testing and initializing of constructor
            String[] test1Answers = {"7","12","9"};
            MultipleChoiceQuestion test1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test1Answers, 1, 5);
            
            // Test for getTitle getter method
            if (!(test1.getTitle().equals("Numbers"))) {
                return false;
            }

            // Test for getQuestion getter method
            if (!(test1.getQuestion().equals("What is even?"))) {
                return false;
            }

            // Test for getQuestion getter method
            if (!(test1.getAnswers().equals("1. 7\n2. 12\n3. 9"))) {
                return false;
            }

            // Test for getCorrectAnswerIndex getter method
            if (test1.getCorrectAnswerIndex() != 1) {
                return false;
            }

            // Test for getPointsPossible getter method
            if (test1.getPointsPossible() != 5) {
                return false;
            }

            // Test for toString overridden method
            if (!(test1.toString().equals("QUESTION TITLE: " + "\"" + "Numbers" + "\"" + 
                    "\nQuestion:\nWhat is even?\nAvailable Answers:\n1. 7\n2. 12\n3. 9"))) {
                return false;
            }

            // Test for equals overridden method
            // Different question entirely
            String[] test2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion test2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    test2Answers, 2, 3);
            if (test1.equals(test2)) {
                return false;
            }

            // Different answers array (index 2)
            String[] test3Answers = {"7","12","7"};
            MultipleChoiceQuestion test3 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test3Answers, 1, 5);
            if (test1.equals(test3)) {
                return false;
            }

            // Different title
            MultipleChoiceQuestion test4 = new MultipleChoiceQuestion("Num Ques", "What is even?", 
                    test1Answers, 1, 5);
            if (test1.equals(test4)) {
                return false;
            }

            // Different question
            MultipleChoiceQuestion test5 = new MultipleChoiceQuestion("Numbers", "Which is even?", 
                    test1Answers, 1, 5);
            if (test1.equals(test5)) {
                return false;
            }

            // Different correctAnswerIndex
            String[] test6Answers = {"7","9","12"};
            MultipleChoiceQuestion test6 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test6Answers, 2, 5);
            if (test1.equals(test6)) {
                return false;
            }

            // Different pointsPossible
            MultipleChoiceQuestion test7 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test1Answers, 1, 10);
            if (test1.equals(test7)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            String[] test6Answers = {"7","9","12"};
            MultipleChoiceQuestion test7 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test6Answers, -2, 5);
        }
        catch (IndexOutOfBoundsException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            String[] test6Answers = {"7","9","12"};
            MultipleChoiceQuestion test8 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    test6Answers, 2, -5);
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * This method test and make use of the LinkedNode constructor, an accessor (getter) method, 
     * and a mutator (setter) method defined in the LinkedNode class.
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testLinkedNode() {
        try {
            LinkedNode testNull = new LinkedNode(null);
        }
        catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            LinkedNode testNull2 = new LinkedNode(null,null);
        }
        catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        
        // Testing and initializing of constructors test2 -> test1
        LinkedNode test1 = new LinkedNode ("Hello");
        LinkedNode test2 = new LinkedNode("Bye", test1);

    
        // Test for setNext and getNext
        LinkedNode test3 = new LinkedNode ("testSetNext");
        try {
            test1.setNext(test3);
            if (!(test1.getNext().equals(test3))) {
                return false;
            }

            if (!(test2.getData().equals("Bye"))) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            if (test3.getNext() != null) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Tester for ListQuizzer.addLast() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAddLast() {
        ListQuizzer test1 = new ListQuizzer();
        try {
            test1.addLast(null);
        }
        catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            // Initialize LinkedList
            String[] ques1Answers = {"7","12","9"};
            MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    ques1Answers, 1, 5);
            String[] ques2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    ques2Answers, 2, 3);

            test1.addLast(ques1);
            if (test1.getLast() != ques1) {
                return false;
            }
            test1.addLast(ques2);
            if (test1.getLast() != ques2) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tester for ListQuizzer.addFirst() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAddFirst() {
        try {
        ListQuizzer test1 = new ListQuizzer();
            test1.addFirst(null);
        }
        catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            ListQuizzer test1 = new ListQuizzer();
            // Initialize LinkedList
            String[] ques1Answers = {"7","12","9"};
            MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    ques1Answers, 1, 5);
            String[] ques2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    ques2Answers, 2, 3);

            test1.addFirst(ques1);
            if (test1.getFirst() != ques1) {
                return false;
            }
            test1.addFirst(ques2);
            if (test1.getFirst() != ques2 && test1.getLast() == ques1) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tester for ListQuizzer.add() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAdd() {
        try {
        // Initialize LinkedList
            ListQuizzer test1 = new ListQuizzer();
            String[] ques1Answers = {"7","12","9"};
            MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    ques1Answers, 1, 5);
            String[] ques2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    ques2Answers, 2, 3);
            String[] ques3Answers = {"yellow","brown","orange"};
            MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                    ques3Answers, 0, 3);
            test1.addFirst(ques1);
            test1.addLast(ques2);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        ListQuizzer test1 = new ListQuizzer();
        String[] ques1Answers = {"7","12","9"};
        MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                ques1Answers, 1, 5);
        String[] ques2Answers = {"3","10","5.5"};
        MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                ques2Answers, 2, 3);
        String[] ques3Answers = {"yellow","brown","orange"};
        MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                ques3Answers, 0, 3);
        test1.addFirst(ques1);
        test1.addLast(ques2);
        try {
            test1.add(-1, ques3);
        }
        catch (IndexOutOfBoundsException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            test1.add(1, null);
        }
        catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            test1.add(1, ques3);
            if (!(test1.get(1).equals(ques3))) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tester for ListQuizzer.removeLast() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testRemoveLast() {
        try {
            ListQuizzer test1 = new ListQuizzer();
            test1.removeLast();
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            ListQuizzer test1 = new ListQuizzer();
            // Initialize LinkedList
            String[] ques1Answers = {"7","12","9"};
            MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    ques1Answers, 1, 5);
            String[] ques2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    ques2Answers, 2, 3);
            String[] ques3Answers = {"yellow","brown","orange"};
            MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                    ques3Answers, 0, 3);
            test1.addFirst(ques1);
            test1.addLast(ques2);
            test1.addLast(ques3);
            test1.removeLast();
            if (test1.size() != 2 && test1.getLast() != ques2) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tester for ListQuizzer.removeFirst() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testRemoveFirst() {
        ListQuizzer test1 = new ListQuizzer();
        try {
            test1.removeLast();
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            // Initialize LinkedList
            String[] ques1Answers = {"7","12","9"};
            MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                    ques1Answers, 1, 5);
            String[] ques2Answers = {"3","10","5.5"};
            MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                    ques2Answers, 2, 3);
            String[] ques3Answers = {"yellow","brown","orange"};
            MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                    ques3Answers, 0, 3);
            test1.addFirst(ques1);
            test1.addLast(ques2);
            test1.addLast(ques3);
            test1.removeFirst();

            if (test1.size() != 2 && test1.getFirst() != ques2) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tester for ListQuizzer.remove() method
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testRemove() {
        // Initialize LinkedList
        ListQuizzer test1 = new ListQuizzer();
        String[] ques1Answers = {"7","12","9"};
        MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                ques1Answers, 1, 5);
        String[] ques2Answers = {"3","10","5.5"};
        MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                ques2Answers, 2, 3);
        String[] ques3Answers = {"yellow","brown","orange"};
        MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                ques3Answers, 0, 3);
        test1.addFirst(ques1);
        test1.addLast(ques2);
        test1.addLast(ques3);
        try {
            test1.remove(-1);
        }
        catch (IndexOutOfBoundsException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            test1.remove(5);
        }
        catch (IndexOutOfBoundsException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        test1.remove(1);
        if (test1.size() != 2 && test1.getFirst() != ques1 && test1.getLast() != ques3) {
            return false;
        }
        return true;
    }

    /**
     * This method checks for the correctness of CorrectQuestionsIterator class
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testCorrectQuestionsIterator() {
        ListQuizzer test1 = new ListQuizzer();
        // Initialize LinkedNodes
        String[] ques1Answers = {"7","12","9"};
        MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                ques1Answers, 0, 5);
        ques1.setStudentAnswerIndex(0); // ques1 is correct

        LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(ques1);
        String[] ques2Answers = {"3","10","5.5"};
        MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                ques2Answers, 2, 3);
        ques2.setStudentAnswerIndex(0); // ques2 is wrong

        LinkedNode<MultipleChoiceQuestion> node2 = new LinkedNode<MultipleChoiceQuestion>(ques2);
        String[] ques3Answers = {"yellow","brown","orange"}; 
        MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                ques3Answers, 0, 3);
        ques3.setStudentAnswerIndex(0); // ques3 is correct

        LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(ques3);
        // node1 -> node2 -> node3
        node1.setNext(node2);
        node2.setNext(node3);
        test1.addLast(ques1);
        test1.addLast(ques2);
        test1.addLast(ques3);

        try {
            CorrectQuestionsIterator itr = new CorrectQuestionsIterator(node1);
            if (itr.next() != ques1) {
                return false;
            }
            if (itr.next() != ques3) {
                return false;
            }

            // Test for all wrong answers
            node3.getData().setStudentAnswerIndex(1); // changes ques3 to wrong
            if (itr.hasNext()) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            CorrectQuestionsIterator itr = new CorrectQuestionsIterator(node1);
            itr.next();
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         
        // Test for switching modes
        try {
            test1.switchMode(ListingMode.INCORRECT);
            Iterator itr = test1.iterator();
            if (!itr.next().equals(ques2)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // Test for enhanced for loop
        try {
            test1.switchMode(ListingMode.INCORRECT);
            Iterator itr = test1.iterator();
            String correctString = ques2.toString() + ques3.toString();
            String checkString = "";

            for (MultipleChoiceQuestion mcq : test1) {
                checkString = checkString + mcq.toString();
            }

            if (!correctString.equals(checkString)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method checks for the correctness of IncorrectQuestionsIterator class
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testInCorrectQuestionsIterator() {
        // Initialize LinkedNodes
        ListQuizzer test1 = new ListQuizzer();
        String[] ques1Answers = {"7","12","9"};
        MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                ques1Answers, 2, 5);
        ques1.setStudentAnswerIndex(1); // ques1 is wrong
        LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(ques1);
        String[] ques2Answers = {"3","10","5.5"};
        MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                ques2Answers, 2, 3);
        ques2.setStudentAnswerIndex(0); // ques2 is wrong
        LinkedNode<MultipleChoiceQuestion> node2 = new LinkedNode<MultipleChoiceQuestion>(ques2);
        String[] ques3Answers = {"yellow","brown","orange"}; 
        MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                ques3Answers, 0, 3);
        ques3.setStudentAnswerIndex(0); // ques3 is correct
        LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(ques3);
        // node1 -> node2 -> node3
        node1.setNext(node2);
        node2.setNext(node3);
        test1.addLast(ques1);
        test1.addLast(ques2);
        test1.addLast(ques3);

        try {
            IncorrectQuestionsIterator itr = new IncorrectQuestionsIterator(node1);
            if (itr.next() != ques1) {
                return false;
            }
            if (itr.next() != ques2) {
                return false;
            }

            // Test for all correct answers
            node2.getData().setStudentAnswerIndex(2); // set ques2 to correct
            if (itr.hasNext()) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            IncorrectQuestionsIterator itr = new IncorrectQuestionsIterator(node1);
            itr.next();
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test for switching modes
        try {
            test1.switchMode(ListingMode.ALL);
            Iterator itr = test1.iterator();
            if (!itr.next().equals(ques1)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // Test for enhanced for loop
        try {
            test1.switchMode(ListingMode.CORRECT);
            Iterator itr = test1.iterator();
            String correctString = ques2.toString() + ques3.toString();
            String checkString = "";

            for (MultipleChoiceQuestion mcq : test1) {
                checkString = checkString + mcq.toString();
            }

            if (!correctString.equals(checkString)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method checks for the correctness of QuizQuestionsIterator class
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testQuizQuestionsIterator() {
        // Initialize 1st LinkedNode
        ListQuizzer test1 = new ListQuizzer();
        String[] ques1Answers = {"7","12","9"};
        MultipleChoiceQuestion ques1 = new MultipleChoiceQuestion("Numbers", "What is even?", 
                ques1Answers, 1, 5);
        ques1.setStudentAnswerIndex(0); // ques1 is wrong
        LinkedNode<MultipleChoiceQuestion> node1 = new LinkedNode<MultipleChoiceQuestion>(ques1);
        try {
            QuizQuestionsIterator itr = new QuizQuestionsIterator(node1);
            if (!itr.hasNext()) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            QuizQuestionsIterator itr = new QuizQuestionsIterator(node1);
            itr.next();
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Initialize other 2 nodes
        String[] ques2Answers = {"3","10","5.5"};
        MultipleChoiceQuestion ques2 = new MultipleChoiceQuestion("Decimals", "What is a decimal?", 
                ques2Answers, 2, 3);
        ques2.setStudentAnswerIndex(0); // ques2 is wrong
        LinkedNode<MultipleChoiceQuestion> node2 = new LinkedNode<MultipleChoiceQuestion>(ques2);
        String[] ques3Answers = {"yellow","brown","orange"}; 
        MultipleChoiceQuestion ques3 = new MultipleChoiceQuestion("Colors", "What is yellow?", 
                ques3Answers, 0, 3);
        ques3.setStudentAnswerIndex(0); // ques3 is correct
        LinkedNode<MultipleChoiceQuestion> node3 = new LinkedNode<MultipleChoiceQuestion>(ques3);
        // node1 -> node2 -> node3
        node1.setNext(node2);
        node2.setNext(node3);
        test1.addLast(ques1);
        test1.addLast(ques2);
        test1.addLast(ques3);
        try {
            QuizQuestionsIterator itr2 = new QuizQuestionsIterator(node1);

            if (!itr2.hasNext()) {
                return false;
            }
            if (itr2.next() != ques1) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Test for switching modes
        try {
            test1.switchMode(ListingMode.INCORRECT);
            Iterator itr = test1.iterator();
            if (!itr.next().equals(ques1)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // Test for enhanced for loop
        try {
            test1.switchMode(ListingMode.CORRECT);
            Iterator itr = test1.iterator();
            String correctString = ques3.toString();
            String checkString = "";

            for (MultipleChoiceQuestion mcq : test1) {
                checkString = checkString + mcq.toString();
            }

            if (!correctString.equals(checkString)) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Runs all the tester methods defined in this QuizzerTester
     * @returns true if all tests pass and false if any of the tests fails
     */
    public static boolean runAllTests() {
        try {
            if (!testMultipleChoiceQuestion()||!testLinkedNode()||!testAdd()||!testAddFirst()||
                    !testAddLast() || !testCorrectQuestionsIterator() ||
                    !testInCorrectQuestionsIterator()||!testQuizQuestionsIterator()||
                    !testRemove()||!testRemoveFirst() || !testRemoveLast()) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("testMultipleChoiceQuestion: " + testMultipleChoiceQuestion());
        System.out.println("testLinkedNode: " + testLinkedNode());
        System.out.println("testAddLast: " + testAddLast());
        System.out.println("testAddFirst: " + testAddFirst());
        System.out.println("testAdd: " + testAdd());
        System.out.println("testRemoveFirst: " + testRemoveFirst());
        System.out.println("testRemoveLast: " + testRemoveLast());
        System.out.println("testRemove: " + testRemove());
        System.out.println("testCorrectQuestionsIterator: " + testCorrectQuestionsIterator());
        System.out.println("testInCorrectQuestionsIterator: " + testInCorrectQuestionsIterator());
        System.out.println("testQuizQuestionsIterator: " + testQuizQuestionsIterator());
        System.out.println("runAllTests: " + runAllTests());
        ListQuizzer test = new ListQuizzer();
        File testFile = new File("quizquestions.txt");
        /*try {
            test.loadQuestions(testFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        test.takeQuiz();*/
    }
}
