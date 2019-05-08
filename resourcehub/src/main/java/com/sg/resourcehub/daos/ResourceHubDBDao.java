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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abdulmalik
 */
@Component
public class ResourceHubDBDao implements ResourceHubDao {

    JdbcTemplate jdbc;
    
    @Autowired
    public ResourceHubDBDao( JdbcTemplate jdbc ){
        this.jdbc = jdbc;
    }

    @Override
    public List<Requester> getRequesters() throws DaoPersistenceException {
        List<Requester> allRequesters = jdbc.query("SELECT * From Requesters", new RequesterMapper());
        return allRequesters;
    }

    @Override
    public List<Supplier> getSuppliers() throws DaoPersistenceException {
        List<Supplier> allSuppliers = jdbc.query("SELECT * From Suppliers", new SupplierMapper());
        return allSuppliers;
    }

    @Override
    public List<Item> getItems() throws DaoPersistenceException {
        List<Item> allItems = jdbc.query("SELECT * From Items LEFT JOIN Suppliers "
                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
                + "ON Items.RequesterID = Requesters.RequesterID", new ItemMapper());
        return allItems;
    }

    @Override
    public List<Tag> getAllTags() throws DaoPersistenceException {
        List<Tag> allTags = jdbc.query("SELECT * From Tags", new TagMapper());
        return allTags;
    }

