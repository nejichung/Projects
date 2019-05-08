/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Jacob
 */
public class Organization {

    private Integer organizationID;

    @Size(max = 30, message = "Name must be less than 30 characters.")
    @NotBlank(message = "Name must not be blank")
    private String organizationName;

    @Size(max = 50, message = "Description must be less than 50 characters.")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Size(max = 60, message = "Address must be less than 60 characters.")
    @NotBlank(message = "Address must not be blank")
    private String address;
    @Size(max = 13, message ="Phone Number must not be greater 13 characters")
    @NotBlank(message = "Phone Number must not be blank")
    private String phoneNumber;
    
    @Email
    @Size(min = 3, max = 30, message = "Email Address must be between 3 and 30 characters.")
//    @NotBlank(message = "Email Address must not be blank")
    private String emailAddress;
    private Integer superID;
    private List<Super> allSupers;
    
    
    public boolean hasSup(int superID){
        boolean found = false;
        for(Super toCheck : allSupers){
            if( toCheck.getSuperID() == superID){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * @return the organizationID
     */
    public Integer getOrganizationID() {
        return organizationID;
    }

    /**
     * @param organizationID the organizationID to set
     */
    public void setOrganizationID(Integer organizationID) {
        this.organizationID = organizationID;
    }

    /**
     * @return the organizationName
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * @param organizationName the organizationName to set
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the superID
     */
    public Integer getSuperID() {
        return superID;
    }

    /**
     * @param superID the superID to set
     */
    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the allSupers
     */
    public List<Super> getAllSupers() {
        return allSupers;
    }

    /**
     * @param allSupers the allSupers to set
     */
    public void setAllSupers(List<Super> allSupers) {
        this.allSupers = allSupers;
    }

}
