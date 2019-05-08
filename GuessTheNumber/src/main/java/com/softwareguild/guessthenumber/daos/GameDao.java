/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.daos;

import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public interface GameDao {
    public Game add(Game toGame) throws PersistenceException ;

    public Round addRound(Round toRound) throws PersistenceException;

    public List<Game> getAllGames() throws PersistenceException;

    public List<Round> getAllRounds(int gameID) throws PersistenceException;

    public Game getGameById(int gameID) throws PersistenceException;

    public void updateGameStatus(int gameID) throws PersistenceException;

    public Game displaySingleGame(int gameID) throws PersistenceException;
}
