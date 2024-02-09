import java.util.Iterator;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Course Registration
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

/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg {

  // data fields
  private CourseQueue courses;  // the priority queue of all courses
  private int creditLoad;       // the maximum number of credits you want to take
  
  /**
   * Creates a new course registration object
   * @param capacity the maximum number of courses to store in the priority queue
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
   */
  public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException {
    if (creditLoad < 1 || capacity < 1) {
      throw new IllegalArgumentException("parameters must be a positive integer");
    }
    this.courses = new CourseQueue(capacity);
    this.creditLoad = creditLoad;
  }
  
  /**
   * Returns a string representation of the highest-priority courses with a total number of credits
   * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
   * CourseQueue to help you out!
   * 
   * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
   * credits. Just add courses to your result String until adding the next would take you over this
   * CourseReg object's creditLoad limit.
   * 
   * @return a string representation with one course on each line, where the total number of credits
   *   represented is less than or equal to the current creditLoad value
   */
  public String getRecommendedCourses() {
    StringBuilder val = new StringBuilder();
    int creditCount = 0;
    Iterator<Course> courseIterator = courses.iterator();
    while (courseIterator.hasNext() && creditCount <= creditLoad) {
      Course nextCourse = courseIterator.next();
      if ((creditCount + nextCourse.getNumCredits()) <= creditLoad) {
        val.append(nextCourse.toString()).append("\n");
        creditCount = creditCount + nextCourse.getNumCredits();
      }
      if ((creditCount + nextCourse.getNumCredits()) > creditLoad) {
        return val.toString().trim();
      }
    }
    return val.toString().trim();
  }
  
  /**
   * Tries to add the given course to the priority queue; return false if the queue is full
   * 
   * @param toAdd the course to add
   * @return true if the course was successfully added to the queue
   */
  public boolean add(Course toAdd) {
    try {
      courses.enqueue(toAdd);
      return true;
    }
    catch (IllegalStateException e) {
      return false;
    }
    catch (NullPointerException e) {
      return false;
    }
  }
  
  /**
   * Updates the creditLoad data field to the provided value
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if creditLoad is not a positive integer
   */
  public void setCreditLoad(int creditLoad) throws IllegalArgumentException {
    if (creditLoad < 1) {
      throw new IllegalArgumentException("creditLoad must be a positive int");
    }
    this.creditLoad = creditLoad;
  }
}
