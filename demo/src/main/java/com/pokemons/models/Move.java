/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.models;

/**
 *
 * @author Jacob
 */
public class Move {
    private Integer moveID;
    private String moveName;
    private String type;
    private String category;
    private int damage;
    private int accuracy;
    private int powerPoints;

    /**
     * @return the moveID
     */
    public Integer getMoveID() {
        return moveID;
    }

    /**
     * @param moveID the moveID to set
     */
    public void setMoveID(Integer moveID) {
        this.moveID = moveID;
    }

    /**
     * @return the moveName
     */
    public String getMoveName() {
        return moveName;
    }

    /**
     * @param moveName the moveName to set
     */
    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * @return the powerPoints
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * @param powerPoints the powerPoints to set
     */
    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
