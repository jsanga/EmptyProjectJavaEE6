/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.CmisServices;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.LoguinFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaFct;
import com.jscompany.ebsystem.entidades.usuarios.RolFct;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.CmisUtil;
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
    
    @EJB(beanName = "cmisEngine")
    private CmisServices cmisServices;
    
    private LoguinFct loguin;
    private PersonaFct persona;
    private RolFct rol;
    private CmisUtil cmis;
    
    @EJB(beanName = "aclService")
    private AclService aclServices;
    
    @EJB(beanName = "usuariosServices")
    private UsuariosServices usuariosServices;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @PostConstruct
    public void init(){
        try{
            cmis = cmisServices.obtenerAlfrescoShareEngine();
            loguin = new LoguinFct();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void validarUsuario(){
        if(loguin.getUsername()==null || loguin.getPass()==null)
            JsfUti.messageError(null, "Error", "No ha ingresado datos.");
        
        if(usuariosServices.validarUsuario(loguin.getUsername(), loguin.getPass())){
            JsfUti.messageInfo(null, "Info", "Usuario encontrado.");
            persona = (PersonaFct) aclServices.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{loguin.getUsername()});
            uSession.setUsername(loguin.getUsername());
            uSession.setIdPersona(persona.getId());
            uSession.setIsLogged(true);
        }
        else
            JsfUti.messageError(null, "Error", "Usuario no encontrado.");
    }
    
    public void salir(){
        uSession.cerrarSesion();
    }
    
    public void irIndex(){
        JsfUti.redirectFaces("/");
    }
    
    public void guardarLoguin(){
        aclServices.saveEntity(loguin);
    }

    

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public LoguinFct getLoguin() {
        return loguin;
    }

    public void setLoguin(LoguinFct loguin) {
        this.loguin = loguin;
    }
    
}
