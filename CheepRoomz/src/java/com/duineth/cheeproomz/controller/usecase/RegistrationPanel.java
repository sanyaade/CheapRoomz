/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.controller.usecase;

import com.duineth.cheeproomz.core.MessageManager;
import com.duineth.cheeproomz.core.ScopeAttributeManager;
import com.duineth.cheeproomz.core.SecureDigitsHandler;
import com.duineth.cheeproomz.databse.entity.Customer;
import com.duineth.cheeproomz.databse.entity.Logindata;
import com.duineth.cheeproomz.databse.jpa.CustomerJpaController;
import com.duineth.cheeproomz.databse.jpa.LogindataJpaController;
import com.duineth.cheeproomz.databse.jpa.exceptions.IllegalOrphanException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;

/**
 *
 * @author dewmal
 */
@ManagedBean(name = "ucRegistration")
@ViewScoped
public class RegistrationPanel implements Serializable{

    private CustomerJpaController controller;
    private LogindataJpaController logindataJpaController;
    //
    private Customer customer;
    private Logindata logindata;
    private MessageManager messageManager;
    private boolean userAcceptTNC = true;
//

    /** Creates a new instance of RegistrationPanel */
    public RegistrationPanel() {
    }

    @PostConstruct
    private void init() {
        setCustomer(new Customer());
        setLogindata(new Logindata());

    }

    public String registration() {
        logindataJpaController = new LogindataJpaController(new ScopeAttributeManager().getEms());
        messageManager = new ScopeAttributeManager().getMessageManager();
        if (userAcceptTNC) {
            try {

                logindata.setPassword(new SecureDigitsHandler().getSeureCode(logindata.getPassword()));
                logindata.setUsername(customer.getEmail());
                logindata.setCustomer(customer);
                //
                logindataJpaController.create(logindata);
                //
                messageManager.sendEmail("sudeshanieranthika@gmail.com", "Admin", customer.getEmail(),
                        customer.getFname(), "Thank for Registration", "Hi " + customer.getFname() + "User name" + customer.getEmail() + "<p></p>"
                        + "Password your entered one");
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(RegistrationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegistrationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(RegistrationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(RegistrationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please accept T & C", null);
            FacesContext.getCurrentInstance().addMessage("sbcAcceptTnC", message);
        }
        return "regsucsess";
    }

    public void validateSamePassword(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String confirmPassword = (String) value;
        System.out.println(confirmPassword);
        System.out.println(logindata.getPassword());
        String pw = (String) toValidate.findComponent("regForm:itPassword").getAttributes().get("value");
        //
        if (!confirmPassword.equals(pw)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", "Passwords do not match!");
            throw new ValidatorException(message);
        }
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        if (customer == null) {
            customer = new Customer();
        }
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the logindata
     */
    public Logindata getLogindata() {
        if (logindata == null) {
            logindata = new Logindata();
        }
        return logindata;
    }

    /**
     * @param logindata the logindata to set
     */
    public void setLogindata(Logindata logindata) {
        this.logindata = logindata;
    }

    /**
     * @return the userAcceptTNC
     */
    public boolean isUserAcceptTNC() {
        return userAcceptTNC;
    }

    /**
     * @param userAcceptTNC the userAcceptTNC to set
     */
    public void setUserAcceptTNC(boolean userAcceptTNC) {
        this.userAcceptTNC = userAcceptTNC;
    }
}
