/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.controller.usecase;

import java.util.Date;

/**
 *
 * @author dewmal
 */
public class AdvanceSearch {

    private int guestNumber;
    private int nightDays;
    private double price;
    private Date arivalDate;
    private String destiString;

    /** Creates a new instance of AdvanceSearch */
    public AdvanceSearch() {
    }

    public String updateResult() {
        return null;
    }

    /**
     * @return the guestNumber
     */
    public int getGuestNumber() {
        return guestNumber;
    }

    /**
     * @param guestNumber the guestNumber to set
     */
    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    /**
     * @return the nightDays
     */
    public int getNightDays() {
        return nightDays;
    }

    /**
     * @param nightDays the nightDays to set
     */
    public void setNightDays(int nightDays) {
        this.nightDays = nightDays;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @return the destiString
     */
    public String getDestiString() {
        return destiString;
    }

    /**
     * @param destiString the destiString to set
     */
    public void setDestiString(String destiString) {
        this.destiString = destiString;
    }
}
