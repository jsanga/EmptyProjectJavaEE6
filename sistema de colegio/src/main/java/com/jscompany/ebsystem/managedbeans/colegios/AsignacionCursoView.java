/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.colegios.PeriodoLectivo;
import com.jscompany.ebsystem.lazymodels.MateriasLazy;
import com.jscompany.ebsystem.lazymodels.ParalelosLazy;
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
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class AsignacionCursoView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @EJB(beanName = "colegiosServices")
    private ColegiosServices colServices;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Colegio colegio;
    private List<Colegio> colegiosList;
    private Curso curso;
    private List<Curso> cursosList;
    private PeriodoLectivo periodo;
    private List<PeriodoLectivo> periodosList;
    private AsignacionCurso asignacion;
    private List<AsignacionCurso> asignacionesList;
    private List<Materia> materiasList;
    private List<Paralelo> paralelosList;
    private MateriasLazy materias;
    private ParalelosLazy paralelos;
    
    @PostConstruct
    public void init(){
        colegiosList = (List<Colegio>)services.getListEntitiesByParameters(Querys.getColegiosList, new String[]{}, new Object[]{});
        cursosList = services.getListEntitiesByParameters(Querys.getCursosList, new String[]{}, new Object[]{});
        periodosList =  services.getListEntitiesByParameters(Querys.getPeriodosList, new String[]{}, new Object[]{});
        asignacionesList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoListNoState, new String[]{}, new Object[]{});
        //materiasList = services.getListEntitiesByParameters(Querys.getMateriasList, new String[]{}, new Object[]{});
        //paralelosList = services.getListEntitiesByParameters(Querys.getParalelosList, new String[]{}, new Object[]{});
        materias = new MateriasLazy();
        paralelos = new ParalelosLazy();
        if(asignacionesList==null)
            asignacionesList = new ArrayList<>();
    }
    
    public void nuevaAsignacion(){
        asignacion = new AsignacionCurso();
        asignacion.setEstado(Boolean.TRUE);
        asignacion.setFechaCreacion(new Date());
    }
    
    public void agregarMaterias(AsignacionCurso ac){
        asignacion = ac;
    }
    
    public void editarAsignacion(AsignacionCurso ac){
        asignacion = ac;
    }
    
    public void eliminarAsignacion(AsignacionCurso ac){
        asignacion = ac;
        asignacion.setEstado(Boolean.FALSE);
        services.updateAndPersistEntity(asignacion);
        JsfUti.messageInfo(null, "Info", "Asignación eliminada.");
    }
    
    public void masInfo(AsignacionCurso ac){
        JsfUti.redirectNewTab("/colegionetworksystem/faces/admin/cursos/masinfo.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idAsigCurso", ac.getId());
    }
    
    public void activarAsignacion(AsignacionCurso ac){
        asignacion = ac;
        asignacion.setEstado(Boolean.TRUE);
        services.updateAndPersistEntity(asignacion);
        JsfUti.messageInfo(null, "Info", "Asignación habilitada.");
    }
    
    public void guardarNuevo(){
        try{            
            if(colServices.crearAsignacionCurso(asignacion, materiasList, paralelosList)!=null){
                asignacionesList.add(asignacion);
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
            if(colServices.actualizarAsignacionCurso(asignacion))
                JsfUti.messageInfo(null, "Info", "Se editó la asignacion satisfactoriamente");
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al editar la asignacion.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public List<Colegio> getColegiosList() {
        return colegiosList;
    }

    public void setColegiosList(List<Colegio> colegiosList) {
        this.colegiosList = colegiosList;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursosList() {
        return cursosList;
    }

    public void setCursosList(List<Curso> cursosList) {
        this.cursosList = cursosList;
    }

    public PeriodoLectivo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoLectivo periodo) {
        this.periodo = periodo;
    }

    public List<PeriodoLectivo> getPeriodosList() {
        return periodosList;
    }

    public void setPeriodosList(List<PeriodoLectivo> periodosList) {
        this.periodosList = periodosList;
    }

    public AsignacionCurso getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(AsignacionCurso asignacion) {
        this.asignacion = asignacion;
    }

    public List<AsignacionCurso> getAsignacionesList() {
        return asignacionesList;
    }

    public void setAsignacionesList(List<AsignacionCurso> asignacionesList) {
        this.asignacionesList = asignacionesList;
    }

    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public List<Paralelo> getParalelosList() {
        return paralelosList;
    }

    public void setParalelosList(List<Paralelo> paralelosList) {
        this.paralelosList = paralelosList;
    }

    public MateriasLazy getMaterias() {
        return materias;
    }

    public void setMaterias(MateriasLazy materias) {
        this.materias = materias;
    }

    public ParalelosLazy getParalelos() {
        return paralelos;
    }

    public void setParalelos(ParalelosLazy paralelos) {
        this.paralelos = paralelos;
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }
    
}
