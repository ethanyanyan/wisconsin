
import java.io.FileNotFoundException;
import org.junit.*;
import static org.junit.Assert.assertEquals;
public class DataWranglerTests {
	public static void main(String[] args) {
		ItemReader file1 = new ItemReader();
		try {
			System.out.println(file1.readItemsFromFile("BigBasket Product.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * test 1 check functionality of Item class
 */
	@Test
public void test1() {
 Item item1 = new Item(1,"water","Drinks",1.00,"best water on earth",5,"Agua");
 assertEquals("water",item1.getName());
 assertEquals("Drinks",item1.getCategory());
 assertEquals(1,item1.getIndex());
 assertEquals("Agua",item1.getBrand());
 assertEquals(1.00,item1.getSalePrice());
 assertEquals(5,item1.getRating());
 assertEquals("best water on earth",item1.getDescription());
}
/**
 * test 2 check functionality of ItemReader class
 */
@Test
public void test2() {
	ItemReader file1 = new ItemReader();
	try {
		assertEquals("Water Bottle - Orange",file1.readItemsFromFile("BigBasket Product.csv").get(1).getName());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		assertEquals("", e);
	}
}
/**
 * test 3 check when reading file doesnt not exist
 */
@Test
public void test3() {
	ItemReader file1 = new ItemReader();
	try {
		assertEquals("",file1.readItemsFromFile("").get(1).getName());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * test 4 check 
 */
@Test
public void test4() {
	
}
@Test
public void test5() {
	

	}
}
