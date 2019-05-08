/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

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
public class SightingDaoDB implements SightingDao {

    JdbcTemplate jdbc;

    @Autowired
    public SightingDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Sighting> getAllSightings() throws SightingPersistenceException {
        List<Sighting> allSightings = null;
        try {
            allSightings = jdbc.query("SELECT * FROM Sightings", new SightingMapper());
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return allSightings;
    }

    @Override
    public Sighting getSightingByID(Integer id) throws SightingPersistenceException {
        String SELECT_SIGHTING_BY_ID = null;
        try {
            SELECT_SIGHTING_BY_ID = "SELECT * FROM Sightings WHERE SightingID = ?";
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightingsForSuper(Integer id) throws SightingPersistenceException {
        String SELECT_SIGHTINGS_FOR_SUPER = null;
        try {
            SELECT_SIGHTINGS_FOR_SUPER = "SELECT * FROM Sightings JOIN SupersSightings ON "
                    + "Sightings.SightingID = SupersSightings.SightingID "
                    + "WHERE SupersSightings.SuperID = ?";
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return jdbc.query(SELECT_SIGHTINGS_FOR_SUPER, new SightingMapper(), id);

    }

    @Override
    public List<Sighting> getAllSightingsForLocation(Integer id) throws SightingPersistenceException {
        // SELECT * FROM Sightings JOIN Locations ON Locations.LocationID = Sightings.LocationID WHERE Locations.LocationID = ?
//        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sightings JOIN Locations ON Locations.LocationID = Sightings.LocationID "
//                + "JOIN SupersSightings ON SupersSightings.SightingID = Sightings.SightingID "
//                + "WHERE Locations.LocationID = ?";

        String SELECT_SIGHTINGS_FOR_LOCATION = null;
        try {
            SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sightings WHERE LocationID = ?";
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION, new SightingMapper(), id);
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting toAdd) throws SightingPersistenceException {
        try {
            final String INSERT_SIGHTING = "INSERT INTO Sightings(Date,LocationID)"
                    + "VALUES(?,?)";
            jdbc.update(INSERT_SIGHTING,
                    toAdd.getDate(),
                    toAdd.getLocationID());

//            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            int newID = jdbc.queryForObject("SELECT MAX(SightingID) FROM Sightings", Integer.class);
            toAdd.setSightingID(newID);
            for (Super singleSuper : toAdd.getAllSupers()) {
                int superID = singleSuper.getSuperID();
                final String ADD_SUPERS_SIGHTINGS = "INSERT INTO SupersSightings (SuperID, SightingID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_SIGHTINGS,
                        superID,
                        toAdd.getSightingID());
            }
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return toAdd;
    }

    @Override
    @Transactional
    public void deleteSighting(Integer id) throws SightingPersistenceException {
        try {
            final String DELETE_SUPERS_SIGHTINGS = "DELETE FROM SupersSightings WHERE SightingID = ?";
            jdbc.update(DELETE_SUPERS_SIGHTINGS, id);

            final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE SightingID =?";
            jdbc.update(DELETE_SIGHTING, id);
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
    }

    @Override
    @Transactional
    public Sighting editSighting(Sighting sighting) throws SightingPersistenceException {
        try {
            final String DELETE_SUPERS_SIGHTINGS = "DELETE FROM SupersSightings WHERE SightingID = ?";
            jdbc.update(DELETE_SUPERS_SIGHTINGS, sighting.getSightingID());

            for (Super singleSuper : sighting.getAllSupers()) {
                int superID = singleSuper.getSuperID();
                final String ADD_SUPERS_SIGHTINGS = "INSERT INTO SupersSightings (SuperID, SightingID)"
                        + "VALUES (?,?)";
                jdbc.update(ADD_SUPERS_SIGHTINGS,
                        superID,
                        sighting.getSightingID());
            }

            final String DELETE_SIGHTINGS_LOCATIONS = "UPDATE Sightings SET LocationID = null WHERE SightingID = ?";
            jdbc.update(DELETE_SIGHTINGS_LOCATIONS, sighting.getSightingID());
            final String ADD_SIGHTINGS_LOCATIONS = "UPDATE Sightings SET LocationID = ? WHERE SightingID = ?";
            jdbc.update(ADD_SIGHTINGS_LOCATIONS,
                    sighting.getLocationID(),
                    sighting.getSightingID());

            final String EDIT_SIGHTING = "UPDATE Sightings SET Date = ? WHERE SightingID = ?";

            int returnedInt = jdbc.update(EDIT_SIGHTING, sighting.getDate(),
                    sighting.getSightingID());

            if (returnedInt < 1) {
                throw new SightingPersistenceException("Failed to match " + sighting.getSightingID());
            }
            if (returnedInt > 1) {
                throw new SightingPersistenceException("Fatal ERROR Primary Keys are not unique");
            }
        } catch (DataAccessException ex) {
            throw new SightingPersistenceException("Could not access data.", ex);
        }
        return sighting;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setDate(rs.getDate("Date").toLocalDate());
            sighting.setLocationID(rs.getInt("LocationID"));

            return sighting;
        }
    }

}
