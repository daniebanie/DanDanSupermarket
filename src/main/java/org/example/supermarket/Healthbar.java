package org.example.supermarket;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * visualiseert de capaciteit of status van een object in de supermarkt,
 * zoals een schap, klant of restocker.
 */
public class Healthbar {

    /** rechthoek die de visuele healthbar voorstelt */
    public Rectangle healthRectangle;

    /** label dat de huidige en maximale waarde toont */
    public Label capacityLabel;

    /** huidige waarde van de healthbar */
    public int value1;

    /** maximale waarde van de healthbar */
    public int value2;

    /** maximale breedte van de healthbar */
    public double maxWidth;

    /**
     * maakt een nieuwe healthbar aan en voegt deze toe aan de opgegeven stack
     *
     * @param stack de container waarin de healthbar wordt geplaatst
     * @param value1 de huidige waarde
     * @param value2 de maximale waarde
     * @param x x-positie van de healthbar
     * @param y y-positie van de healthbar
     * @param maxWidth maximale breedte van de healthbar
     */
    public Healthbar(StackPane stack, int value1, int value2, double x, double y, double maxWidth) {
        this.value1 = value1;
        this.value2 = value2;
        this.maxWidth = maxWidth;

        // maak een healthbar aan
        healthRectangle = new Rectangle(x, y, maxWidth, 10);
        healthRectangle.setFill(Color.GREEN);
        healthRectangle.setStroke(Color.BLACK);

        // maak label aan dat capaciteit toont
        capacityLabel = new Label(value1 + "/" + value2);
        capacityLabel.setLayoutX(x);
        capacityLabel.setLayoutY(y);

        // voeg alles toe aan de stack
        stack.getChildren().addAll(healthRectangle, capacityLabel);
    }

    /**
     * werkt de healthbar bij op basis van een nieuwe waarde en objecttype
     *
     * @param newValue1 nieuwe huidige waarde
     * @param objectType type object (voor kleur)
     */
    public void updateHealthBar(int newValue1, ObjectType objectType) {
        this.value1 = newValue1;
        double healthPercentage = (double) value1 / value2;
        healthRectangle.setWidth(maxWidth * healthPercentage);
        healthRectangle.setFill(healthPercentage > 0.3 ? objectType.getColorHigh() : objectType.getColorLow());
        capacityLabel.setText(value1 + "/" + value2);
    }

    /**
     * beschrijft het type object dat de healthbar gebruikt,
     * inclusief kleurcodes voor hoge en lage waarden.
     */
    public enum ObjectType {
        SHELF(Color.GREEN, Color.RED),
        RESTOCKER(Color.SKYBLUE, Color.BLUE),
        CUSTOMER(Color.GOLD, Color.YELLOW);

        private final Color colorHigh;
        private final Color colorLow;

        ObjectType(Color colorHigh, Color colorLow) {
            this.colorHigh = colorHigh;
            this.colorLow = colorLow;
        }

        public Color getColorHigh() {
            return colorHigh;
        }

        public Color getColorLow() {
            return colorLow;
        }
    }
}
