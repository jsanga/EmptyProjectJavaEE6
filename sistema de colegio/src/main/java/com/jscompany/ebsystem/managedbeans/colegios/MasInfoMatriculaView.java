/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoMaterias;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
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
public class MasInfoMatriculaView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Long idMatricula;
    private Matricula mat;
    private AsignacionCurso ac;
    private Estudiante est;
    private Paralelo par;
    private List<AsignacionCursoMaterias> materias;
    
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
            if(!utilSession.isNull())
                idMatricula = (Long) utilSession.retornarValor("idMatricula");
            if(idMatricula==null){
                JsfUti.messageError(null, "Error", "Error al cargar la información.");
                return;
            }
            utilSession.borrarDatos();
            mat = (Matricula) services.getEntity(Matricula.class, idMatricula);
            ac = mat.getAsignacionCurso();
            est = mat.getEstudiante();
            par = mat.getParalelo();
            materias = services.getListEntitiesByParameters(Querys.getAsigCursoMateriasByByAsigCursoAndParalelo, new String[]{"paralelo", "asigCur"}, new Object[]{par, ac});
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

    public Matricula getMat() {
        return mat;
    }

    public void setMat(Matricula mat) {
        this.mat = mat;
    }

    public AsignacionCurso getAc() {
        return ac;
    }

    public void setAc(AsignacionCurso ac) {
        this.ac = ac;
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }

    public Paralelo getPar() {
        return par;
    }

    public void setPar(Paralelo par) {
        this.par = par;
    }

    public List<AsignacionCursoMaterias> getMaterias() {
        return materias;
    }

    public void setMaterias(List<AsignacionCursoMaterias> materias) {
        this.materias = materias;
    }
    
    
}
