package Byte_Me;

import java.util.*;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    public static List<FoodItem> menuItems = new ArrayList<>();
    private static boolean isInitialized = false;

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;

        if (!isInitialized) {
            initializeMenu();
            isInitialized = true;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isIsInitialized() {
        return isInitialized;
    }

    public static void setIsInitialized(boolean isInitialized) {
        User.isInitialized = isInitialized;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private void initializeMenu() {
        menuItems.add(new FoodItem(1, "Maggi", 10.0, "Snacks", 5));
        menuItems.add(new FoodItem(2, "Chips", 12.0, "Snacks", 5));
        menuItems.add(new FoodItem(3, "Coke", 11.0, "Beverages", 100));
        menuItems.add(new FoodItem(3, "Pepsi", 11.0, "Beverages", 100));
    }

    public abstract boolean login();
    public abstract void viewOrderHistory();
    public abstract void logout();
}
