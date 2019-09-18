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
public class Nature {
    private Integer natureID;
    private String natureName;
    private String blueStat;
    private String redStat;

    /**
     * @return the natureID
     */
    public Integer getNatureID() {
        return natureID;
    }

    /**
     * @param natureID the natureID to set
     */
    public void setNatureID(Integer natureID) {
        this.natureID = natureID;
    }

    /**
     * @return the natureName
     */
    public String getNatureName() {
        return natureName;
    }

    /**
     * @param natureName the natureName to set
     */
    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    /**
     * @return the blueStat
     */
    public String getBlueStat() {
        return blueStat;
    }

    /**
     * @param blueStat the blueStat to set
     */
    public void setBlueStat(String blueStat) {
        this.blueStat = blueStat;
    }

    /**
     * @return the redStat
     */
    public String getRedStat() {
        return redStat;
    }

    /**
     * @param redStat the redStat to set
     */
    public void setRedStat(String redStat) {
        this.redStat = redStat;
    }
}
