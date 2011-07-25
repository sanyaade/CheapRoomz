/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duineth.cheeproomz.databse.jpa;

import com.duineth.cheeproomz.databse.entity.Logindata;
import com.duineth.cheeproomz.databse.jpa.exceptions.IllegalOrphanException;
import com.duineth.cheeproomz.databse.jpa.exceptions.NonexistentEntityException;
import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.duineth.cheeproomz.databse.entity.Customer;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dewmal
 */
public class LogindataJpaController implements Serializable{

    public LogindataJpaController(EntityManagerService ems) {
        emf = ems.get();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**return true if entered user is availabel
     *
     * @param username
     * @return
     */
    public boolean isUserAvailable(String username) {
        boolean isAvailable = false;
        List<Logindata> loginEntitys = findLogindataEntities();
        L1:
        for (int i = 0; i < loginEntitys.size(); i++) {
            Logindata logindata = loginEntitys.get(i);

            if (username.equalsIgnoreCase(logindata.getCustomer().getEmail())) {
                isAvailable = true;
                break L1;
            }
        }
        return isAvailable;
    }

    public void create(Logindata logindata) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Customer customerOrphanCheck = logindata.getCustomer();
        if (customerOrphanCheck != null) {
            Logindata oldLogindataOfCustomer = customerOrphanCheck.getLogindata();
            if (oldLogindataOfCustomer != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Customer " + customerOrphanCheck + " already has an item of type Logindata whose customer column cannot be null. Please make another selection for the customer field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.persist(logindata);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Logindata logindata) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logindata persistentLogindata = em.find(Logindata.class, logindata.getId());
            Customer customerOld = persistentLogindata.getCustomer();
            Customer customerNew = logindata.getCustomer();
            List<String> illegalOrphanMessages = null;
            if (customerNew != null && !customerNew.equals(customerOld)) {
                Logindata oldLogindataOfCustomer = customerNew.getLogindata();
                if (oldLogindataOfCustomer != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Customer " + customerNew + " already has an item of type Logindata whose customer column cannot be null. Please make another selection for the customer field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (customerNew != null) {
                customerNew = em.getReference(customerNew.getClass(), customerNew.getIdcustomer());
                logindata.setCustomer(customerNew);
            }
            logindata = em.merge(logindata);
            if (customerOld != null && !customerOld.equals(customerNew)) {
                customerOld.setLogindata(null);
                customerOld = em.merge(customerOld);
            }
            if (customerNew != null && !customerNew.equals(customerOld)) {
                customerNew.setLogindata(logindata);
                customerNew = em.merge(customerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Key id = logindata.getId();
                if (findLogindata(id) == null) {
                    throw new NonexistentEntityException("The logindata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Key id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Logindata logindata;
            try {
                logindata = em.getReference(Logindata.class, id);
                logindata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logindata with id " + id + " no longer exists.", enfe);
            }
            Customer customer = logindata.getCustomer();
            if (customer != null) {
                customer.setLogindata(null);
                customer = em.merge(customer);
            }
            em.remove(logindata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Logindata> findLogindataEntities() {
        return findLogindataEntities(true, -1, -1);
    }

    public List<Logindata> findLogindataEntities(int maxResults, int firstResult) {
        return findLogindataEntities(false, maxResults, firstResult);
    }

    private List<Logindata> findLogindataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List<Logindata> logindatas = null;
        try {
            Query q = em.createQuery("select from " + Logindata.class.getName());
            logindatas = q.getResultList();
            logindatas.size();
            return logindatas;
        } finally {
            em.close();
        }
    }

    public Logindata findLogindata(Key id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Logindata.class, id);
        } finally {
            em.close();
        }
    }

    public Logindata findLogindata(String userName) {
        EntityManager em = getEntityManager();
        List<Logindata> logindatas = null;
        try {
            Query q = em.createQuery("select t from " + Logindata.class.getName() + " t where t.username=:value");
            q.setParameter(":value", userName);
            logindatas = q.getResultList();
            int size = logindatas.size();
            if (size == 1) {
                return logindatas.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public int getLogindataCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Logindata as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
