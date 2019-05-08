/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Dvd;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface DvdDao {
    public Dvd addDvd(Dvd toAdd);
    
    public boolean removeDvd( int id);
    
    public List<Dvd> listDvds();
    
    public Dvd getDvdById(int id);
    
    public Dvd editDvd(Dvd toEdit);
    
}
