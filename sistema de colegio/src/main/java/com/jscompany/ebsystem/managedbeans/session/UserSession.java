/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.session;

import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joao Israel
 */
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Persona persona;
    private Rol rolPersona;
    
    public UserSession(){
    
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRolPersona() {
        return rolPersona;
    }

    public void setRolPersona(Rol rolPersona) {
        this.rolPersona = rolPersona;
    }
    
}
