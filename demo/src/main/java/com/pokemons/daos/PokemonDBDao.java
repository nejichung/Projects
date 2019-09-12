/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;


import com.pokemons.models.Pokemon;
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
public class PokemonDBDao implements PokemonDao {
   
    JdbcTemplate jdbc;
    
    @Autowired
    public PokemonDBDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    

    @Override
    public List<Pokemon> getAllPokemon() throws PokePersistenceException {
        List<Pokemon> allPokemon = null;
        try{
            allPokemon = jdbc.query("SELECT * FROM Pokemons", new PokeMapper());
        } catch (DataAccessException ex){
            throw new PokePersistenceException("Could not access data.", ex);
        }
        return allPokemon;
    }

    @Override
    public Pokemon getPokemonByID(Integer id) throws PokePersistenceException {
        String SELECT_POKE_BY_ID = null;
        try{
            SELECT_POKE_BY_ID = "SELECT * FROM Pokemons WHERE pokemonID = ?";
        } catch(DataAccessException ex){
            throw new PokePersistenceException("Could not access data.", ex);

        }
        return jdbc.queryForObject(SELECT_POKE_BY_ID, new PokeMapper(), id);
    }

    @Override
    public List<Pokemon> getAllPokemonForTrainer(Integer id) throws PokePersistenceException {
        String SELECT_POKEMON_FOR_TRAINER = null;
        try{
            SELECT_POKEMON_FOR_TRAINER = "SELECT * FROM Pokemons JOIN TrainersPokemons ON "
            + "Pokemons.PokemonID = TrainersPokemons.PokemonID "
                    + "WHERE TrainersPokemons.TrainerID = ?";        
        } catch (DataAccessException ex){
            throw new PokePersistenceException("Could not access data.", ex); 
        }
        return jdbc.query(SELECT_POKEMON_FOR_TRAINER, new PokeMapper(), id);
    }
    
     public static final class PokeMapper implements RowMapper<Pokemon> {

        @Override
        public Pokemon mapRow(ResultSet rs, int index) throws SQLException {
            Pokemon singlePokemon = new Pokemon(); 
            singlePokemon.setPokemonID(rs.getInt("PokemonID"));
            singlePokemon.setPokemonName(rs.getString("PokemonName"));
            singlePokemon.setGender(rs.getString("Gender"));
            singlePokemon.setDescription(rs.getString("Description"));
            singlePokemon.setNature(rs.getString("Nature"));
            singlePokemon.setLevel(rs.getInt("Level"));
            singlePokemon.setBaseHealthPoints(rs.getInt("BaseHealthPoints"));
            singlePokemon.setBaseAttack(rs.getInt("BaseAttack"));
            singlePokemon.setBaseDefense(rs.getInt("BaseDefense"));
            singlePokemon.setBaseSpecialAttack(rs.getInt("BaseSpecialAttack"));
            singlePokemon.setBaseSpecialDefense(rs.getInt("BaseSpecialDefense"));
            singlePokemon.setBaseSpeed(rs.getInt("BaseSpeed"));
            singlePokemon.setHealthPointEVS(rs.getInt("HealthPointEVS"));
            singlePokemon.setAttackEVS(rs.getInt("AttackEVS"));
            singlePokemon.setDefenseEVS(rs.getInt("DefenseEVS"));
            singlePokemon.setSpecialAttackEVS(rs.getInt("SpecialAttackEVS"));
            singlePokemon.setSpecialDefenseEVS(rs.getInt("SpecialDefenseEVS"));
            singlePokemon.setSpeedEVS(rs.getInt("SpeedEVS"));
            singlePokemon.setHealthPointIVS(rs.getInt("HealthPointIVS"));
            singlePokemon.setAttackIVS(rs.getInt("AttackIVS"));
            singlePokemon.setDefenseIVS(rs.getInt("DefenseIVS"));
            singlePokemon.setSpecialAttackIVS(rs.getInt("SpecialAttackIVS"));
            singlePokemon.setSpecialDefenseIVS(rs.getInt("SpecialDefenseIVS"));
            singlePokemon.setSpeedIVS(rs.getInt("SpeedIVS"));
            singlePokemon.setHealthPoints(rs.getInt("HealthPoints"));
            singlePokemon.setAttack(rs.getInt("Attack"));
            singlePokemon.setDefense(rs.getInt("Defense"));
            singlePokemon.setSpecialAttack(rs.getInt("SpecialAttack"));
            singlePokemon.setSpecialDefense(rs.getInt("SpecialDefense"));
            singlePokemon.setSpeed(rs.getInt("Speed"));
            
            return singlePokemon;
        }
    }
    
}
