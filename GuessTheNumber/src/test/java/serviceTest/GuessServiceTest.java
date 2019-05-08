/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceTest;

import com.softwareguild.guessthenumber.daos.PersistenceException;
import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import com.softwareguild.guessthenumber.service.DisplayGamesResponse;
import com.softwareguild.guessthenumber.service.DisplayRoundsResponse;
import com.softwareguild.guessthenumber.service.DisplaySingleGameResponse;
import com.softwareguild.guessthenumber.service.GuessService;
import com.softwareguild.guessthenumber.service.RoundResponse;
import daoTest.AlwaysFailDao;
import daoTest.InMemoryDao;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Software Guld
 */
public class GuessServiceTest {

    public GuessServiceTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void startGameSuccess() throws PersistenceException { // this one returns a game object. How can we go about testing it?
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        
        Game newGame = service.startGame();
        Assert.assertNotNull(newGame);
        
//        int gameID = newGame.getGameKeyID();
//        List<Game> allGames = successDao.getAllGames();
//        Game matchedGame = null;
//        
//        for(Game toCheck : allGames){
//            if(toCheck.getGameKeyID() == gameID){
//                matchedGame = toCheck;
//            }   
//        }
//        Assert.assertNotNull(matchedGame);
    }

    @Test
    public void startGameFail() {
        AlwaysFailDao failDao = new AlwaysFailDao();
        GuessService service = new GuessService(failDao);
        Game newGame = service.startGame();
        Assert.assertNull(newGame);
    }

    @Test
    public void newRoundSuccess() throws PersistenceException { // expand
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        RoundResponse response = service.newRound(1234, 1);
        
//        List<Round> allRounds = successDao.getAllRounds(1);   
        Round matchedRound = response.getRound();
//        for (Round toCheck : allRounds){
//            if(toCheck.getRoundID() == response.getRound().getRoundID()){ // 1
//                matchedRound = toCheck;
//            }
        
        Assert.assertTrue(response.getSuccess());
        Assert.assertEquals(1 , matchedRound.getGameID()); // null
        Assert.assertEquals(1234, matchedRound.getGuessNumber());
        Assert.assertEquals(3, matchedRound.getRoundID());
        
    }

    @Test
    public void newRoundFail() { 
         AlwaysFailDao failDao = new AlwaysFailDao();
        GuessService service = new GuessService(failDao);
        RoundResponse response = service.newRound(1234, 1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void newRoundInvalidGuessNumber() {
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        RoundResponse response = service.newRound(0000, 1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void newRoundSuccessInvalidGameId() {
        InMemoryDao successDao = new InMemoryDao();
       GuessService service = new GuessService(successDao);
        RoundResponse response = service.newRound(1234, 99);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getAllGamesSuccess() { //expand
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        DisplayGamesResponse response = service.getAllGames();
        Assert.assertTrue(response.getSuccess());
        List<Game> allGames = response.getAllGames();
        
        Game checkerino = allGames.get(allGames.size() - 1);
        
        Assert.assertEquals(2, checkerino.getGameKeyID()); // instead of 2 it could be the size
        Assert.assertEquals(false, checkerino.getIsGameOver());
        
    }

    @Test
    public void getAllGamesFail() {
         AlwaysFailDao failDao = new AlwaysFailDao();
        GuessService service = new GuessService(failDao);
        DisplayGamesResponse response = service.getAllGames();
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getAllRoundsSuccess() { // expand
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        DisplayRoundsResponse response = service.getAllRounds(1);
        Assert.assertTrue(response.getSuccess());
        List<Round> allRounds = response.getAllRounds();
        
        Round checkerino = allRounds.get(allRounds.size() -1);
        
        Assert.assertEquals(2, checkerino.getRoundID());
        
    }

    @Test
    public void getAllRoundsFail() {
         AlwaysFailDao failDao = new AlwaysFailDao();
        GuessService service = new GuessService(failDao);
        DisplayRoundsResponse response = service.getAllRounds(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSingleGameSuccess() { // expand
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        DisplaySingleGameResponse response = service.getSingleGame(1);
        Assert.assertTrue(response.getSuccess());
        
    }

    @Test
    public void getSingleGameFail() {
         AlwaysFailDao failDao = new AlwaysFailDao();
        GuessService service = new GuessService(failDao);
        DisplaySingleGameResponse response = service.getSingleGame(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSingleGameInvalidGameId() { 
        InMemoryDao successDao = new InMemoryDao();
        GuessService service = new GuessService(successDao);
        DisplaySingleGameResponse response = service.getSingleGame(99);
        Assert.assertFalse(response.getSuccess());
    }
}
