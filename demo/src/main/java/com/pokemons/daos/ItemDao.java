/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

import com.pokemons.models.Item;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface ItemDao {

    public List<Item> getAllItems() throws ItemPersistenceException;

    public Item getItemByID(Integer id) throws ItemPersistenceException;

    public List<Item> getAllItemForTrainer(Integer id) throws ItemPersistenceException;
    
}
