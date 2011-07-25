/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.controller.usecase;

import com.duineth.cheeproomz.core.anotation.Navigation;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.ServletException;

/**
 *
 * @author dewmal
 */
@ManagedBean
@ViewScoped
public class SearchPanaleManage implements Serializable {

    private String destination;
    private Date arivalDate;
    private int numberOfGuests;
    private int numberOfNight;
    private int accommodationType;
    //
    private List<String> list = new ArrayList<String>();

    /** Creates a new instance of SearchPanaleManage */
    public SearchPanaleManage() {
        arivalDate = new Date();
    }

    public String search() {
        try {
            Navigation.getInstance().navigate("searchresult.jsf");
        } catch (ServletException ex) {
            Logger.getLogger(SearchPanaleManage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SearchPanaleManage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the arivalDate
     */
    public Date getArivalDate() {
        return arivalDate;
    }

    /**
     * @param arivalDate the arivalDate to set
     */
    public void setArivalDate(Date arivalDate) {
        this.arivalDate = arivalDate;
    }

    /**
     * @return the numberOfGuests
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * @param numberOfGuests the numberOfGuests to set
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * @return the numberOfNight
     */
    public int getNumberOfNight() {
        return numberOfNight;
    }

    /**
     * @param numberOfNight the numberOfNight to set
     */
    public void setNumberOfNight(int numberOfNight) {
        this.numberOfNight = numberOfNight;
    }

    /**
     * @return the accommodationType
     */
    public int getAccommodationType() {
        return accommodationType;
    }

    /**
     * @param accommodationType the accommodationType to set
     */
    public void setAccommodationType(int accommodationType) {
        this.accommodationType = accommodationType;
    }

    /**
     * @return the list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<String> list) {
        this.list = list;
    }
}
