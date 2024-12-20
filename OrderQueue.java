package Byte_Me;


import java.util.*;

public class OrderQueue {
    private static PriorityQueue<Order> orderQueue;
    private static boolean isInitialized = false;
    public OrderQueue() {
        if (!isInitialized) {
            orderQueue = new PriorityQueue<>();
            isInitialized = true;
        }

    }

    public void addOrder(Order order) {
        orderQueue.offer(order);
        System.out.println("Order added: " + order);
    }

    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }

    public PriorityQueue<Order> getOrderQueue() {
        return orderQueue;
    }
}
