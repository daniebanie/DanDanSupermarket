package org.example.supermarket;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Restocker extends Person {
    private CustomerState state = CustomerState.WALKING_TO_STORAGE;
    private final double pickTime = 1.0;
    private long stateStartTime;
    private int maxStock = 10;
    private int currentStock;
    private Shelve shelve;

    public Restocker(String name, BorderPane borderPane) {
        super(name, borderPane, Color.RED, Store.entrance.getX(), Store.entrance.getY(), 50, 50);
        healthbar = new Healthbar(stack, currentStock, maxStock, this.rectangle.getX(), this.rectangle.getY() - 20, 50);
        updateHealth();
        moveToStorage();
    }

    public enum CustomerState {
        WALKING_TO_STORAGE,
        PICKING_BOX,
        WALKING_TO_SHELF,
        STOCKING_SHELF,
        WAITING_FOR_ORDER
    }


    private void moveToStorage() {
        GameObject target = Store.storage;
        moveTo(target.getX(), target.getY());
        state = Restocker.CustomerState.WALKING_TO_STORAGE;
    }

    private void moveToShelf() {
        GameObject target = shelve;
        moveTo(target.getX(), target.getY());
        state = Restocker.CustomerState.WALKING_TO_SHELF;
        System.out.println("Moving to shelf");

    }

    public void update() {
        switch (state) {
            case WALKING_TO_STORAGE:
                if (isAtDestination()) {
                    state = CustomerState.PICKING_BOX;
                    stateStartTime = System.currentTimeMillis();
                    System.out.println("Is in storage");
                }
                break;

            case PICKING_BOX:
                if ((System.currentTimeMillis() - stateStartTime) / 100.0 > pickTime) {
                    currentStock++;
                    updateHealth();
                }
                if (currentStock == maxStock) {
                    if (shelve != null) {
                        moveToShelf();
                    } else {
                        state = CustomerState.WAITING_FOR_ORDER;
                    }
                }
                break;

            case WALKING_TO_SHELF:
                if (isAtDestination()) {
                    state = CustomerState.STOCKING_SHELF;
                    stateStartTime = System.currentTimeMillis();
                    System.out.println("Is in shelf " + shelve.objectName);
                }
                break;

            case STOCKING_SHELF:
                if ((System.currentTimeMillis() - stateStartTime) / 100.0 > pickTime) {
                    stateStartTime = System.currentTimeMillis();
                    shelve.addProduct();
                    currentStock--;
                    updateHealth();
                    System.out.println("PutProduct " + shelve.objectName);

                    if (currentStock == 0) {
                        moveToStorage();
                        return;
                    }
                }
                if (shelve.getCurrentCapacity() == shelve.getMaxCapacity()) {
                    System.out.println("Shelve " + shelve.objectName + " is full");
                    if (currentStock <= maxStock / 2) {
                        shelve = null;
                        moveToStorage();
                    } else {
                        state = CustomerState.WAITING_FOR_ORDER;
                    }
                    return;
                }
                break;

            case WAITING_FOR_ORDER:
                break;
        }
    }



//    public void update(double deltaTime, BorderPane borderPane) {
//        switch (state) {
//            case WALKING_TO_SHELF:
//                if (isAtDestination()) {
//                    state = CustomerState.PICKING_ITEM;
//                    stateStartTime = System.currentTimeMillis();
//                }
//                break;
//            case PICKING_ITEM:
//                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > pickTime) {
//                    Shelve currentShelve = (Shelve) shoppingList.peek();
//                    if (currentShelve.getCurrentCapacity() > 0) {
//                        addProductToCart(shoppingList.peek().getName());
//                        currentShelve.removeProduct();
//                    }
//                    shoppingList.poll(); // done picking at this shelf
//                    moveToNextShelf();
//                }
//                break;
//            case WALKING_TO_CHECKOUT:
//                if (isAtDestination()) {
//                    state = CustomerState.WAITING_IN_LINE;
//                }
//                break;
//            case WAITING_IN_LINE:
//                if (isAtDestination()) {
//                    state = CustomerState.CHECKING_OUT;
//                    stateStartTime = System.currentTimeMillis();
//                }
//                break;
//            case CHECKING_OUT:
//                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > shoppingCart.items.size()) {
//                    moveTo(Store.exit.getX(), Store.exit.getY());
//                    Store.pay(shoppingCart.calculateTotal());
//                    System.out.println(shoppingCart.items.size() + "/" +  shoppingCart.calculateTotal() + "/" + Store.storeMoney);
//                    state = CustomerState.WALKING_TO_EXIT;
//                }
//                break;
//            case WALKING_TO_EXIT:
//                if (isAtDestination()) {
//                    state = CustomerState.DONE;
//                }
//                break;
//            case DONE:
//        }
//    }

    public CustomerState getState() {
        return state;
    }
    public void giveOrder(Shelve shelve) {
        this.shelve = shelve;
        this.moveToShelf();
        state = CustomerState.WALKING_TO_SHELF;
    }

    private void updateHealth() {
        this.healthbar.updateHealthBar(currentStock, Healthbar.ObjectType.RESTOCKER);
    }
}