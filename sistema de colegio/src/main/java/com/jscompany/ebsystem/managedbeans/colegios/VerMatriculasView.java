/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.DetalleMateria;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
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
public class VerMatriculasView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Persona estudiante;
    private List<Matricula> matriculasList;
    private Matricula matricula;
    private List<DetalleMateria> detalleMateriaList;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        estudiante = (Persona) services.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{uSession.getUsername()});
        if(estudiante != null && estudiante.getEstudiante()!=null)
            matriculasList = services.getListEntitiesByParameters(Querys.getMatriculasByEstudianteNoState, new String[]{"estudiante"}, new Object[]{estudiante.getEstudiante()});
        else
            JsfUti.messageError(null, "Error", "Error al cargar la información.");
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Persona getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Persona estudiante) {
        this.estudiante = estudiante;
    }

    public List<Matricula> getMatriculasList() {
        return matriculasList;
    }

    public void setMatriculasList(List<Matricula> matriculasList) {
        this.matriculasList = matriculasList;
    }

    public List<DetalleMateria> getDetalleMateriaList() {
        return detalleMateriaList;
    }

    public void setDetalleMateriaList(List<DetalleMateria> detalleMateriaList) {
        this.detalleMateriaList = detalleMateriaList;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
        if(estudiante.getColegio().getTipoCalificacion().getId().equals(new Long(1)))
            JsfUti.redirectNewTab("/colegionetworksystem/faces/estudiante/notas/verNotasSemestral.xhtml");
        if(estudiante.getColegio().getTipoCalificacion().getId().equals(new Long(2)))
            JsfUti.redirectNewTab("/colegionetworksystem/faces/estudiante/notas/verNotasQuimestral.xhtml");
        if(estudiante.getColegio().getTipoCalificacion().getId().equals(new Long(3)))
            JsfUti.redirectNewTab("/colegionetworksystem/faces/estudiante/notas/verNotasTrimestral.xhtml");
        utilSession.instanciarParametros();
        utilSession.agregarParametro("idMatricula", new Long(this.matricula.getId()));
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }
    
}
