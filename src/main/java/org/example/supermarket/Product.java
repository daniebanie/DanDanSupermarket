package org.example.supermarket;

/**
 * abstracte klasse die een algemeen product in de supermarkt voorstelt.
 * bevat informatie zoals naam, prijs en categorie.
 */
abstract class Product {

    /** naam van het product */
    protected String name;

    /** prijs van het product */
    protected double price;

    /** categorie van het product */
    protected String category;

    /**
     * maakt een nieuw product aan met naam, prijs en categorie
     *
     * @param name de naam van het product
     * @param price de prijs van het product
     * @param category de categorie van het product
     */
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /** @return de naam van het product */
    public String getName() {
        return name;
    }

    /** @return de prijs van het product */
    public double getPrice() {
        return price;
    }

    /** @return de categorie van het product */
    public String getCategory() {
        return category;
    }

    /**
     * stelt de naam van het product in
     *
     * @param name de nieuwe naam
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * stelt de prijs van het product in
     *
     * @param price de nieuwe prijs
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * stelt de categorie van het product in
     *
     * @param category de nieuwe categorie
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
