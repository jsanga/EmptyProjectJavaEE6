/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoMaterias;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.DetalleMateria;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
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
public class VerNotasView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Long idMatricula;
    private Matricula matricula;
    private List<AsignacionProfesor> asigProfList;
    private List<DetalleMateria> detalleMateriaList;
    private DetalleMateria detalleMateria;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        if(utilSession.isNull() || utilSession.estaVacio()){
            JsfUti.messageError(null, "Error", "Error al cargar la información.");
            return;
        }
        idMatricula = (Long) utilSession.retornarValor("idMatricula");
        
        matricula = (Matricula) services.getEntity(Matricula.class, idMatricula);
        asigProfList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorByAsignacionCursoAndParaleloList, new String[]{"asigCurso", "paralelo"}, new Object[]{matricula.getAsignacionCurso(), matricula.getParalelo()});
        detalleMateriaList = (List<DetalleMateria>) matricula.getDetalleMateriaCollection();
        utilSession.borrarDatos();
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

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public List<AsignacionProfesor> getAsigProfList() {
        return asigProfList;
    }

    public void setAsigProfList(List<AsignacionProfesor> asigProfList) {
        this.asigProfList = asigProfList;
    }

    public List<DetalleMateria> getDetalleMateriaList() {
        return detalleMateriaList;
    }

    public void setDetalleMateriaList(List<DetalleMateria> detalleMateriaList) {
        this.detalleMateriaList = detalleMateriaList;
    }

    public DetalleMateria getDetalleMateria() {
        return detalleMateria;
    }

    public void setDetalleMateria(DetalleMateria detalleMateria) {
        this.detalleMateria = detalleMateria;
    }
    
}
