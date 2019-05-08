/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
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
public class LocationDaoDB implements LocationDao {

    JdbcTemplate jdbc;

    @Autowired
    public LocationDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Location> getAllLocations() throws LocationPersistenceException {
        List<Location> allLocations = null;
        try {
            allLocations = jdbc.query("SELECT * FROM Locations", new LocationMapper());
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
        return allLocations;
    }

    @Override
    public Location getLocationByID(Integer id) throws LocationPersistenceException {
        String SELECT_LOCATION_BY_ID = null;
        try {
            SELECT_LOCATION_BY_ID = "SELECT * FROM Locations WHERE LocationID = ?";
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
        return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
    }

    @Override
    public Location getLocationForSighting(Integer sightingId) throws LocationPersistenceException { // GOOD

        String SELECT_LOCATION_FOR_SIGHTING = null;
        try {
            SELECT_LOCATION_FOR_SIGHTING = "SELECT L.* FROM Locations L INNER JOIN Sightings S ON L.LocationID = S.LocationID WHERE S.sightingID = ?";
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sightingId);
    }

    @Override
    @Transactional
    public Location addLocation(Location toAdd) throws LocationPersistenceException {
        try {
            final String INSERT_LOCATION = "INSERT INTO Locations(LocationName, Description, Address, Latitude, Longitude)"
                    + "VALUES(?,?,?,?,?)";
            jdbc.update(INSERT_LOCATION,
                    toAdd.getLocationName(),
                    toAdd.getDescription(),
                    toAdd.getAddress(),
                    toAdd.getLatitude(),
                    toAdd.getLongitude());

            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            toAdd.setLocationID(newID);
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
        return toAdd;
    }

    @Override
    @Transactional
    public void deleteLocation(Integer id) throws LocationPersistenceException {
        try {
            final String DELETE_SIGHTING_SUPER = "DELETE FROM SupersSightings WHERE SightingID IN (SELECT SightingID FROM Sightings WHERE LocationID = ?)";
            jdbc.update(DELETE_SIGHTING_SUPER, id);
            
            final String DELETE_LOCATION_AT_SIGHTING = "DELETE FROM Sightings WHERE LocationID = ?";
            jdbc.update(DELETE_LOCATION_AT_SIGHTING, id);

            final String DELETE_LOCATION = "DELETE FROM Locations WHERE LocationID = ?";
            jdbc.update(DELETE_LOCATION, id);
            // will have to delete anywhere where location is assocaiated with first then we can delete location
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
    }

    @Override  // wrong af. have to delete the sighting for th elocation or refresh it
    @Transactional
    public Location editLocation(Location location) throws LocationPersistenceException {
//        final String DELETE_SIGHTING_FOR_LOCATION = "DELETE FROM Sightings WHERE LocationID = ?";
//        jdbc.update(DELETE_SIGHTING_FOR_LOCATION, location.getLocationID());
//        
//        final String ADD_SIGHTING_FOR_LOCATION = "INSERT INTO Sightings (LocationID)"
//                + "VALUES (?,?)";
//             jdbc.update(ADD_SIGHTING_FOR_LOCATION,
//                location.getLocationID());
//       
//        for (Sighting singleSighting : location.getAllSightings()){
//            int sightingID = singleSighting.getSightingID();
//            final String UPDATE_SIGHTINGS_FOR_LOCATION = "UPDATE Sightings SET LocationID = ? WHERE SightingID = ?";
//            jdbc.update(UPDATE_SIGHTINGS_FOR_LOCATION, location.getLocationID(), sightingID);
//        }
        try {
            final String EDIT_LOC = "UPDATE Locations SET LocationName = ?, Description = ?, "
                    + "Address = ?, Latitude = ?, Longitude = ? WHERE LocationID = ?";
            int returnedInt = jdbc.update(EDIT_LOC, location.getLocationName(),
                    location.getDescription(), location.getAddress(), location.getLatitude(),
                    location.getLongitude(), location.getLocationID());

            if (returnedInt < 1) {
                throw new LocationPersistenceException("Failed to match" + location.getLocationID());
            }
            if (returnedInt > 1) {
                throw new LocationPersistenceException("FATAL ERROR, Primary Keys are not unique");
            }
        } catch (DataAccessException ex) {
            throw new LocationPersistenceException("Could not access data.", ex);
        }
        return location;
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setDescription(rs.getString("Description"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));

            return location;
        }
    }
}
