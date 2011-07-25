/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author dewmal
 */
@ManagedBean(name = "vali")
@ApplicationScoped
public class Validation {

    private String EMAIL_PATTERN = "[a-zA-Z0-9.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9-]+";

    /** Creates a new instance of Validation */
    public Validation() {
    }

    /**
     * @return the EMAIL_PATTERN
     */
    public String getEMAIL_PATTERN() {
        return EMAIL_PATTERN;
    }

    /**
     * @param EMAIL_PATTERN the EMAIL_PATTERN to set
     */
    public void setEMAIL_PATTERN(String EMAIL_PATTERN) {
        this.EMAIL_PATTERN = EMAIL_PATTERN;
    }
}
