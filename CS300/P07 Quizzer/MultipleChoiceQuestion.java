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
import java.util.Arrays;

/**
 * This class models a Multiple Choice Question in a Quiz. A MultipleChoiceQuestion object has a
 * title, points, a question (the stem), a list of possible answers, and only one correct answer.
 *
 * @author Jeff and Mouna
 */
public class MultipleChoiceQuestion {

  // instance variables

  /** Title of this question */
  private String title;
  /** Stem which poses the question */
  private String question;
  /** A FULL perfect size array storing the list of possible answers or responses */
  private String[] answers;
  /** Index of the correct answer within the array answers */
  private int correctAnswerIndex;
  /**
   * Index of the answer selected by the student taking this question. This index equals -1 if the
   * question is NOT answered.
   */
  private int studentAnswerIndex;
  /** Possible points assigned to this question */
  private int pointsPossible;


  /**
   * Creates a new MultipleChoiceQuestion (MCQ) with the provided information. We assume that title,
   * question, and answers are valid. When created, a MCQ is NOT yet answered.
   * 
   * @param title              title of this question
   * @param question           stem which poses the question
   * @param answers            A FULL perfect size array storing the list of possible answers or
   *                           responses. We assume that the array does NOT contain any NULL
   *                           references.
   * @param correctAnswerIndex index of the correct answer
   * @param pointsPossible     score assigned to this question
   * @throws IndexOutOfBoundsException with a descriptive error message if correctAnswerIndex is out
   *                                   of the range 1 .. answers.length inclusive
   * @throws IllegalArgumentException  with a descriptive error message if pointsPossible is less or
   *                                   equal to zero
   */
  public MultipleChoiceQuestion(String title, String question, String[] answers,
      int correctAnswerIndex, int pointsPossible) {
    if(correctAnswerIndex < 0 || correctAnswerIndex >= answers.length)
      throw new IndexOutOfBoundsException("Invalid index");
    if(pointsPossible <= 0)
      throw new IllegalArgumentException("Invalid possible points");
    this.title = title;
    this.question = question;
    this.answers = answers;
    this.correctAnswerIndex = correctAnswerIndex;
    this.pointsPossible = pointsPossible;
    this.studentAnswerIndex = -1;
  }


  // getters and setters

  /**
   * Gets the title of this Multiple Choice Question
   * 
   * @return the title of this Multiple Choice Question
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title for this question. We assume that the input title is valid (not null nor blank)
   * 
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the question (stem) of this MultipleChoiceQuestion
   * 
   * @return the question (stem) of this MultipleChoiceQuestion
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Sets or updates the question (stem) of this MultipleChoiceQuestion
   * 
   * @param question the question to set
   */
  public void setQuestion(String question) {
    this.question = question;
  }

  /**
   * Returns a String representation of the different answer options of this MultipleChoiceQuestion.
   * Every answer option should be represented in a separate line preceded by index+1 followed by a
   * dot and one space, where index represents the index of the answer option within the answers
   * array. For instance, if the answers array contains the following:
   * 
   * {"oversize array", "perfect size array"}
   * 
   * The getAnswers method returns:
   * 
   * 1. oversize array 
   * 2. perfect size array
   * 
   * @return a String representation of the different answer options of this MultipleChoiceQuestion.
   */
  public String getAnswers() {
    String s = "";
    for (int i = 1; i <= answers.length; i++) {
      s += i + ". " + answers[i-1] + "\n";
    }

    return s.trim();
  }

  // /**
  // * Sets or updates the list of possible answers of this MultipleChoiceQuestion
  // *
  // * @param answers a FULL perfect size array storing the list of possible answers to set. We
  // assume
  // * that the input answers array is valid
  // */
  // public void setAnswers(String[] answers) {
  // this.answers = answers;
  // }

  /**
   * Gets the index of the correct answer within the array of possible answers
   * 
   * @return the index of the correct answer within the array of possible answers
   */
  public int getCorrectAnswerIndex() {
    return correctAnswerIndex;
  }

