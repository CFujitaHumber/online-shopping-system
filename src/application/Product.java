/**
 * @author Carson Fujita
 */
package application;

/**
 * The <code>Product</code> represents a product at a store
 */
public class Product {

	/**
	 * The name of the <code>Product</code>
	 */
	private String productName; 

	/**
	 * The Unique Identifier for the <code>Product</code>
	 */
	private int productId;
	
	/**
	 * The dollar price in <code>double</code> precision. 
	 */
	private double price;
	
	/**
	 * The number of available <code>Product</code>s
	 */
	private int quantity;

	/**
	 * Empty <code>Product</code>
	 */
	public Product() {}
	
	/**
	 * Initializes a new <code>Product</code> Object.
	 * @param productName name of <code>Product</code>
	 * @param productId unique identifier of <code>Product</code>
	 * @param price the price of <code> Product </code>
	 * @param quantity the number of available <code>Product</code>s
	 */
	public Product(String productName, int productId, double price, int quantity) {
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * @return The gross price of the <code>Product</code>
	 */
	public double calculateTotalPrice() {
		return price * quantity;
	}
	

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
