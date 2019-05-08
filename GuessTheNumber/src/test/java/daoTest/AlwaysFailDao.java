/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import com.softwareguild.guessthenumber.daos.GameDao;
import com.softwareguild.guessthenumber.daos.PersistenceException;
import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AlwaysFailDao implements GameDao {

    @Override
    public Game add(Game toGame) throws PersistenceException {
        throw new PersistenceException(null);
    }

    @Override
    public Round addRound(Round toRound) throws PersistenceException {
        throw new PersistenceException(null);
    }

    @Override
    public List<Game> getAllGames() throws PersistenceException {
       throw new PersistenceException(null);
    }

    @Override
    public List<Round> getAllRounds(int gameID) throws PersistenceException {
        throw new PersistenceException(null);
    }

    @Override
    public Game getGameById(int gameID) throws PersistenceException {
        throw new PersistenceException(null);
    }

    @Override
    public void updateGameStatus(int gameID) throws PersistenceException {
        throw new PersistenceException(null);
    }

    @Override
    public Game displaySingleGame(int gameID) throws PersistenceException {
        throw new PersistenceException(null);
    }
    
}