  /**
   * Sets or updates the index of the correct answer within the array of possible answers
   * 
   * @param correctAnswerIndex the index of the correct answer to set
   */
  public void setCorrectAnswerIndex(int correctAnswerIndex) {
    this.correctAnswerIndex = correctAnswerIndex;
  }


  /**
   * Checks whether the student answer index matches the index of the correct answer
   * 
   * @return true if the indexes of the student answer and correct answer match
   */
  public boolean isCorrect() {
    return this.correctAnswerIndex == this.studentAnswerIndex;
  }


  /**
   * Gets the possible points for this MultipleChoiceQuestion
   * 
   * @return the possible points for this MultipleChoiceQuestion
   */
  public int getPointsPossible() {
    return pointsPossible;
  }


  /**
   * Sets or updates the possible points for this MultipleChoiceQuestion
   * 
   * @param pointsPossible the possible points to set
   * @throws IllegalArgumentException with a descriptive error message if pointsPossible is negative
   *                                 or zero
   */
  public void setPointsPossible(int pointsPossible) {
    if (pointsPossible <= 0) throw new IllegalArgumentException(pointsPossible+" is an invalid value for points possible");
    this.pointsPossible = pointsPossible;
  }



  /**
   * Gets the index of the answer selected by the student
   * 
   * @return the studentAnswerIndex
   */
  public int getStudentAnswerIndex() {
    return studentAnswerIndex;
  }


  /**
   * Sets the student answer index
   * 
   * @param studentAnswerIndex the studentAnswerIndex to set
   * @throws IndexOutOfBoundsException with a descriptive error message if studentAnswerIndex is out
   *                                   of the range 0..answers.length-1 inclusive
   */
  public void setStudentAnswerIndex(int studentAnswerIndex) {
    if (studentAnswerIndex < 0 || studentAnswerIndex >= answers.length)
      throw new IndexOutOfBoundsException("invalid answer");
    this.studentAnswerIndex = studentAnswerIndex;
  }


  // String Version:
  /**
   * Returns a String representation of this MultipleChoiceQuestion conforming to the following
   * format.
   * 
   * "QUESTION TITLE: " + "\"" + title + "\"" + "\n" + "Question:\n" + question + "\n" + 
   * 
   * "Available Answers:\n" + getAnswers() // the string representation of the possible answers.
   * + "\nSelected Answer:" +  
   */
  @Override
  public String toString() {
    String questionText = "QUESTION TITLE: " + "\"" + this.title + "\"" + "\n" + "Question:\n"
        + question + "\n" + "Available Answers:\n" + this.getAnswers();

    return questionText.trim();
  }

  /**
   * Checks whether some other object equals this MultipleChoiceQuestion
   * 
   * @param other the reference object with which to compare.
   * @return true if other is instance of MultipleChoiceQuestion and has the same tile, question,
   *         pointsPossible, correctAnswerIndex, and list of possible answers as this
   *         MultipleChoiceQuestion object. String comparisons are CASE SENSITIVE.
   * 
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof MultipleChoiceQuestion
        && this.title.equals(((MultipleChoiceQuestion) other).title)
        && this.question.equals(((MultipleChoiceQuestion) other).question)
        && this.pointsPossible == ((MultipleChoiceQuestion) other).pointsPossible
        && this.correctAnswerIndex == ((MultipleChoiceQuestion) other).correctAnswerIndex
        && Arrays.deepEquals(answers, ((MultipleChoiceQuestion) other).answers);
  }

  /**
   * Returns a deep copy of this MultipleChoiceQuestion
   * 
   * @return a deep copy of this MultipleChoiceQuestion
   */
  public MultipleChoiceQuestion copy() {
    return new MultipleChoiceQuestion(this.title, this.question, this.answers,
        this.correctAnswerIndex, this.pointsPossible);
  }

}
