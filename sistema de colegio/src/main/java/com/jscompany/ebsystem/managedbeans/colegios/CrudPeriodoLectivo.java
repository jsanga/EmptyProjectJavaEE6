/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.colegios.PeriodoLectivo;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.HiberUtil;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.SQLQuery;

/**
 *
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class CrudPeriodoLectivo implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private PeriodoLectivo periodo;
    private List<PeriodoLectivo> periodoList;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        periodoList = services.getListEntitiesByParameters(Querys.getPeriodosListNoState, new String[]{}, new Object[]{});
        if(periodoList == null)
            periodoList = new ArrayList();
    }
    
    public void nuevoPeriodoLectivo(){
        this.periodo = new PeriodoLectivo();
        periodo.setEstado(Boolean.TRUE);
    }
    
    public void editarPeriodoLectivo(PeriodoLectivo c){
        this.periodo = c;
    }
    
    public void eliminarPeriodoLectivo(){
        periodo.setEstado(Boolean.FALSE);
        if(periodo.getId() != null){
            services.updateAndPersistEntity(periodo); 
        }        
    }
    
    public void guardarNuevo(){
        SQLQuery query;
        try{
            if((periodo = (PeriodoLectivo) services.saveEntity(periodo)) != null){
                periodoList.add(periodo);
                query = HiberUtil.getSession().createSQLQuery("update colegios.matricula set estado=false");
                query.executeUpdate();
                JsfUti.messageInfo(null, "Info", "Se creó el periodo lectivo satisfactoriamente.");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al crear el periodo lectivo.");
        }catch(Exception e){
            e.printStackTrace();
            JsfUti.messageError(null, "Error", "Hubo un problema al crear el periodo lectivo.");
        }
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(periodo))
            JsfUti.messageInfo(null, "Info", "Se editó el periodo lectivo satisfactoriamente.");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar el periodo lectivo.");
    }

    public PeriodoLectivo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoLectivo periodo) {
        this.periodo = periodo;
    }

    public List<PeriodoLectivo> getPeriodoList() {
        return periodoList;
    }

    public void setPeriodoList(List<PeriodoLectivo> periodoList) {
        this.periodoList = periodoList;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }
    
}
