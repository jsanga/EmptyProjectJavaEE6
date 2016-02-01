/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoParalelos;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.lazymodels.MatriculasLazy;
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
public class Matricular implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @EJB(beanName = "colegiosServices")
    private ColegiosServices colServices;
    
    private Matricula matricula;
    private MatriculasLazy matriculas;
    //private List<Matricula> matriculaList;
    private List<AsignacionCurso> asignacionesCursosList;
    private List<Paralelo> paralelosList;
    private Paralelo paralelo;
    private AsignacionCurso asignacionCurso;
    private PersonasByRolLazy estudiantesList; 
    private Colegio colegio;
    private Rol rol;
    private Persona personEst;
   
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
            
            //matriculaList = services.getListEntitiesByParameters(Querys.getMatriculasNoState, new String[]{}, new Object[]{});
            colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
            rol = (Rol) services.getEntityByParameters(Querys.getRolById, new String[]{"rolId"}, new Object[]{new Long(3)});
            estudiantesList = new PersonasByRolLazy(colegio, rol);
            matriculas = new MatriculasLazy(colegio);
            asignacionesCursosList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoList, new String[]{"colegio"}, new Object[]{colegio});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void nuevaMatricula(){
        matricula = new Matricula();
        matricula.setFechaCreacion(new Date());
        matricula.setEstado(Boolean.TRUE);
    }
    
    public void onRowSelectEstudiante(){
        matricula.setEstudiante(personEst.getEstudiante());
        JsfUti.messageInfo(null, "Info", "Estudiante seleccionado.");
    }
    
    public void onRowSelectParalelo(){
        JsfUti.messageInfo(null, "Info", "Paralelo seleccionado.");
    }
    
    public void onRowSelect(){
        try{
            List<AsignacionCursoParalelos> temp2 = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{matricula.getAsignacionCurso()});
            paralelosList = new ArrayList<>();
            for(AsignacionCursoParalelos t : temp2){
                paralelosList.add(t.getParalelo());
            }
            JsfUti.messageInfo(null, "Info", "Curso seleccionado.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void masInfo(Matricula mat){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/estudiantes/masInfoMatricula.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idMatricula", mat.getId());
    }
    
    public void guardarNuevo(){
        try{            
            if(services.saveEntity(matricula)!=null){
                JsfUti.messageInfo(null, "Info", "Se creó la matrícula satisfactoriamente");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al crear la matrícula.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void editarMatricula(Matricula matricula){
        this.matricula = matricula;
    }
    
    public void eliminarMatricula(Matricula matricula){
        try{
            this.matricula = matricula;
            this.matricula.setEstado(Boolean.FALSE);
            services.updateAndPersistEntity(this.matricula);
            JsfUti.messageInfo(null, "Info", "Matrícula eliminada.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void habilitarMatricula(Matricula matricula){
        try{
            this.matricula = matricula;
            this.matricula.setEstado(Boolean.TRUE);
            services.updateAndPersistEntity(this.matricula);
            JsfUti.messageInfo(null, "Info", "Matrícula habilitada.");
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

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public PersonasByRolLazy getEstudiantesList() {
        return estudiantesList;
    }

    public void setEstudiantesList(PersonasByRolLazy estudiantesList) {
        this.estudiantesList = estudiantesList;
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

    public List<AsignacionCurso> getAsignacionesCursosList() {
        return asignacionesCursosList;
    }

    public void setAsignacionesCursosList(List<AsignacionCurso> asignacionesCursosList) {
        this.asignacionesCursosList = asignacionesCursosList;
    }

    public AsignacionCurso getAsignacionCurso() {
        return asignacionCurso;
    }

    public void setAsignacionCurso(AsignacionCurso asignacionCurso) {
        this.asignacionCurso = asignacionCurso;
    }

    public List<Paralelo> getParalelosList() {
        return paralelosList;
    }

    public void setParalelosList(List<Paralelo> paralelosList) {
        this.paralelosList = paralelosList;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public MatriculasLazy getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(MatriculasLazy matriculas) {
        this.matriculas = matriculas;
    }

    public Persona getPersonEst() {
        return personEst;
    }

    public void setPersonEst(Persona personEst) {
        this.personEst = personEst;
    }
    
}
