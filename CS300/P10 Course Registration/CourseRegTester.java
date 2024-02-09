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

import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Course,  CourseIterator, 
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {
  
  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    // Testing constructor for illegal arguments
    try {
      Course test1 = new Course(null, 10, 1, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("", 10, 1, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 0, 1, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", -1, 1, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 0, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, -1, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 6, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, -1);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    // Test mutators and accessor methods
    try {
      Course test1 = new Course("Math", 1, 1, 10);
      if (test1.getNumCredits() != 1) {
        return false;
      }
      if (!test1.toString().equals("Math 1 (10 seats)")) {
        return false;
      }
      test1.setSeatsAvailable(100);
      if (!test1.toString().equals("Math 1 (100 seats)")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, 10);
      test1.setSeatsAvailable(-1);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, 10);
      test1.setProfessor("Ethan", 6);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, 10);
      test1.setProfessor("Ethan", -1);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, 10);
      test1.setProfessor(null, -5);
      if (!test1.toString().equals("Math 1 (10 seats)")) {
        return false;
      }
      test1.setProfessor("Ethan", 3);
      if (!test1.toString().equals("Math 1 (10 seats) with Ethan (3.0)")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("Math", 1, 1, 10);
      Course test2 = new Course("Math", 1, 1, 10);
      if (!test1.equals(test2)) {
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
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels
   * of the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
    try {
      if (!testCourse()) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      Course test1 = new Course("CS", 300, 1, 0);
      Course test2 = new Course("MATH", 340, 1, 10);
      Course test3 = new Course("PHYSICS", 234, 3, 0);
      Course test4 = new Course("MATH", 222, 3, 100);
      test4.setProfessor("Ethan Yan", 2);
      Course test5 = new Course("CHEMISTRY", 221, 3, 50);
      test5.setProfessor("Clarice", 3);
      Course test6 = new Course("CHEMISTRY", 234, 3, 0);
      Course test7 = new Course("CS", 300, 1, 10);
      Course test8 = new Course("CS", 300, 1, 10);
      test8.setProfessor("Ethan2", 2);
      Course test9 = new Course("CS", 300, 1, 10);
      test9.setProfessor("Ethan2", 4);

      // Comparing CS courses
      if (test7.compareTo(test1) <= 0 || test1.compareTo(test7) >= 0) {
        return false;
      }
      if (test8.compareTo(test7) <= 0 || test7.compareTo(test8) >= 0) {
        return false;
      }
      if (test9.compareTo(test8) <= 0 || test8.compareTo(test9) >= 0) {
        return false;
      }

      // Comparing CS course with non-CS course
      if (test1.compareTo(test2) <= 0 || test2.compareTo(test1) >= 0) {
        return false;
      }
      if (test1.compareTo(test3) <= 0 || test3.compareTo(test1) >= 0) {
        return false;
      }
      if (test1.compareTo(test4) <= 0 || test4.compareTo(test1) >= 0) {
        return false;
      }
      if (test1.compareTo(test5) <= 0 || test5.compareTo(test1) >= 0) {
        return false;
      }
      // Comparing 2 non-CS courses, test3 has no seats available
      if (test2.compareTo(test3) <= 0 || test3.compareTo(test2) >= 0) {
        return false;
      }
      // Compare 2 non-CS courses with same params, different course name, ensure course name is
      // not compared
      if (test6.compareTo(test3) != 0 || test3.compareTo(test6) != 0) {
        return false;
      }
      // Comparing 2 MATH courses, both with seats, test4 has known professor
      if (test4.compareTo(test2) <= 0 || test2.compareTo(test4) >= 0) {
        return false;
      }
      // Comparing 2 MATH courses, both with seats and known professor, test5 higher rating prof
      // Ensure values of seats and professor name are not compared, in both cases, test5 < test4
      // if compared
      if (test5.compareTo(test4) <= 0 || test4.compareTo(test5) >= 0) {
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
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal 
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    try {
      CourseQueue test1 = new CourseQueue(0);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      if (!test1.isEmpty()) {
        return false;
      }
      if (test1.size() != 0) {
        return false;
      }
      if (test1.peek() != null) {
        return false;
      }
      return false;
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(1);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      test1.enqueue(course1);
      test1.enqueue(course2);
      return false;
    }
    catch (IllegalStateException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(1);
      Course course1 = new Course("CS", 200, 3, 100);
      test1.enqueue(course1);
      if (test1.size() != 1) {
        return false;
      }
      if (test1.isEmpty()) {
        return false;
      }
      if (!test1.peek().equals(course1)) {
        return false;
      }
      test1.dequeue();
      if (test1.size() != 0) {
        return false;
      }
      if (!test1.isEmpty()) {
        return false;
      }
      test1.peek();
      return false;
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(3);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      test1.enqueue(course1);
      test1.enqueue(course2);
      test1.enqueue(course3); // courseQueue full

      if (!test1.peek().equals(course3)) {
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
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
    try {
      CourseQueue test1 = new CourseQueue(5);
      test1.enqueue(null);
      return false;
    }
    catch (NullPointerException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      test1.dequeue();
      return false;
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(3);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      test1.enqueue(course1);
      test1.enqueue(course2);
      test1.enqueue(course3); // courseQueue full
      test1.enqueue(course4);
      return false;
    }
    catch (IllegalStateException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      test1.enqueue(course1);
      if (!test1.peek().equals(course1)) {
        return false;
      }
      if (test1.isEmpty()) {
        return false;
      }
      if (test1.size() != 1) {
        return false;
      }

      //       course 2
      //  course 1
      test1.enqueue(course2);
      if (!test1.peek().equals(course2)) {
        return false;
      }
      if (test1.isEmpty()) {
        return false;
      }
      if (test1.size() != 2) {
        return false;
      }

      //       course 3
      //  course 1   course 2
      test1.enqueue(course3);
      if (!test1.peek().equals(course3)) {
        return false;
      }
      if (test1.isEmpty()) {
        return false;
      }
      if (test1.size() != 3) {
        return false;
      }

      //             course5
      //      course3      course2
      // course4  course1
      test1.enqueue(course4);
      test1.enqueue(course5);
      if (!test1.peek().equals(course5)) {
        return false;
      }
      if (test1.isEmpty()) {
        return false;
      }
      if (test1.size() != 5) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      test1.enqueue(course1);
      test1.enqueue(course2);
      test1.enqueue(course3);
      test1.enqueue(course4);
      test1.enqueue(course5);
      if (test1.size() != 5) {
        return false;
      }
      //             course5
      //      course3      course2
      // course4  course1
      if (!test1.dequeue().equals(course5)) {
        return false;
      }
      //             course3
      //      course1      course2
      // course4  
      if (test1.size() != 4) {
        return false;
      }
      if (!test1.peek().equals(course3)) {
        return false;
      }

      if (!test1.dequeue().equals(course3)) {
        return false;
      }
      //             course2
      //      course1      course4
      if (test1.size() != 3) {
        return false;
      }
      if (!test1.peek().equals(course2)) {
        return false;
      }

      if (!test1.dequeue().equals(course2)) {
        return false;
      }
      //             course1
      //      course4     
      if (test1.size() != 2) {
        return false;
      }
      if (!test1.peek().equals(course1)) {
        return false;
      }

      if (!test1.dequeue().equals(course1)) {
        return false;
      }
      //             course4    
      if (test1.size() != 1) {
        return false;
      }
      if (!test1.peek().equals(course4)) {
        return false;
      }

      if (!test1.dequeue().equals(course4)) {
        return false;
      }
      if (test1.size() != 0) {
        return false;
      }
      if (!test1.isEmpty()) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 4.5);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      test1.enqueue(course4);
      if (!test1.peek().equals(course4)) {
        return false;
      }
      test1.enqueue(course5);
      if (!test1.peek().equals(course5)) {
        return false;
      }
      test1.enqueue(course1);
      if (!test1.peek().equals(course5)) {
        return false;
      }
      test1.enqueue(course2);
      if (!test1.peek().equals(course5)) {
        return false;
      }
      test1.enqueue(course3);
      if (!test1.peek().equals(course5)) {
        return false;
      }
      if (!test1.dequeue().equals(course5)) {
        return false;
      }
      //             course3
      //     course2        course1
      //course4  
      if (!test1.dequeue().equals(course3)) {
        return false;
      }
      //             course2
      //     course4        course1
      if (!test1.dequeue().equals(course2)) {
        return false;
      }
      if (!test1.dequeue().equals(course1)) {
        return false;
      }
      if (!test1.dequeue().equals(course4)) {
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
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
    try {
      CourseQueue test1 = new CourseQueue(5);
      CourseIterator tester = new CourseIterator(test1.deepCopy());
      if (tester.hasNext()) {
        return false;
      }
      tester.next();
      return false;
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      test1.enqueue(course1);
      test1.enqueue(course2);
      test1.enqueue(course3);
      test1.enqueue(course4);
      test1.enqueue(course5);
      CourseIterator tester = new CourseIterator(test1.deepCopy());
      if (!tester.hasNext()) {
        return false;
      }
      //             course5
      //      course3      course2
      // course4  course1
      if (!tester.next().equals(course5)) {
        return false;
      }

      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(course3)) {
        return false;
      }

      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(course2)) {
        return false;
      }

      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(course1)) {
        return false;
      }

      if (!tester.hasNext()) {
        return false;
      }
      if (!tester.next().equals(course4)) {
        return false;
      }

      if (tester.hasNext()) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseQueue test1 = new CourseQueue(5);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      test1.enqueue(course2);
      test1.enqueue(course3);
      test1.enqueue(course5);
      CourseIterator tester = new CourseIterator(test1.deepCopy());
      if (!tester.hasNext()) {
        return false;
      }
      //             course5
      //      course3      course2
      if (!tester.next().equals(course5)) {
        return false;
      }
      if (!tester.next().equals(course3)) {
        return false;
      }
      if (!tester.next().equals(course2)) {
        return false;
      }

      tester.next();
    }
    catch (NoSuchElementException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; 
  }
  
  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    try {
      CourseReg tester = new CourseReg(0, 10);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseReg tester = new CourseReg(5, 0);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseReg tester = new CourseReg(5, 5);
      tester.setCreditLoad(0);
      return false;
    }
    catch (IllegalArgumentException e) {}
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    
    try {
      CourseReg tester = new CourseReg(1, 20);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      if (!tester.add(course1)) {
        return false;
      }
      if (tester.add(course2)) {
        return false;
      }
      if (!tester.getRecommendedCourses().trim().equals("CS 200 (100 seats)")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseReg tester = new CourseReg(5, 9);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 3, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      if (!tester.add(course1)) {
        return false;
      }
      if (!tester.add(course2)) {
        return false;
      }
      if (!tester.add(course3)) {
        return false;
      }
      if (!tester.add(course4)) {
        return false;
      }
      if (!tester.add(course5)) {
        return false;
      }
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)")) {
            return false;
      }

      tester.setCreditLoad(11);
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)")) {
            return false;
      }

      tester.setCreditLoad(12);
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)" + 
          "\nCS 200 (100 seats)")) {
            return false;
      }

      tester.setCreditLoad(15);
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)" + 
          "\nCS 200 (100 seats)\nMATH 400 (100 seats) with Ethan3 (4.0)")) {
            return false;
      }

      tester.setCreditLoad(20);
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)" + 
          "\nCS 200 (100 seats)\nMATH 400 (100 seats) with Ethan3 (4.0)")) {
            return false;
      }

      tester.setCreditLoad(1);
      if (!tester.getRecommendedCourses().trim().equals("")) {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    try {
      CourseReg tester = new CourseReg(5, 10);
      Course course1 = new Course("CS", 200, 3, 100);
      Course course2 = new Course("CS", 300, 3, 100);
      course2.setProfessor("Ethan", 2);
      Course course3 = new Course("CS", 400, 3, 100);
      course3.setProfessor("Ethan2", 3);
      Course course4 = new Course("MATH", 400, 1, 100);
      course4.setProfessor("Ethan3", 4);
      Course course5 = new Course("CS", 500, 3, 200);
      course5.setProfessor("Ethan2", 5);
      if (!tester.add(course1)) {
        return false;
      }
      if (!tester.add(course2)) {
        return false;
      }
      if (!tester.add(course3)) {
        return false;
      }
      if (!tester.add(course4)) {
        return false;
      }
      if (!tester.add(course5)) {
        return false;
      }
      if (!tester.getRecommendedCourses().trim().equals("CS 500 (200 seats) with Ethan2 (5.0)" + 
          "\nCS 400 (100 seats) with Ethan2 (3.0)\nCS 300 (100 seats) with Ethan (2.0)")) {
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
   * This method calls all test methods defined by us; you may add additional methods to this if
   * you like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;
    
    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    return testVal;
  }
  
  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
