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
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import com.jscompany.ebsystem.entidades.colegios.Jornada;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.colegios.PeriodoLectivo;
import com.jscompany.ebsystem.lazymodels.AsignacionCursoLazy;
import com.jscompany.ebsystem.lazymodels.CursosLazy;
import com.jscompany.ebsystem.lazymodels.MateriasLazy;
import com.jscompany.ebsystem.lazymodels.ParalelosLazy;
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
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Colegio colegio;
    private List<Colegio> colegiosList;
    private Curso curso;
    private AsignacionCursoLazy asignacionesCursoLazy;
    private PeriodoLectivo periodo;
    private List<PeriodoLectivo> periodosList;
    private AsignacionCurso asignacion;
    private List<AsignacionCurso> asignacionesList;
    private List<Jornada> jornadasList;
    private List<Materia> materiasList, matListSelect;
    private List<Paralelo> paralelosList, parListSelect;
    private MateriasLazy materias;
    private ParalelosLazy paralelos;
    private CursosLazy cursos;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
                return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        periodosList =  services.getListEntitiesByParameters(Querys.getPeriodosList, new String[]{}, new Object[]{});
        asignacionesList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoListByColegioIdNoState, new String[]{"colegio"}, new Object[]{colegio});
        asignacionesCursoLazy = new AsignacionCursoLazy(colegio);
        jornadasList = services.getListEntitiesByParameters(Querys.getJornadasList, new String[]{}, new Object[]{});
        materias = new MateriasLazy();
        paralelos = new ParalelosLazy();
        cursos = new CursosLazy();
        if(asignacionesList==null)
            asignacionesList = new ArrayList<>();
    }
    
    public void nuevaAsignacion(){
        asignacion = new AsignacionCurso();
        asignacion.setEstado(Boolean.TRUE);
        asignacion.setFechaCreacion(new Date());
        asignacion.setColegio(colegio);
    }
    
    public void agregarMaterias(AsignacionCurso ac){
        asignacion = ac;
    }
    
    public void onRowSelectCurso(){
        if(curso.getEspecializacion()!=null)
            JsfUti.messageInfo(null, "Info", "Se seleccionó el curso: "+curso.getNombre()+" "+curso.getEspecializacion()+".");
        else
            JsfUti.messageInfo(null, "Info", "Se seleccionó el curso: "+curso.getNombre()+".");
        asignacion.setCurso(curso);
    }
    
    public void editarAsignacion(AsignacionCurso ac){
        asignacion = ac;
        
        List<AsignacionCursoMaterias> temp1 = services.getListEntitiesByParameters(Querys.getAsigCursoMaterias, new String[]{"asigCurso"}, new Object[]{asignacion});
        List<AsignacionCursoParalelos> temp2 = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{asignacion});
        parListSelect = new ArrayList<>();
        matListSelect = new ArrayList<>();
        for(AsignacionCursoMaterias t : temp1){
            matListSelect.add(t.getMateria());
        }
        for(AsignacionCursoParalelos t : temp2){
            parListSelect.add(t.getParalelo());
        }
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
            asignacion = colServices.crearAsignacionCurso(asignacion, materiasList, paralelosList);
            if(asignacion != null){
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
            if(colServices.actualizarAsignacionCurso(asignacion, matListSelect, parListSelect))
                JsfUti.messageInfo(null, "Info", "Se editó la asignacion satisfactoriamente");
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al editar la asignacion.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public AsignacionCursoLazy getAsignacionesCursoLazy() {
        return asignacionesCursoLazy;
    }

    public void setAsignacionesCursoLazy(AsignacionCursoLazy asignacionesCursoLazy) {
        this.asignacionesCursoLazy = asignacionesCursoLazy;
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

    public List<Jornada> getJornadasList() {
        return jornadasList;
    }

    public void setJornadasList(List<Jornada> jornadasList) {
        this.jornadasList = jornadasList;
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

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
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

    public List<Materia> getMatListSelect() {
        return matListSelect;
    }

    public void setMatListSelect(List<Materia> matListSelect) {
        this.matListSelect = matListSelect;
    }

    public List<Paralelo> getParListSelect() {
        return parListSelect;
    }

    public void setParListSelect(List<Paralelo> parListSelect) {
        this.parListSelect = parListSelect;
    }

    public CursosLazy getCursos() {
        return cursos;
    }

    public void setCursos(CursosLazy cursos) {
        this.cursos = cursos;
    }
    
}
