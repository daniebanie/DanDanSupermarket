package org.example.supermarket;

/**
 * een algemeen product in de supermarkt,
 * zonder speciale eigenschappen zoals gekoeld of bevroren.
 */
public class GeneralProduct extends Product {

    /**
     * maakt een nieuw algemeen product aan
     *
     * @param name naam van het product
     * @param price prijs van het product
     * @param category categorie waartoe het product behoort
     */
    public GeneralProduct(String name, double price, String category) {
        super(name, price, category);
    }

    /**
     * geeft een tekstuele representatie van het product terug
     *
     * @return string met productinformatie
     */
    @Override
    public String toString() {
        return "generalproduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}
