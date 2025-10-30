package org.example.supermarket;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;

/**
 * vertegenwoordigt de supermarkt en beheert alle onderdelen zoals kassa's,
 * schappen, klanten en restockers. bevat methodes om de winkel te creëren
 * en betalingen te verwerken.
 */
public class Store {

    /** centrale controller van de supermarkt voor updates en logica */
    public static Controller controller;

    /** de ingang van de supermarkt */
    public static Entrance entrance;

    /** de uitgang van de supermarkt */
    public static Exit exit;

    /** opslagruimte van de supermarkt */
    public static Storage storage;

    /** teller voor het bijhouden van geld in de supermarkt */
    public static MoneyCounter moneyCounter;

    /** teller voor het bijhouden van het aantal klanten */
    public static CustomerCounter customerCounter;

    /** catalogus met alle beschikbare producten */
    public static ProductCatalog catalog = new ProductCatalog();

    /** lijst van alle producten in de supermarkt */
    public static List<Product> products = catalog.getAllProducts();

    /** lijst van alle schappen in de supermarkt */
    public static List<Shelve> shelves = new ArrayList<>();

    /** lijst van alle kassa's in de supermarkt */
    public static List<Checkout> checkouts = new ArrayList<>();

    /** lijst van alle klanten in de supermarkt */
    public static List<Customer> customers = new ArrayList<>();

    /** lijst van alle medewerkers die schappen bijvullen */
    public static List<Restocker> restockers = new ArrayList<>();

    /** totale hoeveelheid geld in de supermarkt */
    public static double storeMoney = 0;

    /**
     * verwijdert schappen die verwijzen naar producten die niet bestaan in de catalogus
     *
     * @param borderPane het pane waarin de schappen zichtbaar zijn
     */
    public static void removeDeadShelves(BorderPane borderPane) {
        List<Shelve> deadShelves = new ArrayList<>();
        for (Shelve shelve : shelves) {
            if (ProductCatalog.getProductByName(shelve.objectName) == null) {
                System.out.println("product named '" + shelve.objectName + "' not found");
                deadShelves.add(shelve);
            }
        }
        for (Shelve shelve : deadShelves) {
            System.out.println("shelve '" + shelve.objectName + "' removed");
            borderPane.getChildren().remove(shelve.rectangle);
            shelves.remove(shelve);
        }
    }

    /**
     * voegt het opgegeven bedrag toe aan de winkelkas
     *
     * @param price het te betalen bedrag
     */
    public static void pay(double price) {
        storeMoney += price;
        moneyCounter.update();
    }

    /**
     * maakt alle onderdelen van de supermarkt aan zoals ingang, uitgang,
     * opslag, kassa's, schappen en restockers
     *
     * @param borderPane het pane waarin alles weergegeven wordt
     */
    public static void create(BorderPane borderPane) {
        entrance = new Entrance(borderPane, 1500.0, 50.0);
        exit = new Exit(borderPane, 300.0, 50.0);
        storage = new Storage(borderPane, 1200.0, 900.0);

        controller = new Controller(borderPane);

        moneyCounter = new MoneyCounter(borderPane, 1000.0, 100.0);
        moneyCounter.update();
        customerCounter = new CustomerCounter(borderPane, 1600.0, 30.0);
        customerCounter.update();

        checkouts.add(new Checkout("c1", borderPane, 300, 400));
        checkouts.add(new Checkout("c2", borderPane, 300, 600));
        checkouts.add(new Checkout("c3", borderPane, 300, 800));

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
            restockers.add(new Restocker("bart", borderPane));
        }

        removeDeadShelves(borderPane);
    }
}
