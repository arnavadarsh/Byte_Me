package Byte_Me;

import java.io.*;
import java.util.*;

public class FileManager {
    private static final String user_file = "users.txt";
    private static final String order_file = "orders_user_";

    public void saveUsers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(user_file))) {
            for (Customer c : customers) {
                writeCustomerToFile(c, writer);
            }
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    private void writeCustomerToFile(Customer c, BufferedWriter w) throws IOException {
        String customerData = String.format("%s,%s,%b", c.getEmail(), c.getPassword(), c.isVIP());
        w.write(customerData);
        w.newLine();
    }


    public List<Customer> loadUsers(Admin admin) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(user_file))) {
            String l;
            while ((l = reader.readLine()) != null) {
                processLine(l, customers, admin);
            }
            System.out.println("Users loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return customers;
    }

    private void processLine(String l, List<Customer> c, Admin admin) {
            String[] parts = l.split(",");
            if (parts.length == 3) {
                Customer customer = createCustomer(parts, admin);
                c.add(customer);
            }
    }

    private Customer createCustomer(String[] p, Admin admin) {
        String email = p[0];
        String password = p[1];
        boolean isVIP = Boolean.parseBoolean(p[2]);
        return new Customer(email, password, isVIP, admin);
    }


    public void saveOrderHistory(int id, List<Order> order) {
        System.out.println("Saving order history for user " + id);
        if (order.isEmpty()) {
            System.out.println("No orders to save for user " + id);
            return;
        }

        String fileName = order_file + id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order o : order) {
                saveOrder(writer, o);
            }
            System.out.println("Order history saved for user " + id);
        } catch (IOException e) {
            System.err.println("Error saving order history: " + e.getMessage());
        }
    }

    private void saveOrder(BufferedWriter w, Order o) throws IOException {
        String orderData = String.format("%d,%b,%s", o.getOrderId(), o.isVIP(), o.getStatus());
        w.write(orderData);
        for (FoodItem item : o.getOrderItems()) {
            writeOrderItem(w, item);
        }
        w.newLine();
    }

    private void writeOrderItem(BufferedWriter w, FoodItem i) throws IOException {
        String itemData = String.format(",%s-%f-%d", i.getName(), i.getPrice(), i.getQuantity());
        w.write(itemData);
    }


    public List<Order> loadOrderHistory(int id) {
        String fileName = order_file + id + ".txt";
        List<Order> orders = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No order history for user " + id);
            return orders;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = parseOrder(line);
                orders.add(order);
            }
            System.out.println("Order history loaded for user " + id);
        } catch (IOException e) {
            System.err.println("Error loading order history: " + e.getMessage());
        }
        return orders;
    }

    private Order parseOrder(String l) {
        String[] parts = l.split(",");
        int id = Integer.parseInt(parts[0]);
        boolean isVIP = Boolean.parseBoolean(parts[1]);
        String status = parts[2];
        List<FoodItem> item = parseFoodItems(parts);
        return new Order(id, item, isVIP, null, status);
    }

    private List<FoodItem> parseFoodItems(String[] parts) {
        List<FoodItem> item = new ArrayList<>();
        for (int j = 3; j < parts.length; j++) {
            FoodItem i = parseFoodItem(parts[j]);
            item.add(i);
        }
        return item;
    }

    private FoodItem parseFoodItem(String itemData) {
        String[] ip = itemData.split("-");
        String name = ip[0];
        double price = Double.parseDouble(ip[1]);
        int quantity = Integer.parseInt(ip[2]);
        return new FoodItem(0, name, price, "Unknown", quantity);
    }

}
