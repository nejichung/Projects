/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;


import com.pokemons.models.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jacob
 */
@Component
public class ItemDBDao implements ItemDao{
    
    JdbcTemplate jdbc;
    @Autowired
    public ItemDBDao (JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
       List<Item> allItems = null;
       try{
           allItems = jdbc.query("SELECT * FROM Items", new ItemMapper());
      } catch (DataAccessException ex) {
            throw new ItemPersistenceException("Could not access data.", ex);
        }
        return allItems;
    }
    
    public static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int index) throws SQLException {
            Item singleItem = new Item();
            singleItem.setItemID(rs.getInt("ItemID"));
            singleItem.setItemName(rs.getString("ItemName"));
            singleItem.setDescription(rs.getString("Description"));

            return singleItem;
        }
    }
    
}
