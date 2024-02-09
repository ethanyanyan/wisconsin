public class Item implements ItemInterface, Comparable<Item> {

	 // 	public ItemInterface(int index, String product, String cateory, String brand,double salePrice, double rating, String description);
	private int index;
	private String product;
	private String category ;
	private String brand;
	private double salePrice ;

	private double rating;
	
	private String description ;
	
	
	public Item(int index, String product, String category, double rating, String description, double salePrice, String brand) {
		this.index=index;
		this.product=product;
		this.category=category;
		this.rating=rating;
		this.description=description;
		this.salePrice=salePrice;
		this.brand=brand;
	}
/**
 * accessor return this item's index;
 *@return item's index
 */
	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return this.index;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.product;
	}
	/**
	 * accessor return this item's category;
	 *@return item's category
	 */
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}
	/**
	 * accessor return this item's Rating;
	 *@return item's Rating
	 */
	@Override
	public double getRating() {
		// TODO Auto-generated method stub
		return this.rating;
	}
	/**
	 * accessor return this item's Description;
	 *@return item's Description
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}
	/**
	 * accessor return this item's SalePrice;
	 *@return item's SalePrice
	 */
	@Override
	public double getSalePrice() {
		// TODO Auto-generated method stub
		return this.salePrice;
	}
	/**
	 * accessor return this item's Brand;
	 *@return item's Brand
	 */
	@Override
	public String getBrand() {
		// TODO Auto-generated method stub
		return this.brand;
	}

	@Override
    public int compareTo(Item other) {
        return getName().compareTo(other.getName());
    }
}
