package Byte_Me;

import javax.swing.*;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Admin admin = new Admin();
    private static OrderQueue orderQueue = new OrderQueue();
//    private static Customer customer1 = new Customer("student1@byteme.com", "123", true, admin);
//    private static Customer customer2 = new Customer("student2@byteme.com", "123", false, admin);

    public static void main(String[] args) {
        System.out.println("Welcome to Byte Me!");


        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Launch CLI");
            System.out.println("2. Launch GUI");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CLI();
                    break;
                case 2:
                    GUI();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void registerCustomer(String email, String password, boolean isVIP, Admin admin, List<Customer> customers, FileManager fileManager) {
        Customer newCustomer = new Customer(email, password, isVIP, admin);
        customers.add(newCustomer);
        fileManager.saveUsers(customers);
        System.out.println("Registration successful for: " + email);
    }

    public static void GUI() {
        System.out.println("Launching GUI...");
        SwingUtilities.invokeLater(() -> new GUIAppSwing(admin, orderQueue).show());
    }

    public static void CLI() {
        while (true) {
            System.out.print("Are you an Admin or Customer? (Type 'exit' to quit): ");
            String role = scanner.nextLine().toLowerCase();
            FileManager fileManager = new FileManager();
            Admin admin = new Admin();
            List<Customer> customers = fileManager.loadUsers(admin);

            if (role.equals("exit")) {
                System.out.println("Exiting CLI. Goodbye!");
                break;
            }

            if (role.equals("admin")) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    if (admin.login()) {
                        System.out.println("Welcome Admin!");
                        admin.adminMenu();
                        loggedIn = true;
                    } else {
                        System.out.println("Invalid email or password for Admin. Please try again.");
                    }
                }
            } else if (role.equals("customer")) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    Customer currentCustomer = null;
                    for (Customer customer : customers) {
                        if (customer.authenticate(email, password)) {
                            currentCustomer = customer;
                            break;
                        }
                    }

                    if (currentCustomer == null) {
                        System.out.println("Invalid email or password. Would you like to register? (yes/no)");
                        String response = scanner.nextLine();
                        if (response.equalsIgnoreCase("yes")) {
                            System.out.print("Enter email: ");
                            String newEmail = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String newPassword = scanner.nextLine();
                            Customer newCustomer = new Customer(newEmail, newPassword, false, admin);
                            fileManager.saveUsers(customers);
                            System.out.println("Registration successful. Please log in.");
                        }
                    } else {
                        currentCustomer.customerMenu();
                        loggedIn = true;
                    }
                }
            } else {
                System.out.println("Invalid role. Please enter 'Admin' or 'Customer'.");
            }

        }
    }
}
