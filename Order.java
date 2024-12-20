package Byte_Me;

import java.util.*;

public class Order implements Comparable<Order> {
    private int orderId;
    private List<FoodItem> orderItems;
    private String status;
    private boolean isVIP;
    private String specialRequest;


    public static final String STATUS_RECEIVED = "Order Received";
    public static final String STATUS_SHIPPED = "Shipped";
    public static final String STATUS_DELIVERED = "Delivered";
    public static final String STATUS_CANCELLED = "Canceled";


    public Order(int orderId, List<FoodItem> orderItems, boolean isVIP, String specialRequest, String status) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.isVIP = isVIP;
        this.specialRequest = specialRequest;
        this.status = status;
    }

    public Order(int orderId, List<FoodItem> orderItems, boolean isVIP, String specialRequest) {
        this(orderId, orderItems, isVIP, specialRequest, STATUS_RECEIVED);
    }

    public Order(int orderId, List<FoodItem> orderItems, boolean isVIP) {
        this(orderId, orderItems, isVIP, null, STATUS_RECEIVED);
    }

    public int getOrderId() { return orderId; }
    public String getSpecialRequest() { return specialRequest; }
    public List<FoodItem> getOrderItems() { return orderItems; }
    public String getStatus() { return status; }
    public boolean isVIP() { return isVIP; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setOrderItems(List<FoodItem> orderItems) { this.orderItems = orderItems; }
    public void setStatus(String status) { this.status = status; }

    public double calculateTotal() {
        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Order ID: " + orderId);
        orderDetails.append(", Status: ").append(status)
                .append(", VIP: ").append(isVIP)
                .append(", Total: $").append(String.format("%.2f", calculateTotal()));

        if (specialRequest != null && !specialRequest.isEmpty()) {
            orderDetails.append(", Special Request: ").append(specialRequest);
        }

        orderDetails.append("\nItems: ");
        for (FoodItem item : orderItems) {
            orderDetails.append("\n  - ").append(item.getName())
                    .append(", Qty: ").append(item.getQuantity())
                    .append(", Price: $").append(item.getPrice());
        }

        return orderDetails.toString();
    }
    public void updateStatus(String newStatus) {
        switch (newStatus) {
            case STATUS_RECEIVED:
            case STATUS_SHIPPED:
            case STATUS_DELIVERED:
            case STATUS_CANCELLED:
                this.status = newStatus;
                System.out.println("Order status updated to: " + newStatus);
                break;
            default:
                System.out.println("Invalid status. Please use a valid status.");
        }
    }

    @Override
    public int compareTo(Order other) {
        if (this.isVIP && !other.isVIP) {
            return -1;
        } else if (!this.isVIP && other.isVIP) {
            return 1;
        } else {
            return Integer.compare(this.orderId, other.orderId);
        }
    }
}
