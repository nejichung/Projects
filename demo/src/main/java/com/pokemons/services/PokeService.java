/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services;

import com.pokemons.daos.ItemDao;
import com.pokemons.daos.ItemPersistenceException;
import com.pokemons.daos.MoveDao;
import com.pokemons.daos.MovePersistenceException;
import com.pokemons.daos.NatureDao;
import com.pokemons.daos.NaturePersistenceException;
import com.pokemons.daos.PokePersistenceException;
import com.pokemons.daos.PokemonDao;
import com.pokemons.daos.TrainerDao;
import com.pokemons.daos.TrainerPersistenceException;
import com.pokemons.models.Item;
import com.pokemons.models.Move;
import com.pokemons.models.Nature;
import com.pokemons.models.Pokemon;
import com.pokemons.models.Trainer;
import com.pokemons.services.responses.DisplayItemsResponse;
import com.pokemons.services.responses.DisplayMovesResponse;
import com.pokemons.services.responses.DisplayPokemonResponse;
import com.pokemons.services.responses.DisplayTrainersResponse;
import com.pokemons.services.responses.ItemDetailsResponse;
import com.pokemons.services.responses.MoveDetailsResponse;
import com.pokemons.services.responses.PokemonDetailsResponse;
import com.pokemons.services.responses.TrainerDetailsResponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacob
 */
@Service
public class PokeService {

    @Autowired
    ItemDao itemDao;
    @Autowired
    MoveDao moveDao;
    @Autowired
    PokemonDao pokeDao;
    @Autowired
    TrainerDao trainerDao;
    @Autowired
    NatureDao natureDao;

