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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the
 * max-heap invariant, so that the Course at the root should have the highest score, and all
 * children always have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> {
  
  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size;       // number of courses currently in this priority queue
  
  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("capcity cannot be negative");
    }
    this.queue = new Course[capacity];
    this.size = 0;
  }
  
  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate 
   * courses. Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    CourseQueue deepCopy = new CourseQueue(this.queue.length);
    Course[] deepCopyArr = new Course[this.queue.length];
    System.arraycopy(this.queue, 0, deepCopyArr, 0, size);
    deepCopy.queue = deepCopyArr;
    deepCopy.size = this.size;
    return deepCopy;
  }
  
  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY
   * of this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    return new CourseIterator(this.deepCopy());
  }
  
  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!
  
  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    }
    return false; 
  }
  
  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return this.size;
  }
  
  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to
   * maintain max-heap invariant of CourseQueue. Courses should be compared using 
   * the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  @Override
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    if (toAdd == null) {
      throw new NullPointerException("Course to add is null");
    }
    if (this.queue.length == size) {
      throw new IllegalStateException("CourseQueue is full");
    }
    if (this.size < this.queue.length) {
      this.queue[this.size] = toAdd;
      if (this.size > 0) {
        this.percolateUp(this.size);
      }
      this.size++;
    }
  }
  
  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course
   * with the highest priority. Use the percolateDown() method to maintain max-heap invariant of
   * CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is
   *                                empty
   */
  public Course dequeue() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("courseQueue is empty");
    }
    Course returnCourse = this.queue[0];
    this.queue[0] = this.queue[this.size - 1];
    this.percolateDown(0);
    this.size--;
    return returnCourse;
  }
  
  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with
   * the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  public Course peek() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("courseQueue is empty");
    }
    return this.queue[0];
  }
  
  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////
  
  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If 
   * the element at the given index does not violate the max-heap invariant (it is higher priority 
   * than its children), then this method does not modify the heap. 
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and 
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index out of Bounds");
    }
    int parentIndex = index;
    while (true) {
      int leftChildIndex = (2 * parentIndex) + 1;
      int rightChildIndex = (2 * parentIndex) + 2;
      int maxChildIndex;
      if (leftChildIndex > this.size - 1 || rightChildIndex > this.size - 1) {
        break;
      }
      if (this.queue[leftChildIndex] == null) {
        break;
      }
      Course parent = this.queue[parentIndex];
      Course leftChild = this.queue[leftChildIndex];
      Course rightChild = this.queue[rightChildIndex];
      if (parent.compareTo(rightChild) >= 0 && parent.compareTo(leftChild) >= 0) {
        break;
      }
      Course maxChild;
      if (leftChild.compareTo(rightChild) > 0) {
        maxChild = leftChild;
        maxChildIndex = leftChildIndex; 
      } else {
        maxChild = rightChild;
        maxChildIndex = rightChildIndex; 
      }

      // Swap current parent with max Child
      Course temp = parent;
      this.queue[parentIndex] = maxChild;
      this.queue[maxChildIndex] = temp;
      parentIndex = maxChildIndex; // Set up next loops' parentIndex to further percolate down

    }
  }
  
  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element 
   * at the given index does not violate the max-heap invariant (it is lower priority than its 
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("Index out of Bounds");
    }
    int childIndex = index;
    while (true) {
      if (childIndex == 0) {
        break;
      }
      int parentIndex = (childIndex - 1) / 2;
      Course child = this.queue[childIndex];
      Course parent = this.queue[parentIndex];
      if (parent.compareTo(child) >= 0) {
        break;
      }

      // Swap current parent with max Child
      Course temp = parent;
      this.queue[parentIndex] = child;
      this.queue[childIndex] = temp;

      childIndex = parentIndex; // Set up next loops' childIndex to further percolate up

    }
  }
  
  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////
  
  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is 
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();
    
    for (Course c : this) {
      val.append(c).append("\n");
    }
    
    return val.toString().trim();
  }

}
