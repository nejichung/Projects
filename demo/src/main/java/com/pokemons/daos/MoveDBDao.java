/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

import com.pokemons.models.Move;
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
public class MoveDBDao implements MoveDao{

    JdbcTemplate jdbc;
    
    @Autowired
    public MoveDBDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    @Override
    public List<Move> getAllMoves() throws MovePersistenceException {
        List<Move> allMoves = null;
     try {
            allMoves = jdbc.query("SELECT * FROM Moves", new MoveMapper());
        } catch (DataAccessException ex) {
            throw new MovePersistenceException("Could not access data.", ex);
        }
        return allMoves;
    }
    
    public static final class MoveMapper implements RowMapper<Move> {

        @Override
        public Move mapRow(ResultSet rs, int index) throws SQLException {
            Move singleMove = new Move();
            singleMove.setMoveID(rs.getInt("MoveID"));
            singleMove.setMoveName(rs.getString("MoveName"));
            singleMove.setDamage(rs.getInt("Damage"));
            singleMove.setAccuracy(rs.getInt("Accuracy"));
            singleMove.setPowerPoints(rs.getInt("PowerPoints"));

            return singleMove;
        }
    }
}