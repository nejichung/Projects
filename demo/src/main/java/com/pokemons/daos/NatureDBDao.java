/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

import com.pokemons.models.Nature;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class NatureDBDao implements NatureDao{

    JdbcTemplate jdbc;
    
    @Autowired
    public NatureDBDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    @Override
    public Nature getNatureByID(int natureID) throws NaturePersistenceException {
        String SELECT_NATURE_BY_ID = null;
        try{
            SELECT_NATURE_BY_ID = "SELECT * FROM Natures WHERE NatureID = ?";
        } catch (DataAccessException ex){
            throw new NaturePersistenceException("Could not access data", ex);
        }
        return jdbc.queryForObject(SELECT_NATURE_BY_ID, new NatureMapper(),natureID);
    }
    
    public static final class NatureMapper implements RowMapper<Nature> {

        @Override
        public Nature mapRow(ResultSet rs, int index) throws SQLException {
            Nature singleNature = new Nature();
            singleNature.setNatureID(rs.getInt("NatureID"));
            singleNature.setNatureName(rs.getString("NatureName"));
            singleNature.setBlueStat(rs.getString("BlueStat"));
            singleNature.setRedStat(rs.getString("RedStat"));

            return singleNature;
        }
    }
    
}
