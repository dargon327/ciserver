package edu.cis.Model;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> eatriumItems = new ArrayList<MenuItem>();
    private String adminID;
    public Menu() {

    }

    public ArrayList<MenuItem> getEatriumItems() {
        return eatriumItems;
    }

    public String getAdminID() {
        return adminID;
    }
    public void addEatriumItem(MenuItem item) {
        eatriumItems.add(item);
    }
    public String deleteEatriumItem(String id) {
        for (int i = 0; i < eatriumItems.size();i++) {
            if (id.equals(eatriumItems.get(i).getId())) {
                eatriumItems.remove(i);
                return CISConstants.SUCCESS;
            }
        }
        return CISConstants.INVALID_MENU_ITEM_ERR;
    }
    public void setEatriumItems(ArrayList<MenuItem> eatriumItems) {
        this.eatriumItems = eatriumItems;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "eatriumItems=" + eatriumItems +
                ", adminID='" + adminID  +
                "'}";
    }
}
