/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
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
public class MasInfoAsigProfView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private AsignacionProfesor asignacion;
    private Persona profesor;
    private AsignacionCurso curso;
    private Paralelo paralelo;
    private List<Materia> materias;
    
    private Long idAsigProf;
    
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
            if(!utilSession.isNull())
                idAsigProf = (Long) utilSession.retornarValor("idAsigProf");
            if(idAsigProf==null){
                JsfUti.messageError(null, "Error", "Error al cargar la información.");
                return;
            }
            utilSession.borrarDatos();
            asignacion = (AsignacionProfesor) services.getEntity(AsignacionProfesor.class, idAsigProf);
            profesor = asignacion.getProfesor().getPersona();
            curso = asignacion.getAsignacionCurso();
            paralelo = asignacion.getParalelo();
            materias = new ArrayList<>();
            /*for(AsignacionProfesorMaterias temp : (List<AsignacionProfesorMaterias>) services.getListEntitiesByParameters(Querys.getAsignacionProfesorMateriaByAsignacionId, new String[]{"asigProf"}, new Object[]{asignacion})){
                materias.add(temp.getMateria());
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public AsignacionProfesor getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionProfesor asignacion) {
        this.asignacion = asignacion;
    }

    public Persona getProfesor() {
        return profesor;
    }

    public void setProfesor(Persona profesor) {
        this.profesor = profesor;
    }

    public AsignacionCurso getCurso() {
        return curso;
    }

    public void setCurso(AsignacionCurso curso) {
        this.curso = curso;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public Long getIdAsigProf() {
        return idAsigProf;
    }

    public void setIdAsigProf(Long idAsigProf) {
        this.idAsigProf = idAsigProf;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }
    
}
