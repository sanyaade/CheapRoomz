/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core.validators;

import com.duineth.cheeproomz.core.ScopeAttributeManager;
import com.duineth.cheeproomz.databse.jpa.EntityManagerService;
import com.duineth.cheeproomz.databse.jpa.LogindataJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validate User's Email Address
 *
 * @author dewmal
 * @since v1
 */
public class UserNameValidator implements Validator {

    private static final Logger logger = Logger.getLogger(UserNameValidator.class.getName());
    private ScopeAttributeManager attributeManager;
    private LogindataJpaController logindataJpaController;

    public UserNameValidator() {
        attributeManager = new ScopeAttributeManager();
        EntityManagerService ems = new ScopeAttributeManager().getEms();
        logindataJpaController = new LogindataJpaController(ems);
    }

    /**Validate User Name
     *
     * @param fc
     * @param uic
     * @param o
     * @throws ValidatorException
     */
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String enteredValue = (String) o;
        logger.log(Level.INFO, "User enterd Value :", new Object[]{enteredValue});
        if (logindataJpaController.isUserAvailable(enteredValue)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Not Available", "Email Not Available");
            throw new ValidatorException(message);
        }
    }
}
