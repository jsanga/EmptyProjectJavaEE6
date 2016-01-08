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

    private String username;
    private String rolPersona;
    private Boolean isLogged = false;
    
    public void cerrarSesion(){
        username = null;
        rolPersona = null;
        isLogged = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolPersona() {
        return rolPersona;
    }

    public void setRolPersona(String rolPersona) {
        this.rolPersona = rolPersona;
    }

    public Boolean getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }
    
}