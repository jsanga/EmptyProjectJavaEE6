/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.entidades.entidadesUsuarios.Loguin;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class Login implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private Loguin loguin;
    
    @EJB(beanName = "aclService")
    protected AclService aclServices;
    
    @PostConstruct
    public void init(){
        loguin = new Loguin();
        loguin.setUsername("Joao");
    }
    
    public void cambiarValor(){
        loguin.setUsername("Israel");
        JsfUti.update("frmMain");
    }
    
    public void guardarLoguin(){
        aclServices.saveEntity(loguin);
    }

    public Loguin getLoguin() {
        return loguin;
    }

    public void setLoguin(Loguin loguin) {
        this.loguin = loguin;
    }
    
}
