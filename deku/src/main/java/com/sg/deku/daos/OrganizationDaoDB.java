/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Organization;
import com.sg.deku.models.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jacob
 */
@Component
public class OrganizationDaoDB implements OrganizationDao {

    JdbcTemplate jdbc;

    @Autowired
    public OrganizationDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Organization> getAllOrganizations() throws OrganizationPersistenceException {
        List<Organization> allOrganizations = null;
        try {
            allOrganizations = jdbc.query("SELECT * FROM Organizations", new OrganizationMapper());
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
        return allOrganizations;
    }

    @Override
    public Organization getOrganizationByID(Integer id) throws OrganizationPersistenceException {
        String SELECT_ORGANIZATION_BY_ID = null;
        try {
            SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organizations WHERE organizationID = ?";
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
        return jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
    }

//    @Override
//    public Organization getOrganizationForSuper(Integer id) {
//        final String SELECT_ORGANIZATION_FOR_SUPER = "SELECT s.* FROM Organizations s " +
//                "JOIN Supers u ON u.SuperID = s.superID WHERE u.superID = ?";
//        return jdbc.queryForObject(SELECT_ORGANIZATION_FOR_SUPER, new OrganizationMapper(), id);
//    
//    }
//    @Override // This IS GETTING ALL DATA BETWEEN THE TABLES AND BRIDGE
//    public List<Organization> getAllOrganizationsForSuper(Integer id) {
//        final String SELECT_ORGANIZATIONS_FOR_SUPER = "SELECT * FROM  Supers JOIN SupersOrganizations ON "
//                + "Supers.SuperID = SupersOrganizations.SuperID "
//                + "JOIN Organizations ON Organizations.OrganizationID = SupersOrganizations.OrganizationID "
//                + "WHERE Supers.SuperID = ?";
//        return jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPER, new OrganizationMapper(),id);
//    }
    @Override
    public List<Organization> getAllOrganizationsForSuper(Integer id) throws OrganizationPersistenceException {
        String SELECT_ORGANIZATIONS_FOR_SUPER = null;
        try {
            SELECT_ORGANIZATIONS_FOR_SUPER = "SELECT * FROM Organizations JOIN SupersOrganizations ON "
                    + "Organizations.OrganizationID = SupersOrganizations.OrganizationID "
                    + "WHERE SupersOrganizations.SuperID = ?";
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPER, new OrganizationMapper(), id);

    }

    @Override
    @Transactional
    public Organization addOrganization(Organization toAdd) throws OrganizationPersistenceException {
        try {
            final String INSERT_ORGANIZATION = "INSERT INTO Organizations(Name, Description, Address, PhoneNumber, EmailAddress)"
                    + "VALUES (?,?,?,?,?)";
            jdbc.update(INSERT_ORGANIZATION,
                    toAdd.getOrganizationName(),
                    toAdd.getDescription(),
                    toAdd.getAddress(),
                    toAdd.getPhoneNumber(),
                    toAdd.getEmailAddress());
            int newID = jdbc.queryForObject("SELECT MAX(OrganizationID) FROM Organizations", Integer.class);
            toAdd.setOrganizationID(newID);
            for (Super singleSuper : toAdd.getAllSupers()) {
                int superID = singleSuper.getSuperID();
                final String ADD_SUPERS_ORGS = "INSERT INTO SupersOrganizations (SuperID, OrganizationID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_ORGS,
                        superID,
                        toAdd.getOrganizationID());
            }
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
        return toAdd;
    }
// delete from org and super bridge table and then reinsert the selectedsuperIds intot he bridge table

    @Override
    @Transactional
    public Organization editOrg(Organization org) throws OrganizationPersistenceException {

        try {
            final String DELETE_SUPERS_ORGANIZATIONS = "DELETE FROM SupersOrganizations WHERE OrganizationID = ?";
            jdbc.update(DELETE_SUPERS_ORGANIZATIONS, org.getOrganizationID());

            for (Super singleSuper : org.getAllSupers()) {
                int superID = singleSuper.getSuperID();
                final String ADD_SUPERS_ORGANIZATIONS = "INSERT INTO SupersOrganizations (SuperID, OrganizationID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_ORGANIZATIONS,
                        superID,
                        org.getOrganizationID());
            }

            final String EDIT_ORG = "UPDATE Organizations SET Name = ?, Description = ?, "
                    + "Address = ?, PhoneNumber = ?, EmailAddress = ? WHERE organizationID = ?";

            int returnedInt = jdbc.update(EDIT_ORG, org.getOrganizationName(),
                    org.getDescription(), org.getAddress(),
                    org.getPhoneNumber(), org.getEmailAddress(),
                    org.getOrganizationID());

            if (returnedInt < 1) {
                throw new OrganizationPersistenceException("Failed to match " + org.getOrganizationID());
                //failed to match
                // if its greater than 1 then, there was some extreme error where primary keys aren't unique
            }
            if (returnedInt > 1) {
                throw new OrganizationPersistenceException("Fatal ERROR Primary Keys are not unique");
            }
//                if(returnedInt == 1){
//                
//        
//                }
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
        return org;
    }

    @Override
    @Transactional
    public void deleteOrganization(Integer id) throws OrganizationPersistenceException {
        try {
            final String DELETE_SUPERS_ORGANIZATIONS = "DELETE FROM SupersOrganizations WHERE OrganizationID = ?";
            jdbc.update(DELETE_SUPERS_ORGANIZATIONS, id);

            final String DELETE_ORGANIZATION = "DELETE FROM Organizations WHERE OrganizationID = ?";
            jdbc.update(DELETE_ORGANIZATION, id);
        } catch (DataAccessException ex) {
            throw new OrganizationPersistenceException("Could not access data.", ex);
        }
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("Name"));
            organization.setDescription(rs.getString("Description"));
            organization.setAddress(rs.getString("Address"));
            organization.setPhoneNumber(rs.getString("PhoneNumber"));
            organization.setEmailAddress(rs.getString("emailAddress"));

            return organization;
        }
    }

}
