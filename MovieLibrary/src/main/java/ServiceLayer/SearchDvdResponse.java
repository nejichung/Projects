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
public class SearchDvdResponse extends Response {
    Dvd searchedDvd;

    /**
     * @return the searchedDvd
     */
    public Dvd getSearchedDvd() {
        return searchedDvd;
    }

    /**
     * @param searchedDvd the searchedDvd to set
     */
    public void setSearchedDvd(Dvd searchedDvd) {
        this.searchedDvd = searchedDvd;
    }
    
}
