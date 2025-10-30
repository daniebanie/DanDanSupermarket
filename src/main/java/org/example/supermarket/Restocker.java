package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

/**
 * een medewerker die schappen bijvult in de supermarkt.
 * beweegt tussen opslag en schappen, pakt producten en vult de schappen aan.
 */
public class Restocker extends Person {

    /** huidige staat van de restocker */
    private CustomerState state = CustomerState.WALKING_TO_STORAGE;

    /** tijd in seconden om één product te pakken of plaatsen */
    private final double pickTime = 1.0;

    /** tijdstip waarop de huidige actie gestart is */
    private long stateStartTime;

    /** maximale voorraad die de restocker kan dragen */
    private int maxStock = 10;

    /** huidige voorraad bij de restocker */
    private int currentStock;

    /** het schap dat de restocker momenteel vult */
    private Shelve shelve;

    /**
     * mogelijke staten van de restocker tijdens zijn werk
     */
    public enum CustomerState {
        WALKING_TO_STORAGE,
        PICKING_BOX,
        WALKING_TO_SHELF,
        STOCKING_SHELF,
        WAITING_FOR_ORDER
    }

    /**
     * maakt een nieuwe restocker aan op de ingang van de supermarkt
     *
     * @param name naam van de restocker
     * @param borderPane het pane waarin de restocker weergegeven wordt
     */
    public Restocker(String name, BorderPane borderPane) {
        super(name, borderPane, Color.RED, Store.entrance.getX(), Store.entrance.getY(), 50, 50);
        healthbar = new Healthbar(stack, currentStock, maxStock, this.rectangle.getX(), this.rectangle.getY() - 20, 50);
        updateHealth();
        moveToStorage();
    }

    /** verplaatst de restocker naar de opslag */
    private void moveToStorage() {
        GameObject target = Store.storage;
        moveTo(target.getX(), target.getY());
        state = CustomerState.WALKING_TO_STORAGE;
    }

    /** verplaatst de restocker naar het toegewezen schap */
    private void moveToShelf() {
        GameObject target = shelve;
        moveTo(target.getX(), target.getY());
        state = CustomerState.WALKING_TO_SHELF;
        System.out.println("moving to shelf");
    }

    /** update de staat van de restocker en voert acties uit afhankelijk van de huidige staat */
    public void update() {
        switch (state) {
            case WALKING_TO_STORAGE:
                if (isAtDestination()) {
                    state = CustomerState.PICKING_BOX;
                    stateStartTime = System.currentTimeMillis();
                    System.out.println("is in storage");
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
                    System.out.println("is in shelf " + shelve.objectName);
                }
                break;

            case STOCKING_SHELF:
                if ((System.currentTimeMillis() - stateStartTime) / 100.0 > pickTime) {
                    stateStartTime = System.currentTimeMillis();
                    shelve.addProduct();
                    currentStock--;
                    updateHealth();
                    System.out.println("put product " + shelve.objectName);

                    if (currentStock == 0) {
                        moveToStorage();
                        return;
                    }
                }
                if (shelve.getCurrentCapacity() == shelve.getMaxCapacity()) {
                    System.out.println("shelve " + shelve.objectName + " is full");
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

    /** @return de huidige staat van de restocker */
    public CustomerState getState() {
        return state;
    }

    /**
     * wijst een schap toe aan de restocker en laat hem er naartoe bewegen
     *
     * @param shelve het schap dat bijgevuld moet worden
     */
    public void giveOrder(Shelve shelve) {
        this.shelve = shelve;
        this.moveToShelf();
        state = CustomerState.WALKING_TO_SHELF;
    }

    /** werkt de healthbar bij op basis van de huidige voorraad van de restocker */
    private void updateHealth() {
        this.healthbar.updateHealthBar(currentStock, Healthbar.ObjectType.RESTOCKER);
    }
}
