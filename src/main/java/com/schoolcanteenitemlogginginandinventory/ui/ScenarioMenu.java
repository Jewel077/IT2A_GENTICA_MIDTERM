package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao dao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        dao.createTableIfNotExists();
        boolean running = true;

        while (running) {
            printHeader("CANTEEN INVENTORY MANAGEMENT");
            System.out.println("[1] Add New Food Item");
            System.out.println("[2] View Canteen Inventory");
            System.out.println("[3] Exit System");
            printDivider();
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addNewItem();
                    break;
                case "2":
                    viewInventory();
                    break;
                case "3":
                    System.out.println("\nExiting system... Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void addNewItem() {
        printHeader("ADD NEW FOOD ITEM");

        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine().trim();

        System.out.print("Enter Category (MEAL / SNACK / BEVERAGE): ");
        String category = scanner.nextLine().trim().toUpperCase();

        double price = 0.0;
        while (true) {
            System.out.print("Enter Price: ");
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please enter a valid number.");
            }
        }

        int stock = 0;
        while (true) {
            System.out.print("Enter Stock Quantity: ");
            try {
                stock = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid stock quantity. Please enter a valid integer.");
            }
        }

        ScenarioEntity item = new ScenarioEntity(itemName, category, price, stock);
        dao.addRecord(item);
        System.out.println("\nSUCCESS: Food item added to inventory!\n");
    }

    private void viewInventory() {
        printHeader("CANTEEN FOOD INVENTORY");
        List<ScenarioEntity> items = dao.getAllRecords();

        if (items.isEmpty()) {
            System.out.println("No items found in inventory.\n");
            return;
        }

        String border = "+-----+-------------------+----------+---------+--------+";
        System.out.println(border);
        System.out.printf("| %-3s | %-17s | %-8s | %-7s | %-6s |\n", "ID", "ITEM NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println(border);

        for (ScenarioEntity item : items) {
            System.out.printf("| %-3d | %-17s | %-8s | %7.2f | %-6d |\n",
                    item.getId(),
                    item.getItemName(),
                    item.getCategory(),
                    item.getPrice(),
                    item.getStock()
            );
        }
        System.out.println(border);
        System.out.println("Total Records Displayed: " + items.size() + "\n");
    }

    private void printHeader(String title) {
        printDivider();
        System.out.println("\n" + title);
        printDivider();
    }

    private void printDivider() {
        System.out.println("==================================================");
    }
}