package org.example.supermarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * de catalogus van producten in de supermarkt.
 * houdt een lijst bij van alle producten en biedt methodes om producten op te halen,
 * te zoeken op naam, categorie of willekeurig te kiezen.
 */
public class ProductCatalog {

    /** lijst van alle producten in de catalogus */
    private static List<Product> catalog;

    /** random generator voor willekeurige producten */
    private Random random;

    /** maakt een nieuwe productcatalogus en initialiseert de lijst met standaardproducten */
    public ProductCatalog() {
        this.catalog = new ArrayList<>();
        this.random = new Random();
        initializeCatalog();
    }

    /** vult de catalogus met een vaste set van producten bij het opstarten */
    private void initializeCatalog() {
        catalog.add(new DrinkProduct("heineken", 2.50, "bier"));
        catalog.add(new DrinkProduct("coca cola", 1.99, "frisdrank"));
        catalog.add(new DrinkProduct("spa rood", 0.89, "water"));
        catalog.add(new DrinkProduct("rode wijn", 7.99, "wijn"));
        catalog.add(new DrinkProduct("melk", 1.29, "zuivel"));
        catalog.add(new DrinkProduct("jus d'orange", 2.49, "sap"));

        catalog.add(new FrozenProduct("ben & jerry's cookie dough", 5.99, "ijs"));
        catalog.add(new FrozenProduct("iglo vissticks", 3.49, "diepvries"));
        catalog.add(new FrozenProduct("pizza margherita", 2.99, "diepvries"));
        catalog.add(new FrozenProduct("friet", 1.79, "diepvries"));
        catalog.add(new FrozenProduct("magnum classic", 3.99, "ijs"));

        catalog.add(new GeneralProduct("lays paprika", 1.99, "snacks"));
        catalog.add(new GeneralProduct("brood wit", 1.49, "bakkerij"));
        catalog.add(new GeneralProduct("toiletpapier", 4.99, "huishouden"));
        catalog.add(new GeneralProduct("appels", 2.29, "groente & fruit"));
        catalog.add(new GeneralProduct("koffie", 3.99, "koffie & thee"));
        catalog.add(new GeneralProduct("pasta", 0.99, "pasta & rijst"));
        catalog.add(new GeneralProduct("kaas", 3.49, "zuivel"));
        catalog.add(new GeneralProduct("chips naturel", 1.79, "snacks"));
        catalog.add(new GeneralProduct("bananen", 1.89, "groente & fruit"));
        catalog.add(new GeneralProduct("rijst", 2.29, "pasta & rijst"));
    }

    /**
     * @return een nieuwe lijst met alle producten in de catalogus
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(catalog);
    }

    /**
     * zoekt een product op basis van de naam
     *
     * @param name de naam van het product
     * @return het product als het gevonden is, anders null
     */
    public static Product getProductByName(String name) {
        for (Product p : catalog) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * geeft een lijst terug van alle producten die tot een bepaalde categorie horen
     *
     * @param category de categorie om te filteren
     * @return lijst van producten in de opgegeven categorie
     */
    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : catalog) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * kiest een willekeurig product uit de catalogus
     *
     * @return een random product
     */
    public Product getRandomProduct() {
        int index = random.nextInt(catalog.size());
        return catalog.get(index);
    }

    /**
     * voegt een nieuw product toe aan de catalogus
     *
     * @param product het product dat toegevoegd wordt
     */
    public void addProduct(Product product) {
        catalog.add(product);
    }

    /**
     * @return het totale aantal producten in de catalogus
     */
    public int getTotalProducts() {
        return catalog.size();
    }
}
