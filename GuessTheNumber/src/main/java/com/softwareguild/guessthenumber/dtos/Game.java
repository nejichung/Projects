/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.dtos;

/**
 *
 * @author Software Guld
 */
public class Game {
    
    private int gameKeyID;
    private int targetNum; // thinka bout this might be string // first number can't be 0. and repeating numbers
    private boolean isGameOver;

    /**
     * @return the gameKeyID
     */
    public int getGameKeyID() {
        return gameKeyID;
    }

    /**
     * @param gameKeyID the gameKeyID to set
     */
    public void setGameKeyID(int gameKeyID) {
        this.gameKeyID = gameKeyID;
    }

    /**
     * @return the targetNum
     */
    public int getTargetNum() {
        return targetNum;
    }

    /**
     * @param targetNum the targetNum to set
     */
    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }

    /**
     * @return the isGameOver
     */
    public boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * @param isGameOver the isGameOver to set
     */
    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
    
}
