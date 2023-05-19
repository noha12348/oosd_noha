package noha_oosd;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CoffeShopProgram {
    public static void main(String[] args) {
        CoffeeShop teshaCoffeeShop = new CoffeeShop("Tesha's Coffee Shop");

        // Create menu items
        MenuItem item1 = new MenuItem("Cinnamon Roll", "food", 1.99);
        MenuItem item2 = new MenuItem("Iced Coffee", "drink", 0.99);
        MenuItem item3 = new MenuItem("Tuna Sandwich", "food", 4.99);
        MenuItem item4 = new MenuItem("Lemonade", "drink", 2.49);
        MenuItem item5 = new MenuItem("Ham and Cheese Sandwich", "food", 3.99);
        MenuItem item6 = new MenuItem("Bacon and Egg Sandwich", "food", 4.49);
        MenuItem item7 = new MenuItem("Orange Juice", "drink", 1.99);
        MenuItem item8 = new MenuItem("Cranberry Juice", "drink", 2.99);

        // Add menu items to the coffee shop's menu
        teshaCoffeeShop.addToMenu(item1);
        teshaCoffeeShop.addToMenu(item2);
        teshaCoffeeShop.addToMenu(item3);
        teshaCoffeeShop.addToMenu(item4);
        teshaCoffeeShop.addToMenu(item5);
        teshaCoffeeShop.addToMenu(item6);
        teshaCoffeeShop.addToMenu(item7);
        teshaCoffeeShop.addToMenu(item8);

        // Test cases
        System.out.println("Hey there! Welcome to Tesha's Coffee Shop!");

        System.out.println("Let's start by placing some orders...");

        System.out.println("Adding an order for hot cocoa");
        teshaCoffeeShop.addOrder("hot cocoa");
        System.out.println("Oops! Sorry, but hot cocoa is currently unavailable.");

        System.out.println("Adding an order for iced tea");
        teshaCoffeeShop.addOrder("iced tea");
        System.out.println("Oops! Sorry, but iced tea is currently unavailable.");

        System.out.println("Adding an order for a cinnamon roll");
        teshaCoffeeShop.addOrder("cinnamon roll");
        System.out.println("Great choice! Your order for a cinnamon roll has been added.");

        System.out.println("Adding an order for an iced coffee");
        teshaCoffeeShop.addOrder("iced coffee");
        System.out.println("Wonderful! Your order for an iced coffee has been added.");

        System.out.println("Let's see the current orders:");
        List<String> currentOrders = teshaCoffeeShop.listOrders();
        System.out.println(currentOrders);

        System.out.println("The total amount due for the orders is: $" + teshaCoffeeShop.dueAmount());

        System.out.println("Fulfilling orders...");

        System.out.println(teshaCoffeeShop.fulfillOrder());
        System.out.println(teshaCoffeeShop.fulfillOrder());
        System.out.println(teshaCoffeeShop.fulfillOrder());

        System.out.println("Current orders after fulfillment:");
        currentOrders = teshaCoffeeShop.listOrders();
        System.out.println(currentOrders);

        System.out.println("The total amount due for the orders after fulfillment is: $" + teshaCoffeeShop.dueAmount());

        System.out.println("The cheapest item on the menu is: " + teshaCoffeeShop.cheapestItem());

        System.out.println("Here are the drink items on the menu:");
        List<String> drinkItems = teshaCoffeeShop.drinksOnly();
        System.out.println(drinkItems);

        System.out.println("And here are the food items on the menu:");
        List<String> foodItems = teshaCoffeeShop.foodOnly();
        System.out.println(foodItems);

        System.out.println("Thank you for visiting Tesha's Coffee Shop! Have a great day!");
    }
}

class MenuItem {
    private String itemName;
    private String itemType;
    private double price;

    public MenuItem(String itemName, String itemType, double price) {
        this.itemName = itemName;
        setType(itemType);
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setType(String itemType) {
        if (itemType.equalsIgnoreCase("food") || itemType.equalsIgnoreCase("drink")) {
            this.itemType = itemType.toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid item type. Please choose either 'food' or 'drink'.");
        }
    }

    public double getPrice() {
        return price;
    }
}

class CoffeeShop {
    private String name;
    private List<MenuItem> menu;
    private List<String> orders;

    public CoffeeShop(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addToMenu(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void addOrder(String itemName) {
        boolean itemExistsInMenu = false;

        for (MenuItem item : menu) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                itemExistsInMenu = true;
                break;
            }
        }

        if (itemExistsInMenu) {
            orders.add(itemName);
        } else {
            System.out.println("Oops! Sorry, but this item is currently unavailable.");
        }
    }

    public String fulfillOrder() {
        if (!orders.isEmpty()) {
            String item = orders.remove(0);
            return "The item '" + item + "' is ready.";
        } else {
            return "All orders have been fulfilled.";
        }
    }

    public List<String> listOrders() {
        return new ArrayList<>(orders);
    }

    public double dueAmount() {
        double totalAmount = 0.0;

        for (String itemName : orders) {
            for (MenuItem item : menu) {
                if (item.getItemName().equalsIgnoreCase(itemName)) {
                    totalAmount += item.getPrice();
                    break;
                }
            }
        }

        return totalAmount;
    }

    public String cheapestItem() {
        if (menu.isEmpty()) {
            return null;
        }

        MenuItem cheapestItem = menu.get(0);

        for (MenuItem item : menu) {
            if (item.getPrice() < cheapestItem.getPrice()) {
                cheapestItem = item;
            }
        }

        return cheapestItem.getItemName();
    }

    public List<String> drinksOnly() {
        List<String> drinkItems = new ArrayList<>();

        for (MenuItem item : menu) {
            if (item.getItemType().equalsIgnoreCase("drink")) {
                drinkItems.add(item.getItemName());
            }
        }

        return drinkItems;
    }

    public List<String> foodOnly() {
        List<String> foodItems = new ArrayList<>();

        for (MenuItem item : menu) {
            if (item.getItemType().equalsIgnoreCase("food")) {
                foodItems.add(item.getItemName());
            }
        }

        return foodItems;
    }
}

 