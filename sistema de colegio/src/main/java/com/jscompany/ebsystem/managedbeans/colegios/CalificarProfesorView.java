/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesorMaterias;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
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
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class CalificarProfesorView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
        
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @EJB(beanName = "colegiosServices")
    private ColegiosServices colServices;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private List<AsignacionProfesor> asignacionesProfList;
    private AsignacionProfesor asignacion;
    private Colegio colegio;
    private Materia materiaACalificar;
    private List<AsignacionProfesorMaterias> middleList;
    private List<Materia> materiasList;
    private Rol rol;
    private Persona profesor;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        profesor = (Persona) services.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{uSession.getUsername()});
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(2)});
        asignacionesProfList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorList, new String[]{"profesor"}, new Object[]{profesor});
        
    }
    
    public void calificarMateria(Materia mat){
        materiaACalificar = mat;
        JsfUti.redirectNewTab("/colegionetworksystem/faces/profesor/notas/calificarMateria.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idAsignacionProfesor", new Long(asignacion.getId()));
        utilSession.agregarParametro("idMateria", new Long(materiaACalificar.getId()));
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

    public List<AsignacionProfesor> getAsignacionesProfList() {
        return asignacionesProfList;
    }

    public void setAsignacionesProfList(List<AsignacionProfesor> asignacionesProfList) {
        this.asignacionesProfList = asignacionesProfList;
    }

    public AsignacionProfesor getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionProfesor asignacion) {
        this.asignacion = asignacion;
        materiasList = new ArrayList<>();
        middleList = services.getListEntitiesByParameters(Querys.getAsignacionProfesorMateriaByAsignacionId, new String[]{"idAsigProf"}, new Object[]{this.asignacion});
        for(AsignacionProfesorMaterias temp : middleList){
            materiasList.add(temp.getMateria());
        }
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Persona getProfesor() {
        return profesor;
    }

    public void setProfesor(Persona profesor) {
        this.profesor = profesor;
    }

    public Materia getMateriaACalificar() {
        return materiaACalificar;
    }

    public void setMateriaACalificar(Materia materiaACalificar) {
        this.materiaACalificar = materiaACalificar;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }
    
}
