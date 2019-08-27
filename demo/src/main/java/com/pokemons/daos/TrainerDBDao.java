/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

import com.pokemons.models.Move;
import com.pokemons.models.Trainer;
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
public class TrainerDBDao implements TrainerDao {

    JdbcTemplate jdbc;
    
    @Autowired
    public TrainerDBDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    @Override
    public List<Trainer> getAllTrainers() throws TrainerPersistenceException {
        List<Trainer> allTrainers = null;
     try {
            allTrainers = jdbc.query("SELECT * FROM Trainers", new TrainerMapper());
        } catch (DataAccessException ex) {
            throw new TrainerPersistenceException("Could not access data.", ex);
        }
        return allTrainers;
    }   
    
     public static final class TrainerMapper implements RowMapper<Trainer> {

        @Override
        public Trainer mapRow(ResultSet rs, int index) throws SQLException {
            Trainer singleTrainer = new Trainer();
            singleTrainer.setTrainerID(rs.getInt("TrainerID"));
            singleTrainer.setTrainerName(rs.getString("Name"));
            return singleTrainer;
        }
    }
    }
