/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.controller.usecase;

import com.duineth.cheeproomz.core.ScopeAttributeManager;
import com.duineth.cheeproomz.databse.entity.Logindata;
import com.duineth.cheeproomz.databse.jpa.LogindataJpaController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author dewmal
 */
@ManagedBean(name = "ucLogin")
@ViewScoped
public class LoginPanel implements Serializable{

    private ScopeAttributeManager scopeAttributeManager;
    private LogindataJpaController logindataJpaController;
    //
    private String userName;
    private String password;

    /** Creates a new instance of LoginPanel */
    public LoginPanel() {
    }

    @PostConstruct
    private void init() {
    }

    /**
     * 
     * @return
     */
    public String loginAuth() {
        scopeAttributeManager = new ScopeAttributeManager();
        logindataJpaController = new LogindataJpaController(scopeAttributeManager.getEms());
        Logindata logindata = logindataJpaController.findLogindata(userName);
        if (logindata != null) {
            scopeAttributeManager.getRequest().getSession().setAttribute("user", logindata);
            return "loginsuccsess";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name or password", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
