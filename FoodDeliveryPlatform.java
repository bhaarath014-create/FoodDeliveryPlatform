package foodDelivery;

import java.util.*;

class Restaurant {
    String name;
    int ratingCount = 0;
    int ratingTotal = 0;

    Restaurant(String name) { this.name = name; }

    void addRating(int rating) {
        ratingTotal += rating;
        ratingCount++;
    }

    double averageRating() {
        return ratingCount == 0 ? 0 :
               (double) ratingTotal / ratingCount;
    }
}

class FoodItem {
    String name;
    double price;
    int stock;
    Restaurant restaurant;

    FoodItem(String n, double p, int s, Restaurant r) {
        name = n;
        price = p;
        stock = s;
        restaurant = r;
    }
}

class Customer {
    String name;
    Customer(String n) { name = n; }
}

class Order {
    int id, quantity;
    FoodItem food;
    Customer customer;
    boolean paid = false;
    boolean completed = false;
    boolean cancelled = false;
    boolean reviewed = false;

    Order(int id, FoodItem f, Customer c, int q) {
        this.id = id;
        food = f;
        customer = c;
        quantity = q;
    }

    double total() {
        return food.price * quantity;
    }
}

class Payment {
    void pay(Order o) {
        if (o.cancelled || o.completed || o.paid) {
            System.out.println("Payment not allowed.");
        } else if (o.food.stock < o.quantity) {
            System.out.println("Food unavailable.");
        } else {
            o.food.stock -= o.quantity;
            o.paid = true;
            System.out.println("Payment successful!");
            System.out.println("Amount: Rs." + o.total());
            System.out.println("Restaurant: " +
                               o.food.restaurant.name);
        }
    }
}

class Cancellation {
    void cancel(Order o) {
        if (o.cancelled || o.completed) {
            System.out.println("Cannot cancel this order.");
            return;
        }

        if (o.paid) {
            double fee = o.total() * 0.10;
            System.out.println("Cancellation fee: Rs." + fee);
            System.out.println("Refund: Rs." + (o.total() - fee));
            o.food.stock += o.quantity;
            o.paid = false;
        } else {
            System.out.println("No payment made. No refund needed.");
        }

        o.cancelled = true;
        System.out.println("Order cancelled.");
    }
}

class Review {
    void add(Order o, int rating) {
        if (!o.completed || o.cancelled || o.reviewed) {
            System.out.println(
                "Review requires a completed, unreviewed order.");
        } else if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
        } else {
            o.food.restaurant.addRating(rating);
            o.reviewed = true;
            System.out.println("Review added successfully.");
        }
    }
}

public class FoodDeliveryPlatform {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Restaurant> restaurants = new ArrayList<>();
    static ArrayList<FoodItem> foods = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    static Payment payment = new Payment();
    static Cancellation cancellation = new Cancellation();
    static Review review = new Review();
    static int nextOrderId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- FOOD DELIVERY PLATFORM ---");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item");
            System.out.println("3. Add Customer");
            System.out.println("4. Create Order");
            System.out.println("5. Record Payment");
            System.out.println("6. Complete Order");
            System.out.println("7. Cancel Order");
            System.out.println("8. Add Review");
            System.out.println("9. Display Availability and Ratings");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addRestaurant(); break;
                case 2: addFood(); break;
                case 3: addCustomer(); break;
                case 4: createOrder(); break;
                case 5:
                    Order a = findOrder();
                    if (a != null) payment.pay(a);
                    break;
                case 6:
                    Order b = findOrder();
                    if (b != null && b.paid &&
                        !b.completed && !b.cancelled) {
                        b.completed = true;
                        System.out.println("Order completed.");
                    } else {
                        System.out.println("Order cannot be completed.");
                    }
                    break;
                case 7:
                    Order c = findOrder();
                    if (c != null) cancellation.cancel(c);
                    break;
                case 8:
                    Order d = findOrder();
                    if (d != null) {
                        System.out.print("Rating (1-5): ");
                        review.add(d, sc.nextInt());
                    }
                    break;
                case 9: displayDetails(); break;
                case 10: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 10);

        sc.close();
    }

    static void addRestaurant() {
        System.out.print("Restaurant name: ");
        restaurants.add(new Restaurant(sc.next()));
        System.out.println("Restaurant added.");
    }

    static void addFood() {
        if (restaurants.isEmpty()) {
            System.out.println("Add a restaurant first.");
            return;
        }

        System.out.println("Select restaurant:");
        for (int i = 0; i < restaurants.size(); i++)
            System.out.println((i + 1) + ". " + restaurants.get(i).name);

        int r = sc.nextInt() - 1;
        if (r < 0 || r >= restaurants.size()) {
            System.out.println("Invalid restaurant.");
            return;
        }

        System.out.print("Food name, price, stock: ");
        String name = sc.next();
        double price = sc.nextDouble();
        int stock = sc.nextInt();

        if (price > 0 && stock >= 0) {
            foods.add(new FoodItem(name, price, stock,
                                   restaurants.get(r)));
            System.out.println("Food item added.");
        } else System.out.println("Invalid details.");
    }

    static void addCustomer() {
        System.out.print("Customer name: ");
        customers.add(new Customer(sc.next()));
        System.out.println("Customer registered.");
    }

    static void createOrder() {
        if (foods.isEmpty() || customers.isEmpty()) {
            System.out.println("Add food and customer first.");
            return;
        }

        displayFoods();
        System.out.print("Food number, customer number, quantity: ");
        int f = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int q = sc.nextInt();

        if (f >= 0 && f < foods.size() &&
            c >= 0 && c < customers.size() && q > 0 &&
            foods.get(f).stock >= q) {
            Order o = new Order(nextOrderId++, foods.get(f),
                                customers.get(c), q);
            orders.add(o);
            System.out.println("Order ID: " + o.id);
            System.out.println("Total: Rs." + o.total());
            System.out.println("Please record payment.");
        } else System.out.println("Invalid selection or stock.");
    }

    static Order findOrder() {
        System.out.print("Enter order ID: ");
        int id = sc.nextInt();

        for (Order o : orders)
            if (o.id == id) return o;

        System.out.println("Order not found.");
        return null;
    }

    static void displayFoods() {
        for (int i = 0; i < foods.size(); i++) {
            FoodItem f = foods.get(i);
            System.out.println((i + 1) + ". " + f.name +
                " Rs." + f.price + " Stock:" + f.stock +
                " Restaurant:" + f.restaurant.name);
        }
    }

    static void displayDetails() {
        System.out.println("\n--- FOOD AVAILABILITY ---");
        displayFoods();

        System.out.println("\n--- RESTAURANT RATINGS ---");
        for (Restaurant r : restaurants)
            System.out.println(r.name + " Rating: " +
                r.averageRating() + "/5 (" +
                r.ratingCount + " reviews)");
    }
}