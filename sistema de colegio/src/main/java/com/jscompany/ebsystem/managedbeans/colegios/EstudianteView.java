/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.RelacionPersona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.entidades.usuarios.TipoRelacionPersona;
import com.jscompany.ebsystem.lazymodels.PersonasByRolLazy;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.math.BigInteger;
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
public class EstudianteView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Estudiante estudiante;
    private List<Estudiante> estudianteList;
    private Persona persona, personaTemp;
    private List<Persona> personasEncontradasList;
    private PersonasByRolLazy personasList;
    private List<Colegio> colegios;
    private Colegio colegio;
    private String cedula;
    private Rol rol;
    private RelacionPersona relacion;
    private TipoRelacionPersona tipoRelacion;
    
    private List<TipoRelacionPersona> tipoRelacionList;
    
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(3)});
        personasList = new PersonasByRolLazy(colegio, rol);
        colegios = services.getListEntitiesByParameters(Querys.getColegiosList, new String[]{}, new Object[]{});
        tipoRelacionList = services.getListEntitiesByParameters(Querys.getRelacionesList, new String[]{}, new Object[]{});
    }
    
    public void buscarPersona(){
        personasEncontradasList = new ArrayList<>();
        
        try{
            personaTemp = (Persona) services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{"cedula"}, new Object[]{cedula});
            if(persona.equals(personaTemp)){
                personaTemp = null;
                JsfUti.messageError(null, "Error", "No se puede agregar a sí mismo.");
                return;
            }
            if(persona != null){
                personasEncontradasList.add(personaTemp);
                JsfUti.messageInfo(null, "Info", "Se encontró la persona.");
            }
            else
                JsfUti.messageError(null, "Error", "No se encontró a la persona.");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void nuevoEstudiante(){
    
    }
    
    public void agregarRelacion(Persona personaRelacionada){
        if(tipoRelacion==null){
            JsfUti.messageError(null, "Error", "Debe seleccionar el tipo de relación.");
            return;
        }
        relacion = new RelacionPersona();
        relacion.setEstado(Boolean.TRUE);
        relacion.setPersona(BigInteger.valueOf(persona.getId()));
        relacion.setPersonaEs(BigInteger.valueOf(personaRelacionada.getId()));
        relacion.setTipoRelacion(tipoRelacion);
        services.saveEntity(relacion);
        JsfUti.messageInfo(null, "Info", personaRelacionada.getNombres().toUpperCase() + " "+ personaRelacionada.getApellidos().toUpperCase() +" ahora es "+tipoRelacion.getNombreRelacion()+" de "+persona.getNombres().toUpperCase()+ " "+persona.getApellidos().toUpperCase()+".");
        personasEncontradasList = null;
        relacion = null;
    }
    
    public void eliminarRelacion(RelacionPersona relacion){
        relacion.setEstado(Boolean.FALSE);
        services.updateAndPersistEntity(relacion);
    }
    
    public void editarEstudiante(Persona est){
        persona = est;
        colegio = persona.getColegio();
    }
    
    public void eliminarEstudiante(Persona est){
        est.setRol(null);
        services.updateAndPersistEntity(est);
        personasList = new PersonasByRolLazy(colegio, rol);
        JsfUti.messageInfo(null, "Info", "La persona ya no es estudiante de la instituciòn.");
    }
    
    public void verMasDatos(Persona est){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/estudiantes/masinfo.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idEstudiante", new Long(est.getId()));
    }
    
    public void ingresarEstudiante(Persona p){
        p.setRol(rol);
        p.setColegio(colegio);
        services.saveEntity(p);
        personasList = new PersonasByRolLazy(colegio, rol);
        personasEncontradasList.remove(p);
        cedula = "";
        JsfUti.messageInfo(null, "Info", "La persona ahora es un profesor de la institución.");
        
    }
    
    public void guardarNuevo(){
        if((estudiante = (Estudiante) services.saveEntity(estudiante)) != null){
            estudianteList.add(estudiante);
            JsfUti.messageInfo(null, "Info", "Se creó la persona satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la persona.");
    }
    
    public void guardarEdicion(){
        persona.setColegio(colegio);
        if(tipoRelacion!=null){
            relacion = new RelacionPersona();
            relacion.setEstado(Boolean.TRUE);
            relacion.setPersona(BigInteger.valueOf(persona.getId()));
            relacion.setPersonaEs(BigInteger.valueOf(personaTemp.getId()));
            relacion.setTipoRelacion(tipoRelacion);
        }            
        if(services.updateAndPersistEntity(persona))
            JsfUti.messageInfo(null, "Info", "Se editó la persona satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la persona.");
        personaTemp = null;
        tipoRelacion = null;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public PersonasByRolLazy getPersonasList() {
        return personasList;
    }

    public void setPersonasList(PersonasByRolLazy personasList) {
        this.personasList = personasList;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public RelacionPersona getRelacion() {
        return relacion;
    }

    public void setRelacion(RelacionPersona relacion) {
        this.relacion = relacion;
    }

    public TipoRelacionPersona getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacionPersona tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public List<TipoRelacionPersona> getTipoRelacionList() {
        return tipoRelacionList;
    }

    public void setTipoRelacionList(List<TipoRelacionPersona> tipoRelacionList) {
        this.tipoRelacionList = tipoRelacionList;
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
    
}
