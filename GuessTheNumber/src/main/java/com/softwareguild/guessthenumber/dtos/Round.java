/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.dtos;

import java.time.LocalDate;

/**
 *
 * @author Software Guld
 */
public class Round {
    
    private int roundID;
    private int gameID;
    private int guessNumber;
    private int exactCorrect;
    private int partialCorrect;
    
    private String guessResult;
    private java.sql.Timestamp timeOfGuess;

    /**
     * @return the roundID
     */
    public int getRoundID() {
        return roundID;
    }

    /**
     * @param roundID the roundID to set
     */
    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    /**
     * @return the guessNumber
     */
    public int getGuessNumber() {
        return guessNumber;
    }

    /**
     * @param guessNumber the guessNumber to set
     */
    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    /**
     * @return the exactCorrect
     */
    public int getExactCorrect() {
        return exactCorrect;
    }

    /**
     * @param exactCorrect the exactCorrect to set
     */
    public void setExactCorrect(int exactCorrect) {
        this.exactCorrect = exactCorrect;
    }

    /**
     * @return the partialCorrect
     */
    public int getPartialCorrect() {
        return partialCorrect;
    }

    /**
     * @param partialCorrect the partialCorrect to set
     */
    public void setPartialCorrect(int partialCorrect) {
        this.partialCorrect = partialCorrect;
    }

    /**
     * @return the guessResult
     */
    public String getGuessResult() {
         guessResult = "e: " + exactCorrect + " p: " + partialCorrect; // REVAMP THIS LATER
         return guessResult;
    }

    /**
     * @param guessResult the guessResult to set
     */
    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    /**
     * @return the gameID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * @param gameID the gameID to set
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * @return the timeOfGuess
     */
    public java.sql.Timestamp getTimeOfGuess() {
        return timeOfGuess;
    }

    /**
     * @param timeOfGuess the timeOfGuess to set
     */
    public void setTimeOfGuess(java.sql.Timestamp timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }
    
}
