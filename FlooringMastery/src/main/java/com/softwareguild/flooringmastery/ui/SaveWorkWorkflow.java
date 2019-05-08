/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.service.FloorService;

/**
 *
 * @author Software Guld
 */
class SaveWorkWorkflow {
    FloorService service;

    SaveWorkWorkflow(FloorService service) {
        this.service = service;
    }
    public void run(ConsoleIO ui){
        ui.print("SaveWorkWorkflow testing\n");
    }
    
}
