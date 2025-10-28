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
    public static CustomerCounter customerCounter;
    public static ProductCatalog catalog = new ProductCatalog();
    public static List<Product> products = catalog.getAllProducts();
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

    public static void create (BorderPane borderPane) {
        // Alle onderdelen van de supermarkt aanmaken
        entrance = new Entrance(borderPane, 1500.0, 50.0);
        exit = new Exit(borderPane, 300.0, 50.0);
        storage = new Storage(borderPane, 1200.0, 900.0);

        controller = new Controller(borderPane);

        moneyCounter = new MoneyCounter(borderPane, 1000.0, 100.0);
        moneyCounter.update();
        customerCounter = new CustomerCounter(borderPane, 1600.0, 30.0);
        customerCounter.update();

        // Meerdere checkouts aanmaken
        checkouts.add(new Checkout("C1", borderPane, 500, 400));
        checkouts.add(new Checkout("C2", borderPane, 500, 600));
        checkouts.add(new Checkout("C3", borderPane, 500, 800));

        // Logica om de Shelves te maken gebaseerd op de bestaande producten in products
        int[] xPositions = {800, 1000, 1200, 1400, 1600};
        int[] yPositions = {300, 500, 700};
        int index = 0;
        for (Product product : products) {
            if (index < xPositions.length * yPositions.length) {
                int x = xPositions[index % xPositions.length];
                int y = yPositions[index / xPositions.length];
                shelves.add(new Shelve(product.getName(), borderPane, x, y, 10));
                index++;
            }
        }

        for (int i = 0; i < 3; i++) {
            Store.restockers.add(new Restocker("Bart", borderPane));
        }

        // Check of er shelves zijn zonder geldige productnaam (eigenlijk overbodig)
        removeDeadShelves(borderPane);
    }
}
