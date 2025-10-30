package org.example.supermarket;

import java.util.*;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static org.example.supermarket.Restocker.CustomerState.WAITING_FOR_ORDER;

/**
 * bestuurt de logica van de supermarkt, inclusief klanten, rekken en restockers
 */
public class Controller {

    private double spawnTime = 2.0;
    private int maxCustomers = 20;
    private int maxShoppingListSize = 10;
    private long spawnStartTime = System.currentTimeMillis();
    public Random rand = new Random();
    public static List<Shelve>  restockingShelves = new LinkedList<>();

    /**
     * maakt een nieuwe controller aan die de simulatie bestuurt
     *
     * @param borderPane het bord waarop de objecten staan
     */
    public Controller(BorderPane borderPane) {

    }

    /**
     * voert elke updatecyclus uit: klanten spawnen, rekken bijvullen en restockers aansturen
     *
     * @param borderPane het hoofdscherm
     * @param deltaTime verstreken tijd sinds de vorige update
     */
    public void update(BorderPane borderPane, double deltaTime) {
        spawnCustomers(borderPane);
        checkRestockableShelves();
        updateCustomers(deltaTime, borderPane);
        updateRestockers();
    }

    /**
     * spawnt nieuwe klanten als het maximum nog niet is bereikt en de wachttijd voorbij is
     *
     * @param borderPane het speelveld waar klanten worden toegevoegd
     */
    public void spawnCustomers(BorderPane borderPane) {
        if (Store.customers.size() < maxCustomers && (System.currentTimeMillis() - spawnStartTime) / 1000.0 > spawnTime)
        {
            Store.customers.add(( new Customer("dan", borderPane, createShoppingList())));
            Store.customerCounter.update();
            spawnStartTime = System.currentTimeMillis();
        }
    }

    /**
     * maakt een willekeurige boodschappenlijst met rekken voor een klant
     *
     * @return lijst van rekken die de klant zal bezoeken
     */
    public List<GameObject> createShoppingList() {
        List<GameObject> list = new LinkedList<GameObject>();
        int numberOfShelves = rand.nextInt(maxShoppingListSize) + 1;

        for (int i = 0; i < numberOfShelves; i++) {
            int targetNumber = rand.nextInt(Store.shelves.size());
            list.add(Store.shelves.get(targetNumber));
        }
        return list;

    }

    /**
     * werkt alle klanten bij en verwijdert degenen die klaar zijn met winkelen
     *
     * @param deltaTime verstreken tijd
     * @param borderPane het scherm waarop de klanten bewegen
     */
    public void updateCustomers(double deltaTime, BorderPane borderPane) {
        List<Customer> toKill = new ArrayList<>();

        for (Customer c : Store.customers) {
            c.update(deltaTime, borderPane);
            if (c.isDone()) {
                toKill.add(c);
            }
        }

        for (Customer c : toKill) {
            borderPane.getChildren().remove(c.stack);
            Store.customers.remove(c);
            Store.customerCounter.update();
        }
    }

    /**
     * controleert welke rekken moeten worden bijgevuld en geeft opdrachten aan beschikbare restockers
     */
    public void checkRestockableShelves() {
        Iterator<Shelve> iterator = restockingShelves.iterator();

        while (iterator.hasNext()) {
            Shelve shelve = iterator.next();

            for (Restocker restocker : Store.restockers) {
                if (restocker.getState() == WAITING_FOR_ORDER) {
                    System.out.println("Gave restocker order for " + shelve.objectName);
                    restocker.giveOrder(shelve);

                    iterator.remove(); // safely remove this shelf from list
                    break; // only one restocker per shelf
                }
            }
        }
    }

    /**
     * controleert of een rek al in de lijst van te restocken rekken staat
     *
     * @param shelve het rek dat gecontroleerd wordt
     * @return true als het rek al in de restock-lijst staat
     */
    public static Boolean isInRestockingShelves(Shelve shelve) {
        for (Shelve checkShelve : restockingShelves)
        {
            if (checkShelve.equals(shelve))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * werkt alle restockers bij zodat zij hun opdrachten kunnen uitvoeren
     */
    public void updateRestockers() {
        for (Restocker r : Store.restockers) {
            r.update();
        }
    }

    /**
     * geeft het maximum aantal klanten dat tegelijk aanwezig mag zijn
     *
     * @return maximum aantal klanten
     */
    public int getMaxCustomers() {
        return maxCustomers;
    }
}
