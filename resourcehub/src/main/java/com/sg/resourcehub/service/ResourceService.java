/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service;

import com.sg.resourcehub.service.responses.DisplayItemsResponse;
import com.sg.resourcehub.service.responses.DisplayAllSuppliersResponse;
import com.sg.resourcehub.service.responses.DisplayAllRequestersResponse;
import com.sg.resourcehub.service.responses.DeleteUserResponse;
import com.sg.resourcehub.service.responses.CreateUserResponse;
import com.sg.resourcehub.service.responses.AddSupplierItemResponse;
import com.sg.resourcehub.service.responses.DisplayRolesResponse;
import com.sg.resourcehub.service.responses.DisplayTagsResponse;
import com.sg.resourcehub.service.responses.DisplayUsersResponse;
import com.sg.resourcehub.service.responses.GetRoleByIdResponse;
import com.sg.resourcehub.service.responses.GetUserByIdResponse;
import com.sg.resourcehub.service.responses.ItemDetailsResponse;
import com.sg.resourcehub.service.responses.RequesterAddItemResponse;
import com.sg.resourcehub.service.responses.RequesterDetailsResponse;
import com.sg.resourcehub.service.responses.SupplierAddItemResponse;
import com.sg.resourcehub.service.responses.SupplierDetailsResponse;
import com.sg.resourcehub.service.responses.TagDetailsResponse;
import com.sg.resourcehub.service.responses.UpdateUserResponse;
import com.sg.resourcehub.daos.DaoPersistenceException;
import com.sg.resourcehub.daos.ResourceHubDao;
import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.models.Requester;
import com.sg.resourcehub.models.Role;
import com.sg.resourcehub.models.Supplier;
import com.sg.resourcehub.models.Tag;
import com.sg.resourcehub.models.User;
import com.sg.resourcehub.service.exceptions.InvalidNameException;
import com.sg.resourcehub.service.exceptions.InvalidQuantityException;
import com.sg.resourcehub.service.responses.AddRequesterItemResponse;
import com.sg.resourcehub.service.responses.AddRequesterResponse;
import com.sg.resourcehub.service.responses.AddSupplierResponse;
import com.sg.resourcehub.service.responses.DeleteItemResponse;
import com.sg.resourcehub.service.responses.DeleteRequesterResponse;
import com.sg.resourcehub.service.responses.DeleteSupplierResponse;
import com.sg.resourcehub.service.responses.EditItemResponse;
import com.sg.resourcehub.service.responses.EditRequesterResponse;
import com.sg.resourcehub.service.responses.EditSupplierResponse;
import com.sg.resourcehub.service.responses.GetItemByIDResponse;
import com.sg.resourcehub.service.responses.GetRequesterByIDResponse;
import com.sg.resourcehub.service.responses.GetSupplierByIDResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author abdulmalik
 */
@Service
public class ResourceService implements UserDetailsService {

    @Autowired
    ResourceHubDao dao;

    public DisplayTagsResponse getAllTags() {
        List<Tag> allTags;
        DisplayTagsResponse getAllTags = new DisplayTagsResponse();
        try {
            allTags = dao.getAllTags();
            getAllTags.setIsSuccess(true);
            getAllTags.setAllTags(allTags);
            if (getAllTags == null) {
                getAllTags.setIsSuccess(false);
                getAllTags.setMessage("Error: Failed to get all Tags");
            } else {
                getAllTags.setIsSuccess(true);
                getAllTags.setAllTags(allTags);
            }

        } catch (DaoPersistenceException ex) {
            getAllTags.setIsSuccess(false);
            getAllTags.setMessage("Error: Failed to get all Tags");
        }

        return getAllTags;
    }

    public DisplayAllRequestersResponse getAllRequesters() {
        List<Requester> allRequesters;
        DisplayAllRequestersResponse requesterResponse = new DisplayAllRequestersResponse();

        try {
            allRequesters = dao.getRequesters();
            requesterResponse.setIsSuccess(true);
            requesterResponse.setAllRequesters(allRequesters);
            if (requesterResponse == null) {
                requesterResponse.setIsSuccess(false);
                requesterResponse.setMessage("Error: Failed to retrieve all Requseters");
            } else {
                requesterResponse.setIsSuccess(true);
                requesterResponse.setAllRequesters(allRequesters);
            }
        } catch (DaoPersistenceException ex) {
            requesterResponse.setIsSuccess(false);
            requesterResponse.setMessage("Error: Failed to display all Requesters");

        }

        return requesterResponse;
    }

