/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.databse.entity;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dewmal
 */
@Entity
public class Customer implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key idcustomer;
    private String fname;
    private String lname;
    private String email;
    private String contactNumber;
    private Boolean ismale;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String country;
    @Column(nullable = true)
    private String aboutme;
    @Embedded
    @OneToOne(cascade = CascadeType.ALL)
    private Logindata logindata;

    public Customer() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsmale() {
        return ismale;
    }

    public void setIsmale(Boolean ismale) {
        this.ismale = ismale;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public Logindata getLogindata() {
        return logindata;
    }

    public void setLogindata(Logindata logindata) {
        this.logindata = logindata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdcustomer() != null ? getIdcustomer().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.getIdcustomer() == null && other.getIdcustomer() != null) || (this.getIdcustomer() != null && !this.idcustomer.equals(other.idcustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cheeproomzdb.entity.Customer[idcustomer=" + getIdcustomer() + "]";
    }

    /**
     * @return the idcustomer
     */
    public Key getIdcustomer() {
        return idcustomer;
    }

    /**
     * @param idcustomer the idcustomer to set
     */
    public void setIdcustomer(Key idcustomer) {
        this.idcustomer = idcustomer;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
