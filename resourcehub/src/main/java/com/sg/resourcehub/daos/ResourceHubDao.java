/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.daos;

import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.models.Requester;
import com.sg.resourcehub.models.Role;
import com.sg.resourcehub.models.Supplier;
import com.sg.resourcehub.models.Tag;
import com.sg.resourcehub.models.User;
import java.util.List;

/**
 *
 * @author abdulmalik
 */
public interface ResourceHubDao {
  

    User getUserById(int id) throws DaoPersistenceException;
    User getUserByUsername(String username) throws DaoPersistenceException;
    List<User> getAllUsers() throws DaoPersistenceException;
    void updateUser(User user) throws DaoPersistenceException;
    void deleteUser(int id) throws DaoPersistenceException;
    User createUser(User user) throws DaoPersistenceException;
    
    Role getRoleById(int id) throws DaoPersistenceException;
    Role getRoleByRole(String role) throws DaoPersistenceException;
    List<Role> getAllRoles() throws DaoPersistenceException;
    void deleteRole(int id) throws DaoPersistenceException;
    void updateRole(Role role) throws DaoPersistenceException;
    Role createRole(Role role) throws DaoPersistenceException;



    List<User> getAllUsersForSupplier(int id)throws DaoPersistenceException;
    List<User> getAllUsersForRequester(int id)throws DaoPersistenceException;
    Requester getRequesterForUser(int id)throws DaoPersistenceException;
    Supplier getSupplierForUser(int id)throws DaoPersistenceException;
    //might need to see the  supplier/requester for a user
 
    
    public List<Requester> getRequesters() throws DaoPersistenceException;

    public List<Supplier> getSuppliers() throws DaoPersistenceException;

    public List<Item> getItems() throws DaoPersistenceException;

    public List<Tag> getAllTags() throws DaoPersistenceException;


    public Item requesterItem(Item buildItem) throws DaoPersistenceException;
    
    public Item supplierItem(Item buildItem) throws DaoPersistenceException;
    
    public Item getItemByID(Integer id) throws DaoPersistenceException;

    public List<Tag> getAllTagsForItem(Integer id) throws DaoPersistenceException;

    public Supplier getSupplierByID(Integer id) throws DaoPersistenceException;

    public List<Item> getAllItemsForSupplier(Integer id) throws DaoPersistenceException;

    public Requester getRequesterByID(Integer id) throws DaoPersistenceException;

    public List<Item> getAllItemsForRequester(Integer id) throws DaoPersistenceException;

    public Tag getTagByID(Integer id) throws DaoPersistenceException;

    public List<Item> getItemsByTag(Integer id) throws DaoPersistenceException;

    public Item addSupplierItem(Item toAdd) throws DaoPersistenceException;

    public Requester addRequester(Requester toAdd) throws DaoPersistenceException;

    public Supplier addSupplier(Supplier toAdd) throws DaoPersistenceException;

    public Item addRequesterItem(Item toAdd) throws DaoPersistenceException;

    public void deleteItem(Integer id) throws DaoPersistenceException;

    public void deleteSupplier(Integer id) throws DaoPersistenceException;

    public void deleteRequester(Integer id) throws DaoPersistenceException;

    public Item editItem(Item editItem) throws DaoPersistenceException;

    public Requester editRequester(Requester editRequester) throws DaoPersistenceException;

    public Supplier editSupplier(Supplier editSupplier) throws DaoPersistenceException;
}
