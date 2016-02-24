/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.TipoColegio;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.lazymodels.AsignacionCursoLazy;
import com.jscompany.ebsystem.lazymodels.MatriculasLazy;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import java.io.Serializable;
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
public class ReportesView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Colegio colegio;
    private Persona person;
    private List<Colegio> catList;
    private TipoColegio tipoCol;
    private MatriculasLazy matriculas;
    private AsignacionCursoLazy asignacionesCursoLazy;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        person = (Persona) services.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{uSession.getUsername()});
        tipoCol = (TipoColegio) services.getEntity(TipoColegio.class, new Long(3));
        catList = services.getListEntitiesByParameters(Querys.getColegiosByTipoList, new String[]{"tipo"}, new Object[]{tipoCol});
        asignacionesCursoLazy = new AsignacionCursoLazy(colegio);
        matriculas = new MatriculasLazy();
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

    public List<Colegio> getCatList() {
        return catList;
    }

    public void setCatList(List<Colegio> catList) {
        this.catList = catList;
    }

    public AsignacionCursoLazy getAsignacionesCursoLazy() {
        return asignacionesCursoLazy;
    }

    public void setAsignacionesCursoLazy(AsignacionCursoLazy asignacionesCursoLazy) {
        this.asignacionesCursoLazy = asignacionesCursoLazy;
    }

    public MatriculasLazy getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(MatriculasLazy matriculas) {
        this.matriculas = matriculas;
    }
    
}
