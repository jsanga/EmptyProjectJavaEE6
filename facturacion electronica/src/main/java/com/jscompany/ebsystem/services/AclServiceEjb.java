/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.services;


import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.entitymanager.Entitymanager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Session;

/**
 *
 * @author CarlosLoorVargas
 */
@Stateless(name = "aclService")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class AclServiceEjb implements AclService {

    @EJB(beanName = "manager")
    private Entitymanager manager;

    //**************************************************************************
    //*************************** MÉTODOS GENÉRICOS ****************************
    //**************************************************************************
    
    @Override
    public Session getSession() {
        return manager.getSession();
    }
    
    @Override
    public <T> Object getEntity(Class<T> entity, Object id) {
        try {
            return manager.find(entity, id);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    @Override
    public Object getEntityByParametersNoProxy(String query, String[] par, Object[] val){
        Object o;
        try {
            o = manager.findNoProxy(query, par, val);
        } catch (Exception e) {
            o = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return o;
    }

    @Override
    public Object getEntityByParameters(String query, String[] par, Object[] val) {
        Object o;
        try {
            o = manager.find(query, par, val);
        } catch (Exception e) {
            o = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return o;
    }

    @Override
    public <T> List<T> getAllEntities(Class<T> entity) {
        try {
            return manager.findAll(entity);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public List getListEntitiesByParameters(String query, String[] par, Object[] val) {
        List list = null;
        try {
            list = manager.findAll(query, par, val);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List getListEntitiesMax(String query, String[] par, Object[] val, Integer max) {
        List list;
        try {
            list = manager.findMax(query, par, val, max);
        } catch (Exception e) {
            list = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List getListEntitiesNoProxyByParameters(String query, String[] par, Object[] val) {
        List list;
        try {
            list = manager.findAllEntCopy(query, par, val);
        } catch (Exception e) {
            list = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public <T> List<T> getEntitiesNoProxy(Class<T> entity) {
        try {
            return manager.findAllEntCopy(entity);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Object saveEntity(Object o) {
        Object ob;
        try {
            ob = manager.persist(o);
        } catch (Exception e) {
            ob = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public Boolean updateEntity(Object o) {
        try {
            manager.update(o);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public Object saveAll(Object entity) {
        Object o;
        try {
            o = manager.saveAll(entity);
        } catch (Exception e) {
            o = null;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return o;
    }

    @Override
    public Boolean updateAndPersistEntity(Object o) {
        try {
            manager.persist(o);
        } catch (Exception e) {
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Object o) {
        Boolean flag;
        try {
            flag = manager.delete(o);
        } catch (Exception e) {
            flag = false;
            Logger.getLogger(AclServiceEjb.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public boolean saveEntities(List entities) {
        return manager.saveList(entities);
    }

    @Override
    public <T> List<T> getAllOrderedEntities(Class<T> entity, String[] order, Boolean [] prior) {
        return manager.findAllOrdered(entity, order, prior);
    }

    @Override
    public <T> List<T> getEntitiesOrdNoProxy(Class<T> entity, String[] order, Boolean[] prior) {
        return manager.findAllOrdEntCopy(entity, order, prior);
    }

}
