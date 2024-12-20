package Byte_Me;

import java.util.*;

public class Admin extends User {

    private double dailySales;
    private int totalOrders;
    private Map<FoodItem, Integer> itemOrderCount = new HashMap<>();
    private static final String PREDEFINED_EMAIL = "admin@byteme.com";
    private static final String PREDEFINED_PASSWORD = "admin123";


    public Admin() {
        super(1, "Admin", PREDEFINED_EMAIL, PREDEFINED_PASSWORD);
        this.dailySales=0.0;
        this.totalOrders = 0;

    }
    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String inputEmail = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();


        return inputEmail.equals(PREDEFINED_EMAIL) && inputPassword.equals(PREDEFINED_PASSWORD);
    }


    public List<FoodItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<FoodItem> menuItems) {

        this.menuItems = menuItems;
    }

    public void adminMenu() {

        System.out.println("Admin Menu - Choose an option:");
        System.out.println("1.Menu Management");
        System.out.println("2.Order Management");
        System.out.println("3.Report Generation");
        System.out.println("4.Back");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("1.Add Item");
                System.out.println("2.Update Existing Item");
                System.out.println("3.Remove Item");
                System.out.println("4.Back");
                int adminMenuOption = scanner.nextInt();
                switch (adminMenuOption) {
                    case 1:
                        addItem();
                        adminMenu();
                        break;
                    case 2:
                        updateItem();
                        adminMenu();
                    case 3:
                        removeItem();
                        adminMenu();
                        break;
                    case 4:
                        adminMenu();
                        break;
                    default:
                        System.out.println("Wrong option!");

                }
                break;
            case 2:
                System.out.println("1.View Pending Orders");
                System.out.println("2.Update Order Status");
                System.out.println("3.Process Refunds");
                System.out.println("4.Handle Special Requests");

                int adminOrderOption = scanner.nextInt();
                OrderQueue orderQueue = new OrderQueue();
                switch (adminOrderOption) {
                    case 1:
                        viewPendingOrders(orderQueue);
                        adminMenu();
                        break;
                    case 2:
                        updateOrderStatus(orderQueue);
                        adminMenu();
                        break;
                    case 3:
                        processRefund(orderQueue);
                        adminMenu();
                        break;
                    case 4:
                        handleSpecialRequest(orderQueue);
                        adminMenu();
                        break;
                    default:
                        System.out.println("Wrong option!");
                        adminMenu();
                }
                break;
            case 3:
                System.out.println("Report:");
                dailySalesReport();
                break;
            case 4:
                Main.main(null);
            default:
                System.out.println("Wrong Choice");
        }
    }
    public void addItem() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the food id, name, price, category, and quantity:");
            int foodId = scanner.nextInt();
            scanner.nextLine();
            String foodName = scanner.nextLine();
            double foodPrice = scanner.nextDouble();
            scanner.nextLine();
            String foodCategory = scanner.nextLine();
            int foodQuantity = scanner.nextInt();
            FoodItem food = new FoodItem(foodId, foodName, foodPrice, foodCategory, foodQuantity);
            menuItems.add(food);
            System.out.println("Item added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }


    public void removeItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Remove Item using Food ID");
        System.out.println("2. Remove Item using Food Name");
        int optionRemove = scanner.nextInt();
        scanner.nextLine();

        switch (optionRemove) {
            case 1:
                System.out.println("Enter the food id:");
                int itemId = scanner.nextInt();
                boolean found = false;
                Iterator<FoodItem> iteratorById = menuItems.iterator();
                while (iteratorById.hasNext()) {
                    FoodItem item = iteratorById.next();
                    if (item.getId() == itemId) {
                        iteratorById.remove();
                        System.out.println("Food item removed.");
                        found = true;
                        break;
                    }
                }
                if (!found)
                    System.out.println("Item with ID " + itemId + " not found.");
                break;
            case 2:
                System.out.println("Enter the food name:");
                String itemName = scanner.nextLine();
                boolean foundByName = false;
                Iterator<FoodItem> iteratorByName = menuItems.iterator();
                while (iteratorByName.hasNext()) {
                    FoodItem item = iteratorByName.next();
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        iteratorByName.remove();
                        System.out.println("Food item removed.");
                        foundByName = true;
                        break;
                    }
                }
                if (!foundByName)
                    System.out.println("Item with name " + itemName + " not found.");
                break;
            default:
                System.out.println("Wrong Choice");
        }
    }

    public void updateItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Update Item using Food ID");
        System.out.println("2.Update Item using Food Name");
        int optionUpdate = scanner.nextInt();
        switch (optionUpdate) {
            case 1:
                System.out.println("Enter the food id:");
                int itemId = scanner.nextInt();
                for (FoodItem item : menuItems) {
                    if (item.getId() == itemId) {
                        System.out.println("Do you want to update item id? (yes/no)");
                        String updateItemId = scanner.next();
                        if (updateItemId.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new food id.");
                            int newFoodId = scanner.nextInt();
                            item.setId(newFoodId);
                        }
                        System.out.println("Do you want to update item name? (yes/no)");
                        String updateItemName = scanner.nextLine();
                        if (updateItemName.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new food name.");
                            String newFoodName = scanner.next();
                            item.setName(newFoodName);
                        }
                        System.out.println("Do you want to update item price? (yes/no)");
                        String updateItemPrice = scanner.next();
                        if (updateItemPrice.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new price.");
                            double newPrice = scanner.nextDouble();
                            item.setPrice(newPrice);
                        }
                        System.out.println("Do you want to update item category? (yes/no)");
                        String updateItemCategory = scanner.next();
                        if (updateItemCategory.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new category.");
                            String newCategory = scanner.next();
                            item.setCategory(newCategory);
                        }
                        System.out.println("Do you want to update item quantity? (yes/no)");
                        String updateItemQuantity = scanner.next();
                        if (updateItemQuantity.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new quantity.");
                            int newQuantity = scanner.nextInt();
                            item.setQuantity(newQuantity);
                        }
                        break;
                    }
                }
                break;
            case 2:
                System.out.println("Enter the food name:");
                String itemName = scanner.nextLine();
                for (FoodItem item : menuItems) {
                    if (Objects.equals(item.getName(), itemName)) {
                        System.out.println("Do you want to update item id? (yes/no)");
                        String updateItemId = scanner.next();
                        if (updateItemId.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new food id.");
                            int newFoodId = scanner.nextInt();
                            item.setId(newFoodId);
                        }
                        System.out.println("Do you want to update item name? (yes/no)");
                        String updateItemName = scanner.next();
                        if (updateItemName.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new food name.");
                            String newFoodName = scanner.next();
                            item.setName(newFoodName);
                        }
                        System.out.println("Do you want to update item price? (yes/no)");
                        String updateItemPrice = scanner.next();
                        if (updateItemPrice.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new price.");
                            double newPrice = scanner.nextDouble();
                            item.setPrice(newPrice);
                        }
                        System.out.println("Do you want to update item category? (yes/no)");
                        String updateItemCategory = scanner.next();
                        if (updateItemCategory.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new category.");
                            String newCategory = scanner.next();
                            item.setCategory(newCategory);
                        }
                        System.out.println("Do you want to update item quantity? (yes/no)");
                        String updateItemQuantity = scanner.next();
                        if (updateItemQuantity.equalsIgnoreCase("yes")) {
                            System.out.println("Enter new quantity.");
                            int newQuantity = scanner.nextInt();
                            item.setQuantity(newQuantity);
                        }
                        break;
                    }
                }
                break;
            default:
                System.out.println("Wrong Choice");
        }

    }


    public void viewPendingOrders(OrderQueue orderQueue) {
        if (orderQueue.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }
        System.out.println("Pending Orders:");
        PriorityQueue<Order> sortedOrders = orderQueue.getOrderQueue();
        for (Order order : sortedOrders) {
            System.out.println(order);
        }
    }

    public void updateOrderStatus(OrderQueue orderQueue) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Order ID:");
        int orderId = scanner.nextInt();

        Order targetOrder = null;
        for (Order order : orderQueue.getOrderQueue()) {
            if (order.getOrderId() == orderId) {
                targetOrder = order;
                break;
            }
        }

        if (targetOrder == null) {
            System.out.println("Order ID not found.");
            return;
        }

        System.out.println("Select Order Status:");
        System.out.println("1. " + Order.STATUS_RECEIVED);
        System.out.println("2. " + Order.STATUS_SHIPPED);
        System.out.println("3. " + Order.STATUS_DELIVERED);
        System.out.println("4. " + Order.STATUS_CANCELLED);
        int orderStatus = scanner.nextInt();

        switch (orderStatus) {
            case 1:
                targetOrder.updateStatus(Order.STATUS_RECEIVED);
                System.out.println("Order status updated to '" + Order.STATUS_RECEIVED + "'.");
                break;
            case 2:
                targetOrder.updateStatus(Order.STATUS_SHIPPED);
                System.out.println("Order status updated to '" + Order.STATUS_SHIPPED + "'.");
                break;
            case 3:
                targetOrder.updateStatus(Order.STATUS_DELIVERED);
                System.out.println("Order status updated to '" + Order.STATUS_DELIVERED + "'.");
                break;
            case 4:
                targetOrder.updateStatus(Order.STATUS_CANCELLED);
                System.out.println("Order status updated to '" + Order.STATUS_CANCELLED + "'.");
                break;
            default:
                System.out.println("Invalid status choice.");
                break;
        }
    }


    public void processRefund(OrderQueue orderQueue) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Order ID:");
        int orderId = scanner.nextInt();

        Order targetOrder = null;
        for (Order order : orderQueue.getOrderQueue()) {
            if (order.getOrderId() == orderId) {
                targetOrder = order;
                break;
            }
        }

        if (targetOrder == null) {
            System.out.println("Order ID not found.");
            return;
        }

        double refundAmount = targetOrder.calculateTotal();
        targetOrder.updateStatus("Cancelled");
        orderQueue.getOrderQueue().remove(targetOrder);
        dailySales-= refundAmount;
        System.out.println("Order " + orderId + " has been cancelled and removed from pending orders.");
        System.out.println("Refund of $" + refundAmount + " has been processed for Order ID: " + orderId);
    }

    public void handleSpecialRequest(OrderQueue orderQueue) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Order ID:");
        int orderId = scanner.nextInt();
        Order targetOrder = null;
        for (Order order : orderQueue.getOrderQueue()) {
            if (order.getOrderId() == orderId) {
                targetOrder = order;
                break;
            }
        }
        if (targetOrder == null) {
            System.out.println("Order ID not found.");
            return;
        }
        String specialRequest = targetOrder.getSpecialRequest();
        if (specialRequest != null && !specialRequest.isEmpty()) {
            System.out.println("Special request for Order ID " + orderId + ": " + specialRequest);
        } else {
            System.out.println("No special request found for Order ID " + orderId + ".");
        }
    }


    private FoodItem findMostPopularItem() {
        return itemOrderCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public void dailySalesReport() {
        System.out.println("Total Sales Reported: $" + dailySales);
        System.out.println("Total Number of Orders: " + totalOrders);
        FoodItem mostPopularItem = findMostPopularItem();
        if (mostPopularItem != null) {
            System.out.println("Most Popular Item: " + mostPopularItem.getName() + " (Ordered: " + itemOrderCount.get(mostPopularItem) + " times)");
        } else {
            System.out.println("No orders placed yet.");
        }
    }
    public void viewAllItems() {
        if (menuItems.isEmpty()) {
            System.out.println("No items available in the menu.");
        } else {
            System.out.println("Menu Items:");
            for (FoodItem item : menuItems) {
                System.out.println(item);
            }
        }
    }



    public List<FoodItem> menuItemsSearch = new ArrayList<>();
    public void searchMenuItems(String searchTerm) {
        List<FoodItem> foundItems = new ArrayList<>();
        for (FoodItem item : menuItems) {
            if (item.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                foundItems.add(item);
            }
        }
        for(FoodItem item : foundItems) {
            System.out.println(item.getName());
        }
    }

    public void filterByCategory(String category) {
        List<FoodItem> filteredItems = new ArrayList<>();
        for (FoodItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filteredItems.add(item);
            }
        }
        for(FoodItem item : filteredItems) {
            System.out.println(item.getName());
        }
    }


    public void sortByPrice() {
        List<FoodItem> sortedItems = new ArrayList<>(menuItems);
        Collections.sort(sortedItems, new Comparator<FoodItem>() {
            @Override
            public int compare(FoodItem item1, FoodItem item2) {
                return Double.compare(item1.getPrice(), item2.getPrice());
            }
        });
        System.out.println("Menu items sorted by price:");
        for (FoodItem item : sortedItems) {
            System.out.println(item);
        }
    }











    @Override
    public void viewOrderHistory() {
        System.out.println("Viewing order history...");
    }

    @Override
    public void logout() {
        System.out.println("Admin logged out.");
    }
}
