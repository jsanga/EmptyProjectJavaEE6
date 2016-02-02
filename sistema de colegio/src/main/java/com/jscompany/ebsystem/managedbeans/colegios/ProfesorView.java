/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.lazymodels.PersonasByRolLazy;
import com.jscompany.ebsystem.lazymodels.ProfesoresLazy;
import com.jscompany.ebsystem.managedbeans.Login;
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
public class ProfesorView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Profesor profesor;
    private List<Profesor> profesorList;
    private Persona persona;
    private List<Persona> personasEncontradasList;
    private PersonasByRolLazy personasList;
    private List<Colegio> colegios;
    private Colegio colegio;
    private String cedula;
    private Rol rol;
    private ProfesoresLazy profesores;
    
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
            colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
            rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(2)});
            //personasList = new PersonasByRolLazy(colegio, rol);
            profesores = new ProfesoresLazy(colegio);
            colegios = services.getListEntitiesByParameters(Querys.getColegiosList, new String[]{}, new Object[]{});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void masInfo(Profesor prof){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/profesores/masInfoProf.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idProfesor", prof.getId());
    }
    
    public void buscarPersona(){
        personasEncontradasList = new ArrayList<>();
        
        try{
            persona = (Persona) services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{"cedula"}, new Object[]{cedula});
            
            if(persona != null){
                personasEncontradasList.add(persona);
                JsfUti.messageInfo(null, "Info", "Se encontró la persona.");
            }
            else
                JsfUti.messageError(null, "Error", "No se encontró a la persona.");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void nuevoProfesor(){
        
    }
    
    public void editarProfesor(Profesor prof){
        persona = prof.getPersona();
    }
    
    public void eliminarProfesor(Profesor prof){
        prof.getPersona().setRol(null);
        services.updateAndPersistEntity(prof.getPersona());
        JsfUti.messageInfo(null, "Info", "La persona ya no es profesor de la instituciòn.");
    }
    
    public void ingresarProfesor(Persona p){
        if(services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{"cedula"}, new Object[]{p.getCedula()}) == null){
            p.setRol(rol);
            p.setColegio(colegio);
            services.saveEntity(p);
            personasList = new PersonasByRolLazy(colegio, rol);
            personasEncontradasList.remove(p);
            cedula = "";
            JsfUti.messageInfo(null, "Info", "La persona ahora es un profesor de la institución.");
        }else{
            JsfUti.messageError(null, "Error", "La persona que se trata de ingresar ya es un profesor de la institución.");
        }
    }
    
    public void guardarNuevo(){
        if((profesor = (Profesor) services.saveEntity(profesor)) != null){
            profesorList.add(profesor);
            JsfUti.messageInfo(null, "Info", "Se creó la persona satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la persona.");
    }
    
    public void guardarEdicion(){
        persona.setColegio(colegio);
        if(services.updateAndPersistEntity(persona))
            JsfUti.messageInfo(null, "Info", "Se editó la persona satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la persona.");
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public PersonasByRolLazy getPersonasList() {
        return personasList;
    }

    public void setPersonasList(PersonasByRolLazy personasList) {
        this.personasList = personasList;
    }

    public List<Persona> getPersonasEncontradasList() {
        return personasEncontradasList;
    }

    public void setPersonasEncontradasList(List<Persona> personasEncontradasList) {
        this.personasEncontradasList = personasEncontradasList;
    }

    public List<Colegio> getColegios() {
        return colegios;
    }

    public void setColegios(List<Colegio> colegios) {
        this.colegios = colegios;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ProfesoresLazy getProfesores() {
        return profesores;
    }

    public void setProfesores(ProfesoresLazy profesores) {
        this.profesores = profesores;
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }
    
}