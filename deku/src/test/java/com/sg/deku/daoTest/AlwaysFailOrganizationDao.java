/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.OrganizationDao;
import com.sg.deku.daos.OrganizationPersistenceException;
import com.sg.deku.models.Organization;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AlwaysFailOrganizationDao implements OrganizationDao {

    @Override
    public List<Organization> getAllOrganizations() throws OrganizationPersistenceException {
        throw new OrganizationPersistenceException(null);
    }

    @Override
    public Organization getOrganizationByID(Integer id) throws OrganizationPersistenceException {
        throw new OrganizationPersistenceException(null);
    }

    @Override
    public List<Organization> getAllOrganizationsForSuper(Integer id) throws OrganizationPersistenceException {
        throw new OrganizationPersistenceException(null);
    }

    @Override
    public Organization addOrganization(Organization toAdd) throws OrganizationPersistenceException {
        throw new OrganizationPersistenceException(null);
    }

    @Override
    public Organization editOrg(Organization org) throws OrganizationPersistenceException {
        throw new OrganizationPersistenceException(null);
    }

    @Override
    public void deleteOrganization(Integer id) throws OrganizationPersistenceException {
         throw new OrganizationPersistenceException(null);
    }

}
