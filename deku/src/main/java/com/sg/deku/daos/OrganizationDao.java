/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Organization;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface OrganizationDao {

    public List<Organization> getAllOrganizations() throws OrganizationPersistenceException;

    public Organization getOrganizationByID(Integer id) throws OrganizationPersistenceException;

    public List<Organization> getAllOrganizationsForSuper(Integer id) throws OrganizationPersistenceException;

    public Organization addOrganization(Organization toAdd) throws OrganizationPersistenceException;

    public Organization editOrg(Organization org) throws OrganizationPersistenceException;

    public void deleteOrganization(Integer id) throws OrganizationPersistenceException;
    
}