    @Override
    public User getUserById(int id) throws DaoPersistenceException {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setRoles(getRolesForUser(user.getId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) throws DaoPersistenceException {
        try {
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRoles(getRolesForUser(user.getId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() throws DaoPersistenceException {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        for (User user : users) {
            user.setRoles(getRolesForUser(user.getId()));
        }
        return users;
    }

    private Set<Role> getRolesForUser(int id) throws DataAccessException {
        final String SELECT_ROLES_FOR_USER = "SELECT r.* FROM user_role ur "
                + "JOIN role r ON ur.role_id = r.id "
                + "WHERE ur.user_id = ?";
        Set<Role> roles = new HashSet(jdbc.query(SELECT_ROLES_FOR_USER, new RoleMapper(), id));
        return roles;
    }

    @Override
    public void updateUser(User user) throws DaoPersistenceException {
        final String UPDATE_USER = "UPDATE user SET username = ?, password = ?,enabled = ? WHERE id = ?";
        jdbc.update(UPDATE_USER, user.getUsername(), user.getPassword(), user.isEnabled(), user.getId());

        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE user_id = ?";
        jdbc.update(DELETE_USER_ROLE, user.getId());
        for (Role role : user.getRoles()) {
            final String INSERT_USER_ROLE = "INSERT INTO user_role(user_id, role_id) VALUES(?,?)";
            jdbc.update(INSERT_USER_ROLE, user.getId(), role.getId());
        }
    }

    @Override
    public void deleteUser(int id) throws DaoPersistenceException {
        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE user_id = ?";
        final String DELETE_USER = "DELETE FROM user WHERE id = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        jdbc.update(DELETE_USER, id);
    }

    @Override
    @Transactional
    public User createUser(User user) throws DaoPersistenceException {
        final String INSERT_USER = "INSERT INTO user(username, password, enabled) VALUES(?,?,?)";
        jdbc.update(INSERT_USER, user.getUsername(), user.getPassword(), user.isEnabled());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setId(newId);

        for (Role role : user.getRoles()) {
            final String INSERT_USER_ROLE = "INSERT INTO user_role(user_id, role_id) VALUES(?,?)";
            jdbc.update(INSERT_USER_ROLE, user.getId(), role.getId());
        }
        return user;
    }

    @Override
    public Role getRoleById(int id) throws DaoPersistenceException {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Role getRoleByRole(String role) throws DaoPersistenceException {
        try {
            final String SELECT_ROLE_BY_ROLE = "SELECT * FROM role WHERE role = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() throws DaoPersistenceException {
        final String SELECT_ALL_ROLES = "SELECT * FROM role";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public void deleteRole(int id) throws DaoPersistenceException {
        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE role_id = ?";
        final String DELETE_ROLE = "DELETE FROM role WHERE id = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        jdbc.update(DELETE_ROLE, id);
    }

    @Override
    public void updateRole(Role role) throws DaoPersistenceException {
        final String UPDATE_ROLE = "UPDATE role SET role = ? WHERE id = ?";
        jdbc.update(UPDATE_ROLE, role.getRoleName(), role.getId());
    }

    @Override
    @Transactional
    public Role createRole(Role role) throws DaoPersistenceException {
        final String INSERT_ROLE = "INSERT INTO role(role) VALUES(?)";
        jdbc.update(INSERT_ROLE, role.getRoleName());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setId(newId);
        return role;
    }

//
    @Override
    public Item requesterItem(Item buildItem) throws DaoPersistenceException {
        final String UPDATE_item = "INSERT INTO items(itemName, itemQuantity) VALUES(?,?)";
        jdbc.update(UPDATE_item, buildItem.getItemName(), buildItem.getQuantity());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        buildItem.setItemId(newId);
        return buildItem;

    }
// 

    @Override
    public List<User> getAllUsersForSupplier(int id) throws DaoPersistenceException {
        final String SELECT_USERS_FOR_SUPPLIER = "SELECT * FROM Users WHERE SupplierID = ?";
        return jdbc.query(SELECT_USERS_FOR_SUPPLIER, new UserMapper(), id);
    }

    @Override
    public List<User> getAllUsersForRequester(int id) throws DaoPersistenceException {
        final String SELECT_USERS_FOR_REQUESTER = "SELECT * FROM Users WHERE RequesterID = ?";
        return jdbc.query(SELECT_USERS_FOR_REQUESTER, new UserMapper(), id);
    }

    @Override
    public Requester getRequesterForUser(int id) throws DaoPersistenceException {
        final String SELECT_REQUESTER_FOR_USER = "SELECT * FROM Requester WHERE Users.RequesterID = Requesters.RequesterID";
        return jdbc.queryForObject(SELECT_REQUESTER_FOR_USER, new RequesterMapper(), id);
    }

    @Override
    public Supplier getSupplierForUser(int id) throws DaoPersistenceException {
        final String SELECT_SUPPLIER_FOR_USER = "SELECT * FROM Supplier WHERE Users.SupplierID = Suppliers.SupplierID";
        return jdbc.queryForObject(SELECT_SUPPLIER_FOR_USER, new SupplierMapper(), id);
    }
//

    @Override
    public Item supplierItem(Item buildItem) throws DaoPersistenceException {
        final String UPDATE_item = "INSERT INTO items(itemName, itemQuantity) VALUES(?,?)";
        jdbc.update(UPDATE_item, buildItem.getItemName(), buildItem.getQuantity());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        buildItem.setItemId(newId);
        return buildItem;
    }

    public Item getItemByID(Integer id) throws DaoPersistenceException {
        final String SELECT_ITEM_BY_ID = "SELECT * From Items LEFT JOIN Suppliers "
                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
                + "ON Items.RequesterID = Requesters.RequesterID WHERE ItemID = ?";
        return jdbc.queryForObject(SELECT_ITEM_BY_ID, new ItemMapper(), id);
    }

    @Override
    public List<Tag> getAllTagsForItem(Integer id) throws DaoPersistenceException {
        final String SELECT_TAGS_BY_ITEM = "SELECT * FROM Tags JOIN ItemsTags ON "
                + "Tags.TagID = ItemsTags.TagID "
                + "WHERE ItemsTags.ItemID = ?";
        return jdbc.query(SELECT_TAGS_BY_ITEM, new TagMapper(), id);
    }

    @Override
    public Supplier getSupplierByID(Integer id) throws DaoPersistenceException {
        final String SELECT_SUPPLIER_BY_ID = "SELECT * FROM Suppliers WHERE SupplierID = ?";
        return jdbc.queryForObject(SELECT_SUPPLIER_BY_ID, new SupplierMapper(), id);
    }

    @Override
    public List<Item> getAllItemsForSupplier(Integer id) throws DaoPersistenceException {
        //"SELECT * From Items LEFT JOIN Suppliers "
//                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
//                + "ON Items.RequesterID = Requesters.RequesterID WHERE ItemID = ?;" 
        final String SELECT_ITEMS_BY_SUPPLIER = "SELECT * FROM Items LEFT JOIN Suppliers "
                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
                + "ON Items.RequesterID = Requesters.RequesterID WHERE Suppliers.SupplierID = ?";
        return jdbc.query(SELECT_ITEMS_BY_SUPPLIER, new ItemMapper(), id);
    }

    @Override
    public Requester getRequesterByID(Integer id) throws DaoPersistenceException {
        final String SELECT_REQUESTER_BY_ID = "SELECT * FROM Requesters WHERE RequesterID = ?";
        return jdbc.queryForObject(SELECT_REQUESTER_BY_ID, new RequesterMapper(), id);
    }

    @Override
    public List<Item> getAllItemsForRequester(Integer id) throws DaoPersistenceException {

        final String SELECT_ITEMS_BY_REQUESTER = "SELECT * From Items LEFT JOIN Suppliers "
                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
                + "ON Items.RequesterID = Requesters.RequesterID WHERE Requesters.RequesterID = ?";
        return jdbc.query(SELECT_ITEMS_BY_REQUESTER, new ItemMapper(), id);
    }

    @Override
    public Tag getTagByID(Integer id) throws DaoPersistenceException {
        final String SELECT_TAG_BY_ID = "SELECT * FROM Tags WHERE TagID = ?";
        return jdbc.queryForObject(SELECT_TAG_BY_ID, new TagMapper(), id);
    }

    @Override
    public List<Item> getItemsByTag(Integer id) throws DaoPersistenceException {
        final String SELECT_ITEMS_BY_TAG = "SELECT * FROM Items JOIN ItemsTags ON "
                + "Items.ItemID = ItemsTags.ItemID "
                + "LEFT JOIN Suppliers "
                + "ON Items.SupplierID = Suppliers.SupplierID LEFT JOIN Requesters "
                + "ON Items.RequesterID = Requesters.RequesterID "
                + "WHERE ItemsTags.TagID = ?";
        return jdbc.query(SELECT_ITEMS_BY_TAG, new ItemMapper(), id);
    }

    @Override
    @Transactional
    public Item addSupplierItem(Item toAdd) throws DaoPersistenceException {
        final String INSERT_ITEM_FOR_SUP = "INSERT INTO Items (ItemName, Quantity, url, SupplierID)"
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ITEM_FOR_SUP,
                toAdd.getItemName(),
                toAdd.getQuantity(),
                toAdd.getUrl(),
                toAdd.getSupplierId());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        toAdd.setItemId(newID);
        return toAdd;
    }

    @Override
    public Requester addRequester(Requester toAdd) throws DaoPersistenceException {
        final String INSERT_REQUESTER = "INSERT INTO Requesters (RequesterName)"
                + "VALUES(?)";
        jdbc.update(INSERT_REQUESTER,
                toAdd.getRequesterName());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        toAdd.setRequesterId(newID);
        return toAdd;
    }

    @Override
    public Supplier addSupplier(Supplier toAdd) throws DaoPersistenceException {
        final String INSERT_SUPPLIER = "INSERT INTO Suppliers (SupplierName, SupplierAddress)"
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPPLIER,
                toAdd.getSupplierName(),
                toAdd.getSupplierAddress());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        toAdd.setSupplierId(newID);
        return toAdd;
    }

    @Override
    public Item addRequesterItem(Item toAdd) throws DaoPersistenceException {
        final String INSERT_ITEM_FOR_REQ = "INSERT INTO Items (ItemName, Quantity, url, RequesterID)"
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ITEM_FOR_REQ,
                toAdd.getItemName(),
                toAdd.getQuantity(),
                toAdd.getUrl(),
                toAdd.getRequesterId());
        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        toAdd.setItemId(newID);
        return toAdd;
    }

    @Override
    @Transactional
    public void deleteItem(Integer id) throws DaoPersistenceException {
        final String DELETE_ITEMS_TAGS = "DELETE FROM ItemsTags WHERE ItemID = ?";
        jdbc.update(DELETE_ITEMS_TAGS, id);

        final String DELETE_ITEM = "DELETE FROM Items WHERE ItemID = ?";
        jdbc.update(DELETE_ITEM, id);
    }

    @Override
    @Transactional
    public void deleteSupplier(Integer id) throws DaoPersistenceException {
        final String DELETE_USER_SUPPLIER = "DELETE FROM user WHERE SupplierID = ?";
        jdbc.update(DELETE_USER_SUPPLIER, id);

        final String DELETE_ITEM_SUPPLIER = "DELETE FROM Items WHERE SupplierID = ?";
        jdbc.update(DELETE_ITEM_SUPPLIER, id);

        final String DELETE_SUPPLIER = "DELETE FROM Suppliers WHERE SupplierID = ?";
        jdbc.update(DELETE_SUPPLIER, id);
    }

    @Override
    @Transactional
    public void deleteRequester(Integer id) throws DaoPersistenceException {
        final String DELETE_USER_REQUESTER = "DELETE FROM user WHERE RequesterID = ?";
        jdbc.update(DELETE_USER_REQUESTER, id);

        final String DELETE_ITEM_REQUESTER = "DELETE FROM Items WHERE RequesterID = ?";
        jdbc.update(DELETE_ITEM_REQUESTER, id);

        final String DELETE_REQUESTER = "DELETE FROM Requesters WHERE RequesterID = ?";
        jdbc.update(DELETE_REQUESTER, id);
    }

    @Override
    public Item editItem(Item editItem) throws DaoPersistenceException {
        final String EDIT_ITEM = "UPDATE Items SET ItemName = ?, Quantity = ?, url = ?,"
                + "SupplierID = ?, RequesterID = ? WHERE ItemID = ?";
        jdbc.update(EDIT_ITEM, editItem.getItemName(), editItem.getQuantity(),
        editItem.getUrl(), editItem.getSupplierId(), editItem.getRequesterId(), editItem.getItemId());
        return editItem;
    }

    @Override
    public Requester editRequester(Requester editRequester) throws DaoPersistenceException {
        final String EDIT_REQUESTER = "UPDATE Requesters SET RequesterName = ? WHERE RequesterID = ?"; 
               
        jdbc.update(EDIT_REQUESTER, editRequester.getRequesterName(),
        editRequester.getRequesterId());
        return editRequester;
    }

    @Override
    public Supplier editSupplier(Supplier editSupplier) throws DaoPersistenceException {
        final String EDIT_SUPPLIER = "UPDATE Suppliers SET SupplierName = ?, SupplierAddress = ? WHERE SupplierID = ?"; 
               
        jdbc.update(EDIT_SUPPLIER, editSupplier.getSupplierName(), editSupplier.getSupplierAddress(),
        editSupplier.getSupplierId());
        return editSupplier;
    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            return user;
        }
    }

    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int index) throws SQLException {

            Item toGet = new Item();
            toGet.setItemId(rs.getInt("itemId"));
            toGet.setItemName(rs.getString("itemName"));
            toGet.setQuantity(rs.getInt("Quantity"));

            toGet.setSupplierId(rs.getInt("supplierId"));
            if (rs.wasNull()) {
                toGet.setSupplierId(null);

            }
            toGet.setRequesterId(rs.getInt("requesterId"));
            if (rs.wasNull()) {
                toGet.setRequesterId(null);

            }
            toGet.setUrl(rs.getString("url"));

            if (toGet.getSupplierId() != null) {
                Supplier sup = new Supplier();
                sup.setSupplierId(rs.getInt("supplierId"));
                sup.setSupplierAddress(rs.getString("supplierAddress"));
                sup.setSupplierName(rs.getString("supplierName"));

                toGet.setSingleSup(sup);
            }

            if (toGet.getRequesterId() != null) {
                Requester req = new Requester();
                req.setRequesterId(rs.getInt("requesterId"));
                req.setRequesterName(rs.getString("requesterName"));

                toGet.setSingleReq(req);
            }

            return toGet;
        }

    }

    private static final class SupplierMapper implements RowMapper<Supplier> {

        public Supplier mapRow(ResultSet rs, int index) throws SQLException {
            Supplier toGet = new Supplier();
            toGet.setSupplierId(rs.getInt("supplierId"));
            toGet.setSupplierName(rs.getString("supplierName"));
            toGet.setSupplierAddress(rs.getString("supplierAddress"));
            return toGet;
        }
    }

    private static final class TagMapper implements RowMapper<Tag> {

        public Tag mapRow(ResultSet rs, int index) throws SQLException {
            Tag toGet = new Tag();
            toGet.setTagId(rs.getInt("tagId"));
            toGet.setTagName(rs.getString("tagName"));
            return toGet;
        }
    }

    private static final class RequesterMapper implements RowMapper<Requester> {

        public Requester mapRow(ResultSet rs, int index) throws SQLException {
            Requester toGet = new Requester();
            toGet.setRequesterId(rs.getInt("requesterId"));
            toGet.setRequesterName(rs.getString("requesterName"));
            return toGet;
        }
    }

    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRoleName(rs.getString("role"));
            return role;
        }
    }
}
