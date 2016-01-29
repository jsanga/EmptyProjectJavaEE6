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
import com.jscompany.ebsystem.lazymodels.AsignacionCursoLazy;
import com.jscompany.ebsystem.lazymodels.AsignacionProfesorLazy;
import com.jscompany.ebsystem.lazymodels.ProfesoresLazy;
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
    //private List<AsignacionProfesor> asignacionesProfList;
    //private List<AsignacionCurso> asignacionesCursosList;
    private List<Persona> personasListPrueba;
    private AsignacionCurso asigcurso;
    private Paralelo paralelo;
    //private PersonasByRolLazy profesoresList;
    private AsignacionProfesorLazy asignacionesProfList;
    private AsignacionCursoLazy asignacionesCursosList;
    private ProfesoresLazy profesoresList;
    private AsignacionProfesorLazy asigProfList;
    private List<Paralelo> paralelosList, paralelosSeleccionados;
    private Colegio colegio;
    private Rol rol;
    private List<AsignacionCursoMaterias> asigCurMatList, materiasSeleccionadas;
    private AsignacionCursoMaterias asigCurMat;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(2)});
        try{
            //asignacionesProfList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorListNoState, new String[]{}, new Object[]{});
            asignacionesProfList = new AsignacionProfesorLazy(colegio);
            //asigProfList = new AsignacionProfesorLazy();
            //asignacionesCursosList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoList, new String[]{"colegio"}, new Object[]{colegio});
            asignacionesCursosList = new AsignacionCursoLazy(colegio);
            profesoresList = new ProfesoresLazy(colegio);
            //profesoresList = new PersonasByRolLazy(colegio, rol);
            //personasListPrueba = services.getListEntitiesByParameters(Querys.getPersonaListByRolAndColegio, new String[]{"rol", "colegio"}, new Object[]{rol, colegio});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void nuevaAsignacion(){
        asignacion = new AsignacionProfesor();
        asignacion.setEstado(Boolean.TRUE);
        asignacion.setFechaCreacion(new Date());
    }
    
    public void agregarMaterias(AsignacionProfesor ap){
        asignacion = ap;
    }
    
    public void reasignarMateriaEdit(AsignacionCursoMaterias mat){
        mat.setAsignacionProfesor(asignacion);
        mat.setAsignacionCurso(asignacion.getAsignacionCurso());
        mat.setFueTomada(Boolean.TRUE);
        if(services.updateAndPersistEntity(mat)){
            JsfUti.messageInfo(null, "Info", "Se asignó la materia a "+asignacion.getProfesor().getPersona().getNombres().toUpperCase()+" "+asignacion.getProfesor().getPersona().getApellidos().toUpperCase()+" correcctamente.");
        }else{
            JsfUti.messageError(null, "Error", "Hubo un error al actualizar. Refresque la página e inténtelo nuevamente.");        
        }
    }
    
    public void borrarAsigMat(AsignacionCursoMaterias mat){
        mat.setAsignacionProfesor(null);
        mat.setFueTomada(Boolean.FALSE);
        if(services.updateAndPersistEntity(mat)){
            JsfUti.messageInfo(null, "Info", "Se actualizó correcctamente.");
        }else{
            JsfUti.messageError(null, "Error", "Hubo un error al actualizar. Refresque la página e inténtelo nuevamente.");        
        }
    }
    
    public void editarAsignacion(){
        try{
            //asignacion = ap;
            asigCurMatList = services.getListEntitiesByParameters(Querys.getAsigCursoMaterias, new String[]{"asigCurso"}, new Object[]{asignacion.getAsignacionCurso()});
            List<AsignacionCursoParalelos> temp2 = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{asignacion.getAsignacionCurso()});
            paralelosList = new ArrayList<>();
            
            for(AsignacionCursoParalelos t : temp2){
                paralelosList.add(t.getParalelo());
            }
            materiasSeleccionadas = new ArrayList<>();
            /*List<AsignacionCursoMaterias> materiasProf = services.getListEntitiesByParameters(Querys.getAsigCursoMateriasByByAsigProfAndAsigCurso, new String[]{"asigProf", "asigCur"}, new Object[]{asignacion, asignacion.getAsignacionCurso()});
            if(materiasProf!=null){
                for(AsignacionCursoMaterias temp : materiasProf){
                    temp.setAsignacionProfesor(null);
                    temp.setFueTomada(Boolean.FALSE);
                    services.updateAndPersistEntity(temp);
                }
            }*/
            JsfUti.messageInfo(null, "Info", "Curso "+asignacion.getAsignacionCurso().getCurso().getNombre().toUpperCase()+" seleccionado.");
        }catch(Exception e){
            e.printStackTrace();
        }
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
        utilSession.agregarParametro("idAsigProf", ap.getId());
    }
    
    public void activarAsignacion(AsignacionProfesor ap){
        asignacion = ap;
        asignacion.setEstado(Boolean.TRUE);
        services.updateAndPersistEntity(asignacion);
        JsfUti.messageInfo(null, "Info", "Asignación habilitada.");
    }
    
    public void onRowSelect(){
        try{
            asigCurMatList = services.getListEntitiesByParameters(Querys.getAsigCursoMateriasNoTomadas, new String[]{"asigCurso"}, new Object[]{asignacion.getAsignacionCurso()});
            List<AsignacionCursoParalelos> temp2 = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{asignacion.getAsignacionCurso()});
            paralelosList = new ArrayList<>();
            
            for(AsignacionCursoParalelos t : temp2){
                paralelosList.add(t.getParalelo());
            }
            JsfUti.messageInfo(null, "Info", "Curso seleccionado.");
        }catch(Exception e){
            e.printStackTrace();
        }
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
            if(materiasSeleccionadas.isEmpty()){
                JsfUti.messageError(null, "Error", "Debe seleccionar alguna una materia.");
                return;
            }
            if(asignacion.getParalelo() == null){
                JsfUti.messageError(null, "Error", "Debe seleccionar un paralelo.");
                return;
            }
            if(colServices.crearAsignacionProfesor(asignacion, asigcurso, materiasSeleccionadas)!=null){
                //asignacionesProfList.add(asignacion);
                JsfUti.messageInfo(null, "Info", "Se creó la asignación satisfactoriamente");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al crear la asignación.");
        }catch(Exception e){
            JsfUti.messageError(null, "Error", "Hubo un error al guardar. Recargue la página e inténtelo de nuevo.");
            e.printStackTrace();
        }
    }
    
    public void guardarEdicion(){
        try{
            if(services.updateAndPersistEntity(asignacion))
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

    public AsignacionProfesorLazy getAsignacionesProfList() {
        return asignacionesProfList;
    }

    public void setAsignacionesProfList(AsignacionProfesorLazy asignacionesProfList) {
        this.asignacionesProfList = asignacionesProfList;
    }

    public AsignacionCursoLazy getAsignacionesCursosList() {
        return asignacionesCursosList;
    }

    public void setAsignacionesCursosList(AsignacionCursoLazy asignacionesCursosList) {
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

    public List<Paralelo> getParalelosSeleccionados() {
        return paralelosSeleccionados;
    }

    public void setParalelosSeleccionados(List<Paralelo> paralelosSeleccionados) {
        this.paralelosSeleccionados = paralelosSeleccionados;
    }

    public List<AsignacionCursoMaterias> getMateriasSeleccionadas() {
        return materiasSeleccionadas;
    }

    public void setMateriasSeleccionadas(List<AsignacionCursoMaterias> materiasSeleccionadas) {
        this.materiasSeleccionadas = materiasSeleccionadas;
    }

    public AsignacionProfesorLazy getAsigProfList() {
        return asigProfList;
    }

    public void setAsigProfList(AsignacionProfesorLazy asigProfList) {
        this.asigProfList = asigProfList;
    }

    public ProfesoresLazy getProfesoresList() {
        return profesoresList;
    }

    public void setProfesoresList(ProfesoresLazy profesoresList) {
        this.profesoresList = profesoresList;
    }

    public List<AsignacionCursoMaterias> getAsigCurMatList() {
        return asigCurMatList;
    }

    public void setAsigCurMatList(List<AsignacionCursoMaterias> asigCurMatList) {
        this.asigCurMatList = asigCurMatList;
    }

    public AsignacionCursoMaterias getAsigCurMat() {
        return asigCurMat;
    }

    public void setAsigCurMat(AsignacionCursoMaterias asigCurMat) {
        this.asigCurMat = asigCurMat;
    }
    
}
