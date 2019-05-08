/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.daos;

import com.sg.resourcehub.models.Item;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;

/**
 *
 * @author abdulmalik
 */
public class InMemoryDao {
     List<Item> allItems = new ArrayList<Item>();
    public InMemoryDao(){
        
      Item firstItem = new Item();
      firstItem.setItemName("Chair");
      firstItem.setQuantity(1);
      firstItem.setRequesterId(1);
      allItems.add(firstItem);
      
      Item secondItem = new Item();
      secondItem.setItemName("Table");
      secondItem.setQuantity(1);
      secondItem.setRequesterId(2);
      allItems.add(secondItem);
      
      Item firstSupply = new Item();
      firstSupply.setSupplierId(1);
      firstSupply.setItemName("sock");
      firstSupply.setQuantity(5);
      allItems.add(firstSupply);
      

      Item secondSupply = new Item();
      secondSupply.setSupplierId(2);
      secondSupply.setItemName("Microwave");
      secondSupply.setQuantity(4);
      allItems.add(secondSupply);
      
    }
    
    public Item supplierAddItem(Item toAdd){
        Item singleItem = toAdd;
        singleItem.setItemName("sock");
        singleItem.setQuantity(1);
       allItems.add(toAdd);
       return singleItem;
    }
}
