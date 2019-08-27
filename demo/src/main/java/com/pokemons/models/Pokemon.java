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
    private String nature;
    private int level;
    private int baseHealthPoints;
    private int baseAttack;
    private int baseDefense;
    private int baseSpecialAttack;
    private int baseSpecialDefense;
    private int baseSpeed;
    private int healthPoints;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private int healthPointEVS;
    private int attackEVS;
    private int defenseEVS;
    private int specialAttackEVS;
    private int specialDefenseEVS;
    private int speedEVS;
    private int healthPointIVS;
    private int attackIVS;
    private int defenseIVS;
    private int specialAttackIVS;
    private int specialDefenseIVS;
    private int speedIVS;
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
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @return the healthPoints
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * @param healthPoints the healthPoints to set
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @return the specialAttack
     */
    public int getSpecialAttack() {
        return specialAttack;
    }

    /**
     * @param specialAttack the specialAttack to set
     */
    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    /**
     * @return the specialDefense
     */
    public int getSpecialDefense() {
        return specialDefense;
    }

    /**
     * @param specialDefense the specialDefense to set
     */
    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the healthPointEVS
     */
    public int getHealthPointEVS() {
        return healthPointEVS;
    }

    /**
     * @param healthPointEVS the healthPointEVS to set
     */
    public void setHealthPointEVS(int healthPointEVS) {
        this.healthPointEVS = healthPointEVS;
    }

    /**
     * @return the attackEVS
     */
    public int getAttackEVS() {
        return attackEVS;
    }

    /**
     * @param attackEVS the attackEVS to set
     */
    public void setAttackEVS(int attackEVS) {
        this.attackEVS = attackEVS;
    }

    /**
     * @return the defenseEVS
     */
    public int getDefenseEVS() {
        return defenseEVS;
    }

    /**
     * @param defenseEVS the defenseEVS to set
     */
    public void setDefenseEVS(int defenseEVS) {
        this.defenseEVS = defenseEVS;
    }

    /**
     * @return the specialAttackEVS
     */
    public int getSpecialAttackEVS() {
        return specialAttackEVS;
    }

    /**
     * @param specialAttackEVS the specialAttackEVS to set
     */
    public void setSpecialAttackEVS(int specialAttackEVS) {
        this.specialAttackEVS = specialAttackEVS;
    }

    /**
     * @return the specialDefenseEVS
     */
    public int getSpecialDefenseEVS() {
        return specialDefenseEVS;
    }

    /**
     * @param specialDefenseEVS the specialDefenseEVS to set
     */
    public void setSpecialDefenseEVS(int specialDefenseEVS) {
        this.specialDefenseEVS = specialDefenseEVS;
    }

    /**
     * @return the speedEVS
     */
    public int getSpeedEVS() {
        return speedEVS;
    }

    /**
     * @param speedEVS the speedEVS to set
     */
    public void setSpeedEVS(int speedEVS) {
        this.speedEVS = speedEVS;
    }

    /**
     * @return the healthPointIVS
     */
    public int getHealthPointIVS() {
        return healthPointIVS;
    }

    /**
     * @param healthPointIVS the healthPointIVS to set
     */
    public void setHealthPointIVS(int healthPointIVS) {
        this.healthPointIVS = healthPointIVS;
    }

    /**
     * @return the attackIVS
     */
    public int getAttackIVS() {
        return attackIVS;
    }

    /**
     * @param attackIVS the attackIVS to set
     */
    public void setAttackIVS(int attackIVS) {
        this.attackIVS = attackIVS;
    }

    /**
     * @return the defenseIVS
     */
    public int getDefenseIVS() {
        return defenseIVS;
    }

    /**
     * @param defenseIVS the defenseIVS to set
     */
    public void setDefenseIVS(int defenseIVS) {
        this.defenseIVS = defenseIVS;
    }

    /**
     * @return the specialAttackIVS
     */
    public int getSpecialAttackIVS() {
        return specialAttackIVS;
    }

    /**
     * @param specialAttackIVS the specialAttackIVS to set
     */
    public void setSpecialAttackIVS(int specialAttackIVS) {
        this.specialAttackIVS = specialAttackIVS;
    }

    /**
     * @return the specialDefenseIVS
     */
    public int getSpecialDefenseIVS() {
        return specialDefenseIVS;
    }

    /**
     * @param specialDefenseIVS the specialDefenseIVS to set
     */
    public void setSpecialDefenseIVS(int specialDefenseIVS) {
        this.specialDefenseIVS = specialDefenseIVS;
    }

    /**
     * @return the speedIVS
     */
    public int getSpeedIVS() {
        return speedIVS;
    }

    /**
     * @param speedIVS the speedIVS to set
     */
    public void setSpeedIVS(int speedIVS) {
        this.speedIVS = speedIVS;
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
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the baseHealthPoints
     */
    public int getBaseHealthPoints() {
        return baseHealthPoints;
    }

    /**
     * @param baseHealthPoints the baseHealthPoints to set
     */
    public void setBaseHealthPoints(int baseHealthPoints) {
        this.baseHealthPoints = baseHealthPoints;
    }

    /**
     * @return the baseAttack
     */
    public int getBaseAttack() {
        return baseAttack;
    }

    /**
     * @param baseAttack the baseAttack to set
     */
    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    /**
     * @return the baseDefense
     */
    public int getBaseDefense() {
        return baseDefense;
    }

    /**
     * @param baseDefense the baseDefense to set
     */
    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    /**
     * @return the baseSpecialAttack
     */
    public int getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    /**
     * @param baseSpecialAttack the baseSpecialAttack to set
     */
    public void setBaseSpecialAttack(int baseSpecialAttack) {
        this.baseSpecialAttack = baseSpecialAttack;
    }

    /**
     * @return the baseSpecialDefense
     */
    public int getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    /**
     * @param baseSpecialDefense the baseSpecialDefense to set
     */
    public void setBaseSpecialDefense(int baseSpecialDefense) {
        this.baseSpecialDefense = baseSpecialDefense;
    }

    /**
     * @return the baseSpeed
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * @param baseSpeed the baseSpeed to set
     */
    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }
}
