/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.databse.jpa;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dewmal
 */
public class EntityManagerService implements Serializable {

    private final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("CheepRoomzPU");

    public EntityManagerService() {
    }

    public EntityManagerFactory get() {
        return emfInstance;
    }
}