    public DisplayAllSuppliersResponse getAllSuppliers() {

        List<Supplier> allSuppliers;
        DisplayAllSuppliersResponse SupplierResponse = new DisplayAllSuppliersResponse();
        try {
            allSuppliers = dao.getSuppliers();
            SupplierResponse.setIsSuccess(true);
            SupplierResponse.setAllSuppliers(allSuppliers);
            if (SupplierResponse == null) {
                SupplierResponse.setIsSuccess(false);
                SupplierResponse.setMessage("Error: Failed to retrieve all Suppliers");
            } else {
                SupplierResponse.setIsSuccess(true);
                SupplierResponse.setAllSuppliers(allSuppliers);
            }
        } catch (DaoPersistenceException ex) {
            SupplierResponse.setIsSuccess(false);
            SupplierResponse.setMessage("Error: Failed to display all Suppliers");
        }
        return SupplierResponse;

    }

    public DisplayItemsResponse getAllItems() {

        List<Item> allItems;
        DisplayItemsResponse ItemResponse = new DisplayItemsResponse();
        try {
            allItems = dao.getItems();
            ItemResponse.setIsSuccess(true);
            ItemResponse.setAllItems(allItems);
            if (ItemResponse == null) {
                ItemResponse.setIsSuccess(false);
                ItemResponse.setMessage("Error Failed to retrieve all Items");
            } else {
                ItemResponse.setIsSuccess(true);
                ItemResponse.setAllItems(allItems);
            }
        } catch (DaoPersistenceException ex) {
            ItemResponse.setIsSuccess(false);
            ItemResponse.setMessage("Error: Failed to display all Items");
        }

        return ItemResponse;

    }

    public RequesterAddItemResponse getRequestItem(String itemName, int quantity) {

        RequesterAddItemResponse toReturn = new RequesterAddItemResponse();
        try {
            //        private int itemId;
//    private String itemName;
//    private int Quantity;
//    private List<Tag> allTags;
            //   List<Item> allItems = dao.requesterItem(buildItem);
            Item buildItem = new Item();
            buildItem.setItemName(itemName);
            buildItem.setQuantity(quantity);
            if (toReturn == null) {
                toReturn.setIsSuccess(false);
                toReturn.setMessage("Error: Failed to Add Item");
            } else {
                dao.requesterItem(buildItem);
                toReturn.setIsSuccess(true);
                toReturn.setRequestItem(buildItem);
                toReturn.setMessage("Item Added successfully");
            }
        } catch (DaoPersistenceException ex) {
            toReturn.setIsSuccess(false);
            toReturn.setMessage("Error Unable get Items");
        }

        return toReturn;

    }

    public SupplierAddItemResponse getSupplierItem(String itemName, int quantity) {
        SupplierAddItemResponse toReturn = new SupplierAddItemResponse();
        try {
            Item supplyItem = new Item();
            supplyItem.setItemName(itemName);
            supplyItem.setQuantity(quantity);
            dao.supplierItem(supplyItem);
            if (toReturn == null) {
                toReturn.setIsSuccess(false);
                toReturn.setMessage("Error: Failed to build an item");
            } else {
                toReturn.setIsSuccess(true);
                toReturn.setSupplierItem(supplyItem);

            }
        } catch (DaoPersistenceException ex) {
            toReturn.setIsSuccess(false);
            toReturn.setMessage("Error: Failed to Add an item");
        }
        return toReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = dao.getUserByUsername(username);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles()) {

                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));

            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } catch (DaoPersistenceException ex) {
            //TODO: our service methods should return responses, not data directly
            //here we can't just return an unsuccessful response the way we should because

            throw new UnsupportedOperationException();

        }
    }

    public DisplayUsersResponse getAllUsers() {
        DisplayUsersResponse response = new DisplayUsersResponse();
        List<User> allUsers;
        try {
            allUsers = dao.getAllUsers();
            response.setIsSuccess(true);
            response.setAllUsers(allUsers);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error Failed to retrieve all Users");
            } else {
                response.setIsSuccess(true);
                response.setAllUsers(allUsers);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error Failed to display all Users");
        }
        return response;
    }

    public CreateUserResponse createUser(User user) {
        CreateUserResponse response = new CreateUserResponse();
        try {
            User singleUser = dao.createUser(user);
            response.setIsSuccess(true);
            response.setSingleUser(singleUser);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error Failed to create Users");
            } else {
                response.setIsSuccess(true);
                response.setSingleUser(singleUser);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error Failed to display Users");
        }
        return response;
    }

