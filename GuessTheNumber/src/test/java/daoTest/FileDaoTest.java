/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.softwareguild.guessthenumber.daos.GameDao;
import com.softwareguild.guessthenumber.daos.PersistenceException;
import com.softwareguild.guessthenumber.daos.TemplateGameDao;
import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Software Guld
 */
public class FileDaoTest {
    
    @Autowired
    private JdbcTemplate jdbc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception { //set up ???
       MysqlDataSource ds = new MysqlDataSource();
       ds.setServerName("localhost");
       ds.setDatabaseName("guessGameTest");
       ds.setUser("root");
       ds.setPassword("Interestaeng7!");
       ds.setServerTimezone("America/Chicago");
       ds.setUseSSL(false);
       ds.setAllowPublicKeyRetrieval(true);
       
        
        jdbc = new JdbcTemplate(ds);
        
        
        jdbc.update("DELETE FROM Rounds");
        jdbc.update("DELETE FROM Games");
        jdbc.update("INSERT INTO Games(GameKeyID, IsGameOver, TargetNumber)"
                + " VALUES ('1','0','1024')");
        jdbc.update("INSERT INTO Rounds(RoundID, GameKeyId, GuessNum, ExactCorrect, "
                + "PartialCorrect, GuessResult, TimeOfGuess)"
                + " VALUES ('1', '1', '2690', '0', '2', 'e: 0 p: 2','2019-03-10 13:48:12')");
        
        
         // make sample data
// deleteall the rows and then insert the rows that you want to test with
//    "DELETE * FROM Games"
// make a test SQL database and do the delete and stuff
    }

    @After
    public void tearDown() {
    }

    @Test
    public void add() throws PersistenceException {
        //jdbc template lesson read for this stuff
        GameDao successDao = new TemplateGameDao(jdbc);
        
        Game toAdd = new Game();
        toAdd.setGameKeyID(2);
        toAdd.setIsGameOver(false);
        toAdd.setTargetNum(4569);
        Game testGame = successDao.add(toAdd);
        List<Game> allGames = successDao.getAllGames(); // could not figure out why it's returning null
        
//        Assert.assertEquals(2, testGame.getGameKeyID()); // everytime i run this method the actual gameID increases
        Assert.assertEquals(false, testGame.getIsGameOver());
        Assert.assertEquals(4569, testGame.getTargetNum());
        Assert.assertEquals(2, allGames.size());

    }

    @Test
    public void addRound() throws PersistenceException {
        GameDao successDao = new TemplateGameDao(jdbc);
        Round toAdd = new Round();
        toAdd.setRoundID(10);
        toAdd.setGameID(1);
        toAdd.setGuessNumber(1234);
        toAdd.setExactCorrect(2);
        toAdd.setPartialCorrect(1);
        // do i have to set time added and guessResult?
        Round testRound = successDao.addRound(toAdd);
        List<Round> allRounds = successDao.getAllRounds(1);
      
//        Assert.assertEquals(10, testRound.getRoundID());
//        Assert.assertEquals(1, testRound.getGameID());
        Assert.assertEquals(1234, testRound.getGuessNumber());
        Assert.assertEquals(2, testRound.getExactCorrect());
        Assert.assertEquals(1, testRound.getPartialCorrect());
        Assert.assertEquals(2, allRounds.size());

    }

    @Test
    public void getAllGames() throws PersistenceException {
        GameDao successDao = new TemplateGameDao(jdbc);
        List<Game> allGames = successDao.getAllGames();
        Assert.assertNotNull(allGames);
        
        Game toGame = allGames.get(0);
        Assert.assertEquals(1, toGame.getGameKeyID());
        Assert.assertEquals(false, toGame.getIsGameOver());
        Assert.assertEquals(1024, toGame.getTargetNum());
        
    }

    @Test
    public void getAllRounds() throws PersistenceException {
        GameDao successDao = new TemplateGameDao(jdbc);
        List<Round> allRounds = successDao.getAllRounds(1);
        Assert.assertNotNull(allRounds);
        
        Round toRound = allRounds.get(0);
        Assert.assertEquals(1, toRound.getRoundID());
        Assert.assertEquals(1, toRound.getGameID());
        Assert.assertEquals(2690, toRound.getGuessNumber());
        Assert.assertEquals(0, toRound.getExactCorrect());
        Assert.assertEquals(2, toRound.getPartialCorrect());
        List<Round> noRounds = successDao.getAllRounds(1000);
        Assert.assertEquals(0, noRounds.size());
        
    }

    @Test
    public void getGameById() throws PersistenceException {
        GameDao successDao = new TemplateGameDao(jdbc);
        Game yolo = successDao.getGameById(1);
        Assert.assertNotNull(yolo);
        
        Assert.assertEquals(1, yolo.getGameKeyID());
        Assert.assertEquals(false, yolo.getIsGameOver());
        Assert.assertEquals(1024, yolo.getTargetNum());
         
    }

    @Test
    public void updateGameStatus() throws PersistenceException {
        GameDao successDao = new TemplateGameDao(jdbc);
        successDao.updateGameStatus(1);
        Game toGame = successDao.getGameById(1);
        
        Assert.assertEquals(true, toGame.getIsGameOver());
    }

    @Test
    public void displaySingleGame() throws PersistenceException { 
         // work on this What should I be doing here? I think this is redundant
         GameDao successDao = new TemplateGameDao(jdbc);
         Game singleGame = successDao.displaySingleGame(1);
         Assert.assertNotNull(singleGame);
         
         
         
    }

}
