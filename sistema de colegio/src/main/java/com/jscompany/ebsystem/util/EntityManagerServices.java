/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.util;

import com.jscompany.ebsystem.entitymanager.Entitymanager;
import com.jscompany.ebsystem.services.AclService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.hibernate.Session;

/**
 *
 * @author JoaoIsrael
 */
public class EntityManagerServices {
    
    private static Entitymanager manager = null;
    private static Session session = null;
    
    public static Session getCurrentSession(){
        try {
            session = HiberUtil.getSession();
        } catch (Exception e) {
            session = null;
            Logger.getLogger(EntityManagerServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return session;
    }
    
    public static Entitymanager getTransactionManager() {
        try {
            manager = (Entitymanager) new InitialContext().lookup("java:global/colegionetworksystem/manager");
        } catch (Exception e) {
            manager = null;
            Logger.getLogger(EntityManagerServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return manager;
    }
    
    public static AclService aclService(){
        
        try {
            return (AclService)new InitialContext().lookup("java:global/colegionetworksystem/aclService");
        } catch (NamingException ex) {
            Logger.getLogger(EntityManagerServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
