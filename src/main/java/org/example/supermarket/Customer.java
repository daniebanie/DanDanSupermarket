package org.example.supermarket;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Customer extends Person {

    private Queue<GameObject> shoppingList = new LinkedList<GameObject>();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private CustomerState state = CustomerState.WALKING_TO_SHELF;
    private final double pickTime = 2.0;
    private long stateStartTime;

    public Customer(String name, BorderPane borderPane, List<GameObject> shelves) {
        super(name, borderPane, Color.GREEN, Store.entrance.getX(), Store.entrance.getY(), 50, 50);
        shoppingList.addAll(shelves);
        moveToNextShelf();
    }

    public enum CustomerState {
        WALKING_TO_SHELF,
        PICKING_ITEM,
        WALKING_TO_CHECKOUT,
        WAITING_IN_LINE,
        CHECKING_OUT,
        WALKING_TO_EXIT,
        DONE
    }

    private void moveToNextShelf() {
        if (!shoppingList.isEmpty()) {
            GameObject target = shoppingList.peek();
            moveTo(target.getX(), target.getY());
            state = CustomerState.WALKING_TO_SHELF;
        }
        else{
            int targetNumber = rand.nextInt(Store.checkouts.size());
            GameObject target = Store.checkouts.get(targetNumber);
            moveTo(target.getX(), target.getY());
            state = CustomerState.WALKING_TO_CHECKOUT;
        }
        stateStartTime = System.currentTimeMillis();
    }

    public void update(double deltaTime, BorderPane borderPane) {
        switch (state) {
            case WALKING_TO_SHELF:
                if (isAtDestination()) {
                    state = CustomerState.PICKING_ITEM;
                    stateStartTime = System.currentTimeMillis();
                }
                break;
            case PICKING_ITEM:
                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > pickTime) {
                    addProductToCart(shoppingList.peek().getName());
                    shoppingList.poll(); // done picking at this shelf
                    moveToNextShelf();
                }
                break;
            case WALKING_TO_CHECKOUT:
                if (isAtDestination()) {
                    state = CustomerState.WAITING_IN_LINE;
                }
                break;
            case WAITING_IN_LINE:
                if (isAtDestination()) {
                    state = CustomerState.CHECKING_OUT;
                    stateStartTime = System.currentTimeMillis();
                }
                break;
            case CHECKING_OUT:
                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > shoppingCart.items.size()) {
                    moveTo(Store.exit.getX(), Store.exit.getY());
                    Store.pay(shoppingCart.calculateTotal());
                    System.out.println(shoppingCart.items.size() + "/" +  shoppingCart.calculateTotal() + "/" + Store.storeMoney);
                    state = CustomerState.WALKING_TO_EXIT;
                }
                break;
            case WALKING_TO_EXIT:
                if (isAtDestination()) {
                    state = CustomerState.DONE;
                }
                break;
            case DONE:
        }
    }

    private void addProductToCart(String productName) {
        Product product = ProductCatalog.getProductByName(productName);
        shoppingCart.addItem(product);
    }
    private boolean isAtDestination() {
        double dx = rectangle.getX() - targetX;
        double dy = rectangle.getY() - targetY;
        return Math.hypot(dx, dy) < 5;
    }

    public boolean isDone() {
        return state == CustomerState.DONE;
    }
}