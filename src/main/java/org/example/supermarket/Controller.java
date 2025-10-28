package org.example.supermarket;

import java.util.*;

import javafx.scene.layout.BorderPane;

import static org.example.supermarket.Restocker.CustomerState.WAITING_FOR_ORDER;

public class Controller {

    private double spawnTime = 2.0;
    private int maxCustomers = 20;
    private int maxShoppingListSize = 10;
    private long spawnStartTime = System.currentTimeMillis();
    public Random rand = new Random();
    public static List<Shelve>  restockingShelves = new LinkedList<>();

    public Controller(BorderPane borderPane) {

    }

    public void spawnCustomers(BorderPane borderPane)
    {
        if (Store.customers.size() < maxCustomers && (System.currentTimeMillis() - spawnStartTime) / 1000.0 > spawnTime)
        {
            Store.customers.add(( new Customer("dan", borderPane, createShoppingList())));
            Store.customerCounter.update();
            spawnStartTime = System.currentTimeMillis();
        }
    }

    public List<GameObject> createShoppingList() {
        List<GameObject> list = new LinkedList<GameObject>();
        int numberOfShelves = rand.nextInt(maxShoppingListSize) + 1;

        for (int i = 0; i < numberOfShelves; i++) {
            int targetNumber = rand.nextInt(Store.shelves.size());
            list.add(Store.shelves.get(targetNumber));
        }
        return list;

    }

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

    public static Boolean isInRestockingShelves(Shelve shelve)
    {
        for (Shelve checkShelve : restockingShelves)
        {
            if (checkShelve.equals(shelve))
            {
                return true;
            }
        }
        return false;
    }

    public int getMaxCustomers() {
        return maxCustomers;
    }
}
