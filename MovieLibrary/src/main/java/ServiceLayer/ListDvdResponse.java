/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceLayer;

import DTOs.Dvd;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class ListDvdResponse extends Response {
    List<Dvd> allMovies;

    /**
     * @return the listedDvd
     */
    public List<Dvd> getAllMovies() {
        return allMovies;
    }

    /**
     * @param allMovies the listedDvd to set
     */
    public void setAllMovies(List<Dvd> allMovies) {
        this.allMovies = allMovies;
    }
    
}
