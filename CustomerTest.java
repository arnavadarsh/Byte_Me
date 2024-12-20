package Byte_Me;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartOperationsTest {

    @Test
    void testAddToCart() {
        Admin admin = new Admin();
        Customer customer = new Customer("student1@byteme.com", "123", false, admin);
        FoodItem foodItem = new FoodItem(1, "Maggi", 10.0, "Snacks", 5);
        customer.addToCart(foodItem, 2);
        double expectedTotal = foodItem.getPrice() * 2;
        double cartTotal = customer.getCart().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        assertEquals(expectedTotal, cartTotal, "Cart total should be updated accurately after adding items.");
    }

    @Test
    void testModifyCartQuantity() {
        Admin admin = new Admin();
        Customer customer = new Customer("student1@byteme.com", "123", false, admin);
        FoodItem foodItem = new FoodItem(1, "Maggi", 10.0, "Snacks", 5);
        customer.addToCart(foodItem, 2);
        customer.modifyCartQuantity("Maggi", 3);
        double expectedTotal = foodItem.getPrice() * 3;
        double cartTotal = customer.getCart().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        assertEquals(expectedTotal, cartTotal, "Cart total should be recalculated correctly after modifying item quantity.");
    }

    @Test
    void testNegativeQuantity() {
        Admin admin = new Admin();
        Customer customer = new Customer("student1@byteme.com", "123", false, admin);
        FoodItem foodItem = new FoodItem(1, "Maggi", 10.0, "Snacks", 5);
        customer.addToCart(foodItem, 2);
        assertThrows(IllegalArgumentException.class, () -> customer.modifyCartQuantity("Maggi",-1  ),
                "A negative quantity should trigger an exception.");
        int quantity = customer.getCart().stream()
                .filter(item -> item.getName().equalsIgnoreCase("Maggi"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Item not found in cart."))
                .getQuantity();
        assertTrue(quantity >= 0, "Cart item quantity must be non-negative.");
    }

    @Test
    void testRemoveFromCart() {
        Admin admin = new Admin();
        Customer customer = new Customer("student1@byteme.com", "123", false, admin);
        FoodItem foodItem = new FoodItem(1, "Maggi", 10.0, "Snacks", 5);
        customer.addToCart(foodItem, 2);
        customer.removeFromCart("Maggi");

        assertTrue(customer.getCart().isEmpty(), "The cart should be empty after the item is removed.");
    }

    @Test
    void testViewCartTotal() {
        Admin admin = new Admin();
        Customer customer = new Customer("student1@byteme.com", "123", false, admin);

        FoodItem foodItem1 = new FoodItem(1, "Maggi", 10.0, "Snacks", 5);
        FoodItem foodItem2 = new FoodItem(2, "Coke", 5.0, "Beverages", 3);
        customer.addToCart(foodItem1, 2);
        customer.addToCart(foodItem2, 3);
        double expectedTotal = (foodItem1.getPrice() * 2) + (foodItem2.getPrice() * 3);
        double cartTotal = customer.getCart().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        assertEquals(expectedTotal, cartTotal, "The cart total should be calculated accurately.");
    }
}


