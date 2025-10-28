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
    private final double pickTime = 1.0;
    private long stateStartTime;

    public Customer(String name, BorderPane borderPane, List<GameObject> shelves) {
        super(name, borderPane, Color.GREEN, Store.entrance.getX(), Store.entrance.getY(), 50, 50);
        shoppingList.addAll(shelves);
        healthbar = new Healthbar(stack, shoppingCart.items.size(), shoppingList.size(), this.rectangle.getX(), this.rectangle.getY() - 20, 50);
        updateHealth();
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
            moveTo(target.stack.getLayoutX(), target.stack.getLayoutY());
            state = CustomerState.WALKING_TO_SHELF;
        }
        else{
            int targetNumber = rand.nextInt(Store.checkouts.size());
            GameObject target = Store.checkouts.get(targetNumber);
            moveTo(target.getX(), target.getY() - this.rectangle.getHeight() - 5);
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
                if (shoppingList.peek() instanceof Shelve) {
                    int stock = ((Shelve) shoppingList.peek()).getCurrentCapacity();
                    if (stock == 0) {
                        shoppingList.poll();
                        moveToNextShelf();
                    }
                }
                break;
            case PICKING_ITEM:
                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > pickTime) {
                    Shelve currentShelve = (Shelve) shoppingList.peek();
                    if (currentShelve.getCurrentCapacity() > 0) {
                        addProductToCart(shoppingList.peek().getName());
                        updateHealth();
                        currentShelve.removeProduct();
                        if (currentShelve.needsRestock() &! Controller.isInRestockingShelves(currentShelve)) {
                            Controller.restockingShelves.add(currentShelve);
                            System.out.println("Product " + currentShelve.getName() + " needs restock");
                        }
                    }
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
//                    System.out.println(shoppingCart.items.size() + "/" +  shoppingCart.calculateTotal() + "/" + Store.storeMoney);
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

    private void updateHealth() {
        this.healthbar.updateHealthBar(shoppingCart.items.size(), Healthbar.ObjectType.CUSTOMER);
    }
    private void addProductToCart(String productName) {
        Product product = ProductCatalog.getProductByName(productName);
        shoppingCart.addItem(product);
    }


    public boolean isDone() {
        return state == CustomerState.DONE;
    }
}