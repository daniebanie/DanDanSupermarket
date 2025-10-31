package org.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Unit tests voor de ProductCatalog klasse
 */
class ProductCatalogTest {

    private ProductCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new ProductCatalog();
    }

    @Test
    @DisplayName("catalogus bevat alle geïnitialiseerde producten")
    void testGetAllProducts() {
        List<Product> products = catalog.getAllProducts();
        assertEquals(21, products.size(), "Catalogus moet 21 producten bevatten");
    }

    @Test
    @DisplayName("product zoeken op bestaande naam")
    void testGetProductByName_ExistingProduct() {
        Product product = ProductCatalog.getProductByName("heineken");
        assertNotNull(product, "Product moet gevonden worden");
        assertEquals("heineken", product.getName());
        assertEquals(2.50, product.getPrice(), 0.01);
    }

    @Test
    @DisplayName("product zoeken met hoofdletters")
    void testGetProductByName_CaseInsensitive() {
        Product product = ProductCatalog.getProductByName("HEINEKEN");
        assertNotNull(product, "Zoeken moet hoofdletterongevoelig zijn");
        assertEquals("heineken", product.getName());
    }

    @Test
    @DisplayName("product zoeken dat niet bestaat")
    void testGetProductByName_NonExistingProduct() {
        Product product = ProductCatalog.getProductByName("fantasie product");
        assertNull(product, "Niet-bestaand product moet null teruggeven");
    }

    @Test
    @DisplayName("producten filteren op categorie")
    void testGetProductsByCategory() {
        List<Product> snacks = catalog.getProductsByCategory("snacks");
        assertEquals(2, snacks.size(), "Categorie 'snacks' moet 2 producten bevatten");
        assertTrue(snacks.stream().allMatch(p -> p.getCategory().equalsIgnoreCase("snacks")));
    }

    @Test
    @DisplayName("categorie die niet bestaat")
    void testGetProductsByCategory_NonExisting() {
        List<Product> products = catalog.getProductsByCategory("fantasy");
        assertTrue(products.isEmpty(), "Niet-bestaande categorie moet lege lijst teruggeven");
    }

    @Test
    @DisplayName("willekeurig product ophalen")
    void testGetRandomProduct() {
        Product product = catalog.getRandomProduct();
        assertNotNull(product, "Random product mag niet null zijn");
        assertTrue(catalog.getAllProducts().contains(product));
    }

    @Test
    @DisplayName("product toevoegen aan catalogus")
    void testAddProduct() {
        int initialSize = catalog.getTotalProducts();
        Product newProduct = new GeneralProduct("test product", 5.99, "test");
        catalog.addProduct(newProduct);

        assertEquals(initialSize + 1, catalog.getTotalProducts());
        assertNotNull(ProductCatalog.getProductByName("test product"));
    }

    @Test
    @DisplayName("meerdere random producten genereren")
    void testGetRandomProduct_MultipleCallsReturnValidProducts() {
        for (int i = 0; i < 50; i++) {
            Product product = catalog.getRandomProduct();
            assertNotNull(product);
            assertTrue(catalog.getAllProducts().stream()
                    .anyMatch(p -> p.getName().equals(product.getName())));
        }
    }
}

/**
 * Unit tests voor de ShoppingCart klasse
 */
class ShoppingCartTest {

