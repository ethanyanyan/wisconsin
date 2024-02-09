import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ItemReader implements ItemReaderInterface {

	@Override
	public List<Item> readItemsFromFile(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<Item> items = new ArrayList<>();
		Scanner input =new Scanner(new File(filename));
		while(input.hasNextLine()) {
			//for each line in the file being read:
			String line =input.nextLine();
			//split the line in to parts base on ','
			String [] part = line.split(",");
			//data field- skip index 4,7,8 for part
			int index= Integer.parseInt(part[0]);
			String product = part[1];
			String category = part[2];
			String brand = part[4];
			double salePrice = Double.parseDouble(part[5]);
			double rating = Double.parseDouble(part[8]);
			String description = part[9];
			
			//public ItemInterface(int index, String product, String cateory, String brand,double salePrice, double rating, String description);
			items.add(new Item(index, product, category, rating, description, salePrice, brand));
			
		}
		//close scanner and return items;
		input.close();
		return items;
	}
	
}
