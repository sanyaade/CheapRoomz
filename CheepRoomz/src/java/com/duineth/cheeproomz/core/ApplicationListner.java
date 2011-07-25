/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.core;

import com.duineth.cheeproomz.databse.jpa.EntityManagerService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author dewmal
 */
public class ApplicationListner implements ServletContextListener {

    private MessageManager messageManager;
    private EntityManagerService ems;

    public void contextInitialized(ServletContextEvent sce) {
        messageManager = new MessageManager();
        ems = new EntityManagerService();
        sce.getServletContext().setAttribute("mailsender", messageManager);
        sce.getServletContext().setAttribute("ems", ems);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
