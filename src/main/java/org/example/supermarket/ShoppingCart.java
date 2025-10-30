package org.example.supermarket;

import java.util.ArrayList;

/**
 * een winkelwagentje van een klant.
 * slaat producten op die de klant wil kopen en houdt de totale prijs bij.
 */
public class ShoppingCart {

    /** lijst van producten in het winkelwagentje */
    public ArrayList<Product> items = new ArrayList<>();

    /** totale prijs van alle producten in het wagentje */
    public double totalPrice = 0;

    /** maakt een leeg winkelwagentje aan */
    public ShoppingCart() {
    }

    /**
     * voegt een product toe aan het winkelwagentje
     *
     * @param item het product dat toegevoegd wordt
     */
    public void addItem(Product item) {
        this.items.add(item);
    }

    /**
     * berekent de totale prijs van alle producten in het winkelwagentje
     *
     * @return de totale prijs
     */
    public double calculateTotal() {
        totalPrice = 0;
        for (Product item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
