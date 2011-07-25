/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core;

import com.duineth.cheeproomz.databse.entity.Logindata;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author dewmal
 */
@ManagedBean
@RequestScoped
public class AuthDetectionInClientArea implements Serializable {

    private ScopeAttributeManager attributeManager;
    private String name;

    public AuthDetectionInClientArea() throws IOException {
        attributeManager = new ScopeAttributeManager();
        Object attribute = attributeManager.getRequest().getAttribute("user");
//        if (attribute == null || !(attribute instanceof Logindata)) {
//            attributeManager.getResponse().sendError(403,"Not allwoed to login");
//        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
