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

    @Override
    public Item getItemByID(Integer id) throws ItemPersistenceException {
        String SELECT_ITEM_BY_ID = null;
        try{
            SELECT_ITEM_BY_ID = "SELECT * FROM Items WHERE ItemID = ?";
        } catch (DataAccessException ex){
            throw new ItemPersistenceException("Could not access data.", ex);
            
        }
        return jdbc.queryForObject(SELECT_ITEM_BY_ID, new ItemMapper(), id);
    }

    @Override
    public List<Item> getAllItemForTrainer(Integer id) throws ItemPersistenceException {
        String SELECT_ITEM_FOR_TRAINER = null;
        try{
            SELECT_ITEM_FOR_TRAINER = "SELECT * FROM Items JOIN TrainersItems ON "
            + "Items.ItemID = TrainersItems.ItemID "
                    + "WHERE TrainersItems.TrainerID = ?";        
        } catch (DataAccessException ex){
            throw new ItemPersistenceException("Could not access data.", ex); 
        }
        return jdbc.query(SELECT_ITEM_FOR_TRAINER, new ItemMapper(), id);
    }
    
    public static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int index) throws SQLException {
            Item singleItem = new Item();
            singleItem.setItemID(rs.getInt("ItemID"));
            singleItem.setItemName(rs.getString("ItemName"));
            singleItem.setDescription(rs.getString("Description"));
            singleItem.setQuantity(rs.getInt("Quantity"));

            return singleItem;
        }
    }
    
}
