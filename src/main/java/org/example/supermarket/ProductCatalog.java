package org.example.supermarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductCatalog {
    private List<Product> catalog;
    private Random random;


    public ProductCatalog() {
        this.catalog = new ArrayList<>();
        this.random = new Random();
        // Wordt statisch geïnitialiseerd bij het opstarten
        initializeCatalog();
    }


    // Lijst van producten
    private void initializeCatalog() {

        catalog.add(new DrinkProduct("Heineken", 2.50, "Bier"));
        catalog.add(new DrinkProduct("Coca Cola", 1.99, "Frisdrank"));
        catalog.add(new DrinkProduct("Spa Rood", 0.89, "Water"));
        catalog.add(new DrinkProduct("Rode Wijn", 7.99, "Wijn"));
        catalog.add(new DrinkProduct("Melk", 1.29, "Zuivel"));
        catalog.add(new DrinkProduct("Jus d'Orange", 2.49, "Sap"));

        catalog.add(new FrozenProduct("Ben & Jerry's Cookie Dough", 5.99, "IJs"));
        catalog.add(new FrozenProduct("Iglo Vissticks", 3.49, "Diepvries"));
        catalog.add(new FrozenProduct("Pizza Margherita", 2.99, "Diepvries"));
        catalog.add(new FrozenProduct("Friet", 1.79, "Diepvries"));
        catalog.add(new FrozenProduct("Magnum Classic", 3.99, "IJs"));

        catalog.add(new GeneralProduct("Lays Paprika", 1.99, "Snacks"));
        catalog.add(new GeneralProduct("Brood Wit", 1.49, "Bakkerij"));
        catalog.add(new GeneralProduct("Toiletpapier", 4.99, "Huishouden"));
        catalog.add(new GeneralProduct("Appels", 2.29, "Groente & Fruit"));
        catalog.add(new GeneralProduct("Koffie", 3.99, "Koffie & Thee"));
        catalog.add(new GeneralProduct("Pasta", 0.99, "Pasta & Rijst"));
        catalog.add(new GeneralProduct("Kaas", 3.49, "Zuivel"));
        catalog.add(new GeneralProduct("Chips Naturel", 1.79, "Snacks"));
        catalog.add(new GeneralProduct("Bananen", 1.89, "Groente & Fruit"));
        catalog.add(new GeneralProduct("Rijst", 2.29, "Pasta & Rijst"));
    }

    // Haalt alle producten op
    public List<Product> getAllProducts() {
        return new ArrayList<>(catalog);
    }

    // Zoekt product op met bepaalde naam
    public Product getProductByName(String name) {
        for (Product p : catalog) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Geeft lijst terug van alle producten die tot een bepaalde categorie horen
    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : catalog) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // Kiest willekeurig product uit catalogus
    public Product getRandomProduct() {
        int index = random.nextInt(catalog.size());
        return catalog.get(index);
    }

    // Voegt een product toe aan de catalogus
    public void addProduct(Product product) {
        catalog.add(product);
    }

    // Geeft het aantal producten in de catalogus terug
    public int getTotalProducts() {
        return catalog.size();
    }
}