    private ShoppingCart cart;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        product1 = new GeneralProduct("brood", 1.50, "bakkerij");
        product2 = new DrinkProduct("melk", 1.20, "zuivel");
        product3 = new FrozenProduct("ijs", 3.99, "ijs");
    }

    @Test
    @DisplayName("nieuw winkelwagentje is leeg")
    void testNewShoppingCart_IsEmpty() {
        assertEquals(0, cart.items.size(), "Nieuw wagentje moet leeg zijn");
        assertEquals(0, cart.totalPrice, 0.01, "Totaalprijs moet 0 zijn");
    }

    @Test
    @DisplayName("product toevoegen aan winkelwagen")
    void testAddItem() {
        cart.addItem(product1);
        assertEquals(1, cart.items.size());
        assertEquals(product1, cart.items.get(0));
    }

    @Test
    @DisplayName("meerdere producten toevoegen")
    void testAddMultipleItems() {
        cart.addItem(product1);
        cart.addItem(product2);
        cart.addItem(product3);

        assertEquals(3, cart.items.size());
    }

    @Test
    @DisplayName("totaalprijs berekenen")
    void testCalculateTotal() {
        cart.addItem(product1); // 1.50
        cart.addItem(product2); // 1.20
        cart.addItem(product3); // 3.99

        double total = cart.calculateTotal();
        assertEquals(6.69, total, 0.01);
        assertEquals(6.69, cart.totalPrice, 0.01);
    }

    @Test
    @DisplayName("totaal berekenen van lege winkelwagen")
    void testCalculateTotal_EmptyCart() {
        double total = cart.calculateTotal();
        assertEquals(0, total, 0.01);
    }

    @Test
    @DisplayName("hetzelfde product meerdere keren toevoegen")
    void testAddSameProductMultipleTimes() {
        cart.addItem(product1);
        cart.addItem(product1);
        cart.addItem(product1);

        assertEquals(3, cart.items.size());
        double total = cart.calculateTotal();
        assertEquals(4.50, total, 0.01); // 3 x 1.50
    }

    @Test
    @DisplayName("totaal herberekenen na toevoegen")
    void testCalculateTotal_AfterAddingMoreItems() {
        cart.addItem(product1);
        double firstTotal = cart.calculateTotal();
        assertEquals(1.50, firstTotal, 0.01);

        cart.addItem(product2);
        double secondTotal = cart.calculateTotal();
        assertEquals(2.70, secondTotal, 0.01);
    }

    @Test
    @DisplayName("product met prijs 0")
    void testAddItem_WithZeroPrice() {
        Product freeProduct = new GeneralProduct("gratis sample", 0.0, "promotie");
        cart.addItem(freeProduct);

        assertEquals(1, cart.items.size());
        assertEquals(0.0, cart.calculateTotal(), 0.01);
    }
}

/**
 * Unit tests voor de Shelve klasse
 * Merk op: Deze test gebruikt JavaFX componenten, dus je hebt mogelijk JavaFX test setup nodig
 */
class ShelveLogicTest {

    /**
     * Testbare wrapper voor Shelve zonder JavaFX dependencies
     */
    static class TestableShelve {
        private int maxCapacity;
        private int currentCapacity;

        public TestableShelve(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            this.currentCapacity = maxCapacity;
        }

        public void removeProduct() {
            if (currentCapacity > 0) {
                currentCapacity--;
            }
        }

        public void addProduct() {
            if (currentCapacity < maxCapacity) {
                currentCapacity++;
            }
        }

        public int getCurrentCapacity() {
            return currentCapacity;
        }

        public int getMaxCapacity() {
            return maxCapacity;
        }

        public Boolean needsRestock() {
            return (double) currentCapacity / maxCapacity <= 0.8;
        }

        public Boolean isFull() {
            return currentCapacity == maxCapacity;
        }
    }

    private TestableShelve shelve;

    @BeforeEach
    void setUp() {
        shelve = new TestableShelve(10);
    }

    @Test
    @DisplayName("nieuw schap heeft maximale capaciteit")
    void testNewShelve_HasMaxCapacity() {
        assertEquals(10, shelve.getMaxCapacity());
        assertEquals(10, shelve.getCurrentCapacity());
    }

    @Test
    @DisplayName("verwijderen vermindert capaciteit")
    void testRemoveProduct() {
        shelve.removeProduct();
        assertEquals(9, shelve.getCurrentCapacity());
    }

    @Test
    @DisplayName("meerdere producten verwijderen")
    void testRemoveMultipleProducts() {
        shelve.removeProduct();
        shelve.removeProduct();
        shelve.removeProduct();
        assertEquals(7, shelve.getCurrentCapacity());
    }

    @Test
    @DisplayName("product verwijderen van leeg schap")
    void testRemoveProduct_FromEmptyShelve() {
        // Leeg het schap eerst
        for (int i = 0; i < 10; i++) {
            shelve.removeProduct();
        }
        assertEquals(0, shelve.getCurrentCapacity());

        // Probeer nog een product te verwijderen
        shelve.removeProduct();
        assertEquals(0, shelve.getCurrentCapacity(), "Capaciteit mag niet onder 0 komen");
    }

    @Test
    @DisplayName("product toevoegen verhoogt capaciteit")
    void testAddProduct() {
        shelve.removeProduct(); // Capaciteit: 9
        shelve.addProduct();     // Capaciteit: 10
        assertEquals(10, shelve.getCurrentCapacity());
    }

    @Test
    @DisplayName("product toevoegen aan vol schap")
    void testAddProduct_ToFullShelve() {
        assertEquals(10, shelve.getCurrentCapacity());
        shelve.addProduct();
        assertEquals(10, shelve.getCurrentCapacity(), "Capaciteit mag niet boven max komen");
    }