//    public GetRoleByIdResponse getRoleByRole(String role_user) {
//        GetRoleByIdResponse response = new GetRoleByIdResponse();
//        Role singleRole = dao.getRoleByRole(role_user);
//        response.setIsSuccess(true);
//        response.setSingleRole(singleRole);
//        return response;
//    }
    public GetRoleByIdResponse getRoleById(int parseInt) {
        GetRoleByIdResponse response = new GetRoleByIdResponse();
        try {
            Role singleRole = dao.getRoleById(parseInt);
            response.setIsSuccess(true);
            response.setSingleRole(singleRole);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error Failed to get roleById");
            } else {
                response.setIsSuccess(true);
                response.setSingleRole(singleRole);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error Failed to display roleById");
        }
        return response;
    }

    public DisplayRolesResponse getRolesByName() {
        DisplayRolesResponse response = new DisplayRolesResponse();
        try {
            List<Role> allRoles = dao.getAllRoles();
            response.setIsSuccess(true);
            response.setAllRoles(allRoles);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error Failed to get roleById");
            } else {
                response.setIsSuccess(true);
                response.setAllRoles(allRoles);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error Failed to get roleById");
        }
        return response;
    }

    public DeleteUserResponse deleteUser(Integer id) {
        DeleteUserResponse response = new DeleteUserResponse();

        try {
            dao.deleteUser(id);
            if (response != null) {
                response.setIsSuccess(false);
                response.setMessage("Error Failed to not Delete");
            } else {
                response.setIsSuccess(true);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error Failed to not show Delete");
        }
        return response;
    }

    public GetUserByIdResponse getUserById(Integer id) {
        GetUserByIdResponse response = new GetUserByIdResponse();
        try {
            User singleUser = dao.getUserById(id);
            response.setSingleUser(singleUser);
            response.setIsSuccess(true);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error: can not Create User by Id");
            } else {
                response.setIsSuccess(true);
                response.setSingleUser(singleUser);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error: can not get user by Id");
        }
        return response;
    }

    public UpdateUserResponse updateUser(User user) {
        UpdateUserResponse response = new UpdateUserResponse();
        try {
            dao.updateUser(user);
//       response.setUpdatedUser(user);
            response.setIsSuccess(true);
            if (response == null) {
                response.setIsSuccess(false);
                response.setMessage("Error: Failed to UpdateUser");
            } else {
                response.setIsSuccess(true);
                response.setUpdatedUser(user);
            }
        } catch (DaoPersistenceException ex) {
            response.setIsSuccess(false);
            response.setMessage("Error: Failed to display UpdateUser");
        }
        return response;
    }

    public ItemDetailsResponse getItemDetails(Integer id) {
        ItemDetailsResponse response = new ItemDetailsResponse();
        
        try{
           
        
        Item singleItem = dao.getItemByID(id);
        List<Tag> allTags = dao.getAllTagsForItem(id);
        singleItem.setAllTags(allTags);

        response.setIsSuccess(true);
        response.setSingleItem(singleItem);
        } catch(DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }

    public SupplierDetailsResponse getSupplierDetails(Integer id) {
        SupplierDetailsResponse response = new SupplierDetailsResponse();
        try{
            
        
        Supplier singleSupplier = dao.getSupplierByID(id);
        List<Item> allItems = dao.getAllItemsForSupplier(id);
        singleSupplier.setAllItems(allItems);
        response.setSingleSupplier(singleSupplier);
        response.setIsSuccess(true);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }

    public RequesterDetailsResponse getRequesterDetails(Integer id) {
        RequesterDetailsResponse response = new RequesterDetailsResponse();
        try {
            
       
        Requester singleRequester = dao.getRequesterByID(id);
        List<Item> allItems = dao.getAllItemsForRequester(id);
        singleRequester.setAllItems(allItems);
        response.setSingleRequester(singleRequester);
        response.setIsSuccess(true);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());

                    ;
        }
        return response;
    }

    public TagDetailsResponse getTagDetails(Integer id) {
        TagDetailsResponse response = new TagDetailsResponse();
        try {
            Tag singleTag = dao.getTagByID(id);
            List<Item> allItems = dao.getItemsByTag(id);
            singleTag.setAllItems(allItems);
            response.setSingleTag(singleTag);
            response.setIsSuccess(true);

        } catch (DaoPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setIsSuccess(false);

        }
        return response;
    }

    public AddSupplierItemResponse addSupplierItem(Item toAdd) {
        AddSupplierItemResponse response = new AddSupplierItemResponse();
        try {
            validateName(toAdd.getItemName());
            validateQuantity(toAdd.getQuantity());
            Item addedItem = dao.addSupplierItem(toAdd);
            response.setAddedSupItem(addedItem);
            response.setIsSuccess(true);
        } catch (DaoPersistenceException | InvalidNameException | InvalidQuantityException ex) {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void validateName(String itemName) throws InvalidNameException {
        if (itemName.equalsIgnoreCase("") || itemName.length() > 25){
            throw new InvalidNameException(itemName + "is not a valid name.");
        }
    }

    private void validateQuantity(Integer quantity) throws InvalidQuantityException{
        if (quantity < 0 || quantity.equals("")){
        throw new InvalidQuantityException(quantity + "is not a valid quantity."); 
    }
    }

    public AddSupplierResponse addSupplier(Supplier toAdd) {
        AddSupplierResponse response = new AddSupplierResponse();
        
        try{
            validateName(toAdd.getSupplierName());
            Supplier addedSupplier = dao.addSupplier(toAdd);
            response.setAddedSupplier(addedSupplier);
        } catch (DaoPersistenceException | InvalidNameException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public AddRequesterResponse addRequester(Requester toAdd) {
         AddRequesterResponse response = new AddRequesterResponse();
        
        try{
            validateName(toAdd.getRequesterName());
            Requester addedRequester = dao.addRequester(toAdd);
            response.setAddedRequester(addedRequester);
            response.setIsSuccess(true);
        } catch (DaoPersistenceException | InvalidNameException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public AddRequesterItemResponse addRequesterItem(Item toAdd) {
         AddRequesterItemResponse response = new AddRequesterItemResponse();
        try {
            validateName(toAdd.getItemName());
            validateQuantity(toAdd.getQuantity());
            Item addedItem = dao.addRequesterItem(toAdd);
            response.setAddedRequesterItem(addedItem);
            response.setIsSuccess(true);
        } catch (DaoPersistenceException | InvalidNameException | InvalidQuantityException ex) {
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DeleteItemResponse deleteItem(Integer id) {
        DeleteItemResponse response = new DeleteItemResponse();
        try{ 
            dao.deleteItem(id);
            response.setIsSuccess(true);
           } catch (DaoPersistenceException ex){
               response.setIsSuccess(false);
               response.setMessage(ex.getMessage());
           }
        return response;
    }

    public DeleteSupplierResponse deleteSupplier(Integer id) {
         DeleteSupplierResponse response = new DeleteSupplierResponse();
        try{ 
            dao.deleteSupplier(id);
            response.setIsSuccess(true);
           } catch (DaoPersistenceException ex){
               response.setIsSuccess(false);
               response.setMessage(ex.getMessage());
           }
        return response;

    }

    public DeleteRequesterResponse deleteRequester(Integer id) {
         DeleteRequesterResponse response = new DeleteRequesterResponse();
        try{ 
            dao.deleteRequester(id);
            response.setIsSuccess(true);
           } catch (DaoPersistenceException ex){
               response.setIsSuccess(false);
               response.setMessage(ex.getMessage());
           }
        return response;
    }

    public GetItemByIDResponse getItemByID(int id) {
        GetItemByIDResponse response = new GetItemByIDResponse();
        try{
          Item getItem = dao.getItemByID(id); 
          response.setSingleItem(getItem);
          response.setIsSuccess(true);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public EditItemResponse editItem(Item editItem) {
        EditItemResponse response = new EditItemResponse();
        try{
            Item editedItem = dao.editItem(editItem);
            response.setIsSuccess(true);
            response.setEditedItem(editedItem);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public GetRequesterByIDResponse getRequesterByID(Integer id) {
        GetRequesterByIDResponse response = new GetRequesterByIDResponse();
        try{
          Requester getRequester = dao.getRequesterByID(id); 
          response.setSingleRequester(getRequester);
          response.setIsSuccess(true);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
    public GetSupplierByIDResponse getSupplierByID(Integer id){
        GetSupplierByIDResponse response = new GetSupplierByIDResponse();
        try{
          Supplier getSupplier = dao.getSupplierByID(id); 
          response.setSingleSupplier(getSupplier);
          response.setIsSuccess(true);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public EditRequesterResponse editRequester(Requester editRequester) {
         EditRequesterResponse response = new EditRequesterResponse();
        try{
            Requester editedRequester = dao.editRequester(editRequester);
            response.setIsSuccess(true);
            response.setEditedRequester(editedRequester);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public EditSupplierResponse editSupplier(Supplier editSupplier) {
        EditSupplierResponse response = new EditSupplierResponse();
        try{
            Supplier editedSupplier = dao.editSupplier(editSupplier);
            response.setIsSuccess(true);
            response.setEditedSupplier(editedSupplier);
        } catch (DaoPersistenceException ex){
            response.setIsSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
