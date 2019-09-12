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

    @Override
    public Move getMoveByID(Integer id) throws MovePersistenceException {
        String SELECT_MOVE_BY_ID = null;
        try{
            SELECT_MOVE_BY_ID = "SELECT * FROM Moves WHERE moveID = ?";
        } catch(DataAccessException ex){
            throw new MovePersistenceException("Could not access data.", ex);

        }
        return jdbc.queryForObject(SELECT_MOVE_BY_ID, new MoveMapper(), id);
    }

    @Override
    public List<Move> getMovesForPokemon(Integer id) throws MovePersistenceException {
        String SELECT_MOVES_FOR_POKEMON = null;
        try{
            SELECT_MOVES_FOR_POKEMON = "SELECT * FROM Moves JOIN PokemonsMoves ON "
                    + "Moves.MoveID = PokemonsMoves.MoveID "
                    + "WHERE PokemonsMoves.PokemonID = ?";
        } catch (DataAccessException ex){
            throw new MovePersistenceException("Could not access data", ex);
        }
        return jdbc.query(SELECT_MOVES_FOR_POKEMON, new MoveMapper(), id);
    }
    
    
    public static final class MoveMapper implements RowMapper<Move> {

        @Override
        public Move mapRow(ResultSet rs, int index) throws SQLException {
            Move singleMove = new Move();
            singleMove.setMoveID(rs.getInt("MoveID"));
            singleMove.setMoveName(rs.getString("MoveName"));
            singleMove.setType(rs.getString("type"));
            singleMove.setCategory(rs.getString("category"));
            singleMove.setDamage(rs.getInt("Damage"));
            singleMove.setAccuracy(rs.getInt("Accuracy"));
            singleMove.setPowerPoints(rs.getInt("PowerPoints"));

            return singleMove;
        }
    }
}
