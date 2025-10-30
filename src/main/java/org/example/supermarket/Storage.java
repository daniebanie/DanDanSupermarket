package org.example.supermarket;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * de opslagruimte van de supermarkt.
 * wordt gebruikt om producten tijdelijk op te slaan voordat ze in de schappen komen.
 */
public class Storage extends GameObject {

    /**
     * maakt een nieuwe opslagruimte aan op de opgegeven positie
     *
     * @param borderPane het pane waarin de opslag weergegeven wordt
     * @param x de x-positie van de opslag
     * @param y de y-positie van de opslag
     */
    public Storage(BorderPane borderPane, Double x, Double y) {
        super(borderPane, "storage", Color.YELLOW, x, y, 200, 100);
    }
}
