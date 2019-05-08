/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.SuperDao;
import com.sg.deku.daos.SuperPersistenceException;
import com.sg.deku.models.Super;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AlwaysFailSuperDao implements SuperDao {

    @Override
    public List<Super> getAllSupers() throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public Super getSuperByID(Integer id) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public List<Super> getAllSupersForOrganization(Integer id) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public List<Super> getAllSupersForSighting(Integer id) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public Super addSuper(Super toAdd) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public void deleteSuper(Integer id) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

    @Override
    public Super editSuper(Super sup) throws SuperPersistenceException {
        throw new SuperPersistenceException(null);
    }

}
