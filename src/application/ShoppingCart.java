/**
 * @author Carson Fujita
 * copyright Carson Fujita, 2025 
 */
package application;

import java.util.Scanner;

/**
 * A shopping cart that holds {@link application.Product products}.
 */
public class ShoppingCart {
	
	/**
	 * The contained <code>Products</code> in the cart. <br /> Normally I would use a const static to define the 
	 * array size, but assignment restricts static use from anything from main. If this wasn't the case I wouldn't hard-code
	 * the array size to 3.
	 */
	private Product[] cart = new Product[3];
	
	
	/**
	 * The amount of {@link application.Product products} in {@link application.ShoppingCart#cart cart} that aren't null values.
	 * @see application.ShoppingCart#cart
	 */
	private int amount = 3;
	
	/**
	 * Counts for the total quantity of everything in {@link application.ShoppingCart#cart} and not total {@link application.Product products}.
	 * @see application.ShoppingCart#cart
	 */
	private int itemsTotal = 3;

	/**
	 * Creates shopping cart with 3 pre-loaded items. 
	 * <ul>
	 * 	<li>Laptop (1001) costs: $800 quantity: 1</li>
	 * 	<li>Phone (1002) costs: $400 quantity: 3</li>
	 * 	<li>Headphones (1003) costs: $50 quantity: 2</li>
	 * </ul>
	 */
	public ShoppingCart() {
		
		//Item 1
		cart[0] = new Product(
				"Laptop", //name
				1001, //ID
				800.00,//cost
				1 //quantity
		);
		
		//Item 2
		cart[1] = new Product(
				"Phone",
				1002,
				400.00,
				3
		);

		//Item 3
		cart[2] = new Product(
				"Headphones",
				1003,
				50,
				2
		);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//instances
		ShoppingCart shop = new ShoppingCart();
		Scanner sc = new Scanner(System.in);
		
		//Welcome Message
		System.out.println("Welcome to the Online Shopping System!");
		
		//input variable
		int input = 0;
		
		//total cost of everything
		double total = 0;
		
		//menu loop
		do {
			System.out.print("\nPlease select an option:\n1. View Cart\n2. Add Product\n3. Remove Product\n4. Checkout\n\nEnter your choice: ");
			input = sc.nextInt();
			
			if(input == 2 || input == 3) { // 2 and 3 start the same then change; this isn't the ideal way to do it, but I'm limited here.
				Product searchResult;
				int attempts = 0; // current count of failed search attempts
				
				do {
					System.out.printf("Enter product ID to %s: ", input == 2 ? "add" : "remove");
					int id = sc.nextInt();
					searchResult = shop.find(id);
					if(searchResult==null) {
						System.out.println("ID not found\n");
						attempts++;
					} 
				} while(searchResult==null && attempts != 3); // max attempts 3
				
				if(attempts!=3) { // if user did not reach max attempts
					if(input == 2)  {// if adding
						shop.add(searchResult);
						
						System.out.println("Product Added!");
					} else { // we are removing
						shop.remove(searchResult);
						
						System.out.println("Product Removed!");
					}
				} else { //user failed to search within the limit
					System.out.println("\nToo many failed search attempts.\nCart IDs:");
					
					//display all ids so user knows the ids
					for(int products = 0; products < shop.getAmount(); products++) {
						Product thisProduct = shop.getAt(products);
						System.out.printf("%s, ID: %d\n",thisProduct.getProductName(),thisProduct.getProductId());
					}
				}
				
				
			} else if(input == 1 || input == 4) { // 1 and 4 do the same thing; 4 just exits after.
				//print cart
				System.out.println("\nYour cart:");
				
				//iterate over everything
				for(int products = 0; products < shop.getAmount(); products++) {
					Product thisProduct = shop.getAt(products);
					total+=thisProduct.calculateTotalPrice(); //total cost of everything
					System.out.printf("%s, Price: $%.2f, Quantity: %d, Total: $%.2f\n",thisProduct.getProductName(),thisProduct.getPrice(), thisProduct.getQuantity(), thisProduct.calculateTotalPrice());
				}
			}
			
		} while(input != 4);
		
		//print tax
		System.out.printf("\n\nTotal cost: $%.2f\nSales tax(10%%): $%.2f\nFinal total after tax: $%.2f\n\nThank you for shopping with us!",total, total*0.13, total*1.13); 
		
		System.exit(0); //shutdown
	}
	
