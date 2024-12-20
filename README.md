# Byte Me! Food Ordering System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
  - [Admin Interface](#admin-interface)
  - [Customer Interface](#customer-interface)
  - [Enhanced Features](#enhanced-features)
    - [Graphical User Interface (GUI)](#graphical-user-interface-gui)
    - [I/O Stream Management](#io-stream-management)
    - [JUnit Testing](#junit-testing)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [How to Run](#how-to-run)
- [Acknowledgment](#acknowledgment)

## Introduction
The "Byte Me!" Food Ordering System is a comprehensive application designed to simplify food ordering and management processes for a college canteen. The system includes features for both customers and admins, providing a seamless experience via Command-Line Interface (CLI) and Graphical User Interface (GUI). It also implements robust data management and testing mechanisms.

## Features

### Admin Interface
1. **Menu Management**
   - Add new items: Admins can add new food items with details like price, category, and availability.
   - Update existing items: Modify details of current menu items.
   - Remove items: Delete discontinued items, automatically updating all pending orders containing these items to "denied".

2. **Order Management**
   - View pending orders: Orders are displayed in the order of receipt.
   - Update order status: Track and modify orders through various stages (e.g., preparing, out for delivery).
   - Process refunds: Handle refunds for canceled or problematic orders.
   - Handle special requests: Allow customization (e.g., "extra spicy").
   - Order priority: VIP customer orders are prioritized over regular orders.

3. **Report Generation**
   - Generate daily sales reports with details like total sales, most popular items, and total orders.

### Customer Interface
1. **Customer Types**
   - VIP: Customers can pay a fee to upgrade to VIP status for prioritized order handling.
   - Regular: Orders are processed on a first-come-first-serve basis after VIP orders.

2. **Browse Menu**
   - View all items: Display the complete menu.
   - Search functionality: Search by item name or keyword.
   - Filter by category: Filter items based on type (e.g., snacks, beverages).
   - Sort by price: Sort items in ascending/descending order by price.

3. **Cart Operations**
   - Add, modify, and remove items.
   - View total price before checkout.
   - Complete orders with payment and delivery details.

4. **Order Tracking**
   - Track order status in real time.
   - Cancel orders before processing.
   - View order history and reorder previous items.

5. **Item Reviews**
   - Provide and view reviews for menu items.

### Enhanced Features

#### Graphical User Interface (GUI)
- Implemented using **Swing** or **JavaFX**.
- Features:
  - Menu browsing with item details (name, price, availability).
  - View pending orders with details (order number, items, status).
- GUI-only displays data and does not modify it. All updates are managed via CLI.
- Includes navigation buttons, tables/lists for data display, and user-friendly design.

#### I/O Stream Management
Implemented the following features:
1. **Order Histories**
   - Save order details (items, quantity, price) for each user using file handling.

2. **User Management**
   - Retrieve existing user data or register new users via file operations.
   - Handle both existing and new user data efficiently.

#### JUnit Testing
Tests implemented to ensure system robustness:
1. **Ordering Out-of-Stock Items**
   - Verify error handling for unavailable items.

2. **Invalid Login Attempts**
   - Test responses to incorrect login credentials.

3. **Cart Operations**
   - Validate adding items, modifying quantities, and handling invalid inputs (e.g., negative quantities).

## Technologies Used
- **Java** for backend development.
- **Swing/JavaFX** for GUI implementation.
- **JUnit** for unit testing.
- **File Handling** for data management.

## Setup Instructions
1. Clone the repository.
2. Compile the project using your preferred IDE or command-line tool.
3. Ensure all dependencies are properly configured.

## How to Run
1. **CLI Module**:
   - Navigate to the CLI module's directory.
   - Compile the Java files using: `javac *.java`.
   - Run the application using: `java Main`.
2. **GUI Module**:
   - Navigate to the GUI module's directory.
   - Compile the Java files using: `javac *.java`.
   - Run the application using: `java Main`.
3. **JUnit Tests**:
   - Use your preferred IDE or command-line tool to execute the JUnit test cases.
   - Verify that all test cases pass.

## Acknowledgment
This project was developed as part of the **CSE201: Object-Oriented Programming** course requirements.