    public DisplayPokemonResponse getAllPokemon() {
        DisplayPokemonResponse response = new DisplayPokemonResponse();
        try {
            List<Pokemon> allPokemon = pokeDao.getAllPokemon();
            response.setAllPokemon(allPokemon);
            response.setSuccess(true);
        } catch (PokePersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayMovesResponse getAllMoves() {
        DisplayMovesResponse response = new DisplayMovesResponse();
        try {
            List<Move> allMoves = moveDao.getAllMoves();
            response.setAllMoves(allMoves);
            response.setSuccess(true);
        } catch (MovePersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayTrainersResponse getAllTrainers() {
        DisplayTrainersResponse response = new DisplayTrainersResponse();
        try {
            List<Trainer> allTrainers = trainerDao.getAllTrainers();
            response.setAllTrainers(allTrainers);
            response.setSuccess(true);
        } catch (TrainerPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayItemsResponse getAllItems() {
        DisplayItemsResponse response = new DisplayItemsResponse();
        try {
            List<Item> allItems = itemDao.getAllItems();
            response.setAllItems(allItems);
            response.setSuccess(true);

        } catch (ItemPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public PokemonDetailsResponse getPokemonDetails(Integer id) {
        PokemonDetailsResponse response = new PokemonDetailsResponse();

        try {
            Pokemon singlePokemon = pokeDao.getPokemonByID(id);
            Nature singleNature = natureDao.getNatureByID(singlePokemon.getNatureID());
            singlePokemon.setSingleNature(singleNature);

            calculateStats(singlePokemon);

            Trainer singleTrainer = trainerDao.getTrainerByID(singlePokemon.getTrainerID());
            singlePokemon.setSingleTrainer(singleTrainer);

            List<Move> allMoves = moveDao.getMovesForPokemon(id);
            singlePokemon.setAllMoves(allMoves);

            response.setSinglePokemon(singlePokemon);
            response.setSuccess(true);
        } catch (PokePersistenceException | MovePersistenceException | NaturePersistenceException
                | TrainerPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public MoveDetailsResponse getMoveDetails(Integer id) {
        MoveDetailsResponse response = new MoveDetailsResponse();

        try {
            Move singleMove = moveDao.getMoveByID(id);

            response.setSingleMove(singleMove);
            response.setSuccess(true);
        } catch (MovePersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public ItemDetailsResponse getItemDetails(Integer id) {
        ItemDetailsResponse response = new ItemDetailsResponse();

        try {
            Item singleItem = itemDao.getItemByID(id);

            response.setSingleItem(singleItem);
            response.setSuccess(true);
        } catch (ItemPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public TrainerDetailsResponse getTrainerDetails(Integer id) {
        TrainerDetailsResponse response = new TrainerDetailsResponse();

        try {
            Trainer singleTrainer = trainerDao.getTrainerByID(id);
            List<Pokemon> allPokemon = pokeDao.getAllPokemonForTrainer(id);
            singleTrainer.setAllPokemon(allPokemon);

            List<Item> allItems = itemDao.getAllItemForTrainer(id);
            singleTrainer.setAllItems(allItems);

            response.setSingleTrainer(singleTrainer);
            response.setSuccess(true);
        } catch (TrainerPersistenceException | PokePersistenceException | ItemPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    private void calculateStats(Pokemon singlePokemon) {
        String blueStat = singlePokemon.getSingleNature().getBlueStat(); // increase corresponding stat by 10%
        String redStat = singlePokemon.getSingleNature().getRedStat();  // decrease corresponding stat by 10%
        Integer level = singlePokemon.getLevel();

        Integer hpStat = 0;
        Integer baseHP = singlePokemon.getBaseHealthPoints();
        Integer hpIV = singlePokemon.getHealthPointIVS();
        Integer hpEV = singlePokemon.getHealthPointEVS();

        hpStat = (((2 * baseHP + hpIV + (hpEV / 4)) * level) / 100) + level + 10;

        singlePokemon.setHealthPoints(hpStat);

        Integer atkStat = 0;
        Integer baseAtk = singlePokemon.getBaseAttack();
        Integer atkIV = singlePokemon.getAttackIVS();
        Integer atkEV = singlePokemon.getAttackEVS();

        atkStat = (((2 * baseAtk + atkIV + (atkEV / 4)) * level) / 100) + 5;

        if (blueStat.equalsIgnoreCase("Attack")) {
            atkStat = atkStat * 11;
            atkStat = atkStat / 10;
        }
        if (redStat.equalsIgnoreCase("Attack")) {
            atkStat = atkStat * 9;
            atkStat = atkStat / 10;
        }
        singlePokemon.setAttack(atkStat);

        Integer defStat = 0;
        Integer baseDef = singlePokemon.getBaseDefense();
        Integer defIV = singlePokemon.getDefenseIVS();
        Integer defEV = singlePokemon.getDefenseEVS();

        defStat = (((2 * baseDef + defIV + (defEV / 4)) * level) / 100) + 5;

        if (blueStat.equalsIgnoreCase("Defense")) {
            defStat = defStat * 11;
            defStat = defStat / 10;
        }
        if (redStat.equalsIgnoreCase("Defense")) {
            defStat = defStat * 9;
            defStat = defStat / 10;
        }
        singlePokemon.setDefense(defStat);

        Integer spAtkStat = 0;
        Integer baseSpAtk = singlePokemon.getBaseSpecialAttack();
        Integer spAtkIV = singlePokemon.getSpecialAttackIVS();
        Integer spAtkEV = singlePokemon.getSpecialAttackEVS();

        spAtkStat = (((2 * baseSpAtk + spAtkIV + (spAtkEV / 4)) * level) / 100) + 5;

        if (blueStat.equalsIgnoreCase("SpecialAttack")) {
            spAtkStat = spAtkStat * 11;
            spAtkStat = spAtkStat / 10;
        }
        if (redStat.equalsIgnoreCase("SpecialAttack")) {
            spAtkStat = spAtkStat * 9;
            spAtkStat = spAtkStat / 10;
        }
        singlePokemon.setSpecialAttack(spAtkStat);

        Integer spDefStat = 0;
        Integer baseSpDef = singlePokemon.getBaseSpecialDefense();
        Integer spDefIV = singlePokemon.getSpecialDefenseIVS();
        Integer spDefEV = singlePokemon.getSpecialDefenseEVS();

        spDefStat = (((2 * baseSpDef + spDefIV + (spDefEV / 4)) * level) / 100) + 5;

        if (blueStat.equalsIgnoreCase("SpecialDefense")) {
            spDefStat = spDefStat * 11;
            spDefStat = spDefStat / 10;
        }
        if (redStat.equalsIgnoreCase("SpecialDefense")) {
            spDefStat = spDefStat * 9;
            spDefStat = spDefStat / 10;
        }
        singlePokemon.setSpecialDefense(spDefStat);

        Integer spdStat = 0;
        Integer baseSpd = singlePokemon.getBaseSpeed();
        Integer spdIV = singlePokemon.getSpeedIVS();
        Integer spdEV = singlePokemon.getSpeedEVS();

        spdStat = (((2 * baseSpd + spdIV + (spdEV / 4)) * level) / 100) + 5;

        if (blueStat.equalsIgnoreCase("Speed")) {
            spdStat = spdStat * 11;
            spdStat = spdStat / 10;
        }
        if (redStat.equalsIgnoreCase("Attack")) {
            spdStat = spdStat * 9;
            spdStat = spdStat / 10;
        }
        singlePokemon.setSpeed(spdStat);

    }

}
