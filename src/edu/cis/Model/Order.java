package edu.cis.Model;
import java.util.UUID;
public class Order {
    private String itemID;
    private String orderID;
    private String type;

    public Order(MenuItem item, String id) {
        type = item.getType();
        itemID = item.getId();
        orderID = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemID='" + itemID + "', type='" + type +
                "', orderID='" + orderID +

                "'}";
    }
}
