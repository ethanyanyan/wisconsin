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
 * This QuizQuestionsIterator class moves through MultipleChoiceQuestion(s) in a 
 * singly linked list defined by its head
 * 
 * @author Ethan Yikai Yan
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
    private LinkedNode<MultipleChoiceQuestion> next; // refers to a node in the singly linked 
                                                     // list of MultipleChoiceQuestion
    /**
     * Creates a new QuizQuestionsIterator to start iterating through a singly linked list 
     * storing MultipleChoiceQuestion elements
     * @param startNode - pointer to the head of the singly linked list
     */
    public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
        this.next = startNode;
    }

    /**
     * Returns true if this iteration has more MultipleChoiceQuestion(s).
     * Specified by: hasNext in interface Iterator<MultipleChoiceQuestion>
     * @return true if there are more MultipleChoiceQuestion(s) in this iteration
     */
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the next MultipleChoiceQuestion in this iteration
     * Specified by: next in interface Iterator<MultipleChoiceQuestion>
     * @return the next MultipleChoiceQuestion in this iteration
     * @throw NoSuchElementException - with a descriptive error message if there are no more 
     * questions in this iteration
     */
    public MultipleChoiceQuestion next() throws NoSuchElementException {
        if (next == null) {
            throw new NoSuchElementException("No more questions in Iteration!");
        }
        MultipleChoiceQuestion data = next.getData();
        next= next.getNext();
        return data;
    }
}
