package Byte_Me;

import java.util.*;


public class Customer extends User {
    private List<FoodItem> cart = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();
    private boolean isVIP;
    private Admin admin;
    private static final String vip_e = "vip@byteme.com";
    private static final String vip_p = "123";
    private static final String reg_e = "regular@byteme.com";
    private static final String reg_p = "123";

    public Customer(String email, String password, boolean isVIP, Admin admin) {
        super(2, "Customer", email, password);
        this.isVIP = isVIP;
        this.admin = admin;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }
    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }



    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public boolean isHavingVIP(Customer customer) {
        return customer.isVIP==true;

    }

    @Override
    public boolean login() {
        if (isVIP) {
            return this.email.equals(vip_e) && this.password.equals(vip_p);
        } else {
            return this.email.equals(reg_e) && this.password.equals(reg_p);
        }
    }

    public void customerMenu() {
        System.out.println(isVIP ? "Welcome VIP Customer!" : "Welcome Regular Customer!");
        System.out.println("1.Browse Menu");
        System.out.println("2.Cart Operations");
        System.out.println("3.Order Tracking");
        System.out.println("4.Upgrade to VIP");
        System.out.println("5.Back");
        Scanner scanner = new Scanner(System.in);
        int customerOption = scanner.nextInt();

        switch (customerOption) {
            case 1:
                System.out.println("1.View all items.");
                System.out.println("2.Search functionality.");
                System.out.println("3.Filter by category.");
                System.out.println("4.Sort by price.");
                System.out.println("5.Back to main menu");
                int customerMenuOption = scanner.nextInt();
                switch (customerMenuOption) {
                    case 1:
                        admin.viewAllItems();
                        customerMenu();
                        break;
                    case 2:
                        System.out.println("Enter to search.");
                        String searchItem = scanner.next();
                        admin.searchMenuItems(searchItem);
                        customerMenu();
                        break;
                    case 3:
                        System.out.println("Enter the category to search.");
                        String searchCategory = scanner.next();
                        admin.filterByCategory(searchCategory);
                        customerMenu();
                        break;
                    case 4:
                        System.out.println("Sorted Menu");
                        admin.sortByPrice();
                        customerMenu();
                        break;
                    case 5:
                        customerMenu();
                    default:
                        System.out.println("Wrong choice.");
                        customerMenu();

                }
                break;
            case 2:
                System.out.println("1.Add items");
                System.out.println("2.Modify quantity");
                System.out.println("3.Remove items");
                System.out.println("4.View total");
                System.out.println("5.Checkout process");
                System.out.println("6.Back to main menu");
                int customerCartOperations = scanner.nextInt();
                switch (customerCartOperations) {
                    case 1:
                        System.out.println("Enter the item to be added to the cart.");
                        String itemToBeAdded = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter the quantity to be added to the cart.");
                        int quantityToBeAdded = scanner.nextInt();

                        FoodItem foundItem = null;
                        for (FoodItem item : admin.getMenuItems()) {
                            if (item.getName().equalsIgnoreCase(itemToBeAdded)) {
                                foundItem = item;
                                break;
                            }
                        }

                        if (foundItem != null) {
                            addToCart(foundItem, quantityToBeAdded);
                        } else {
                            System.out.println("Item not found in the menu.");
                        }
                        customerMenu();
                        break;
                    case 2:
                        System.out.println("Enter the item to modify in the cart.");
                        String itemToModify = scanner.next();
                        System.out.println("Enter the new quantity.");
                        int newQuantity = scanner.nextInt();
                        modifyCartQuantity(itemToModify, newQuantity);
                        customerMenu();
                        break;
                    case 3:
                        System.out.println("Enter the item to be removed from the cart.");
                        String itemRemoved = scanner.next();
                        removeFromCart(itemRemoved);
                        customerMenu();
                        break;
                    case 4:
                        viewCartTotal();
                        customerMenu();
                        break;
                    case 5:
                        checkout();
                        customerMenu();
                        break;
                    case 6:
                        customerMenu();
                        break;
                    default:
                        System.out.println("Wrong choice.");
                }
                break;
            case 3:
                System.out.println("1.View order status");
                System.out.println("2.Cancel order");
                System.out.println("3.Order history");
                System.out.println("4.Back to main menu");
                int customerOrderTracking= scanner.nextInt();
                switch (customerOrderTracking) {
                    case 1:
                        System.out.println("Enter the order id");
                        int orderId = scanner.nextInt();
                        viewOrderStatus(orderId);
                        customerMenu();
                        break;
                    case 2:
                        System.out.println("Enter the order id");
                        int cancelId = scanner.nextInt();
                        cancelOrder(cancelId);
                        customerMenu();
                        break;
                    case 3:
                        viewOrderHistory();
                        customerMenu();
                        break;
                    case 4:
                        customerMenu();
                        break;
                    default:
                        System.out.println("Wrong choice.");
                }
                break;
            case 4:
                boolean check = isHavingVIP(this);

                if (check) {
                    System.out.println("Already a VIP customer.");
                } else {
                    System.out.println("Pay $20/month for VIP membership");
                    System.out.print("Are you sure you want to buy? (yes/no): ");
                    String answer = scanner.next();

                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("Payment Successful");
                        System.out.println("Welcome to ByteMe+");
                        this.setVIP(true);
                    } else {
                        System.out.println("VIP membership purchase canceled.");
                    }
                }

                customerMenu();
                break;


            case 5:
                Main.main(null);
            default:
                System.out.println("Wrong Choice");
        }
    }

    public void addToCart(FoodItem item, int quantity) {
        item.setQuantity(quantity);
        cart.add(item);
        System.out.println("Added to cart: " + item.getName());
    }
    public void modifyCartQuantity(String itemName, int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        for (FoodItem item : cart) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setQuantity(newQuantity);
                System.out.println("Updated quantity of " + itemName + " to " + newQuantity);
                return;
            }
        }
        System.out.println("Item not found in the cart.");
    }
    public void removeFromCart(String itemName) {
        boolean itemRemoved = false;
        Iterator<FoodItem> iterator = cart.iterator();

        while (iterator.hasNext()) {
            FoodItem item = iterator.next();
            if (item.getName().equalsIgnoreCase(itemName)) {
                iterator.remove();
                System.out.println("Removed " + itemName + " from the cart.");
                itemRemoved = true;
                break;
            }
        }

        if (!itemRemoved) {
            System.out.println("Item " + itemName + " not found in the cart.");
        }
    }



    public List<FoodItem> getCart() {
        return cart;
    }

    public void setCart(List<FoodItem> cart) {
        this.cart = cart;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void viewCartTotal() {
        double total = 0.0;
        for (FoodItem item : cart) {
            total += item.getPrice() * item.getQuantity();
        }
        System.out.println("Total cart value: $" + total);
    }




    @Override
    public void logout() {
        System.out.println("Customer logged out.");
    }
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty! Cannot proceed to checkout.");
            return;
        }
        OrderQueue orderQueue = new OrderQueue();

        System.out.println("Your cart contains:");
        viewCartTotal();
        System.out.println("Do you want to proceed with the purchase? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.next();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Checkout cancelled.");
            return;
        }


        System.out.println("Do you have any special requests for this order? (yes/no)");
        String specialRequest = null;
        String specialRequestConfirmation = scanner.next();
        if (specialRequestConfirmation.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your special request:");
            scanner.nextLine();
            specialRequest = scanner.nextLine();
        }


        int orderId = orderHistory.size() + 1;

        Order newOrder = new Order(orderId, new ArrayList<>(cart), isVIP, specialRequest);
        orderHistory.add(newOrder);

        if (isVIP) {
            System.out.println("As a VIP customer, your order will be prioritized!");
        }
        FileManager fileManager = new FileManager();
        fileManager.saveOrderHistory(userId, orderHistory);

        orderQueue.addOrder(newOrder);
        System.out.println("Order placed successfully!");
        System.out.println("Order Summary:");
        System.out.println(newOrder);

        cart.clear();
        System.out.println("Your cart has been cleared.");
    }

    public void viewOrderStatus(int orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == orderId) {
                String orderStatus = order.getStatus();
                System.out.println("The Order ID is: " + order.getOrderId());
                System.out.println("The Order Status is: " + orderStatus);
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found.");
    }
    private double calculateTotal() {
        double total = 0.0;
        for (FoodItem item : cart) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void cancelOrder(int orderId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == orderId) {
                order.setStatus("Canceled");
                System.out.println("Order with ID " + orderId + " has been canceled.");
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found.");
    }
    @Override
    public void viewOrderHistory() {
        FileManager fileManager = new FileManager();
        List<Order> orders = fileManager.loadOrderHistory(userId);

        if (orders.isEmpty()) {
            System.out.println("No orders found in history.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

   
}


