/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.models;

import java.util.List;

/**
 *
 * @author Jacob
 */
public class Pokemon {
    private Integer pokemonID;
    private String pokemonName;
    private String gender;
    private String description;
    
    private Integer level;
    private Integer baseHealthPoints;
    private Integer baseAttack;
    private Integer baseDefense;
    private Integer baseSpecialAttack;
    private Integer baseSpecialDefense;
    private Integer baseSpeed;
    private Integer healthPoints;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Integer healthPointEVS;
    private Integer attackEVS;
    private Integer defenseEVS;
    private Integer specialAttackEVS;
    private Integer specialDefenseEVS;
    private Integer speedEVS;
    private Integer healthPointIVS;
    private Integer attackIVS;
    private Integer defenseIVS;
    private Integer specialAttackIVS;
    private Integer specialDefenseIVS;
    private Integer speedIVS;
    
    private Integer natureID;
    private Integer trainerID;
    private Trainer singleTrainer;
    private Nature singleNature;
    private List<Move> allMoves;

    /**
     * @return the pokemonID
     */
    public Integer getPokemonID() {
        return pokemonID;
    }

    /**
     * @param pokemonID the pokemonID to set
     */
    public void setPokemonID(Integer pokemonID) {
        this.pokemonID = pokemonID;
    }

    /**
     * @return the pokemonName
     */
    public String getPokemonName() {
        return pokemonName;
    }

    /**
     * @param pokemonName the pokemonName to set
     */
    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the baseHealthPoints
     */
    public Integer getBaseHealthPoints() {
        return baseHealthPoints;
    }

    /**
     * @param baseHealthPoints the baseHealthPoints to set
     */
    public void setBaseHealthPoints(Integer baseHealthPoints) {
        this.baseHealthPoints = baseHealthPoints;
    }

    /**
     * @return the baseAttack
     */
    public Integer getBaseAttack() {
        return baseAttack;
    }

    /**
     * @param baseAttack the baseAttack to set
     */
    public void setBaseAttack(Integer baseAttack) {
        this.baseAttack = baseAttack;
    }

    /**
     * @return the baseDefense
     */
    public Integer getBaseDefense() {
        return baseDefense;
    }

    /**
     * @param baseDefense the baseDefense to set
     */
    public void setBaseDefense(Integer baseDefense) {
        this.baseDefense = baseDefense;
    }

    /**
     * @return the baseSpecialAttack
     */
    public Integer getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    /**
     * @param baseSpecialAttack the baseSpecialAttack to set
     */
    public void setBaseSpecialAttack(Integer baseSpecialAttack) {
        this.baseSpecialAttack = baseSpecialAttack;
    }

    /**
     * @return the baseSpecialDefense
     */
    public Integer getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    /**
     * @param baseSpecialDefense the baseSpecialDefense to set
     */
    public void setBaseSpecialDefense(Integer baseSpecialDefense) {
        this.baseSpecialDefense = baseSpecialDefense;
    }

    /**
     * @return the baseSpeed
     */
    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * @param baseSpeed the baseSpeed to set
     */
    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    /**
     * @return the healthPoints
     */
    public Integer getHealthPoints() {
        return healthPoints;
    }

    /**
     * @param healthPoints the healthPoints to set
     */
    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * @return the attack
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     * @return the defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * @return the specialAttack
     */
    public Integer getSpecialAttack() {
        return specialAttack;
    }

    /**
     * @param specialAttack the specialAttack to set
     */
    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    /**
     * @return the specialDefense
     */
    public Integer getSpecialDefense() {
        return specialDefense;
    }

    /**
     * @param specialDefense the specialDefense to set
     */
    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    /**
     * @return the speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    /**
     * @return the healthPointEVS
     */
    public Integer getHealthPointEVS() {
        return healthPointEVS;
    }

    /**
     * @param healthPointEVS the healthPointEVS to set
     */
    public void setHealthPointEVS(Integer healthPointEVS) {
        this.healthPointEVS = healthPointEVS;
    }

    /**
     * @return the attackEVS
     */
    public Integer getAttackEVS() {
        return attackEVS;
    }

    /**
     * @param attackEVS the attackEVS to set
     */
    public void setAttackEVS(Integer attackEVS) {
        this.attackEVS = attackEVS;
    }

