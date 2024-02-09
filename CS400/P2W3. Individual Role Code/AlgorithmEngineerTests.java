import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class represents the tests to be done for the AlgorithmEngineer's work, both the MinHeap and
 * RBT and the methods within these data structures
 */
public class AlgorithmEngineerTests {
    /**
     * JUnit test to try creating a RBT and test insert function
     */
    @Test
    public void test1() {
        try {
            RedBlackTree<Item> tester = new RedBlackTree<>();
            Item item1 = new Item(0, "Apple", null, 2.5, null, 0, null);
            Item item2 = new Item(0, "Banana", null, 2.5, null, 0, null);
            Item item3 = new Item(0, "Carrot", null, 2.5, null, 0, null);
            Item item4 = new Item(0, "Durian", null, 2.5, null, 0, null);
            Item item5 = new Item(0, "Egg", null, 2.5, null, 0, null);
            // Test rotation in RBT
            tester.insert(item1);
            tester.insert(item2);
            tester.insert(item3);
            assertEquals(3, tester.size);
            assertEquals("[ Banana(1), Apple(0), Carrot(0) ]", tester.toLevelOrderString());
            // Test recoloring then rotation in RBT
            tester.insert(item4);
            tester.insert(item5);
            assertEquals(5, tester.size);
            assertEquals("[ Banana(1), Apple(1), Durian(1), Carrot(0), Egg(0) ]", tester.toLevelOrderString());
            // Test more complex rotations and recoloring (require 2 rotations on insert of Hamburger)
            Item item6 = new Item(0, "Flower", null, 2.5, null, 0, null);
            Item item7 = new Item(0, "Grapes", null, 2.5, null, 0, null);
            Item item8 = new Item(0, "Hamburger", null, 2.5, null, 0, null);
            tester.insert(item6);
            tester.insert(item7);
            tester.insert(item8);
            assertEquals(8, tester.size);
            assertEquals("[ Durian(1), Banana(0), Flower(0), Apple(1), Carrot(1), Egg(1), Grapes(1), Hamburger(0) ]", tester.toLevelOrderString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit test to try creating a RBT and test remove function
     */
    @Test
    public void test2() {
        try {
            RedBlackTree<Item> tester = new RedBlackTree<>();
            Item item1 = new Item(0, "Apple", null, 2.5, null, 0, null);
            Item item2 = new Item(0, "Banana", null, 2.5, null, 0, null);
            Item item3 = new Item(0, "Carrot", null, 2.5, null, 0, null);
            Item item4 = new Item(0, "Durian", null, 2.5, null, 0, null);
            Item item5 = new Item(0, "Egg", null, 2.5, null, 0, null);
            Item item6 = new Item(0, "Flower", null, 2.5, null, 0, null);
            Item item7 = new Item(0, "Grapes", null, 2.5, null, 0, null);
            Item item8 = new Item(0, "Hamburger", null, 2.5, null, 0, null);
            tester.insert(item1);
            tester.insert(item2);
            tester.insert(item3);
            tester.insert(item4);
            tester.insert(item5);
            tester.insert(item6);
            tester.insert(item7);
            tester.insert(item8);
            // Test remove leaf red node (Hamburger)
            tester.remove(item8);
            assertEquals("[ Durian(1), Banana(0), Flower(0), Apple(1), Carrot(1), Egg(1), Grapes(1) ]", tester.toLevelOrderString());

            // Test remove leaf black node (black node with black child)
            tester.remove(item7);
            assertEquals("[ Durian(1), Banana(0), Flower(1), Apple(1), Carrot(1), Egg(0) ]", tester.toLevelOrderString());

            // Test remove black node with one red child and one null
            tester.insert(item7);
            tester.insert(item8);
            tester.remove(item7);
            assertEquals("[ Durian(1), Banana(0), Flower(0), Apple(1), Carrot(1), Egg(1), Hamburger(1) ]", tester.toLevelOrderString());

           // Test remove root node
           tester.insert(item7);
           tester.remove(tester.root.getItem());
           assertEquals("[ Egg(1), Banana(0), Grapes(0), Apple(1), Carrot(1), Flower(1), Hamburger(1) ]", tester.toLevelOrderString());
           
           // Test remove node that is not in RBT
           tester.remove(item4);
        }
        catch(IllegalArgumentException e) {}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit test to try creating a MinHeap and test insert function
     */
    @Test
    public void test3() {
        try {
            MinHeap tester = new MinHeap();
            Item item1 = new Item(0, "Apple", null, 0.5, null, 0, null);
            Item item2 = new Item(0, "Banana", null, 1.0, null, 0, null);
            Item item3 = new Item(0, "Carrot", null, 1.5, null, 0, null);
            Item item4 = new Item(0, "Durian", null, 2.0, null, 0, null);
            Item item5 = new Item(0, "Egg", null, 2.5, null, 0, null);
            Item item6 = new Item(0, "Flower", null, 3.0, null, 0, null);
            Item item7 = new Item(0, "Grapes", null, 3.5, null, 0, null);
            Item item8 = new Item(0, "Hamburger", null, 4.0, null, 0, null);
            // Check that it is empty
            assertEquals(true, tester.isEmpty());
            assertEquals(0, tester.heapSize);
            tester.insert(item1);
            tester.insert(item3);
            tester.insert(item2);
            tester.insert(item4);
            assertEquals(false, tester.isEmpty());
            assertEquals(4, tester.heapSize);
            tester.insert(item5);
            tester.insert(item8);
            tester.insert(item7);
            tester.insert(item6);
            assertEquals(false, tester.isEmpty());
            assertEquals(8, tester.heapSize);
            assertEquals("[Apple, Carrot, Banana, Durian, Egg, Hamburger, Grapes, Flower]", tester.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit test to try creating a MinHeap and test getMin function
     */
    @Test
    public void test4() {
        try {
            MinHeap tester = new MinHeap();
            Item item1 = new Item(0, "Apple", null, 0.5, null, 0, null);
            Item item2 = new Item(0, "Banana", null, 1.0, null, 0, null);
            Item item3 = new Item(0, "Carrot", null, 1.5, null, 0, null);
            Item item4 = new Item(0, "Durian", null, 2.0, null, 0, null);
            Item item5 = new Item(0, "Egg", null, 2.5, null, 0, null);
            Item item6 = new Item(0, "Flower", null, 3.0, null, 0, null);
            Item item7 = new Item(0, "Grapes", null, 3.5, null, 0, null);
            Item item8 = new Item(0, "Hamburger", null, 4.0, null, 0, null);
            tester.insert(item1);
            tester.insert(item3);
            tester.insert(item2);
            tester.insert(item4);
            tester.insert(item5);
            tester.insert(item8);
            tester.insert(item7);
            tester.insert(item6);
            assertEquals(8, tester.heapSize);
            assertEquals("[Apple, Carrot, Banana, Durian, Egg, Hamburger, Grapes, Flower]", tester.toString());
            // Remove first item and heapify
            assertEquals(item1, tester.getMin());
            assertEquals(7, tester.heapSize);
            assertEquals("[Banana, Carrot, Flower, Durian, Egg, Hamburger, Grapes]", tester.toString());
            // Remove second item and heapify
            assertEquals(item2, tester.getMin());
            assertEquals(6, tester.heapSize);
            assertEquals("[Carrot, Durian, Flower, Grapes, Egg, Hamburger]", tester.toString());
            // Remove third item and heapify
            assertEquals(item3, tester.getMin());
            assertEquals(5, tester.heapSize);
            assertEquals("[Durian, Egg, Flower, Grapes, Hamburger]", tester.toString());
            // Remove fourth item and heapify
            assertEquals(item4, tester.getMin());
            assertEquals(4, tester.heapSize);
            assertEquals("[Egg, Grapes, Flower, Hamburger]", tester.toString());
            // Remove fifth item and heapify
            assertEquals(item5, tester.getMin());
            assertEquals(3, tester.heapSize);
            assertEquals("[Flower, Grapes, Hamburger]", tester.toString());
            // Remove sixth item and heapify
            assertEquals(item6, tester.getMin());
            assertEquals(2, tester.heapSize);
            assertEquals("[Grapes, Hamburger]", tester.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit test to test other miscellanous methods 
     * RBT: isEmpty, removeAll, contains
     * MinHeap: isEmpty, removeAll
     */
    @Test
    public void test5() {
        try {
            RedBlackTree<Item> tester = new RedBlackTree<>();
            MinHeap tester2 = new MinHeap();
            assertEquals(true, tester.isEmpty());
            Item item1 = new Item(0, "Apple", null, 0.5, null, 0, null);
            Item item2 = new Item(0, "Banana", null, 1.0, null, 0, null);
            Item item3 = new Item(0, "Carrot", null, 1.5, null, 0, null);
            Item item4 = new Item(0, "Durian", null, 2.0, null, 0, null);
            Item item5 = new Item(0, "Egg", null, 2.5, null, 0, null);
            // Test misc methods in RBT
            tester.insert(item1);
            tester.insert(item2);
            tester.insert(item3);
            tester.insert(item4);
            assertEquals(false, tester.isEmpty());
            assertEquals(false, tester.contains(item5));
            assertEquals(true, tester.contains(item3));
            tester.removeAll();
            assertEquals(true, tester.isEmpty());
            assertEquals(false, tester.contains(item3));
            assertEquals(false, tester.contains(item5));

            // Test misc methods in MinHeap
            assertEquals(true, tester2.isEmpty());
            tester2.insert(item1);
            tester2.insert(item2);
            tester2.insert(item3);
            assertEquals(false, tester2.isEmpty());
            tester2.removeAll();
            assertEquals(true, tester2.isEmpty());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Code review of DataWrangler role, testing the ItemReader class, and demonstrating bug found. 
     * When parsing csv file, method does not take into account inconsistencies, ie, when certain
     * data is missing. This test tests on index 56 which is missing a rating field in original csv.
     */
    @Test
    public void CodeReviewOfDataWrangler1() {
        ItemReader reader1 = new ItemReader();
        try {
            List<Item> list = reader1.readItemsFromFile("test1.csv");
            assertEquals(null, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("", e);
        }
    }

    /**
     * Code review of DataWrangler role, testing the ItemReader class, and demonstrating bug found 
     * when trying to parse fields with commas in it, it does not take into account of this and
     * results in the subsequent part to be pushed to the next field
     * ie. Category: Kitchen, Garden & Pets and Brand: Mastercook, would result in Category: Kitchen
     * Brand: Garden & Pets and salePrice: Mastercook which is not possible since salePrice is a
     * double hence resulting in an error
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        ItemReader reader1 = new ItemReader();
        try {
            reader1.readItemsFromFile("test2.csv");
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("", e);
        }
    }

    /**
     * Integration test of BD and AE methods. IntegrationTest1 is testing to ensure that the
     * findLowestItems method is working appropriately, by using the method provided by the BD,
     * and integrating with the MinHeap of AE, which I had developed
     */
    @Test
    public void IntegrationTest1() {
        ItemReader reader1 = new ItemReader();
        RedBlackTree<Item> rbt = new RedBlackTree<>();
        MinHeap minHeap = new MinHeap();
        ItemSearchBackendBD backend = new ItemSearchBackendBD(reader1, rbt, minHeap);
        try {
            backend.loadData("test3.csv");
            List<Item> lowest3 = backend.findLowestItems(3);
            assertEquals(3, lowest3.size());
            assertEquals("Mango 2.8", lowest3.get(0).getName() + " " + lowest3.get(0).getRating());
            assertEquals("Apple 3.0", lowest3.get(1).getName() + " " + lowest3.get(1).getRating());
            assertEquals("Orange 4.0", lowest3.get(2).getName() + " " + lowest3.get(2).getRating());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Integration test of BD and AE methods. IntegrationTest2 is testing to ensure that the
     * findItemsByProductName method is working appropriately, by using the method provided by the BD,
     * and integrating with the RBT of the AE, which I had developed.
     */
    @Test
    public void IntegrationTest2() {
        ItemReader reader1 = new ItemReader();
        RedBlackTree<Item> rbt = new RedBlackTree<>();
        MinHeap minHeap = new MinHeap();
        ItemSearchBackendBD backend = new ItemSearchBackendBD(reader1, rbt, minHeap);
        try {
            backend.loadData("test3.csv");
            Item item1 = new Item(1,"Apple","Foods", 3.0, "This is a tasty red apple", 3.0, "Dole");
            Item item2 = new Item(4,"Pineapple","Foods", 4.2, "This is a sweet pineapple", 2.0, "Del Monte");
            Item item3 = new Item(1,"Watermelon","Foods", 4.6, "This is a juicy and refreshing watermelon", 3.0, "Sweet Slice");
            assertEquals("Name: Apple Category: Foods Price: 1.0 Rating: 3.0 Description:  This is a tasty red apple",backend.findItemsByProductName("Apple"));
            assertEquals("Name: Pineapple Category: Foods Price: 2.0 Rating: 4.2 Description:  This is a sweet pineapple",backend.findItemsByProductName("Pineapple"));
            assertEquals("Name: Watermelon Category: Foods Price: 3.0 Rating: 4.6 Description:  This is a juicy and refreshing watermelon",backend.findItemsByProductName("Watermelon"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ItemReader reader1 = new ItemReader();
        RedBlackTree<Item> rbt = new RedBlackTree<>();
        MinHeap minHeap = new MinHeap();
        ItemSearchBackendBD backend = new ItemSearchBackendBD(reader1, rbt, minHeap);
        try {
            backend.loadData("test3.csv");
            Item item1 = new Item(1,"Apple","Foods", 3.0, "This is a tasty red apple", 3.0, "Dole");
            Item item2 = new Item(4,"Pineapple","Foods", 4.2, "This is a sweet pineapple", 2.0, "Del Monte");
            Item item3 = new Item(1,"Watermelon","Foods", 4.6, "This is a juicy and refreshing watermelon", 3.0, "Sweet Slice");
            
            System.out.println(backend.findItemsByProductName("Apple"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
