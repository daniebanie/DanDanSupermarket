package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.Queue;


public class Checkout extends GameObject{
    /**
     * stelt een kassa voor met een wachtrij voor klanten
     */
    private Queue<Customer> line = new LinkedList<>();

    /**
     * maakt een nieuwe kassa aan
     *
     * @param itemName naam van de kassa
     * @param borderPane het paneel waarop de kassa wordt geplaatst
     * @param x x-positie
     * @param y y-positie
     */
    public Checkout(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.GRAY, x, y, 100, 70);
    }

    /**
     * voegt een klant toe aan het einde van de wachtrij
     *
     * @param customer de klant die in de rij gaat staan
     */
    public void addToLine(Customer customer) {
        line.add(customer);
    }

    /**
     * verwijdert een klant uit de wachtrij
     *
     * @param customer de klant die verwijderd wordt
     */
    public void removeFromLine(Customer customer) {
        line.remove(customer);
    }

    /**
     * bepaalt de positie van een klant in de rij
     *
     * @param customer de klant waarvan de positie wordt opgevraagd
     * @return positie in de rij (0 = eerste), -1 als niet aanwezig
     */
    public int getLinePosition(Customer customer) {
        int position = 0;
        for (Customer c : line) {
            if (c == customer) {
                return position;
            }
            position++;
        }
        return -1; // niet in rij
    }

    /**
     * geeft de klant die als eerste in de rij staat
     *
     * @return eerste klant of null als de rij leeg is
     */

    public Customer getFirstInLine() {
        return line.peek();
    }


    /**
     * geeft het aantal klanten in de rij
     *
     * @return lengte van de rij
     */
    public int getLineSize() {
        return line.size();
    }
}