	/**
	 * Adds the product to {@link application.ShoppingCart#cart cart} on the condition that the
	 * {@link application.ShoppingCart#itemsTotal total items} in <code>cart</code> is less than 5. 
	 * <br />
	 * It is important to note that if the {@link application.Product product} <b>already exists,</b>
	 * then the {@link application.Product#quantity quantity} of that <code>Product</code> will increase by 1.
	 * 
	 * <br />
	 * Assignment specified a limit of 5 items. This also would have been a static variable instead of hard-coded, but assignment 
	 * restricts static use to main only.
	 * 
	 * <br />
	 * Would also make it throw an exception if you try to add when the <code>itemsTotal</code> is over 5, but assignment rules dictate 
	 * I can't use anything beyond what has currently been covered in class. 
	 * @param product the product to add
	 * TODO Reject if IDs match
	 */
	public void add(Product product) {
		if(itemsTotal < 5) {
			if(contains(product)) {
				product.setQuantity(product.getQuantity()+1);
			} else if(product.getQuantity() + itemsTotal <= 5) { //adding a new product must not exceed the item restriction of 5
				
				//Create new array
				Product[] newCart = new Product[cart.length+1]; //since length counts from 
				
				//add pre-existing objects
				for(int cartIndex = 0; cartIndex < cart.length; cartIndex++) {
					newCart[cartIndex] = cart[cartIndex];
				}
				
				//add to this new cart. remember the length of cart is still 1 lower
				newCart[cart.length] = product;
				
				//new cart
				this.cart = newCart;
				
				this.amount++; //increase amount
				
				//new itemsTotal
				this.itemsTotal += product.getQuantity();
			}
		}
	}
	
	
	public void remove(Product product) {
		if(itemsTotal > 0) { // no point in removing if there is nothing to remove
			if(contains(product)) {
				product.setQuantity(product.getQuantity()-1); //reduce quantity
				this.itemsTotal--; //reduce total
				if(product.getQuantity() == 0) {
					
					//move product to end of array
					for(int arrayIndex = 0; arrayIndex < cart.length; arrayIndex++) {
						if(cart[arrayIndex].equals(product) && arrayIndex+1 < cart.length) {
							this.cart[arrayIndex] = cart[arrayIndex+1];
							this.cart[arrayIndex+1] = product;
						}
					}
					this.amount--;
					this.cart[cart.length-1] = null;
				}
			} 
		}
	}
	
	/**
	 * Searches {@link application.ShoppingCart#cart cart} for any {@link application.Product product} with the specified ID. 
	 * @param id the identification number to search for
	 * @return null if not found or the <code>Product</code> in the <code>cart</code> with the matching ID
	 */
	public Product find(int id) {
		for(int cartIndex = 0; cartIndex < amount; cartIndex++) {
			if(cart[cartIndex].getProductId() == id) {
				return cart[cartIndex];
			}
		}
		return null;
	}
	
	/**
	 * Checks {@link application.ShoppingCart#cart cart} for specified product
	 * @param product product to search for
	 * @return true if contained in {@link application.ShoppingCart#cart cart}; false if not.
	 */
	public boolean contains(Product product) {
		for(int cartIndex = 0; cartIndex < amount; cartIndex++) {
			if(cart[cartIndex].equals(product)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param index the index of {@link application.ShoppingCart#cart cart} to retrieve
	 * @return the {@link application.Product product} at the specified index; returns null if exceeding length. <br /> Would throw error if assignment allowed for it.
	 */
	public Product getAt(int index){
		if(index < cart.length) {
			return cart[index];
		}
		return null;
	}

	/**
	 * @return the itemsTotal
	 */
	public int getItemsTotal() {
		return itemsTotal;
	}

	/**
	 * @return the amount of unique {@link application.Product products} in {@link application.ShoppingCart#cart cart}
	 */
	public int getAmount() {
		return amount;
	}

}
