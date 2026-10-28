
# Food Delivery Platform

## 📌 Project Overview

The **Food Delivery Platform** is a console-based application developed using Java. It simulates the basic operations of an online food delivery system, allowing users to manage restaurants, food items, customers, orders, payments, cancellations, and restaurant reviews.

The project demonstrates Object-Oriented Programming (OOP) concepts in Java, along with collections, conditional statements, loops, and user input handling.

## ✨ Features

- **Add Restaurant:** Register restaurants on the platform.
- **Add Food Items:** Add food items with their prices, stock quantities, and associated restaurants.
- **Register Customers:** Create customer records.
- **Create Orders:** Place orders by selecting food items, customers, and quantities.
- **Record Payment:** Process payments after checking order status and food availability.
- **Complete Orders:** Mark paid orders as completed.
- **Cancel Orders:** Cancel eligible orders and calculate cancellation fees and refunds.
- **Add Reviews:** Allow customers to rate restaurants from 1 to 5 after order completion.
- **Display Availability and Ratings:** View food prices, stock availability, restaurant details, and average restaurant ratings.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Collections:** ArrayList
- **Input Handling:** Scanner
- **Concepts:** Classes, Objects, Constructors, Methods, Encapsulation basics, and Object Relationships

## 📂 Project Structure

```text
FoodDeliveryPlatform/
│
├── FoodDeliveryPlatform.java
└── README.md
```

The Java source file contains the following classes:

| Class | Description |
|---|---|
| `Restaurant` | Stores restaurant information and calculates average ratings. |
| `FoodItem` | Stores food names, prices, stock, and restaurant details. |
| `Customer` | Represents a registered customer. |
| `Order` | Stores order details, quantity, and order status. |
| `Payment` | Handles payment processing and stock deduction. |
| `Cancellation` | Handles order cancellation, refunds, and cancellation fees. |
| `Review` | Validates and records restaurant ratings. |
| `FoodDeliveryPlatform` | Contains the main menu and application logic. |

## ⚙️ Requirements

Before running the application, ensure that you have:

- Java Development Kit (JDK) installed.
- A terminal or command prompt.
- A Java-compatible IDE, such as IntelliJ IDEA, Eclipse, or Visual Studio Code (optional).

## 🚀 How to Run

### Step 1: Clone or Download the Project

Download the source code and save it as `FoodDeliveryPlatform.java`.

### Step 2: Open a Terminal

Navigate to the directory containing the Java source file.

### Step 3: Compile the Program

Run the following command:

```bash
javac FoodDeliveryPlatform.java
```

### Step 4: Execute the Program

Run the compiled application using:

```bash
java FoodDeliveryPlatform
```

## 📋 Application Menu

When the application starts, the following menu is displayed:

```text
--- FOOD DELIVERY PLATFORM ---
1. Add Restaurant
2. Add Food Item
3. Add Customer
4. Create Order
5. Record Payment
6. Complete Order
7. Cancel Order
8. Add Review
9. Display Availability and Ratings
10. Exit
Enter choice:
```

Select a menu option by entering the corresponding number.

## 💻 Example Workflow

Follow these steps to test the application:

1. Add a restaurant, such as `PizzaHouse`.
2. Add a food item, such as `Pizza`, with a price of `250` and stock of `10`.
3. Register a customer, such as `Rahul`.
4. Create an order by selecting the food item, customer, and quantity.
5. Record payment using the generated order ID.
6. Complete the order using the same order ID.
7. Add a restaurant review with a rating between 1 and 5.
8. Select option 9 to view food availability and restaurant ratings.

### Example Input

```text
Restaurant name: PizzaHouse
Food name, price, stock: Pizza 250 10
Customer name: Rahul
Food number, customer number, quantity: 1 1 2
```

### Example Output

```text
Order ID: 1
Total: Rs.500.0
Please record payment.
```

After successful payment:

```text
Payment successful!
Amount: Rs.500.0
Restaurant: PizzaHouse
```

## 💳 Payment and Cancellation Rules

- Payment is allowed only for orders that are not paid, completed, or cancelled.
- Payment is rejected if the available stock is less than the order quantity.
- Successful payment deducts the ordered quantity from food stock.
- Cancelling a paid order applies a cancellation fee of **10% of the order total**.
- The remaining 90% is calculated as the refund.
- Cancelled paid orders return their quantity to the available food stock.
- Orders that are already completed or cancelled cannot be cancelled again.

## ⭐ Review and Rating Rules

- Reviews can only be added to completed orders.
- Each order can be reviewed only once.
- Ratings must be between 1 and 5.
- Restaurant ratings are calculated using the average of all valid reviews.

**Average Rating Formula:**

```text
Average Rating = Total Ratings / Number of Reviews
```

If a restaurant has no reviews, its average rating is displayed as `0.0/5`.

## 📦 Stock Management

Food stock is managed during the order payment and cancellation processes.

- Stock is reduced after successful payment.
- Stock is restored when a paid order is cancelled.
- Order creation checks whether the requested quantity is currently available.

## 🎯 Learning Objectives

This project helps demonstrate:

- Designing a Java application using multiple classes.
- Implementing relationships between restaurants, food items, customers, and orders.
- Using `ArrayList` to store application data.
- Applying conditional logic to validate payments and cancellations.
- Implementing basic stock management.
- Calculating average ratings.
- Building an interactive menu-driven console application.

## ⚠️ Limitations

- Data is stored in memory and is lost when the application terminates.
- The application does not use a database.
- Payment processing is simulated and does not connect to a real payment gateway.
- Customer and restaurant names are read as single words.
- The application does not implement authentication, delivery tracking, or real-time order notifications.

## 🔮 Future Enhancements

Possible improvements include:

- Integrating a database such as MySQL.
- Supporting customer and restaurant login systems.
- Adding food search and category filters.
- Supporting customer names and food names with spaces.
- Improving input validation and error handling.
- Adding order history and order status tracking.
- Integrating a real payment gateway.
- Developing a graphical user interface or web application.

## 👨‍💻 Author

Developed as a Java console-based project to demonstrate fundamental programming and Object-Oriented Programming concepts.

## 📄 License

This project is intended for educational and learning purposes. Add a specific open-source license if you plan to distribute or reuse the project publicly.

Name: Bhaarath A

Roll No: