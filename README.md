# Byte Me! - College Canteen Food Ordering System

Byte Me! is a CLI-based food ordering system developed in Java, designed to simplify food ordering and menu management within a college canteen. This system provides two types of users—Admin and Customer—with different capabilities to manage and interact with canteen orders efficiently.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
   - [Admin Interface](#admin-interface)
   - [Customer Interface](#customer-interface)
- [Data Structures](#data-structures)
- [Setup and Usage](#setup-and-usage)
- [Additional Notes](#additional-notes)

## Introduction

Byte Me! allows:
- **Admins** to manage menu items, orders, and generate sales reports.
- **Customers** to browse the canteen menu, add items to their cart, place orders, and track order status.

## Features

### Admin Interface

The Admin functionality is defined in the `Admin` class, which extends the `User` class. Admins have the following capabilities:

1. **Login System**:
   - Admins can log in with predefined credentials:
      - Email: `admin@byteme.com`
      - Password: `admin123`
   - Upon successful login, Admins are granted access to manage the canteen system.

2. **Admin Menu Options**:
   - **Menu Management**:
      - **Add New Items**: Add items to the canteen menu, specifying properties such as name, price, and availability.
      - **Update Existing Items**: Modify details like price or availability for existing menu items.
      - **Remove Items**: Remove items from the menu; orders containing removed items are updated to reflect the change.

   - **Order Management**:
      - **View Pending Orders**: Display a list of unprocessed orders.
      - **Update Order Status**: Change the status of orders, e.g., preparing, out for delivery.
      - **Process Refunds**: Handle refunds for canceled orders.
      - **Handle Special Requests**: Process any customer special requests included with orders.

   - **Report Generation**:
      - **Daily Sales Report**: Summarizes the daily sales and includes metrics like total orders and total revenue.

### Customer Interface

The `Customer` class allows students to interact with the menu and place orders. Customers can perform the following actions:

1. **Browse and Search Menu**:
   - **View Menu**: View the list of available food items with details.
   - **Filter by Category**: Optionally filter items based on predefined categories.
   - **Search by Name**: Find specific items by entering keywords.

2. **Manage Cart**:
   - **Add Items to Cart**: Select items and add them to the cart with specified quantities.
   - **Modify Quantities**: Change quantities of items in the cart before checking out.
   - **Remove Items**: Delete items from the cart.
   - **View Total**: Calculate and display the total price for items in the cart.

3. **Order and Tracking**:
   - **Checkout**: Complete the order by providing delivery details.
   - **Track Order Status**: Monitor order status updates, from "order received" to "delivered."
   - **Order History**: Review past orders for easy reordering.

## Data Structures

The system uses several Java collections for efficient data management:
- **`ArrayList`**: Manages lists of items, orders, and cart contents.
- **`HashMap`**: Tracks order counts for each item and handles menu data.
- **`PriorityQueue`**: Prioritizes orders, allowing VIP orders to be handled before regular orders.
- **`OrderQueue` Class**: Manages the sequence of orders.

## Additional Notes

- **Predefined Credentials**: Admin users log in with the credentials specified in the code.
- **Error Handling**: Basic input validation ensures that incorrect options are managed.
- **No Persistent Storage**: Data resets each time the program restarts as data is not stored to a database or file.


## Setup and Usage

1. **Compile the Program**:
   ```bash
   javac Main.java
   
## Running the Program

2.**To run the program, use the following command:**
   ```bash
   java Main
   
#User Authentication

- **Admin Login**: Use the predefined credentials to log in as an admin:
  - **Email**: `admin@byteme.com`
  - **Password**: `admin123`
- **Customer Login**: Register with an email and password to access customer features.

# Using the Admin Menu

After logging in, Admins can access menu options for:

- Managing items
- Processing orders
- Generating reports

# Using the Customer Interface

Customers can:

- Browse the menu
- Manage their cart
- Place orders
- View order history

