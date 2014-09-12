/*
 * This file is a part of the RMI Plugin for Eclipse tutorials.
 * Copyright (C) 2002-14 Genady Beryozkin
 */
package demo.rmi.supplier.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;

import demo.rmi.supplier.common.Item;
import demo.rmi.supplier.common.RemoteSupplier;

/**
 * This is a simulation of a printer server. Note that in real application, server level
 * synchronization may be needed.
 * 
 * @author Genady Beryozkin, rmi-info@genady.net
 */
public class RemoteSupplierImpl extends UnicastRemoteObject implements RemoteSupplier {

  /**
   * Collection of items which were read from txt file
   */

  private ArrayList<Item> itemlist = new ArrayList<Item>();

  /**
   * The supplier name
   */
  private String name;

  /**
   * Constant serialVersionUID is needed for serialization interoperability if this file is compiled
   * with different compilers.
   */
  private static final long serialVersionUID = 5885886202424414094L;

  private static final int NOT_ENOUGH_QUANTITY = 0;

  private static final int SUCCESS = 1;

  private static final int COULD_NOT_FIND_ITEM = -1;

  /**
   * Default constructor that only copies the supplier's name. And read txt data into memories
   * 
   * @throws IOException
   */
  public RemoteSupplierImpl(String name) throws IOException {
    this.name = name;
    readDataIntoDB(name);
  }

  /**
   * Continue to read the file until reading sth non-empty
   * 
   * @param file
   * @return the String which is not empty
   * @throws IOException
   */

  private String readUntilNotEmpty(BufferedReader in) throws IOException {
    String tempStr;
    do
      tempStr = in.readLine();
    while (tempStr.equals(""));
    return tempStr;
  }

  /**
   * Used to read supplier txt data into memory (arraylist) Since different suppliers used different
   * data system, it's required to use customized method to read text
   * 
   * @param supplier name
   *          
   * @throws IOException
   */

  private void readDataIntoDB(String sname) throws IOException {
    String tempStr;

    if (sname.equals("supplier1")) {
      BufferedReader in = new BufferedReader(new FileReader("Supplier1.txt"));

      while ((tempStr = in.readLine()) != null) {
        Item item = new Item();
        item.price = Double.parseDouble(tempStr.substring(12, tempStr.length()).replace(",", ""));
        tempStr = readUntilNotEmpty(in);
        item.itemName = tempStr.substring(10, tempStr.length());
        tempStr = readUntilNotEmpty(in);
        StringBuffer str = new StringBuffer("");
        if (tempStr.contains("bike description=")) {
          tempStr = in.readLine();
          while ((tempStr.contains("item number") == false)) {

            if (!tempStr.equals(""))
              str.append(tempStr);
            tempStr = in.readLine();
          }
        }
        item.itemDescription = str.toString();

        item.itemID = tempStr.substring(12, tempStr.length());
        tempStr = readUntilNotEmpty(in);
        item.category = tempStr.substring(9, tempStr.length());
        tempStr = readUntilNotEmpty(in);
        item.inventoryNum = Integer.parseInt(tempStr.substring(10, tempStr.length()));
        itemlist.add(item);
      }
    }
    if (sname.equals("supplier2")) {
      BufferedReader in = new BufferedReader(new FileReader("Supplier2.txt"));

      while ((tempStr = in.readLine()) != null) {
        Item item = new Item();
        item.price = Double.parseDouble(tempStr.substring(1, tempStr.length()).replace(",", ""));
        tempStr = readUntilNotEmpty(in);
        item.itemName = tempStr;
       
        StringBuffer str = new StringBuffer("");
        tempStr = in.readLine();

        while ((tempStr.contains("#3") == false)) {
       
          if (!tempStr.equals(""))
            str.append(tempStr);
          tempStr = in.readLine();
        }
        item.itemDescription = str.toString();
       
        item.itemID = tempStr;
        tempStr = readUntilNotEmpty(in);
        item.category = tempStr;
        tempStr = readUntilNotEmpty(in);
       
        item.inventoryNum = Integer.parseInt(tempStr.substring(10, tempStr.length()));
        itemlist.add(item);
      }
    }

  }

  /**
   * Used for searching particular items by keyword
   * 
   * @param keyword
   *          used in searching inventory
   * @return all of items related with keyword
   */

  public ArrayList<demo.rmi.supplier.common.Item> queryItem(String query) {
    query = query.toLowerCase();
    ArrayList<Item> templist = new ArrayList<Item>();
    for (int i = 0; i < itemlist.size(); i++) {
      if (itemlist.get(i).itemName.contains(query)) {
        templist.add(itemlist.get(i));
      }
    }
    return templist;
  }

  /**
   * Used for ordering item from remote Clients By RMI
   *  
   * @param [itemID ,  quantity needs to be ordered]
   * @return the status of Order (Might be NOT_ENOUGH_QUANTITY, SUCCESS, COULD_NOT_FIND_ITEM)
   */
  
  public int orderItem(String id, int quantity) {
    for (int i = 0; i < itemlist.size(); i++) {

      if (itemlist.get(i).itemID.equals(id)) {
        if (itemlist.get(i).inventoryNum - quantity >= 0)
          itemlist.get(i).inventoryNum -= quantity;
        else
          return NOT_ENOUGH_QUANTITY;
        return SUCCESS;
      }
    }
    return COULD_NOT_FIND_ITEM;
  }

  /**
   * Register suppliers
   */

  public static void main(String[] args) throws IOException {

    try {
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("supplier1", new RemoteSupplierImpl("supplier1"));
      registry.rebind("supplier2", new RemoteSupplierImpl("supplier2"));
    } catch (RemoteException e) {
      System.err.println("Something wrong happended on the remote end");
      e.printStackTrace();
      System.exit(-1); // can't just return, rmi threads may not exit
    }
    System.out.println("The supplier server is ready");
  }

  /**
   * Used for browsing itemlist from remote Clients By RMI
   * 
   * @return the list of items
   */
  public ArrayList<Item> browseItems() {
    // TODO Auto-generated method stub
    return itemlist;
  }

  /**
   * Used for retrieving itemInfo by item ID from remote Clients By RMI
   *
   * @param itemID
   * @return the list of items 
   */
  public Item itemIDtoItem(String input) {
    for (int i = 0; i < itemlist.size(); i++) {
      if (itemlist.get(i).itemID.contains(input)) {
        return itemlist.get(i);
      }
    }
    return null;
  }
}
