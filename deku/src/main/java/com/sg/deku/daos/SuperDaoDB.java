/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
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
public class SuperDaoDB implements SuperDao {

    JdbcTemplate jdbc;

    @Autowired
    public SuperDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Super> getAllSupers() throws SuperPersistenceException {

        List<Super> allSupers = null;
        try {
            allSupers = jdbc.query("SELECT * FROM Supers", new SuperMapper());
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return allSupers;
    }

    @Override
    public Super getSuperByID(Integer id) throws SuperPersistenceException {
        String SELECT_SUPER_BY_ID = null;
        try {
            SELECT_SUPER_BY_ID = "SELECT * FROM Supers WHERE superID = ?";
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return jdbc.queryForObject(SELECT_SUPER_BY_ID, new SuperMapper(), id);
    }

    @Override
    public List<Super> getAllSupersForOrganization(Integer id) throws SuperPersistenceException { //yolo
        String SELECT_SUPERS_FOR_ORGANIZATION = null;
        try {
            SELECT_SUPERS_FOR_ORGANIZATION = "SELECT * FROM Supers JOIN SupersOrganizations ON "
                    + "Supers.SuperID = SupersOrganizations.SuperID "
                    + "WHERE SupersOrganizations.OrganizationID = ?";
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return jdbc.query(SELECT_SUPERS_FOR_ORGANIZATION, new SuperMapper(), id);

    }
//    @Override
//    public List<Sighting> getSightingForSuper(Integer id) { // similar to getTeacherForCourse
//        final String SELECT_SIGHTING_FOR_SUPER = "SELECT s.* FROM Sightings s" +
//                "JOIN Supers u ON u.SuperID = s.superID WHERE superID = ?";
//        return jdbc.query(SELECT_SIGHTING_FOR_SUPER, new SightingMapper(), id);
//    
//    }

    @Override
    public List<Super> getAllSupersForSighting(Integer id) throws SuperPersistenceException {
        String SELECT_SUPERS_FOR_SIGHTING = null;
        try {
            SELECT_SUPERS_FOR_SIGHTING = "SELECT * FROM  Sightings JOIN SupersSightings ON "
                    + "Sightings.SightingID = SupersSightings.SightingID "
                    + "JOIN Supers ON Supers.SuperID = SupersSightings.SuperID "
                    + "WHERE Sightings.SightingID = ?";
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return jdbc.query(SELECT_SUPERS_FOR_SIGHTING, new SuperMapper(), id);
    }

    @Override
    @Transactional
    public Super addSuper(Super toAdd) throws SuperPersistenceException {
        try {
            final String INSERT_SUPER = "INSERT INTO Supers(Name, Description, Quirk)"
                    + "VALUES(?,?,?)";
            jdbc.update(INSERT_SUPER,
                    toAdd.getSuperName(),
                    toAdd.getDescription(),
                    toAdd.getQuirk());
            int newID = jdbc.queryForObject("SELECT MAX(SuperID) FROM Supers", Integer.class);
            toAdd.setSuperID(newID);
            for (Sighting singleSighting : toAdd.getAllSightings()) {
                int sightingID = singleSighting.getSightingID();
                final String ADD_SUPERS_SIGHTINGS = "INSERT INTO SupersSightings (SuperID, SightingID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_SIGHTINGS,
                        toAdd.getSuperID(),
                        sightingID);
            }

            for (Organization singleOrganization : toAdd.getAllOrganizations()) {
                int orgID = singleOrganization.getOrganizationID();
                final String ADD_SUPERS_ORGS = "INSERT INTO SupersOrganizations (SuperID, OrganizationID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_ORGS,
                        toAdd.getSuperID(),
                        orgID);
            }
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return toAdd;
    }

    @Override
    @Transactional
    public void deleteSuper(Integer id) throws SuperPersistenceException {
        try {
            final String DELETE_SUPERS_ORGANIZATIONS = "DELETE FROM SupersOrganizations WHERE SuperID = ?";
            jdbc.update(DELETE_SUPERS_ORGANIZATIONS, id);

            final String DELETE_SUPERS_SIGHTINGS = "DELETE FROM SupersSightings WHERE SuperID = ?";
            jdbc.update(DELETE_SUPERS_SIGHTINGS, id);

            final String DELETE_SUPER = "DELETE FROM Supers WHERE SuperID =?";
            jdbc.update(DELETE_SUPER, id);
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
    }

    @Override
    @Transactional
    public Super editSuper(Super sup) throws SuperPersistenceException {
        try {
            final String DELETE_SUPERS_ORGANIZATIONS = "DELETE FROM SupersOrganizations WHERE SuperID = ?";
            jdbc.update(DELETE_SUPERS_ORGANIZATIONS, sup.getSuperID());

            for (Organization singleOrg : sup.getAllOrganizations()) {
                int orgID = singleOrg.getOrganizationID();
                final String ADD_SUPERS_ORGANIZATIONS = "INSERT INTO SupersOrganizations (SuperID, OrganizationID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_ORGANIZATIONS,
                        sup.getSuperID(),
                        orgID);
            }

            final String DELETE_SUPERS_SIGHTINGS = "DELETE FROM SupersSightings WHERE SuperID = ?";
            jdbc.update(DELETE_SUPERS_SIGHTINGS, sup.getSuperID());

            for (Sighting singleSighting : sup.getAllSightings()) {
                int sightingID = singleSighting.getSightingID();
                final String ADD_SUPERS_SIGHTINGS = "INSERT INTO SupersSightings (SuperID, SightingID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_SIGHTINGS,
                        sup.getSuperID(),
                        sightingID);
            }

            final String EDIT_SUPER = "UPDATE Supers SET Name = ?, Description = ?, "
                    + "Quirk = ? WHERE superID = ?";
            int returnedInt = jdbc.update(EDIT_SUPER, sup.getSuperName(),
                    sup.getDescription(), sup.getQuirk(),
                    sup.getSuperID());
            if (returnedInt < 1) {
                throw new SuperPersistenceException("Failed to match" + sup.getSuperID());
            }
            if (returnedInt > 1) {
                throw new SuperPersistenceException("FATAL ERROR, Primary Keys are not unique");
            }
        } catch (DataAccessException ex) {
            throw new SuperPersistenceException("Could not access data.", ex);
        }
        return sup;
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super singleSuper = new Super();
            singleSuper.setSuperID(rs.getInt("SuperID"));
            singleSuper.setSuperName(rs.getString("Name"));
            singleSuper.setDescription(rs.getString("Description"));
            singleSuper.setQuirk(rs.getString("Quirk"));

            return singleSuper;
        }
    }

}
