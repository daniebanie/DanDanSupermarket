package org.example.supermarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.layout.BorderPane;


public class Store {
    public static Controller controller;
    public static Entrance entrance;
    public static Exit exit;
    public static Storage storage;
    public static MoneyCounter moneyCounter;
    public static List<Shelve> shelves = new ArrayList<Shelve>();
    public static List<Checkout> checkouts = new ArrayList<Checkout>();
    public static List<Customer> customers  = new ArrayList<Customer>();
    public static List<Restocker> restockers   = new ArrayList<Restocker>();

    public static double storeMoney = 0;

    public static void removeDeadShelves(BorderPane borderPane)
    {
        // Deze methode verwijdert de Shelves met een invalid productName.
        List<Shelve> deadShelves = new ArrayList<>();
        for (Shelve shelve : shelves)
        {
            if (ProductCatalog.getProductByName(shelve.objectName) != null)
            {
                Product product = ProductCatalog.getProductByName(shelve.objectName);
                System.out.println(product);
            }
            else {
                System.out.println("Product named '" + shelve.objectName + "' not found");
                deadShelves.add(shelve);
            }
        }
        for (Shelve shelve : deadShelves)
        {
            System.out.println("Shelve '" + shelve.objectName + "' removed");
            borderPane.getChildren().remove(shelve.rectangle);
            shelves.remove(shelve);
        }
    }

    public static void pay(double price) {
        storeMoney += price;
        moneyCounter.update();
    }
}
