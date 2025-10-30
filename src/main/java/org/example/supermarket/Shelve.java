package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

/**
 * een schap in de supermarkt.
 * elk schap heeft een product, maximale capaciteit en huidige capaciteit.
 * houdt ook een healthbar bij om de voorraad visueel te laten zien.
 */
public class Shelve extends GameObject {

    /** het product dat in het schap ligt */
    private Product product;

    /** maximale capaciteit van het schap */
    private int maxCapacity;

    /** huidige hoeveelheid producten in het schap */
    private int currentCapacity;

    /**
     * maakt een nieuw schap aan op de opgegeven positie
     *
     * @param productName naam van het product in het schap
     * @param borderPane het pane waarin het schap weergegeven wordt
     * @param x de x-positie van het schap
     * @param y de y-positie van het schap
     * @param maxCapacity maximale capaciteit van het schap
     */
    public Shelve(String productName, BorderPane borderPane, double x, double y, int maxCapacity) {
        super(borderPane, productName, Color.BROWN, x, y, 100, 70);
        this.product = product;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;

        this.healthbar = new Healthbar(stack, currentCapacity, maxCapacity, x, y, 100);
    }

    /**
     * verwijdert één product uit het schap en werkt de healthbar bij
     */
    public void removeProduct() {
        if (currentCapacity > 0) {
            currentCapacity--;
            this.healthbar.updateHealthBar(currentCapacity, Healthbar.ObjectType.SHELF);
        }
    }

    /**
     * voegt één product toe aan het schap en werkt de healthbar bij
     */
    public void addProduct() {
        if (currentCapacity < maxCapacity) {
            currentCapacity++;
            this.healthbar.updateHealthBar(currentCapacity, Healthbar.ObjectType.SHELF);
        }
    }

    /** @return de huidige capaciteit van het schap */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /** @return de maximale capaciteit van het schap */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * controleert of het schap bijgevuld moet worden
     *
     * @return true als huidige capaciteit <= 80% van maximale capaciteit, anders false
     */
    public Boolean needsRestock() {
        return (double) currentCapacity / maxCapacity <= 0.8;
    }

    /** @return true als het schap vol is, anders false */
    public Boolean isFull() {
        return currentCapacity == maxCapacity;
    }
}
