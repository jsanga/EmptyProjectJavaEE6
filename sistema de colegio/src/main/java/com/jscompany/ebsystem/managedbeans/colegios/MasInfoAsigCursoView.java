/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MasInfoAsigCursoView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private AsignacionCurso ac;
    private Long idAsigCurso;
    
    @PostConstruct
    public void init(){
        try{
            if(!utilSession.isNull())
                idAsigCurso = (Long) utilSession.retornarValor("idAsigCurso");
            if(idAsigCurso==null){
                JsfUti.messageError(null, "Error", "Error al cargar la información.");
                return;
            }
            utilSession.borrarDatos();
            ac = (AsignacionCurso) services.getEntity(AsignacionCurso.class, idAsigCurso);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void masInfoProf(AsignacionProfesor ap){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/profesores/masinfo.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idAsigProf", ap.getId());
    }
    
    public AsignacionCurso getAc() {
        return ac;
    }

    public void setAc(AsignacionCurso ac) {
        this.ac = ac;
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public Long getIdAsigCurso() {
        return idAsigCurso;
    }

    public void setIdAsigCurso(Long idAsigCurso) {
        this.idAsigCurso = idAsigCurso;
    }
    
}
