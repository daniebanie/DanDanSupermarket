package org.example.supermarket;

import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

import java.util.LinkedList;
import java.util.Queue;

public class Checkout extends GameObject{
    //wachtrij voor de kassa
    private Queue<Customer> line = new LinkedList<>();

    public Checkout(String itemName, BorderPane borderPane, double x, double y)
    {
        super(borderPane, itemName, Color.GRAY, x, y, 100, 70);
    }
    //voeg klant toe aan het einde van de rij
    public void addToLine(Customer customer) {
        line.add(customer);
    }
    //verwijder klant uit de rij
    public void removeFromLine(Customer customer) {
        line.remove(customer);
    }
    //vind de positie van een klant in de rij (0 = eerste)
    public int getLinePosition(Customer customer) {
        int position = 0;
        for (Customer c : line) {
            if (c == customer) {
                return position;
            }
            position++;
        }
        return -1; // Niet in rij
    }
    //kijk wie de eerste in rij is
    public Customer getFirstInLine() {
        return line.peek();
    }

    // huidige lengte van de rij
    public int getLineSize() {
        return line.size();
    }
}
