/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core.anotation;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dewmal
 */
public class Navigation {

    public void navigate(String location) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect(location);
    }

    private Navigation() {
    }

    public static Navigation getInstance() {
        return NavigationHolder.INSTANCE;
    }

    private static class NavigationHolder {

        private static final Navigation INSTANCE = new Navigation();
    }
}
