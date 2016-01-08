/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entitymanager.Entitymanager;
import com.jscompany.ebsystem.util.EntityManagerServices;
import com.jscompany.ebsystem.util.HiberUtil;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author CarlosLoorVargas
 * @param <T> Entity Class
 */
public class BaseLazyDataModel<T extends Object> extends LazyDataModel<T> {

    private Entitymanager manager;
    private Class<T> entity;
    private int rowCount = 0;
    private String defaultSorted = "id";
    private String defaultSortOrder = "ASC";

    public BaseLazyDataModel() {
        manager =  EntityManagerServices.getTransactionManager();
    }

    public BaseLazyDataModel(Class<T> entity) {
        this.entity = entity;
        manager =  EntityManagerServices.getTransactionManager();
    }

    public BaseLazyDataModel(Class<T> entity, String defaultSorted) {
        this.entity = entity;
        this.defaultSorted = defaultSorted;
        manager =  EntityManagerServices.getTransactionManager();
    }
    
    public BaseLazyDataModel(Class<T> entity, String defaultSorted, String defaultSortOrder) {
        this.entity = entity;
        this.defaultSorted = defaultSorted;
        this.defaultSortOrder = defaultSortOrder;
        manager =  EntityManagerServices.getTransactionManager();
    }

    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        //put your code here
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List result = null;
        Criteria cq, dcq;
        try {
            
            cq = EntityManagerServices.getCurrentSession().createCriteria(this.getEntity(), "entity");
            this.criteriaFilterSetup(cq, filters);
            cq.setProjection(Projections.projectionList().add(Projections.rowCount()));
            dcq = EntityManagerServices.getCurrentSession().createCriteria(this.getEntity(), "entity1");
            this.criteriaFilterSetup(dcq, filters);
            this.criteriaSortSetup(dcq, sortField, sortOrder);
            this.criteriaPageSetup(dcq, first, pageSize);
            rowCount = 0;
            rowCount = ((Long) cq.uniqueResult()).intValue();
            this.setRowCount(rowCount);
            result = dcq.list();
            Hibernate.initialize(result);
        } catch (Exception ex) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public T getRowData(Object key) {
        T ob = null;
        try {
            ob = manager.find(entity, key);
        } catch (Exception e) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public T getRowData(String rowKey) {
        T ob = null;
        try {
            ob = manager.find(entity, Long.parseLong(rowKey));
        } catch (Exception e) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        try {
            if (rowIndex == -1 || getPageSize() == 0) {
                super.setRowIndex(-1);
            } else {
                super.setRowIndex(rowIndex % getPageSize());
            }
        } catch (Exception e) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void criteriaPageSetup(Criteria crit, int first, int pageSize) {
        try {
            crit.setFirstResult(first);
            crit.setMaxResults(pageSize);
        } catch (Exception e) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void criteriaSortSetup(Criteria crit, String field, SortOrder order) {
        try {
            if (field == null) {
                crit.addOrder((defaultSortOrder.equalsIgnoreCase("ASC")) ? Order.asc(defaultSorted) : Order.desc(defaultSorted));
            } else {
                if (order.equals(SortOrder.ASCENDING)) {
                    crit.addOrder(Order.asc(field));
                } else {
                    crit.addOrder(Order.desc(field));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(BaseLazyDataModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Class<T> getEntity() {
        return entity;
    }

    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }

    public String getDefaultSorted() {
        return defaultSorted;
    }

    public void setDefaultSorted(String defaultSorted) {
        this.defaultSorted = defaultSorted;
    }

    public String getDefaultSortOrder() {
        return defaultSortOrder;
    }

    public void setDefaultSortOrder(String defaultSortOrder) {
        this.defaultSortOrder = defaultSortOrder;
    }

    public Entitymanager getManager() {
        return manager;
    }

    public void setManager(Entitymanager manager) {
        this.manager = manager;
    }

}
