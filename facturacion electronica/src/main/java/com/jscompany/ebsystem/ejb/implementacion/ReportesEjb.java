/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.ReportesServices;
import com.jscompany.ebsystem.services.AclService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author JoaoIsrael
 */
@Stateless(name = "reportesServices")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class ReportesEjb implements ReportesServices{
    
    @EJB(beanName = "aclService")
    protected AclService services;
    
}
