package org.example.supermarket;

/**
 * een bevroren product in de supermarkt
 */
public class FrozenProduct extends Product {

    /**
     * maakt een nieuw bevroren product aan
     *
     * @param name naam van het product
     * @param price prijs van het product
     * @param category categorie waartoe het product behoort
     */
    public FrozenProduct(String name, double price, String category) {
        super(name, price, category);
    }

    /**
     * geeft een tekst van het bevroren product terug
     *
     * @return string met productinformatie
     */
    @Override
    public String toString() {
        return "frozenproduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}
