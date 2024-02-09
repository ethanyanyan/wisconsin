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
import java.util.Scanner;

/**
 * This ListQuizzer class models an iterable singly-linked list data structure which 
 * stores elements of type MultipleChoiceQuestion.
 * 
 * @author Ethan Yikai Yan
 */
public class ListQuizzer implements Iterable<MultipleChoiceQuestion> {
    private LinkedNode<MultipleChoiceQuestion> head; // Head of this singly linked list
    private ListingMode listingMode; // The listing mode of this list quizzer which defines which 
                                     // questions are going to be listed while iterating through 
                                     // this list
    private int size; // Total number of MultipleChoiceQuestions stored in this ListQuizzer
    private LinkedNode<MultipleChoiceQuestion> tail; // Tail of this singly linked list

    /**
     * This constructor creates a new empty instance of ListQuizzer which contains zero elements 
     * and sets its listing mode to be ListingMode.ALL by default.
     */
    public ListQuizzer() {
        this.listingMode = ListingMode.ALL;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Checks whether this list is empty
     * @return true if this list is empty and false otherwise
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the size of this list
     * @return the size of this list
     */
    public int size() {
        return this.size;
    }

    /**
     * Sets the listing mode of this list to the one provided as input
     * @param listingMode - listing mode to set
     */
    public void switchMode(ListingMode listingMode) {
        this.listingMode = listingMode;
    }

    /**
     * Adds a specific MultipleChoiceQuestion to a given position of this list
     * @param index - position index where to add the newQuestion to this list
     * @param question - some MultipleChoiceQuestion to add to this list
     * @throws NullPointerException - with a descriptive error message if newQuestion is null
     * @throws IndexOutOfBoundsException - with a descriptive error message if index is OUT of 
     *                                     the range 0 .. size() inclusive
     */
    public void add(int index, MultipleChoiceQuestion question) 
            throws NullPointerException, IndexOutOfBoundsException {
        if (index == 0) {
            addFirst(question);
        }
        if (index == this.size) {
            addLast(question);
        }
        if (question == null) {
            throw new NullPointerException("newQuestion is null!");
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }
        LinkedNode<MultipleChoiceQuestion> before = this.head;
        for (int i = 0; i < index - 1; i ++) {
            before = before.getNext();
        }
        LinkedNode<MultipleChoiceQuestion> after = before.getNext();
        LinkedNode<MultipleChoiceQuestion> temp = new LinkedNode<MultipleChoiceQuestion>(question);
        before.setNext(temp);
        temp.setNext(after);
        this.size++;
    }

    /**
     * Adds a specific MutlipleChoiceQuestion to the head of this list
     * @param question - some MultipleChoiceQuestion to add to the head of this list
     * @throws NullPointerException - with a descriptive error message if newQuestion is null
     */
    public void addFirst(MultipleChoiceQuestion question)
              throws NullPointerException {
        if (question == null) {
            throw new NullPointerException("question is null!");
        }
        LinkedNode<MultipleChoiceQuestion> temp = new LinkedNode<MultipleChoiceQuestion>(question);
        if (this.size == 0) {
            this.head = temp;
            this.tail = temp;
        } else {
            temp.setNext(this.head);
            this.head = temp;
        }
        this.size++;
    }

    /**
     * Adds a specific MutlipleChoiceQuestion to the tail of this list
     * @param question - some MultipleChoiceQuestion to add to the head of this list
     * @throws NullPointerException - with a descriptive error message if newQuestion is null
     */
    public void addLast(MultipleChoiceQuestion question)
             throws NullPointerException {
        if (question == null) {
            throw new NullPointerException("question is null!");
        }
        LinkedNode<MultipleChoiceQuestion> temp = new LinkedNode<MultipleChoiceQuestion>(question);
        if (this.size == 0) {
            this.head = temp;
            this.tail = temp;
        } else {
            this.tail.setNext(temp);
            this.tail = temp;
            temp.setNext(null);
        }
        this.size++;
    }

    /**
     * This method removes all the question from this list. 
     * The list should be empty after this method is called.
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Checks whether this list contains a match with someQuestion
     * @param someQuestion - some question to find
     * @return true if this list contains a match with someQuestion and false otherwise
     */
    public boolean contains(MultipleChoiceQuestion someQuestion) {
        LinkedNode<MultipleChoiceQuestion> temp = this.head;
        if (temp.getData() == someQuestion) {
            return true;
        }
        for (int i = 0; i < this.size; i++) {
            temp = temp.getNext();
            if (temp.getData() == someQuestion) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the MultipleChoiceQuestion stored at the given index within this list
     * @param index - index of the MultipleChoiceQuestion to return
     * @return the MultipleChoiceQuestion stored at the given index within this list
     * @throws IndexOutOfBoundsException - if index is out of the range 0 .. size()-1 inclusive
     */
    public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }
        LinkedNode<MultipleChoiceQuestion> temp = this.head;
        for (int i = 0; i < index; i ++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /**
     * Gets the MultipleChoiceQuestion at the head of this list
     * @return the MultipleChoiceQuestion at the head of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public MultipleChoiceQuestion getFirst() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("List is empty!");
        }
        return this.head.getData();
    }

    /**
     * Gets the MultipleChoiceQuestion at the tail of this list
     * @return the MultipleChoiceQuestion at the tail of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public MultipleChoiceQuestion getLast() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("List is empty!");
        }
        return this.tail.getData();
    }

    /**
     * Removes and returns the MultipleChoiceQuestion at the given index
     * @param index - of the MultipleChoiceQuestion to remove
     * @return the removed MultipleChoiceQuestion
     * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of 
     *                                     the range 0 .. size()-1 inclusive
     */
    public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == this.size - 1) {
            return removeLast();
        }
        LinkedNode<MultipleChoiceQuestion> before = this.head;
        for (int i = 0; i < index - 1; i ++) {
            before = before.getNext();
        }
        LinkedNode<MultipleChoiceQuestion> after = before.getNext().getNext();
        LinkedNode<MultipleChoiceQuestion> removedNode = before.getNext();
        before.setNext(after);
        this.size--;
        return removedNode.getData();
    }

    /**
     * Removes and returns the MultipleChoiceQuestion at the head of this list
     * @return the MultipleChoiceQuestion at the head of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public MultipleChoiceQuestion removeFirst() throws NoSuchElementException {
        MultipleChoiceQuestion removedData = null;
        if (this.size == 0) {
            throw new NoSuchElementException("List is empty!");
        }
        if (this.size == 1) {
            removedData = this.head.getData();
            this.head = null;
            this.tail = null;
            this.size--;
            return removedData;
        }
        removedData = this.head.getData();
        this.head = this.head.getNext();
        this.size--;
        return removedData;
    }

    /**
     * Removes and returns the MultipleChoiceQuestion at the tail of this list
     * @return the MultipleChoiceQuestion at the tail of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public MultipleChoiceQuestion removeLast() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("List is empty!");
        }
        if (size == 1) {
            this.head = null;
            this.tail = null;
            this.size--;
        }
        LinkedNode<MultipleChoiceQuestion> temp = this.head;
        for (int i = 0; i < size - 2; i ++) {
            temp.getNext();
        }
        MultipleChoiceQuestion removedData = this.tail.getData();
        temp.setNext(null);
        this.tail = temp;
        this.size--;
        return removedData;
    }

    /**
     * Returns an iterator to iterate through this list with respect to the listingMode. If the 
     * listingMode is ALL, the returned iterator is initialized to the head of this list. If the 
     * listingMode is CORRECT, the returned iterator is initialized to the node carrying first 
     * correctly answered question If the listingMode is INCORRECT, the returned iterator is 
     * initialized to the node carrying first incorrectly answered question
     * Specified by: iterator in interface Iterable<MultipleChoiceQuestion>
     * @return an iterator to iterate through this list with respect to the listingMode of this 
     * list.
     */
    // TODO
    public Iterator<MultipleChoiceQuestion> iterator() {
        Iterator<MultipleChoiceQuestion> listItr = null;
        if (listingMode == ListingMode.ALL) {
            return new QuizQuestionsIterator(this.head);
        }
        if (listingMode == ListingMode.CORRECT) {
            LinkedNode<MultipleChoiceQuestion> correctQues = this.head;
            while (!correctQues.getData().isCorrect() && correctQues != null) {
                correctQues = correctQues.getNext();
            }
            if (correctQues.getData().isCorrect()) {
                return new CorrectQuestionsIterator(correctQues);
            }
        }
        if (listingMode == ListingMode.INCORRECT) {
            LinkedNode<MultipleChoiceQuestion> IncorrectQues = this.head;
            while (IncorrectQues.getData().isCorrect() && IncorrectQues != null) {
                IncorrectQues = IncorrectQues.getNext();
            }
            if (!IncorrectQues.getData().isCorrect()) {
                return new IncorrectQuestionsIterator(IncorrectQues);
            }
        }
        return listItr;
    }

    /**
     * Calculates the total points of the correctly answered questions of this ListQuizzer
     * @return the score of this ListQuizzer
     */
    public int calculateScore() {
        int score = 0;
        LinkedNode<MultipleChoiceQuestion> temp = this.head;
        if (temp.getData().isCorrect()) {
            score = score + temp.getData().getPointsPossible();
        }
        for (int i = 0; i < this.size; i++) {
            temp = temp.getNext();
            if (temp.getData().isCorrect()) {
                score = score + temp.getData().getPointsPossible();
            }
        }
        return score;
    }

    /**
     * Calculates the total possible points of this ListQuizzer
     * @return the score of this ListQuizzer
     */
    // TODO
    public int calculateTotalPoints() {
        int totalPoints = 0;
        LinkedNode<MultipleChoiceQuestion> temp = this.head;
        totalPoints = totalPoints + temp.getData().getPointsPossible();
        for (int i = 0; i < this.size; i++) {
            temp = temp.getNext();
            if (temp.getData().isCorrect()) {
                totalPoints = totalPoints + temp.getData().getPointsPossible();
            }
        }
        return totalPoints;
    }

    /**
     * Returns a deep copy of this list. This method creates a copy of this list as a new 
     * ListQuizzer and adds deep copies of each MultipleChoiceQuestion stored in this list to the 
     * deep copy.
     * @return a deep copy of this list
     */
    // TODO
    public ListQuizzer copy() {
        ListQuizzer newCopy = new ListQuizzer();
        LinkedNode<MultipleChoiceQuestion> count = this.head;
        for (int i = 0; i < size; i++) {
            newCopy.addLast(count.getData().copy());
            count.getNext();
        }
        return newCopy;
    }

    /**
     * Loads MultipleChoiceQuestions from a file
     * 
     * @author Jeff and Mouna
     * 
     * @param file file to read
     * @return the number of added MultipleChoiceQuestions to this list
     * @throws FileNotFoundException if the file is not found
     */
    public int loadQuestions(File file) throws FileNotFoundException {
        int loadedCount = 0; // count of loaded multiple choice questions
        int answerCount = 0; // count of possible answers per question
        int indexCorrectAnswer = 0; // index of the correct answer
        int points = 0; // possible points for a multiple choice question
        // try to read the file
        Scanner reader = null; // scanner to read the file line by line
        int lineNumber = 0; // number of the last read line
    
        try {
          reader = new Scanner(file);
          // parse the file lines - while loop to read parts of each multiple choice question
          while (reader.hasNextLine()) { // no more lines to read
            // read title
            String title = reader.nextLine();
            lineNumber++;
    
            // read question stem
            if (!reader.hasNextLine()) { // no more lines to read
              return loadedCount;
            }
            String question = reader.nextLine();
            lineNumber++;
    
            // read possible answers count
            if (!reader.hasNextLine()) { // no more lines to read
              return loadedCount;
            }
            String count = reader.nextLine();
            lineNumber++;
            // check the validity of count
            try {
              answerCount = Integer.parseInt(count.trim());
              if (answerCount <= 0 || answerCount > 10) {
                throw new NumberFormatException();
              }
            } catch (NumberFormatException e) { // count invalid - print an error message and return
              System.out
                  .println("Syntax error! A positive integer less or equal to 10 is expected at line "
                      + lineNumber + (". Load questions operation interrupted!"));
              return loadedCount;
            }
            // valid count -> create the answerList array
            String[] answerList = new String[answerCount];
            int index = 0;
            while (index < answerCount && reader.hasNextLine()) {
              String answer = reader.nextLine();
              lineNumber++;
              answerList[index] = answer;
              index++;
            }
    
            // read index of the correct answer
            if (!reader.hasNextLine()) { // no more lines to read
              return loadedCount;
            }
            String line = reader.nextLine();
            lineNumber++;
            try { // check the validity of the index of the correct answer
              indexCorrectAnswer = Integer.parseInt(line.trim());
              if (indexCorrectAnswer < 0 || indexCorrectAnswer >= answerCount) {
                throw new NumberFormatException();
              }
            } catch (NumberFormatException e) { // indexCorrectAnswer invalid - print error and return
              System.out.println("Syntax error! A positive integer less than " + answerCount
                  + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));
              return loadedCount;
            }
            // valid index of the correct answer -> read possible points
            // read points
            if (!reader.hasNextLine()) { // no more lines to read
              return loadedCount;
            }
            line = reader.nextLine();
    
            lineNumber++;
            try { // check the validity of the index of the correct answer
              points = Integer.parseInt(line.trim());
    
              if (points < 0) {
                throw new NumberFormatException();
              }
            } catch (NumberFormatException e) { // invalid points - print error message and return
              System.out.println("Syntax error! A positive integer for possible points "
                  + " is expected at line " + lineNumber + (". Load questions operation interrupted!"));
    
              return loadedCount;
            }
              // create and add quizQuestion
              MultipleChoiceQuestion quizQuestion =
                  new MultipleChoiceQuestion(title, question, answerList, indexCorrectAnswer, points);
    
              this.addLast(quizQuestion);
              loadedCount += 1;
              System.out.println("Question " + loadedCount + " loaded!");
    
          }
        } finally {
          if (reader != null)
            reader.close();
        }
    
        return loadedCount;
      }

