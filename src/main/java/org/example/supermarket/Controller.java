package org.example.supermarket;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import java.util.Random;

public class Controller {

    private double spawnTime = 2.0;
    private long spawnStartTime = System.currentTimeMillis();
    public Random rand = new Random();

    public Controller(BorderPane borderPane) {

    }

    public void spawnCustomers(BorderPane borderPane)
    {
        if (Store.customers.size() < 5 && (System.currentTimeMillis() - spawnStartTime) / 1000.0 > spawnTime)
        {
            Store.customers.add(( new Customer("dan", borderPane, createShoppingList())));
            spawnStartTime = System.currentTimeMillis();
        }
    }

    public List<GameObject> createShoppingList() {
        List<GameObject> list = new LinkedList<GameObject>();
        int numberOfShelves = rand.nextInt(3) + 1;

        for (int i = 0; i < numberOfShelves; i++) {
            int targetNumber = rand.nextInt(Store.shelves.size());
            list.add(Store.shelves.get(targetNumber));
        }
        return list;

    }

}