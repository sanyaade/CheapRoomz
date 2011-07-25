/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core;

import com.duineth.cheeproomz.databse.jpa.EntityManagerService;
import com.duineth.cheeproomz.databse.jpa.LogindataJpaController;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dewmal
 */
public class ScopeAttributeManager implements Serializable {

    private MessageManager messageManager;
    private LogindataJpaController logindataJpaController;
    private EntityManagerService ems;
    //
    private FacesContext facesContext;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ScopeAttributeManager() {
    }

    private void initializer() {
        setFacesContext(FacesContext.getCurrentInstance());
        setRequest((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        setResponse((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse());
    }

    /**
     * @return the messageManager
     */
    public MessageManager getMessageManager() {
        initializer();
        messageManager = (MessageManager) getFacesContext().getELContext().getELResolver().getValue(getFacesContext().getELContext(),
                null, "mailsender");
        return messageManager;
    }

    /**
     * @return the logindataJpaController
     */
    public LogindataJpaController getLogindataJpaController() {
        return logindataJpaController;
    }

    /**
     * @param logindataJpaController the logindataJpaController to set
     */
    public void setLogindataJpaController(LogindataJpaController logindataJpaController) {
        this.logindataJpaController = logindataJpaController;
    }

    /**
     * @return the ems
     */
    public EntityManagerService getEms() {
        initializer();
        ems = (EntityManagerService) getFacesContext().getELContext().getELResolver().getValue(getFacesContext().getELContext(), null, "ems");
        return ems;
    }

    /**
     * @param ems the ems to set
     */
    public void setEms(EntityManagerService ems) {
        this.ems = ems;
    }

    /**
     * @return the facesContext
     */
    public FacesContext getFacesContext() {
        return facesContext;
    }

    /**
     * @param facesContext the facesContext to set
     */
    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        initializer();
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {

        this.request = request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        initializer();
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
