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
public class EditDvdResponse extends Response {
    Dvd editedDvd;

    /**
     * @return the editedDvd
     */
    public Dvd getEditedDvd() {
        return editedDvd;
    }

    /**
     * @param editedDvd the editedDvd to set
     */
    public void setEditedDvd(Dvd editedDvd) {
        this.editedDvd = editedDvd;
    }
    
}
