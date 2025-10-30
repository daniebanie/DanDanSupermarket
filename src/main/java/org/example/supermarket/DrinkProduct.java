package org.example.supermarket;

/**
 * stelt een drankproduct voor
 */
public class DrinkProduct extends Product {

    /**
     * maakt een nieuw drankproduct aan
     *
     * @param name de naam van het product
     * @param price de prijs van het product
     * @param category de categorie van het product
     */
    public DrinkProduct(String name, double price, String category) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return "DrinkProduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}

