/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.services;

import java.util.List;
import javax.ejb.Local;
import org.hibernate.Session;

/**
 *
 * @author CarlosLoorVargas
 */
@Local
public interface AclService {

    public Session getSession();
    
    public <T> Object getEntity(Class<T> entity, Object id);

    public Object getEntityByParameters(String query, String[] par, Object[] val);
    
    public Object getEntityByParametersNoProxy(String query, String[] par, Object[] val);

    public <T> List<T> getAllEntities(Class<T> entity);

    public List getListEntitiesByParameters(String query, String[] par, Object[] val);

    public List getListEntitiesMax(String query, String[] par, Object[] val, Integer max);

    public List getListEntitiesNoProxyByParameters(String query, String[] par, Object[] val);

    public <T> List<T> getEntitiesNoProxy(Class<T> entity);

    public Object saveEntity(Object o);

    public Boolean updateEntity(Object o);

    public Object saveAll(Object entity);

    public Boolean updateAndPersistEntity(Object o);

    public Boolean delete(Object o);

    public boolean saveEntities(List entities);
    
    public <T> List<T> getAllOrderedEntities(Class<T> entity, String[] order, Boolean [] prior);
    
    public <T> List<T> getEntitiesOrdNoProxy(Class<T> entity, String[] order, Boolean [] prior);

}
