/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.ejb.interfaces.CmisServices;
import com.jscompany.ebsystem.util.ApplicationContextUtils;
import com.jscompany.ebsystem.util.CmisUtil;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author Joao Sanga
 */
@Singleton(name = "cmisEngine")
public class CmisEjb implements CmisServices{
    
    private CmisUtil alfrescoShareEngine;
    
    @PostConstruct
    private void init() {
        alfrescoShareEngine = new CmisUtil("admin", "admin", "http://190.57.138.220:9091/");
    }

    @Override
    public CmisUtil obtenerAlfrescoShareEngine(){
        return alfrescoShareEngine;
    }
    
}
