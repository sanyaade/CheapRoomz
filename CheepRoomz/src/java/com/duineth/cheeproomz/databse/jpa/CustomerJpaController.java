/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.databse.jpa;

import com.duineth.cheeproomz.databse.entity.Customer;
import com.duineth.cheeproomz.databse.jpa.exceptions.IllegalOrphanException;
import com.duineth.cheeproomz.databse.jpa.exceptions.NonexistentEntityException;
import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.duineth.cheeproomz.databse.entity.Logindata;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dewmal
 */
public class CustomerJpaController implements Serializable{

    public CustomerJpaController(EntityManagerService ems) {
        emf = ems.get();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logindata logindata = customer.getLogindata();
            if (logindata != null) {
                logindata = em.getReference(logindata.getClass(), logindata.getId());
                customer.setLogindata(logindata);
            }
            em.persist(customer);
            if (logindata != null) {
                Customer oldCustomerOfLogindata = logindata.getCustomer();
                if (oldCustomerOfLogindata != null) {
                    oldCustomerOfLogindata.setLogindata(null);
                    oldCustomerOfLogindata = em.merge(oldCustomerOfLogindata);
                }
                logindata.setCustomer(customer);
                logindata = em.merge(logindata);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getIdcustomer());
            Logindata logindataOld = persistentCustomer.getLogindata();
            Logindata logindataNew = customer.getLogindata();
            List<String> illegalOrphanMessages = null;
            if (logindataOld != null && !logindataOld.equals(logindataNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Logindata " + logindataOld + " since its customer field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (logindataNew != null) {
                logindataNew = em.getReference(logindataNew.getClass(), logindataNew.getId());
                customer.setLogindata(logindataNew);
            }
            customer = em.merge(customer);
            if (logindataNew != null && !logindataNew.equals(logindataOld)) {
                Customer oldCustomerOfLogindata = logindataNew.getCustomer();
                if (oldCustomerOfLogindata != null) {
                    oldCustomerOfLogindata.setLogindata(null);
                    oldCustomerOfLogindata = em.merge(oldCustomerOfLogindata);
                }
                logindataNew.setCustomer(customer);
                logindataNew = em.merge(logindataNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Key id = customer.getIdcustomer();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Key id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getIdcustomer();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Logindata logindataOrphanCheck = customer.getLogindata();
            if (logindataOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Logindata " + logindataOrphanCheck + " in its logindata field has a non-nullable customer field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Customer as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer findCustomer(Key id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Customer as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
