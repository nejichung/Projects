/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.ui.controllers;

import com.softwareguild.guessthenumber.dtos.Game;
import com.softwareguild.guessthenumber.dtos.Round;
import com.softwareguild.guessthenumber.service.DisplayGamesResponse;
import com.softwareguild.guessthenumber.service.DisplayRoundsResponse;
import com.softwareguild.guessthenumber.service.DisplaySingleGameResponse;
import com.softwareguild.guessthenumber.service.GenerateNumberResponse;
import com.softwareguild.guessthenumber.service.GuessService;
import com.softwareguild.guessthenumber.service.RoundResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Software Guld
 */
@RestController
@RequestMapping("/api")
public class GuessTheNumberApiController {

    
    @Autowired
    private GuessService service;
    
    @PostMapping("/begin")
    public Game startGame(){ // do we have to auto generate the game ID
        Game newGame = service.startGame(); // gonna want to call to a service method which will generate the id and number in the same method
        return newGame;
    }
    
    @PostMapping("/guess") // need to work on the time
    public Round guess(int guessNumber, int gameID){  // should be taking in parameters

       RoundResponse response = service.newRound(guessNumber, gameID);
        return response.getRound();
    }
    
    @GetMapping("/games") // good i think
    public List <Game> displayAllGames(){
        DisplayGamesResponse response = service.getAllGames();
        List<Game> allGames = response.getAllGames();
        return allGames;
    }
    
    @GetMapping("/game/{gameID}")
    public Game displaySingleGame(@PathVariable int gameID){
        DisplaySingleGameResponse response = service.getSingleGame(gameID);
        Game singleGame = response.getSingleGame();
        return singleGame;
    }
    @GetMapping("/rounds/{gameID}")
    public List <Round> displayAllRounds(@PathVariable int gameID){
        DisplayRoundsResponse response = service.getAllRounds(gameID);
        List<Round> allRounds = response.getAllRounds();
        return allRounds;
    }
}
