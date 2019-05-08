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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryDao implements GameDao {
    
    List<Game> allGames = new ArrayList<>();
    List<Round> allRounds = new ArrayList<>();
    //make some stub games and rounds?
    
    
    
    public InMemoryDao(){
        Game first = new Game();
        first.setGameKeyID(1);
        first.setIsGameOver(false);
        first.setTargetNum(1024);
        allGames.add(first);
        
        Game second = new Game();
        second.setGameKeyID(2);
        second.setIsGameOver(false);
        second.setTargetNum(6789);
        allGames.add(second);
        
        Round firstRound = new Round();
        firstRound.setRoundID(1);
        firstRound.setGameID(1);
        firstRound.setGuessNumber(1347);
        firstRound.setExactCorrect(1);
        firstRound.setPartialCorrect(0);
//        firstRound.setTimeOfGuess("2019-03-10 13:48:12");
        allRounds.add(firstRound);
        
        Round secondRound = new Round();
        secondRound.setRoundID(2);
        secondRound.setGameID(1);
        secondRound.setGuessNumber(4201);
        secondRound.setExactCorrect(0);
        secondRound.setPartialCorrect(4);
        allRounds.add(secondRound);

    }

    @Override
    public Game add(Game toGame) throws PersistenceException { // I think this is fine?
        // need to eventually return a game key id + 1
        List<Game> allGames = getAllGames();
        
         int maxNumber = Integer.MIN_VALUE;
            
            for (int i = 0; i < allGames.size(); i++){
                Game toCheck = allGames.get(i);
                if(toCheck.getGameKeyID() > maxNumber){
                    maxNumber = toCheck.getGameKeyID();
                }
            }
            
        toGame.setGameKeyID(maxNumber + 1);
        
        return toGame;
    }

    @Override
    public Round addRound(Round toRound) throws PersistenceException {
//        allRounds.add(toRound);
           List<Round> allRounds = getAllRounds(toRound.getGameID());
        //1. get all the other rounds for the same game
            int maxNumber = Integer.MIN_VALUE;
            
            for (int i = 0; i < allRounds.size(); i++){
                Round toCheck = allRounds.get(i);
                if(toCheck.getRoundID() > maxNumber){
                    maxNumber = toCheck.getRoundID();
                }
            }
            toRound.setRoundID(maxNumber + 1);
            return toRound;
        //2. generate a new round id (max + 1)
        //3. call the setter on toRound
        //4. return toRound
    }

    @Override
    public List<Game> getAllGames() throws PersistenceException {
       return allGames;
    }

    @Override
    public List<Round> getAllRounds(int gameID) throws PersistenceException { // i dont know if this is good enough
        List<Round> newAllRounds = new ArrayList<>();
        
        Game toGame = getGameById(gameID); // need assistance!
        
//        if(allRounds.contains(gameID)){ 
//            newAllRounds = allRounds.get(gameID);
//        }
        
        
        return allRounds;
    }

    @Override
    public Game getGameById(int gameID) throws PersistenceException {
        Game toReturn = null;
       for (Game toCheck : allGames){
            if(toCheck.getGameKeyID() == gameID){
                toReturn = toCheck;
                break;
            }
        } return toReturn;
    }

    @Override
    public void updateGameStatus(int gameID) throws PersistenceException {
       Game toGame = getGameById(gameID);
       toGame.setIsGameOver(true);
    }

    @Override
    public Game displaySingleGame(int gameID) throws PersistenceException { // also don't know what the inmemory is doing here.
        Game singleGame = getGameById(gameID);
        return singleGame;
    }
    
}
