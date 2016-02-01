/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
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
public class MasInfoProfView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Long idProfesor;
    private Profesor profesor;
    private List<AsignacionProfesor> asigProfList;
    
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
            if(!utilSession.isNull())
                idProfesor = (Long) utilSession.retornarValor("idProfesor");
            if(idProfesor==null){
                JsfUti.messageError(null, "Error", "Error al cargar la información.");
                return;
            }
            utilSession.borrarDatos();
            
            profesor = (Profesor) services.getEntity(Profesor.class, idProfesor);
            asigProfList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorListByIdProfesor, new String[]{"idProf"}, new Object[]{profesor});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void masInfoAsigProf(AsignacionProfesor asigProf){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/profesores/masinfo.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idAsigProf", asigProf.getId());
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<AsignacionProfesor> getAsigProfList() {
        return asigProfList;
    }

    public void setAsigProfList(List<AsignacionProfesor> asigProfList) {
        this.asigProfList = asigProfList;
    }
    
}