    /**
     * @return the defenseEVS
     */
    public Integer getDefenseEVS() {
        return defenseEVS;
    }

    /**
     * @param defenseEVS the defenseEVS to set
     */
    public void setDefenseEVS(Integer defenseEVS) {
        this.defenseEVS = defenseEVS;
    }

    /**
     * @return the specialAttackEVS
     */
    public Integer getSpecialAttackEVS() {
        return specialAttackEVS;
    }

    /**
     * @param specialAttackEVS the specialAttackEVS to set
     */
    public void setSpecialAttackEVS(Integer specialAttackEVS) {
        this.specialAttackEVS = specialAttackEVS;
    }

    /**
     * @return the specialDefenseEVS
     */
    public Integer getSpecialDefenseEVS() {
        return specialDefenseEVS;
    }

    /**
     * @param specialDefenseEVS the specialDefenseEVS to set
     */
    public void setSpecialDefenseEVS(Integer specialDefenseEVS) {
        this.specialDefenseEVS = specialDefenseEVS;
    }

    /**
     * @return the speedEVS
     */
    public Integer getSpeedEVS() {
        return speedEVS;
    }

    /**
     * @param speedEVS the speedEVS to set
     */
    public void setSpeedEVS(Integer speedEVS) {
        this.speedEVS = speedEVS;
    }

    /**
     * @return the healthPointIVS
     */
    public Integer getHealthPointIVS() {
        return healthPointIVS;
    }

    /**
     * @param healthPointIVS the healthPointIVS to set
     */
    public void setHealthPointIVS(Integer healthPointIVS) {
        this.healthPointIVS = healthPointIVS;
    }

    /**
     * @return the attackIVS
     */
    public Integer getAttackIVS() {
        return attackIVS;
    }

    /**
     * @param attackIVS the attackIVS to set
     */
    public void setAttackIVS(Integer attackIVS) {
        this.attackIVS = attackIVS;
    }

    /**
     * @return the defenseIVS
     */
    public Integer getDefenseIVS() {
        return defenseIVS;
    }

    /**
     * @param defenseIVS the defenseIVS to set
     */
    public void setDefenseIVS(Integer defenseIVS) {
        this.defenseIVS = defenseIVS;
    }

    /**
     * @return the specialAttackIVS
     */
    public Integer getSpecialAttackIVS() {
        return specialAttackIVS;
    }

    /**
     * @param specialAttackIVS the specialAttackIVS to set
     */
    public void setSpecialAttackIVS(Integer specialAttackIVS) {
        this.specialAttackIVS = specialAttackIVS;
    }

    /**
     * @return the specialDefenseIVS
     */
    public Integer getSpecialDefenseIVS() {
        return specialDefenseIVS;
    }

    /**
     * @param specialDefenseIVS the specialDefenseIVS to set
     */
    public void setSpecialDefenseIVS(Integer specialDefenseIVS) {
        this.specialDefenseIVS = specialDefenseIVS;
    }

    /**
     * @return the speedIVS
     */
    public Integer getSpeedIVS() {
        return speedIVS;
    }

    /**
     * @param speedIVS the speedIVS to set
     */
    public void setSpeedIVS(Integer speedIVS) {
        this.speedIVS = speedIVS;
    }

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
     * @return the trainerID
     */
    public Integer getTrainerID() {
        return trainerID;
    }

    /**
     * @param trainerID the trainerID to set
     */
    public void setTrainerID(Integer trainerID) {
        this.trainerID = trainerID;
    }

    /**
     * @return the singleTrainer
     */
    public Trainer getSingleTrainer() {
        return singleTrainer;
    }

    /**
     * @param singleTrainer the singleTrainer to set
     */
    public void setSingleTrainer(Trainer singleTrainer) {
        this.singleTrainer = singleTrainer;
    }

    /**
     * @return the singleNature
     */
    public Nature getSingleNature() {
        return singleNature;
    }

    /**
     * @param singleNature the singleNature to set
     */
    public void setSingleNature(Nature singleNature) {
        this.singleNature = singleNature;
    }

    /**
     * @return the allMoves
     */
    public List<Move> getAllMoves() {
        return allMoves;
    }

    /**
     * @param allMoves the allMoves to set
     */
    public void setAllMoves(List<Move> allMoves) {
        this.allMoves = allMoves;
    }

    /**
     * @return the pokemonID
     */
    
}
