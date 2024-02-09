// --== CS400 Project One File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the an implementation of CS400 HashtableMap implementation
 * 
 * @author Ethan Yikai Yan
 */
public class HashtableMapTests {
    /**
     * This method test and make use of the constructor.
     * It also tests the getCapacity, getSize methods with valid inputs and put and get methods
     * with invalid inputs
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test1() {
        try {
            // Initialize HashtableMap of capacity X and check getCapacity returns correct number
            HashtableMap tester = new HashtableMap<>(5);
            if (tester.getCapacity() != 5) {
                return false;
            }
            if (tester.getSize() != 0) {
                return false;
            }
            HashtableMap tester2 = new HashtableMap<>();
            if (tester.getSize() != 0) {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        try {
            HashtableMap tester = new HashtableMap<>(5);
            // Perform put for invalid input (null key)
            tester.put(null, 5);
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            HashtableMap tester = new HashtableMap<>(5);
            // Perform put for invalid input (duplicate keys)
            tester.put("cat", 5);
            tester.put("cat", 8);
        }
        catch (IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            HashtableMap tester = new HashtableMap<>(5);
            // Perform put, containsKey, get, getSize on tester (capacity 5) for null inputs
            tester.get("Cat");
        }
        catch (NoSuchElementException e) {}
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        
        return true;
    }

    /**
     * This method tests the put, getCapacity, getSize, clear with valid inputs
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test2() {
        try {
            HashtableMap tester = new HashtableMap<>(5);
            tester.put("cat", 5);
            tester.put("dog", 8);
            tester.put("elephant", 8);
            if (tester.getCapacity() != 5) {
                return false;
            }
            if (tester.getSize() != 3) {
                return false;
            }
            if (!tester.containsKey("cat")) {
                return false;
            }
            if (tester.containsKey("eagle")) {
                return false;
            }
            // CHeck if get returns correct output
            if (!tester.get("cat").equals(5)) {
                return false;
            }
            // Check size has not been altered
            if (tester.getSize() != 3) {
                return false;
            }

            tester.clear();
            if (tester.containsKey("cat")) {
                return false;
            }
            if (tester.getSize() != 0) {
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
     * This method tests the functionality of the HashtableMap when there is a collision
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test3() {
        try {
            HashtableMap tester = new HashtableMap<>(5);
            tester.put(1, "one");
            tester.put(11, "eleven");
            tester.put(111, "hundred eleven");
            if (tester.getSize() != 3) {
                return false;
            }
            if (!tester.get(1).equals("one")) {
                return false;
            }
            if (!tester.get(11).equals("eleven")) {
                return false;
            }
            if (!tester.get(111).equals("hundred eleven")) {
                return false;
            }
            if (tester.getCapacity() != 5) {
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
     * This method tests the functionality of the HashtableMap when there is a need for rehashing,
     * ensuring that getSize and getCapacity returns appropriate values as well
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test4() {
        try {
            HashtableMap tester = new HashtableMap<>(5);
            tester.put(3, "three");
            tester.put(13, "thirteen");
            tester.put(113, "hundred thirteen");
            if (tester.getSize() != 3) {
                return false;
            }
            if (tester.getCapacity() != 5) {
                return false;
            }
            tester.put(23, "twenty three");
            if (tester.getSize() != 4) {
                return false;
            }
            if (tester.getCapacity() != 10) {
                return false;
            }
            tester.put(24, "twenty four");
            tester.put(25, "twenty five");
            tester.put(26, "twenty six");
            if (tester.getSize() != 7) {
                return false;
            }
            if (tester.getCapacity() != 20) {
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
     * This method tests the functionality of the HashtableMap when a HashNode is removed, and that
     * a new Node is appropriately added to the map array at the same index. It also checks that 
     * getSize returns the appropriate value
     * @returns true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean test5() {
        try {
            HashtableMap tester = new HashtableMap<>(5);
            HashNode[] mapTest = tester.map;
            tester.put(3, "three");
            tester.put(13, "thirteen");
            tester.put(113, "hundred thirteen");
            if (tester.getSize() != 3) {
                return false;
            }
            if (tester.getCapacity() != 5) {
                return false;
            }

            if (!tester.remove(3).equals("three")) {
                return false;
            }
            if (tester.getSize() != 2) {
                return false;
            }
            if (tester.getCapacity() != 5) {
                return false;
            }
            if (tester.containsKey(3)) {
                return false;
            }

            tester.put(23, "twenty three");
            if (tester.getSize() != 3) {
                return false;
            }
            if (tester.getCapacity() != 5) {
                return false;
            }

            // Test rehash then remove all
            tester.put(33, "thirty three");
            tester.remove(13);
            tester.remove(113);
            tester.remove(23);
            tester.remove(33);
            if (tester.getSize() != 0) {
                return false;
            }
            if (tester.getCapacity() != 10) {
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
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }
}
