/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private Persona persona;
    private Rol rol;
    
    @EJB(beanName = "aclService")
    private AclService aclServices;
    
    @EJB(beanName = "usuariosServices")
    private UsuariosServices usuariosServices;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @PostConstruct
    public void init(){
        loguin = new Loguin();
    }
    
    public void validarUsuario(){
        if(loguin.getUsername()==null || loguin.getPass()==null)
            JsfUti.messageError(null, "Error", "No ha ingresado datos.");
        
        if(usuariosServices.validarUsuario(loguin.getUsername(), loguin.getPass())){
            JsfUti.messageInfo(null, "Info", "Usuario encontrado.");
            persona = (Persona) aclServices.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{loguin.getUsername()});
            rol = persona.getRol();
            uSession.setUsername(loguin.getUsername());
            uSession.setRolPersona(rol.getRolName());
            uSession.setIsLogged(true);
        }
        else
            JsfUti.messageError(null, "Error", "Usuario no encontrado.");
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }
    
}
