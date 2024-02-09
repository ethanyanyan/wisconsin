// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This interface models an abstract data type to store chugimons.
 * 
 * @author Kendall
 *
 */
public interface ChugidexStorage {

  /**
   * Checks whether storage is empty.
   * 
   * @return true if there are no Chugimon in storage, returns false if storage has at least one
   *         Chugimon.
   */
  public boolean isEmpty();

  /**
   * Adds a Chugimon to storage. Duplicate Chugimon may not be added.
   * 
   * @param chugi the Chugimon to be added.
   * @return true if the Chugimon was successfully added to stroage, false if the Chugimon was not
   *         added
   */
  public boolean add(Chugimon chugi);

  /**
   * Searches for a specific chugimon given its first and second identifiers
   * 
   * @param firstId  the first ID of the Chugimon to look up.
   * @param secondId the second ID of the Chugimon to look up.
   * @return the Chugimon if it is in storage, otherwise null
   */
  public Chugimon lookup(int firstId, int secondId);

  /**
   * Deletes a Chugimon from the tree.
   * 
   * @param chugi the Chugimon to delete.
   * @return true if the Chugimon was found and removed, false if the Chugimon was not found
   */
  public boolean delete(Chugimon chugi);

  /**
   * Returns the next (successor) of a current chugimon in this ChugidexStorage
   * 
   * @param chugi the provided chugimon to find its next
   * @return the Chugimon directly after the provided Chugimon
   * @throws NoSuchElementException if there is no Chugimon directly after the provided Chugimon
   * 
   */
  public Chugimon next(Chugimon chugi);

  /**
   * Returns the previous (predecessor) of a current chugimon in this ChugidexStorage
   * 
   * @param chugi the provided chugimon to find its previous
   * @return the Chugimon directly before the provided Chugimon
   * @throws NoSuchElementException if there is no Chugimon directly before the provided Chugimon
   * 
   */
  public Chugimon previous(Chugimon chugi);

  /**
   * Creates a string representation of this ChugidexStorage containing all the Chugimon. The string
   * should be a comma-separated list of all the Chugimon toString() values.
   * 
   * @return a string containing all of the Chugimon, in the increasing order.
   */
  public String toString();

  /**
   * Gets the size of this ChugidexStorage
   * 
   * @return the number of Chugimon in storage.
   */
  public int size();

  /**
   * Gets the first Chugimon in storage.
   * 
   * @return the first Chugimon in storage.
   */
  public Chugimon getFirst();

  /**
   * Gets the last Chugimon in storage.
   * 
   * @return the last Chugimon in storage.
   */
  public Chugimon getLast();

}
