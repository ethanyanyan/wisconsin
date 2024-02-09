// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This class models a Chugidex catalog offering operations to load chugimons into a storage data
 * structures and navigate through them.
 *
 * @author Kendall
 *
 */
public class Chugidex {

  private ChugidexStorage storage; // data structure to store Chugimons
  private Chugimon currChugimon; // current Chugimon
  private Random random; // generator of ransom numbers

  /**
   * Creates a new Chugidex storage data structure which contains a specific number of chugimons
   *
   * @param size initial number of chugimons added to this new chugidex
   * @throws RuntimeException if an error of any type occurs while trying to create a new chugimon
   *                          to be added to this catalog
   */
  public Chugidex(int size) {
    // TODO(student): replace this:
    this.storage = new DummyStorage();
    // with:
    // this.storage = new ChugiTree();

    this.random = new Random();

    // Creates and adds size chugimons and add them to this chugidex
    for (int i = 0; i < size; i++) {
      addRandomChugimon();
    }

    // this.storage.add(createChugimon(145, 25));

    // initializes the current chugimon to the first chugimon in this data structure
    this.currChugimon = storage.getFirst();
  }

  /**
   * Creates a new Chugidex storage data structure which contains 10 chugimons, initially
   *
   * @throws RuntimeException if an error of any type occurs while trying to create a new chugimon
   *                          to be added to this catalog
   */
  public Chugidex() {
    this(10);
  }

  /**
   * Creates and returns a new Chugimon given its first and second identifiers
   *
   * @param firstID  first identifier of the chugimon to create
   * @param secondID second identifier of the chugimon to create
   * @return the reference to the created chugimon
   */
  public Chugimon createChugimon(int firstID, int secondID) {
    return new Chugimon(firstID, secondID);
  }

  /**
   * Creates and adds a new Chugimon with random first and second identifiers to this Chugidex.
   */
  public void addRandomChugimon() {
    int firstID = this.random.nextInt(Chugimon.MAX_ID - Chugimon.MIN_ID) + Chugimon.MIN_ID;
    int secondID = this.random.nextInt(Chugimon.MAX_ID - Chugimon.MIN_ID) + Chugimon.MIN_ID;

    try {
      Chugimon chugi = this.createChugimon(firstID, secondID);
      storage.add(chugi);
      System.out.println("Adding " + chugi);
    } catch (IllegalArgumentException e) {
      addRandomChugimon();
    }
  }

  /**
   * Gets the current chugimon
   *
   * @return the current chugimon
   */
  public Chugimon currentChugimon() {
    return this.currChugimon;
  }

  /**
   * Moves the iteration to the next Chugimon
   */
  public void nextChugimon() {
    try {
      Chugimon nextChugi = this.storage.next(currChugimon);
      this.currChugimon = nextChugi;
    } catch (NoSuchElementException e) {
      System.out.println("Cannot move next");
    }
  }

  /**
   * Moves the iteration to the previous Chugimon
   */
  public void previousChugimon() {
    try {
      Chugimon prevChugi = this.storage.previous(currChugimon);
      this.currChugimon = prevChugi;
    } catch (NoSuchElementException e) {
      System.out.println("Cannot move previous");
    }
  }

}
