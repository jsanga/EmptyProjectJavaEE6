/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoMaterias;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoParalelos;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.lazymodels.PersonasByRolLazy;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class AsignacionProfesorView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
        
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @EJB(beanName = "colegiosServices")
    private ColegiosServices colServices;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private AsignacionProfesor asignacion;
    private List<AsignacionProfesor> asignacionesProfList;
    private List<AsignacionCurso> asignacionesCursosList;
    private List<Persona> personasListPrueba;
    private AsignacionCurso asigcurso;
    private Paralelo paralelo;
    private PersonasByRolLazy profesoresList;
    private List<Paralelo> paralelosList;
    private List<Materia> materiasList;
    private Colegio colegio;
    private Rol rol;
    
    @PostConstruct
    public void init(){
        if(uSession.getUsername() == null)
            return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(2)});
        asignacionesProfList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorListNoState, new String[]{}, new Object[]{});
        asignacionesCursosList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoList, new String[]{}, new Object[]{});
        profesoresList = new PersonasByRolLazy(colegio, rol);
        //personasListPrueba = services.getListEntitiesByParameters(Querys.getPersonaListByRolAndColegio, new String[]{"rol", "colegio"}, new Object[]{rol, colegio});
    }
    
    public void nuevaAsignacion(){
        asignacion = new AsignacionProfesor();
        asignacion.setEstado(Boolean.TRUE);
        asignacion.setFechaCreacion(new Date());
    }
    
    public void agregarMaterias(AsignacionProfesor ap){
        asignacion = ap;
    }
    
    public void editarAsignacion(AsignacionProfesor ap){
        asignacion = ap;
    }
    
    public void eliminarAsignacion(AsignacionProfesor ap){
        asignacion = ap;
        asignacion.setEstado(Boolean.FALSE);
        services.updateAndPersistEntity(asignacion);
        JsfUti.messageInfo(null, "Info", "Asignación eliminada.");
    }
    
    public void masInfo(AsignacionProfesor ap){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/profesores/masinfo.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idAsigCurso", ap.getId());
    }
    
    public void activarAsignacion(AsignacionProfesor ap){
        asignacion = ap;
        asignacion.setEstado(Boolean.TRUE);
        services.updateAndPersistEntity(asignacion);
        JsfUti.messageInfo(null, "Info", "Asignación habilitada.");
    }
    
    public void onRowSelect(){
        List<AsignacionCursoMaterias> temp1 = services.getListEntitiesByParameters(Querys.getAsigCursoMaterias, new String[]{"asigCurso"}, new Object[]{asigcurso});
        List<AsignacionCursoParalelos> temp2 = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{asigcurso});
        paralelosList = new ArrayList<>();
        materiasList = new ArrayList<>();
        for(AsignacionCursoMaterias t : temp1){
            materiasList.add(t.getMateria());
        }
        for(AsignacionCursoParalelos t : temp2){
            paralelosList.add(t.getParalelo());
        }
        JsfUti.messageInfo(null, "Info", "Curso seleccionado.");
    }
    
    public void onRowSelectParalelo(){
        JsfUti.messageInfo(null, "Info", "Paralelo seleccionado.");
    }
    
    public void onRowSelectMateria(){
        JsfUti.messageInfo(null, "Info", "Materia seleccionada.");
    }
    
    public void onRowSelectProfesor(){
        JsfUti.messageInfo(null, "Info", "Profesor seleccionado.");
    }
    
    public void guardarNuevo(){
        try{            
            if(colServices.crearAsignacionProfesor(asignacion, materiasList)!=null){
                asignacionesProfList.add(asignacion);
                JsfUti.messageInfo(null, "Info", "Se creó la asignación satisfactoriamente");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al crear la asignación.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void guardarEdicion(){
        try{
            if(colServices.actualizarAsignacionProfesor(asignacion))
                JsfUti.messageInfo(null, "Info", "Se editó la asignacion satisfactoriamente");
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al editar la asignacion.");
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

    public List<AsignacionProfesor> getAsignacionesProfList() {
        return asignacionesProfList;
    }

    public void setAsignacionesProfList(List<AsignacionProfesor> asignacionesProfList) {
        this.asignacionesProfList = asignacionesProfList;
    }

    public List<AsignacionCurso> getAsignacionesCursosList() {
        return asignacionesCursosList;
    }

    public void setAsignacionesCursosList(List<AsignacionCurso> asignacionesCursosList) {
        this.asignacionesCursosList = asignacionesCursosList;
    }

    public AsignacionCurso getAsigcurso() {
        return asigcurso;
    }

    public void setAsigcurso(AsignacionCurso asigcurso) {
        this.asigcurso = asigcurso;
    }
    
    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public List<Paralelo> getParalelosList() {
        return paralelosList;
    }

    public void setParalelosList(List<Paralelo> paralelosList) {
        this.paralelosList = paralelosList;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public PersonasByRolLazy getProfesoresList() {
        return profesoresList;
    }

    public void setProfesoresList(PersonasByRolLazy profesoresList) {
        this.profesoresList = profesoresList;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public List<Persona> getPersonasListPrueba() {
        return personasListPrueba;
    }

    public void setPersonasListPrueba(List<Persona> personasListPrueba) {
        this.personasListPrueba = personasListPrueba;
    }
    
}