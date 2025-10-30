package org.example.supermarket;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Customer extends Person {
    /**
     * een klant in de supermarkt die automatisch producten verzamelt, afrekent en vertrekt
     */
    private Queue<GameObject> shoppingList = new LinkedList<GameObject>();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private CustomerState state = CustomerState.WALKING_TO_SHELF;
    private final double pickTime = 1.0;
    private long stateStartTime;
    private Checkout myCheckout;  // De gekozen kassa voor deze klant (voor rij-beheer)

    /**
     * maakt een nieuwe klant aan en vult zijn winkelroute met rekken
     *
     * @param name de naam van de klant
     * @param borderPane het paneel waarop de klant wordt weergegeven
     * @param shelves de lijst met rekken die de klant bezoekt
     */
    public Customer(String name, BorderPane borderPane, List<GameObject> shelves) {
        super(name, borderPane, Color.GREEN, Store.entrance.getX(), Store.entrance.getY(), 50, 50);
        shoppingList.addAll(shelves);
        healthbar = new Healthbar(stack, shoppingCart.items.size(), shoppingList.size(), this.rectangle.getX(), this.rectangle.getY() - 20, 50);
        updateHealth();
        moveToNextShelf();
    }

    /**
     * beschrijft de mogelijke toestanden van een klant
     */
    public enum CustomerState {
        WALKING_TO_SHELF,
        PICKING_ITEM,
        WALKING_TO_CHECKOUT,
        WAITING_IN_LINE,
        CHECKING_OUT,
        WALKING_TO_EXIT,
        DONE
    }

    /**
     * bepaalt het volgende doel van de klant, rek of kassa
     */
    private void moveToNextShelf() {
        if (!shoppingList.isEmpty()) {
            GameObject target = shoppingList.peek();
            moveTo(target.stack.getLayoutX(), target.stack.getLayoutY());
            state = CustomerState.WALKING_TO_SHELF;
        } else {
            int targetNumber = rand.nextInt(Store.checkouts.size());
            Checkout selectedCheckout = (Checkout) Store.checkouts.get(targetNumber);
            myCheckout = selectedCheckout;

            // bereken positie horizontaal naar rechts vanaf de kassa
            int currentLineSize = myCheckout.getLineSize();
            double targetX = myCheckout.getX() + myCheckout.rectangle.getWidth() + (currentLineSize + 1) * (this.rectangle.getWidth() + 10);
            double targetY = myCheckout.getY();
            moveTo(targetX, targetY);
            state = CustomerState.WALKING_TO_CHECKOUT;
        }
        stateStartTime = System.currentTimeMillis();
    }

    /**
     * voert logica uit per frame: bewegen, producten pakken, wachten en afrekenen
     *
     * @param deltaTime verstreken tijd sinds laatste frame
     * @param borderPane het speelveld waarop de klant beweegt
     */
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
                        if (currentShelve.needsRestock() && !Controller.isInRestockingShelves(currentShelve)) {
                            Controller.restockingShelves.add(currentShelve);
                            System.out.println("Product " + currentShelve.getName() + " needs restock");
                        }
                    }
                    shoppingList.poll();
                    moveToNextShelf();
                }
                break;
            case WALKING_TO_CHECKOUT:
                if (isAtDestination()) {
                    myCheckout.addToLine(this);
                    state = CustomerState.WAITING_IN_LINE;
                }
                break;
            case WAITING_IN_LINE:
                // Horizontale rij-logica: beweeg naar rechts vanaf de kassa
                int myPosition = myCheckout.getLinePosition(this);
                if (myPosition == 0) {
                    // Eerste in rij: beweeg naar de kassa (direct rechts van de kassa)
                    double frontX = myCheckout.getX() + myCheckout.rectangle.getWidth() + 10;
                    double frontY = myCheckout.getY();
                    if (Math.abs(getX() - frontX) > 1 || Math.abs(getY() - frontY) > 1) {
                        moveTo(frontX, frontY);
                    } else {
                        state = CustomerState.CHECKING_OUT;
                        stateStartTime = System.currentTimeMillis();
                    }
                } else {
                    // Niet eerste: update positie in horizontale rij
                    double targetX = myCheckout.getX() + myCheckout.rectangle.getWidth() + (myPosition + 1) * (this.rectangle.getWidth() + 10);
                    double targetY = myCheckout.getY();
                    if (Math.abs(getX() - targetX) > 1 || Math.abs(getY() - targetY) > 1) {
                        moveTo(targetX, targetY);
                    }
                }
                break;
            case CHECKING_OUT:
                if ((System.currentTimeMillis() - stateStartTime) / 1000.0 > shoppingCart.items.size()) {
                    myCheckout.removeFromLine(this);
                    moveTo(Store.exit.getX(), Store.exit.getY());
                    Store.pay(shoppingCart.calculateTotal());
                    state = CustomerState.WALKING_TO_EXIT;
                }
                break;
            case WALKING_TO_EXIT:
                if (isAtDestination()) {
                    state = CustomerState.DONE;
                }
                break;
            case DONE:
                break;
        }
    }

    /**
     * werkt de healthbar bij op basis van de gevulde winkelwagen
     */
    private void updateHealth() {
        this.healthbar.updateHealthBar(shoppingCart.items.size(), Healthbar.ObjectType.CUSTOMER);
    }

    /**
     * voegt een product toe aan de winkelwagen
     *
     * @param productName de naam van het product
     */
    private void addProductToCart(String productName) {
        Product product = ProductCatalog.getProductByName(productName);
        shoppingCart.addItem(product);
    }

    /**
     * controleert of de klant klaar is met winkelen
     *
     * @return true als de klant de winkel heeft verlaten
     */
    public boolean isDone() {
        return state == CustomerState.DONE;
    }
}