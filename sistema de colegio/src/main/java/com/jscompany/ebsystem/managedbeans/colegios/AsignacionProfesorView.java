/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
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
    
    private AsignacionProfesor asignacion;
    private List<AsignacionProfesor> asignacionesList;
    private Profesor profesor;
    private List<Profesor> profesoresList;
    private AsignacionCurso asigcurso;
    private List<AsignacionCurso> asigcursoList;
    private Paralelo paralelo;
    private List<Paralelo> paralelosList;
    
    @PostConstruct
    public void init(){
        asignacionesList = services.getListEntitiesByParameters(Querys.getAsignacionesProfesorListNoState, new String[]{}, new Object[]{});
        paralelosList = services.getListEntitiesByParameters(Querys.getParalelosList, new String[]{}, new Object[]{});
        asigcursoList = services.getListEntitiesByParameters(Querys.getAsignacionesCursoList, new String[]{}, new Object[]{});
        profesoresList = services.getListEntitiesByParameters(Querys.getPersonaList, new String[]{}, new Object[]{});
    }
    
}
