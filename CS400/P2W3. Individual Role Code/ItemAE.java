/**
 * This class represents a placeholder class of the Item class to ensure tests can be done on the 
 * AE's other classes
 */
public class ItemAE implements ItemInterface, Comparable<ItemAE> {
    public double rating;
    public String name;
    public ItemAE(int index, String product, String category, double rating, String description, double salePrice, String brand) {
        this.name = product;
        this.rating = rating;
    }
    public int getIndex() {
        return 0;
    } 
    public String getName() {
        return this.name;
    }
    public String getCategory() {
        return "";
    }
    public double getRating() {
        return this.rating;
    }
    public String getDescription() {
        return "";
    }
    public double getSalePrice() {
        return 0;
    }
    public String getBrand() {
        return "";
    }

    @Override
    public int compareTo(ItemAE other) {
        return getName().compareTo(other.getName());
    }

}
