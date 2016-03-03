/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entitymanager;

import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.ReflexionEntity;
import com.jscompany.ebsystem.util.EntityBeanCopy;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import com.jscompany.ebsystem.util.HiberUtil;

/**
 *
 * @author CarlosLoorVargas
 */
@Stateless(name = "manager")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class TransactionManager implements Entitymanager {

    private Session sess = null;
    private Query q = null;
    private Criteria cq = null;

    @Override
    public <T> T find(Class<T> entity, Object id) {
        T ob = null;
        try {
            sess = HiberUtil.getSession();
            ob = (T) sess.get(entity, (Serializable) id);
            Hibernate.initialize(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public <T> List<T> findAll(Class<T> entity) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            sess.enableFilter("activos");
            cq = sess.createCriteria(entity);
            result = (List) cq.list();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public Object find(String query, String[] par, Object[] val) {
        Object ob = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query).setMaxResults(1);
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            ob = (Object) q.uniqueResult();
            Hibernate.initialize(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public Object findNoProxy(String query, String[] par, Object[] val) {
        Object ob = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query).setMaxResults(1);
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            ob = (Object) q.uniqueResult();
            //Hibernate.initialize(ob);
            ob = EntityBeanCopy.clone(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public Object findUnique(String query, String[] par, Object[] val) {
        Object ob = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query).setMaxResults(1);
            q.setMaxResults(1);
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            ob = (Object) q.uniqueResult();
            Hibernate.initialize(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public List findMax(String query, String[] par, Object[] val, Integer max) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            if (max != null) {
                q.setMaxResults(max);
            }
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            result = (List) q.list();
            result.size();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public List findFirstAndMaxResult(String query, String[] par, Object[] val, Integer first, Integer max) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            if (max != null) {
                q.setMaxResults(max);
            }
            if (first != null) {
                q.setFirstResult(first);
            }
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            result = (List) q.list();
            result.size();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public List findAll(String query) {
        List l = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            l = (List) q.list();
            l.size();
            Hibernate.initialize(l);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
    }

    @Override
    public List findAll(String query, String[] par, Object[] val) {
        List l = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            l = (List) q.list();
            l.size();
            Hibernate.initialize(l);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
    }

    @Override
    public <T> List<T> findAllEntCopy(Class<T> entity) {
        List<T> list = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            list = (List) EntityBeanCopy.clone(cq.list());
            list.size();
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List findAllEntCopy(String query, String[] par, Object[] val) {
        List list = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            for (int i = 0; i < par.length; i++) {
                q.setParameter(par[i], val[i]);
            }
            list = (List) EntityBeanCopy.clone(q.list());
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public List findAllEntCopy(String query) {
        List l = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            l = (List) EntityBeanCopy.clone(q.list());
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
    }

    @Override
    public Object persist(Object o) {
        Object ob = null;
        try {
            HiberUtil.requireTransaction();
            sess = HiberUtil.getSession();
            ob = sess.merge(o);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public Object saveAll(Object entity) {
        Object ob = null;
        try {
            HiberUtil.requireTransaction();
            sess = HiberUtil.getSession();
            ob = sess.save(entity);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public boolean delete(Object o) {
        boolean flag;
        try {
            HiberUtil.requireTransaction();
            sess = HiberUtil.getSession();
            sess.delete(o);
            flag = true;
        } catch (Exception e) {
            flag = false;
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public boolean update(Object o) {
        boolean flag;
        try {
            HiberUtil.requireTransaction();
            sess = HiberUtil.getSession();
            sess.update(o);
            sess.flush();
            flag = true;
        } catch (Exception e) {
            flag = false;
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return flag;
    }

    @Override
    public Object getNativeQuery(String query) {
        Object ob = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createSQLQuery(query);
            ob = (Object) q.uniqueResult();
            Hibernate.initialize(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public Object getNativeQuery(String query, Object[] val) {
        Object ob = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createSQLQuery(query);
            for (int i = 0; i < val.length; i++) {
                q.setParameter(i + 1, val[i]);
            }
            ob = (Object) q.uniqueResult();
            Hibernate.initialize(ob);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public List getSqlQuery(String query) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createSQLQuery(query);
            result = q.list();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public <T> List<T> getSqlQueryParametros(Class<T> clase, String query, String[] params, Object[] values) {
        List<T> result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createSQLQuery(query);
            for (int i = 0; i < params.length; i++) {
                q.setParameter(params[i], values[i]);
            }
            result = q.setResultTransformer(Transformers.aliasToBean(clase)).list();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public List<Object[]> getManyColumnsResults(String query, String[] params, Object[] values) {
        List l = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            for (int i = 0; i < params.length; i++) {
                q.setParameter(params[i], values[i]);
            }
            l = q.list();
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
    }

    @Override
    public List<Object[]> getManyColumnsResults(String query) {
        List l = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            l = q.list();
            Hibernate.initialize(l);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
    }

    @Override
    public Criteria getCriteriaQuery(Class entity, String alias) {
        try {
            sess = HiberUtil.getSession();
            if (alias != null) {
                cq = sess.createCriteria(entity, alias);
            } else {
                cq = sess.createCriteria(entity);
            }
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return cq;
    }

    @Override
    public Session getSession() {
        try {
            sess = HiberUtil.getSession();
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return sess;
    }

    @Override
    public Transaction requiredTransaction() {
        try {
            return HiberUtil.requireTransaction();
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public List findAllByEntities(String query, String[] entitiesNames, Object[] entities) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            for (int i = 0; i < entitiesNames.length; i++) {
                q.setEntity(entitiesNames[i], entities[i]);
            }
            result = q.list();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public boolean saveList(List entities) {
        try {
            //int x = 0;
            HiberUtil.requireTransaction();
            sess = HiberUtil.getSession();
            for (Object entitie : entities) {
                sess.merge(entitie);
            }
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

    @Override
    public Object find(String query) {
        Object result = null;
        try {
            sess = HiberUtil.getSession();
            q = sess.createQuery(query);
            result = q.uniqueResult();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public <T> List<T> findAllOrdered(Class<T> entity, String[] order, Boolean[] prior) {
        List result = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            for (int i = 0; i < order.length; i++) {
                if (prior[i] == true) {
                    cq.addOrder(Order.asc(order[i]));
                } else {
                    cq.addOrder(Order.desc(order[i]));
                }
            }
            result = (List) cq.list();
            Hibernate.initialize(result);
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    @Override
    public <T> List<T> findAllOrdEntCopy(Class<T> entity, String[] order, Boolean[] prior) {
        List<T> list = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            for (int i = 0; i < order.length; i++) {
                if (prior[i] == true) {
                    cq.addOrder(Order.asc(order[i]));
                } else {
                    cq.addOrder(Order.desc(order[i]));
                }
            }
            list = (List) EntityBeanCopy.clone(cq.list());
            list.size();
        } catch (Exception e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public <T> T findEntity(String entity, Long id) {
        T ob = null;
        try {
            sess = HiberUtil.getSession();
            ob = (T) sess.get(ReflexionEntity.entityClass(entity), (Serializable) id);
            Hibernate.initialize(ob);
        } catch (HibernateException e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }

    @Override
    public <T> T findObjectByParameter(Class entity, Map<String, Object> paramt) {
        T ob = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            cq.add(Restrictions.allEq(paramt));
            ob = (T) cq.uniqueResult();
            Hibernate.initialize(ob);
        } catch (HibernateException e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }
    
    @Override
    public <T> List<T> findObjectByParameterList(Class entity, Map<String, Object> paramt) {
        List<T> ob = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            cq.add(Restrictions.allEq(paramt));
            ob = (List<T>) cq.list();
            ob.size();
            Hibernate.initialize(ob);
        } catch (HibernateException e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return ob;
    }
    
    @Override
    public <T> List<T> findAllObjectOrder(Class entity, String[] order, Boolean ascDes) {
        List<T> list = null;
        try {
            sess = HiberUtil.getSession();
            cq = sess.createCriteria(entity);
            for (String ord : order) {
               if(ascDes == null || !ascDes){
                   cq.addOrder(Order.desc(ord));
               }else{
                   cq.addOrder(Order.asc(ord));
               }
            }
            list = cq.list();
            list.size();
            Hibernate.initialize(list);
        } catch (HibernateException e) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

}
