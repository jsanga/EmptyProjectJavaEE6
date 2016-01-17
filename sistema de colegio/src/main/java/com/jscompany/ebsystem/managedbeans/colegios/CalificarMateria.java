/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
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
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class CalificarMateria implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private List<Matricula> matriculasList;
    private Long idAsignacionProf, idMateria;
    private Materia materia;
    private AsignacionProfesor asignacionProfesor;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        idAsignacionProf = (Long) utilSession.retornarValor("idAsignacionProfesor");
        idMateria = (Long) utilSession.retornarValor("idMateria");
        if(idAsignacionProf == null || idMateria == null){
            JsfUti.messageError(null, "Error", "Error al cargar la información.");
            return;
        }
        //matriculasList = services.getListEntitiesByParameters(Querys.getMatriculasNoState, par, val);
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

    public List<Matricula> getMatriculasList() {
        return matriculasList;
    }

    public void setMatriculasList(List<Matricula> matriculasList) {
        this.matriculasList = matriculasList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public AsignacionProfesor getAsignacionProfesor() {
        return asignacionProfesor;
    }

    public void setAsignacionProfesor(AsignacionProfesor asignacionProfesor) {
        this.asignacionProfesor = asignacionProfesor;
    }
    
}
