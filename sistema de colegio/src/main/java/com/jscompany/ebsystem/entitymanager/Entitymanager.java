/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entitymanager;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CarlosLoorVargas
 */
@Local
public interface Entitymanager {

    public <T extends Object> T find(Class<T> entity, Object id);

    public <T extends Object> List<T> findAll(Class<T> entity);

    public Object find(String query, String[] par, Object[] val);

    public Object findUnique(String query, String[] par, Object[] val);

    public List findMax(String query, String[] par, Object[] val, Integer max);

    public List findAll(String query);

    public Object findNoProxy(String query, String[] par, Object[] val);

    public List findAll(String query, String[] par, Object[] val);

    public <T> List<T> findAllEntCopy(Class<T> entity);

    public List findAllEntCopy(String query, String[] par, Object[] val);

    public List findAllEntCopy(String query);

    public Object persist(Object o);

    public Object saveAll(Object entity);

    public boolean delete(Object o);

    public boolean update(Object o);

    public Object getNativeQuery(String query);

    public Object getNativeQuery(String query, Object[] val);

    public List getSqlQuery(String query);

    public <T> List<T> getSqlQueryParametros(Class<T> clase, String query, String[] params, Object[] values);

    public List<Object[]> getManyColumnsResults(String query, String[] params, Object[] values);

    public List<Object[]> getManyColumnsResults(String query);

    public Criteria getCriteriaQuery(Class entity, String alias);

    public Session getSession();

    public Transaction requiredTransaction();

    public List findAllByEntities(String query, String[] entitiesNames, Object[] entities);

    public boolean saveList(List entities);

    public Object find(String query);

    public List findFirstAndMaxResult(String query, String[] par, Object[] val, Integer first, Integer max);

    public <T> List<T> findAllOrdered(Class<T> entity, String[] order, Boolean[] prior);

    public <T> List<T> findAllOrdEntCopy(Class<T> entity, String[] order, Boolean[] prior);

    /**
     * Busca la Entiti en el paquete "com.origami.sgm.entities" si no existe
     * retorna nulo, caso contrario la entity si existe en la base de datos
     *
     * @param <T>
     * @param entity Nombre de Entity
     * @param id Id de la tabla.
     * @return Entity.
     */
    public <T> T findEntity(String entity, Long id);

    public <T> T findObjectByParameter(Class entity, Map<String, Object> paramt);

    /**
     * Por default el order es desc
     *
     * @param <T>
     * @param entity Entiti class
     * @param order Nombre de Los campos a ordenar
     * @param ascDes True ordena Asc, False o Null Desc.
     * @return
     */
    public <T> List<T> findAllObjectOrder(Class entity, String[] order, Boolean ascDes);

    public <T> List<T> findObjectByParameterList(Class entity, Map<String, Object> paramt);

}
