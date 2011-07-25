/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.databse.manage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dewmal
 */
public class EMFService {

    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("CheepRoomzPU");

    private EMFService() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;

    }
}
