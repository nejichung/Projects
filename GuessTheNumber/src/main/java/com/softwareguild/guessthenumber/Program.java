/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber;

import com.softwareguild.guessthenumber.service.GuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Software Guld
 */
@SpringBootApplication
public class Program {
    
    @Autowired
    private GuessService service;
    
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }
    
}
