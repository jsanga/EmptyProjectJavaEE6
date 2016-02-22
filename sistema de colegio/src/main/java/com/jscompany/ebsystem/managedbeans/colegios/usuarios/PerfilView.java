/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios.usuarios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Sexo;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class PerfilView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
        
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Persona persona;
    
    private List<Sexo> sexoList;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
                return;
        persona = (Persona) services.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{uSession.getUsername()});
        sexoList = services.getListEntitiesByParameters(Querys.getSexoList, new String[]{}, new Object[]{});
    }
    
    public void actualizarPersona(){
        if(services.updateAndPersistEntity(persona))
            JsfUti.messageInfo(null, "Info", "Se editó la persona satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la persona.");
    }

    public void irPerfil(){
        if(uSession.getRolPersona().equals("admin"))
            JsfUti.redirectFaces("/faces/admin/perfil.xhtml");
        if(uSession.getRolPersona().equals("estudiante"))
            JsfUti.redirectFaces("/faces/estudiante/perfil.xhtml");
        if(uSession.getRolPersona().equals("profesor"))
            JsfUti.redirectFaces("/faces/profesor/perfil.xhtml");
        if(uSession.getRolPersona().equals("invitado"))
            JsfUti.redirectFaces("/faces/invitado/perfil.xhtml");
    }
    
    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Sexo> getSexoList() {
        return sexoList;
    }

    public void setSexoList(List<Sexo> sexoList) {
        this.sexoList = sexoList;
    }
    
}
