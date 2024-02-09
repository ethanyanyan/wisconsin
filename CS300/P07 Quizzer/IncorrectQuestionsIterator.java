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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This IncorrectQuestionsIterator class Implements an iterator to iterate over incorrectly answered 
 * MultipleChoiceQuestion(s) stored in a singly linked list defined by its head.
 * 
 * @author Ethan Yikai Yan
 */
public class IncorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
    private LinkedNode<MultipleChoiceQuestion> next; // refers to a node in the singly linked list 
                                                     // of MultipleChoiceQuestion to traverse
    /**
     * Creates a new InCorrectQuestionsIterator to start iterating through a singly linked list 
     * storing MultipleChoiceQuestion elements
     * @param startNode - pointer to the head of the singly linked list
     */
    public IncorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
        this.next = startNode;
    }

    /**
     * Returns true if this iteration has more MultipleChoiceQuestion(s) answered incorrectly.
     * Specified by: hasNext in interface Iterator<MultipleChoiceQuestion>
     * @return true if there are more incorrect MultipleChoiceQuestion(s) in this iteration
     */
    public boolean hasNext() {
        LinkedNode<MultipleChoiceQuestion> temp = this.next;
        while (temp != null) {
            if (!temp.getData().isCorrect()) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * Returns the next incorrect MultipleChoiceQuestion in this iteration
     * Specified by: next in interface Iterator<MultipleChoiceQuestion>
     * @return the next incorrect MultipleChoiceQuestion in this iteration
     * @throw NoSuchElementException - with a descriptive error message if there are no more 
     * incorrect questions in this iteration
     */
    public MultipleChoiceQuestion next() throws NoSuchElementException {
        MultipleChoiceQuestion data = null;
        if (!hasNext()) {
            throw new NoSuchElementException("No more incorrect questions!");
        }
        while (next.getData().isCorrect() && next != null) {
            next = next.getNext();
        }
        if (next == null) {
            return null;
        }
        if (!next.getData().isCorrect()) {
            data = next.getData();
            next = next.getNext();
        }
        return data;
    }
}
