/*
 * File: CIServer.java
 * ------------------------------
 * When it is finished, this program will implement a basic
 * ecommerce network management server.  Remember to update this comment!
 */

package edu.cis.Controller;

import acm.program.*;
import edu.cis.Model.*;
import edu.cis.Utils.SimpleServer;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class CIServer extends ConsoleProgram
        implements SimpleServerListener
{

    /* The internet port to listen to requests on */
    private static final int PORT = 8000;

    /* The server object. All you need to do is start it */
    private SimpleServer server = new SimpleServer(this, PORT);
    private ArrayList<CISUser> users = new ArrayList<CISUser>();
    private Menu menu = new Menu();


    /**
     * Starts the server running so that when a program sends
     * a request to this server, the method requestMade is
     * called.
     */
    public void run()
    {
        println("Starting server on port " + PORT);
        server.start();
    }

    /**
     * When a request is sent to this server, this method is
     * called. It must return a String.
     *
     * @param request a Request object built by SimpleServer from an
     *                incoming network request by the client
     */
    public String requestMade(Request request)
    {
        String cmd = request.getCommand();
        println(request.toString());
        if (cmd.equals(CISConstants.PING))
        {
            final String PING_MSG = "Hello, internet";

            //println is used instead of System.out.println to print to the server GUI
            println("   => " + PING_MSG);
            return PING_MSG;
        }
        if (cmd.equals(CISConstants.ADD_MENU_ITEM)) {
            println("received");
            String s = addMenuItem(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.CREATE_USER)) {
            println("received");

            String s = createUser(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.DELETE_ORDER)) {
            println("received");

            String s = deleteOrder(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.DELETE_MENU_ITEM)) {
            println("received");

            String s = deleteMenuItem(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.GET_CART)) {
            println("received");

            String s = getCart(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.GET_ITEM)) {
            println("received");

            String s = getItem(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.GET_USER)) {
            println("received");

            String s = getUser(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.GET_ORDER)) {
            println("received");

            String s = getOrder(request);
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.GET_MENU)) {
            println("received");

            String s = menu.toString();
            println("   => " + s);
            return s;
        }
        if (cmd.equals(CISConstants.PLACE_ORDER)) {
            println("received");

            String s = placeOrder(request);
            println("   => " + s);
            return s;
        }



        return "Error: Unknown command " + cmd + ".";
    }

    public String createUser(Request req) {

        String id = req.getParam(CISConstants.USER_ID_PARAM);
        String name = req.getParam(CISConstants.USER_NAME_PARAM);
        String yearLevel = req.getParam(CISConstants.YEAR_LEVEL_PARAM);
        if (id == null || name == null || yearLevel == null) {
            return CISConstants.PARAM_MISSING_ERR;
        }

        for (int i = 0; i < users.size();i++) {
            if (req.getParam(CISConstants.USER_ID_PARAM).equals(users.get(i).getUserID())) {
                return CISConstants.DUP_USER_ERR;
            }
        }

        CISUser user = new CISUser(name, yearLevel,id);
        users.add(user);
        return CISConstants.SUCCESS;
    }
    public String addMenuItem(Request req) {
        String name = req.getParam(CISConstants.ITEM_NAME_PARAM), desc = req.getParam(CISConstants.DESC_PARAM), price = req.getParam(CISConstants.PRICE_PARAM), type = req.getParam(CISConstants.ITEM_TYPE_PARAM),id = req.getParam(CISConstants.ITEM_ID_PARAM);
        if (name == null || desc == null || type == null || price == null) {
            return CISConstants.PARAM_MISSING_ERR;
        }
        for (int i = 0; i < menu.getEatriumItems().size();i++)
            if (req.getParam(CISConstants.ITEM_ID_PARAM).equals(menu.getEatriumItems().get(i).getId())){
                return CISConstants.DUP_ITEM_ERR;
            }
        double pric = parseDouble(price);
        MenuItem item = new MenuItem(req.getParam(CISConstants.ITEM_NAME_PARAM),req.getParam(CISConstants.DESC_PARAM), pric, 10, type, id);

        menu.addEatriumItem(item);
        return CISConstants.SUCCESS;
    }
    public String deleteMenuItem(Request req) {
        return menu.deleteEatriumItem(req.getParam(CISConstants.ITEM_ID_PARAM));
    }
    public String placeOrder(Request req) {
        println("check 1");
        String orderid = req.getParam(CISConstants.ORDER_ID_PARAM), itemid = req.getParam(CISConstants.ITEM_ID_PARAM), userid = req.getParam(CISConstants.USER_ID_PARAM), ordertype = req.getParam(CISConstants.ORDER_TYPE_PARAM);
        if (orderid == null || itemid == null || userid == null || ordertype == null) {
            return CISConstants.PARAM_MISSING_ERR;
        }
        if (menu.getEatriumItems().size()==0) {
            return CISConstants.EMPTY_MENU_ERR;
        }
        println("check 2");
        CISUser user = null;
        for (int i = 0; i < users.size(); i++) {
            if (userid.equals(users.get(i).getUserID())) {
                user = users.get(i);
                println("check 2.5");
                for (int j = 0; j < user.getOrders().size();j++) {
                    if (user.getOrders().get(j).getOrderID().equals(orderid)) {
                        return CISConstants.DUP_ORDER_ERR;
                    }
                }
            }

        }
        println("check 3");
        if (user == null) {
            return CISConstants.USER_INVALID_ERR;
        }
        MenuItem menuitem = null;
        for (int i = 0; i < menu.getEatriumItems().size();i++) {
            if(menu.getEatriumItems().get(i).getId().equals(itemid)) {
                menuitem = menu.getEatriumItems().get(i);
                break;
            }
        }
        println("check 4");
        if (menuitem == null) {return CISConstants.INVALID_MENU_ITEM_ERR;}
        if (menuitem.getAmountAvailable()==0) {return CISConstants.SOLD_OUT_ERR;}
        if (user.getMoney() < menuitem.getPrice()) {return CISConstants.USER_BROKE_ERR;}
        user.setMoney(user.getMoney() - menuitem.getPrice());
        Order order = new Order(menuitem, orderid);
        user.addOrder(order);
        menuitem.setAmountAvailable(menuitem.getAmountAvailable() - 1);
        return CISConstants.SUCCESS;
    }
    public String deleteOrder(Request req) {
        String userid = req.getParam(CISConstants.USER_ID_PARAM), orderid = req.getParam(CISConstants.ORDER_ID_PARAM);
        CISUser user = null;
        for (int i = 0; i < users.size(); i++) {
            if (userid.equals(users.get(i).getUserID())) {
                user = users.get(i);
            }
        }
        if (user ==null) {
            return CISConstants.USER_INVALID_ERR;
        }
        for (int i = 0; i < user.getOrders().size(); i++) {
            if (orderid.equals(user.getOrders().get(i).getOrderID())) {
                user.removeOrder(i);
                return CISConstants.SUCCESS;

            }
        }
        return CISConstants.ORDER_INVALID_ERR;
    }
    public String getOrder(Request req) {
        String userid = req.getParam(CISConstants.USER_ID_PARAM), orderid = req.getParam(CISConstants.ORDER_ID_PARAM);
        CISUser user = null;
        for (int i = 0; i < users.size(); i++) {
            if (userid.equals(users.get(i).getUserID())) {
                user = users.get(i);
            }
        }
        if (user ==null) {
            return CISConstants.USER_INVALID_ERR;
        }
        println("check 1");
        for (int i = 0; i < user.getOrders().size(); i++) {
            if (orderid.equals(user.getOrders().get(i).getOrderID())) {
                return user.getOrders().get(i).toString();
            }
        }
        println("check 2");
        return CISConstants.ORDER_INVALID_ERR;
    }
    public String getUser(Request req) {
        String userid = req.getParam(CISConstants.USER_ID_PARAM);
        for (int i = 0; i < users.size(); i++) {
            if (userid.equals(users.get(i).getUserID())) {
                return users.get(i).toString();
            }
        }
        return CISConstants.USER_INVALID_ERR;
    }
    public String getItem(Request req) {
        for (int i = 0; i < menu.getEatriumItems().size();i++) {
            if (menu.getEatriumItems().get(i).getId().equals(req.getParam(CISConstants.ITEM_ID_PARAM))) {
                return menu.getEatriumItems().get(i).toString();
            }
        }
        return CISConstants.INVALID_MENU_ITEM_ERR;
    }
    public String getCart(Request req) {
        String userid = req.getParam(CISConstants.USER_ID_PARAM);
        for (int i = 0; i < users.size(); i++) {
            if (userid.equals(users.get(i).getUserID())) {
                return users.get(i).getOrders().toString();
            }
        }
        return CISConstants.USER_INVALID_ERR;
    }

    public static void main(String[] args)
    {
        CIServer f = new CIServer();
        f.start(args);
    }
}