    /**
     * Allows a user to take this quiz. The quiz should be taken on a deep copy of this ListQuizzer.
     * This method should not make any changes to the contents of this ListQuizzer.
     * 
     * @author Jeff and Mouna
     * 
     * @return the instance of ListQuizzer taken by the user. It should include the user's responses.
     */
    public ListQuizzer takeQuiz() {

        ListQuizzer copy = this.copy();
        copy.switchMode(ListingMode.ALL);
        Scanner input = new Scanner(System.in);
        for (MultipleChoiceQuestion question : copy) {
          System.out.println(question);
          System.out.print("Enter your answer: ");
          int entry = input.nextInt();
          question.setStudentAnswerIndex(entry - 1);
          if (question.isCorrect()) {
            System.out.println("Correct!");
          } else {
            System.out.println("Incorrect!");
          }
        }
        int correctPoints = copy.calculateScore();
        int totalPoints = copy.calculateTotalPoints();
        System.out.println("Your Score: " + correctPoints);
        System.out.println("Percentage: " + correctPoints / totalPoints);
        input.close();
        return copy;
      }

    /**
     * Returns true if o is a ListQuizzer which has the exact same contents as this ListQuizzer
     * 
     * @author Mouna
     *
     * @param o an object to compare with
     * @return true if o is instanceof ListQuizzer with the exact same contents as this ListQuizzer
     */
    @Override 
    public boolean equals(Object o) {
        if(o instanceof ListQuizzer) {
            ListQuizzer other = (ListQuizzer)o;
            if(this.size()!= other.size())
                return false;
            this.switchMode(ListingMode.ALL);
            other.switchMode(ListingMode.ALL);
            Iterator<MultipleChoiceQuestion> iterator = this.iterator();
            Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
            while(iterator.hasNext()) {
                if(!iterator.next().equals(otherIterator.next()))
                return false;
            }
            return true;
        }
        return false;
    }
}
