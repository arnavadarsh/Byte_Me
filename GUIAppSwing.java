package Byte_Me;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIAppSwing {
    private JFrame frame;
    private JPanel menuPanel, ordersPanel;
    private Admin admin;
    private OrderQueue orderQueue;

    public GUIAppSwing(Admin admin, OrderQueue orderQueue) {
        this.admin = admin;
        this.orderQueue = orderQueue;
        GUIcreation();
    }

    private void GUIcreation() {
        frame = new JFrame("Canteen Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        menuPanel = MenuPanelCreation();
        ordersPanel = OrderPanelCreation();
        frame.add(menuPanel);
        frame.setVisible(true);
    }

    private JPanel MenuPanelCreation() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Canteen Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Title", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        String[] ColumnContents = {"Name", "Price", "Category", "Available"};
        DefaultTableModel model = new DefaultTableModel(ColumnContents, 0);
        JTable menuTable = new JTable(model);
        menuTable.setShowGrid(true);
        menuTable.setGridColor(Color.BLACK);
        menuTable.setShowVerticalLines(true);
        menuTable.setShowHorizontalLines(true);
        List<FoodItem> menu = admin.getMenuItems();
        for (FoodItem item : menu) {
            model.addRow(new Object[]{
                    item.getName(),
                    String.format("$%.2f", item.getPrice()),
                    item.getCategory(),
                    item.getQuantity() > 0 ? "Yes" : "No"
            });
        }


        panel.add(new JScrollPane(menuTable), BorderLayout.CENTER);
        JButton switchToOrdersButton = new JButton("View Pending Orders");
        switchToOrdersButton.setOpaque(true);
        switchToOrdersButton.setBorderPainted(false);
        switchToOrdersButton.setBackground(Color.darkGray);
        switchToOrdersButton.setForeground(Color.WHITE);
        switchToOrdersButton.addActionListener(e -> switchPanel(ordersPanel));
        panel.add(switchToOrdersButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel OrderPanelCreation() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><u>Pending Orders</u></html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Title", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Order ID", "Customer", "Items (with Quantity)", "Amount", "Special Request", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable ordersTable = new JTable(model);

        ordersTable.setShowGrid(true);
        ordersTable.setGridColor(Color.BLACK);
        ordersTable.setShowVerticalLines(true);
        ordersTable.setShowHorizontalLines(true);


        List<Order> pendingOrders = orderQueue.getOrderQueue().stream()
                .filter(order -> Order.STATUS_RECEIVED.equals(order.getStatus()))
                .toList();

        for (Order order : pendingOrders) {
            double totalAmount = TotalAmount(order.getOrderItems());
            String customerType = order.isVIP() ? "VIP Customer" : "Regular Customer";
            String formattedOrderItems = FormatItems(order.getOrderItems());
            String specialRequest = order.getSpecialRequest() != null ? order.getSpecialRequest() : "None";

            model.addRow(new Object[]{
                    order.getOrderId(),
                    customerType,
                    formattedOrderItems,
                    String.format("$%.2f", totalAmount),
                    specialRequest,
                    order.getStatus()
            });
        }


        panel.add(new JScrollPane(ordersTable), BorderLayout.CENTER);

        JButton switchToMenuButton = new JButton("View Menu");
        switchToMenuButton.setOpaque(true);
        switchToMenuButton.setBorderPainted(false);
        switchToMenuButton.setBackground(Color.darkGray);
        switchToMenuButton.setForeground(Color.WHITE);
        switchToMenuButton.addActionListener(e -> switchPanel(menuPanel));
        panel.add(switchToMenuButton, BorderLayout.SOUTH);

        return panel;
    }

    private double TotalAmount(List<FoodItem> items) {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    private String FormatItems(List<FoodItem> items) {
        StringBuilder sb = new StringBuilder();
        for (FoodItem item : items) {
            sb.append(item.getName()).append(" (").append(item.getQuantity()).append("), ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "No items";
    }

    private void switchPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        OrderQueue orderQueue = new OrderQueue();
        List<FoodItem> sampleOrder = admin.getMenuItems();
        SwingUtilities.invokeLater(() -> new GUIAppSwing(admin, orderQueue).show());
    }
}
