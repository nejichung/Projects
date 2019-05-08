/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.service;

import com.softwareguild.guessthenumber.daos.GameDao;
import com.softwareguild.guessthenumber.daos.PersistenceException;
import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Software Guld
 */
@Service
public class GuessService {

    @Autowired
    GameDao dao;

    public GuessService() {

    }

    //testing constructor
    public GuessService(GameDao dao) {
        this.dao = dao;
    }

    public Game startGame() {
        Game newGame = new Game();
        int randomTargetNumber = randomTargetNumber();
        newGame.setTargetNum(randomTargetNumber);
        newGame.setIsGameOver(false);
        try {
            newGame = dao.add(newGame);

        } catch (PersistenceException ex) { // don't know what to print here
            newGame = null;
        }
        return newGame;

    }

    public RoundResponse newRound(int guessNumber, int gameID) {

        RoundResponse response = new RoundResponse();
        try {

            validateGuessNumber(guessNumber); // we also need to validate the gameID to make sure it exists
            validateGameID(gameID);
            Game newGame = dao.getGameById(gameID);
            Round checkedRound = checkGuessNumberToTargetNumber(guessNumber, newGame.getTargetNum());
            if (checkedRound.getExactCorrect() == 4) {
                // we will need to go to the dao and update the gameTable to is finished
                dao.updateGameStatus(gameID);
            }

            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            checkedRound.setTimeOfGuess(date);
//            java.sql.Timestamp roundTime = new TimeStamp();
//            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//            checkedRound.setTimeOfGuess(df.format(LocalDate.now())); // currently Fails
            checkedRound.setGameID(gameID);
            checkedRound.setGuessNumber(guessNumber);
            dao.addRound(checkedRound); // this should set the round Id in the DAO
            response.setSuccess(true);
            response.setRound(checkedRound);
        } catch (PersistenceException | InvalidGuessNumberException | InvalidGameIdException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);

        }

        return response;
    }

    public DisplayGamesResponse getAllGames() {
        DisplayGamesResponse response = new DisplayGamesResponse();
        List<Game> allGames = null;
        // will probably need to make an if statement to check if the gameis over if it is we make the target number 0
        try {
            allGames = dao.getAllGames();
            for (Game toCheck : allGames) {
                if (toCheck.getIsGameOver() == false) {
                    toCheck.setTargetNum(0);
                    dao.getAllGames();
                }
            }
            response.setSuccess(true);
            response.setAllGames(allGames);

//           throw new UnsupportedOperationException(); // loop thru allgames and check if the game isn't finished. then hide the number or just set to 0
        } catch (PersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayRoundsResponse getAllRounds(int gameID) {
        DisplayRoundsResponse response = new DisplayRoundsResponse();
        List<Round> allRounds = null;
        try {
            allRounds = dao.getAllRounds(gameID);
            response.setSuccess(true);
            response.setAllRounds(allRounds);
        } catch (PersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private int randomTargetNumber() { // pretty srue this is good
        Random rng = new Random();
        int randomTargetNumber = 0;
        int num1 = rng.nextInt(9) + 1; // should have no problem generating it sicne it can't match any other digit
        int num2;
        int num3;
        int num4;
        do {
            num2 = rng.nextInt(10);
        } while (num1 == num2);

        do {
            num3 = rng.nextInt(10);
        } while (num3 == num1 || num3 == num2);

        do {
            num4 = rng.nextInt(10);
        } while (num4 == num1 || num4 == num2 || num4 == num3);

        num1 = num1 * 1000;
        num2 = num2 * 100;
        num3 = num3 * 10;
        num4 = num4 * 1;
        randomTargetNumber = num1 + num2 + num3 + num4;
        return randomTargetNumber;
    }

    private void validateGuessNumber(int guessNumber) throws InvalidGuessNumberException {

        ArrayList<Integer> arr = new ArrayList<Integer>();
        if (guessNumber < 1000 || guessNumber > 9999) {
            throw new InvalidGuessNumberException("That is not a valid Guess Number.");
        }
        do {
            arr.add(guessNumber % 10);   // looked this up online
            guessNumber /= 10;
        } while (guessNumber > 0);
        int num1 = arr.get(3);
        int num2 = arr.get(2);
        int num3 = arr.get(1);
        int num4 = arr.get(0);
// also have to check that there are 4 digits entered
        if (num1 == 0 || num1 == num2 || num1 == num3 || num1 == num4 || num2 == num3
                || num2 == num4 || num3 == num4) {
            throw new InvalidGuessNumberException("That is not a valid Guess Number.");
        }
    }

    private void validateGameID(int gameID) throws InvalidGameIdException, PersistenceException {
//        DisplayGamesResponse response = getAllGames();
//        List<Game> allGames = response.getAllGames();
        List<Game> allGames = dao.getAllGames();
        boolean found = false;

        for (Game currentGame : allGames) {
            if (gameID == currentGame.getGameKeyID()) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new InvalidGameIdException("That gameID does not exist.");
        }

    }

    private Round checkGuessNumberToTargetNumber(int guessNumber, int targetNum) { // comparing 2 arrays. if the  guessArray index is == to ExactArray index, exactcorrect++
        // if guess Array .contains winningarray, partialCorrect ++
        Round rounderino = new Round();
        int exactCorrect = 0;
        int partialCorrect = 0;
        int[] guessArray = new int[4];
        int[] targetArray = new int[4];

        for (int i = 0; i < 4; i++) {
            int guessDigit = guessNumber % 10;
            int targetDigit = targetNum % 10;
            guessNumber /= 10;
            targetNum /= 10;
            guessArray[i] = guessDigit;
            targetArray[i] = targetDigit;
        }

        for (int i = 0; i < 4; i++) {
            if (guessArray[i] == targetArray[i]) { // looping and checking if the index i matches
                exactCorrect++;
            } else {
                for (int j = 0; j < 4; j++) {
                    if (guessArray[i] == targetArray[j]) {
                        partialCorrect++;
                    }
                }
            }
        }
        rounderino.setExactCorrect(exactCorrect);
        rounderino.setPartialCorrect(partialCorrect);
        return rounderino;
    }

    public DisplaySingleGameResponse getSingleGame(int gameID) {
        DisplaySingleGameResponse response = new DisplaySingleGameResponse();
        try {
            validateGameID(gameID);
            //Might have to do an if statement to check if the game is finished then we will need to set the targetnumber to 0
//            Game singleGame = dao.getGameById(gameID); // not sure if necessary
            Game singleGame = dao.displaySingleGame(gameID);
//           if(singleGame.getIsGameOver()== false){
//               singleGame.setTargetNum(0);
//           }
            response.setSingleGame(singleGame);
            response.setSuccess(true);

        } catch (PersistenceException | InvalidGameIdException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
}
