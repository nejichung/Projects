/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceLayer;

import DTOs.Dvd;

/**
 *
 * @author Software Guld
 */
public class AddDvdResponse extends Response {
    // add message?
    Dvd movie;

    /**
     * @return the movie
     */
    public Dvd getMovie() {
        return movie;
    }

    /**
     * @param addedDvd the movie to set
     */
    public void setMovie(Dvd movie) {
        this.movie = movie;
    }

   

   

    
}