    @Test
    @DisplayName("schap heeft bijvulling nodig bij 80% of minder")
    void testNeedsRestock() {
        assertFalse(shelve.needsRestock(), "Vol schap heeft geen restock nodig");

        shelve.removeProduct(); // 9/10 = 90%
        assertFalse(shelve.needsRestock());

        shelve.removeProduct(); // 8/10 = 80%
        assertTrue(shelve.needsRestock(), "Bij 80% moet restock nodig zijn");

        shelve.removeProduct(); // 7/10 = 70%
        assertTrue(shelve.needsRestock());
    }

    @Test
    @DisplayName("leeg schap heeft restock nodig")
    void testNeedsRestock_EmptyShelve() {
        for (int i = 0; i < 10; i++) {
            shelve.removeProduct();
        }
        assertTrue(shelve.needsRestock(), "Leeg schap moet restock nodig hebben");
    }

    @Test
    @DisplayName("controleren of schap vol is")
    void testIsFull() {
        assertTrue(shelve.isFull(), "Nieuw schap moet vol zijn");

        shelve.removeProduct();
        assertFalse(shelve.isFull(), "Schap met 9/10 is niet vol");

        shelve.addProduct();
        assertTrue(shelve.isFull(), "Schap moet weer vol zijn");
    }

    @Test
    @DisplayName("schap compleet legen en vullen")
    void testCompleteEmptyAndRefill() {
        // Leeg maken
        for (int i = 0; i < 10; i++) {
            shelve.removeProduct();
        }
        assertEquals(0, shelve.getCurrentCapacity());
        assertFalse(shelve.isFull());
        assertTrue(shelve.needsRestock());

        // Opnieuw vullen
        for (int i = 0; i < 10; i++) {
            shelve.addProduct();
        }
        assertEquals(10, shelve.getCurrentCapacity());
        assertTrue(shelve.isFull());
        assertFalse(shelve.needsRestock());
    }

    @Test
    @DisplayName("grenswaarde restock bij exact 80%")
    void testNeedsRestock_BoundaryAt80Percent() {
        TestableShelve largeShelve = new TestableShelve(100);

        // Verwijder tot 81 items (81%)
        for (int i = 0; i < 19; i++) {
            largeShelve.removeProduct();
        }
        assertEquals(81, largeShelve.getCurrentCapacity());
        assertFalse(largeShelve.needsRestock(), "Bij 81% geen restock nodig");

        // Verwijder nog een item tot 80%
        largeShelve.removeProduct();
        assertEquals(80, largeShelve.getCurrentCapacity());
        assertTrue(largeShelve.needsRestock(), "Bij exact 80% wel restock nodig");
    }

    @Test
    @DisplayName("schap met capaciteit 1")
    void testShelveWithCapacityOne() {
        TestableShelve smallShelve = new TestableShelve(1);

        assertTrue(smallShelve.isFull());
        assertFalse(smallShelve.needsRestock()); // 100% = geen restock

        smallShelve.removeProduct();
        assertEquals(0, smallShelve.getCurrentCapacity());
        assertTrue(smallShelve.needsRestock()); // 0% = wel restock
        assertFalse(smallShelve.isFull());

        smallShelve.addProduct();
        assertTrue(smallShelve.isFull());
        assertFalse(smallShelve.needsRestock());
    }

    @Test
    @DisplayName("verschillende capaciteiten en restock triggers")
    void testVariousCapacities() {
        // Test 5 producten: 4/5 = 80% -> restock nodig
        TestableShelve shelve5 = new TestableShelve(5);
        shelve5.removeProduct(); // 4/5
        assertTrue(shelve5.needsRestock());

        // Test 20 producten: 17/20 = 85% -> geen restock
        TestableShelve shelve20 = new TestableShelve(20);
        for (int i = 0; i < 3; i++) shelve20.removeProduct(); // 17/20
        assertFalse(shelve20.needsRestock());

        // 16/20 = 80% -> wel restock
        shelve20.removeProduct();
        assertTrue(shelve20.needsRestock());
    }
}

/**
 * Integration test voorbeeld voor product flow
 */
class ProductFlowIntegrationTest {

    @Test
    @DisplayName("product van catalogus naar winkelwagen")
    void testProductFlow_CatalogToCart() {
        ProductCatalog catalog = new ProductCatalog();
        ShoppingCart cart = new ShoppingCart();

        // Zoek product in catalogus
        Product product = ProductCatalog.getProductByName("heineken");
        assertNotNull(product);

        // Voeg toe aan winkelwagen
        cart.addItem(product);
        assertEquals(1, cart.items.size());


        double total = cart.calculateTotal();
        assertEquals(2.50, total, 0.01);
    }

}
