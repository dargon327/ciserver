package edu.cis.Model;
import java.util.UUID;
public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String id;
    private int amountAvailable;
    private String type;

    public MenuItem(String name, String description, double price, int amountAvailable, String type, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountAvailable = amountAvailable;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name +
                "', description='" + description  +
                "', price=" + price +
                ", id='" + id +
                "', amountAvailable=" + amountAvailable +
                ", type='" + type  +
                "'}";
    }
}
