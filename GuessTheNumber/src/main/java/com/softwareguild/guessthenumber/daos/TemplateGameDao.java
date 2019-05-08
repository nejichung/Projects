/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.daos;

import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Software Guld
 */
@Component
public class TemplateGameDao implements GameDao{
    //java.sql.timestamp FOR TIME FOR THE ROUND DTO EVENTUALLY
    //@Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    public TemplateGameDao(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
   
    
    public Game add(Game toGame){
        
        final String sql = "INSERT INTO Games(IsGameOver, TargetNumber) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, toGame.getIsGameOver());
            statement.setInt(2, toGame.getTargetNum());
            return statement;
        }, keyHolder);
        toGame.setGameKeyID(keyHolder.getKey().intValue());
        
        return toGame;
    }
    
    public List<Round> getAllRounds(int gameID){ 
        List<Round> allRounds = jdbc.query("SELECT * FROM Rounds WHERE GameKeyID = ? ORDER BY TimeOfGuess", new roundMapper(), gameID);
        return allRounds;
    }

     public Round addRound(Round toRound){
        
        final String sql = "INSERT INTO Rounds(GameKeyID, GuessNum, ExactCorrect, PartialCorrect, GuessResult, TimeOfGuess) VALUES(?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, toRound.getGameID()); 
            statement.setInt(2, toRound.getGuessNumber());
            statement.setInt(3, toRound.getExactCorrect());
            statement.setInt(4, toRound.getPartialCorrect());
            statement.setString(5, toRound.getGuessResult());
            statement.setTimestamp(6, toRound.getTimeOfGuess());
            return statement;
        }, keyHolder);
        toRound.setRoundID(keyHolder.getKey().intValue());
        
        return toRound;
    }

    @Override // needs to list the rounds as well so will need to join the tables
    public List<Game> getAllGames() { // !!need  to if IsGameOver is true can show the targetnumber but if not they hide it..
        List<Game> allGames = jdbc.query("SELECT Games.GameKeyID, Games.TargetNumber, Games.IsGameOver FROM Games LEFT JOIN Rounds ON Rounds.GameKeyID = Games.GameKeyID", new gameMapper()); // may need to set the target number to null or something. Right nwo it's 0
        return allGames;
    }

    @Override
    public Game getGameById(int gameID) {
        List<Game> allGames = jdbc.query("SELECT * FROM Games", new gameMapper());
        Game toReturn = null;
        for (Game toCheck : allGames){
            if(toCheck.getGameKeyID() == gameID){
                toReturn = toCheck;
                break;
            }
        } return toReturn;
    }

    @Override
    public void updateGameStatus(int gameID) {
        jdbc.update("UPDATE Games SET IsGameOver = ? WHERE GameKeyID = ?", 1, gameID); // 1 = true
        
    }

    @Override
    public Game displaySingleGame(int gameID) { 
        List<Game> allGames = jdbc.query("SELECT GameKeyID, TargetNumber, IsGameOver FROM Games WHERE GameKeyID = ?", new gameMapper(), gameID);
        Game singleGame = allGames.get(0);
        
        return singleGame;
    }

    private static final class gameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game toGame = new Game();
            toGame.setGameKeyID(rs.getInt("GameKeyID"));
            toGame.setTargetNum(rs.getInt("TargetNumber"));
            toGame.setIsGameOver(rs.getBoolean("IsGameOver"));
            return toGame;
        }
    }
     private static final class roundMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round toRound = new Round();
            toRound.setRoundID(rs.getInt("RoundID"));
            toRound.setGameID(rs.getInt("GameKeyID"));
            toRound.setGuessNumber(rs.getInt("GuessNum"));
            toRound.setPartialCorrect(rs.getInt("PartialCorrect"));
            toRound.setExactCorrect(rs.getInt("ExactCorrect"));
            toRound.setGuessResult(rs.getString("GuessResult"));
            toRound.setTimeOfGuess(rs.getTimestamp("TimeOfGuess"));
            return toRound;
            
        }
    }
}
