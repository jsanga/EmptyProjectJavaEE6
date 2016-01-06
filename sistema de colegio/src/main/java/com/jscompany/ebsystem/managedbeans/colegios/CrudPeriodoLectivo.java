/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.colegios.PeriodoLectivo;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    
    private PeriodoLectivo periodo;
    private List<PeriodoLectivo> periodoList;
    
    @PostConstruct
    public void init(){
        periodoList = services.getListEntitiesByParameters(Querys.getMateriasList, new String[]{}, new Object[]{});
        if(periodoList == null)
            periodoList = new ArrayList();
    }
    
    public void nuevoParalelo(){
        this.periodo = new PeriodoLectivo();
        periodo.setEstado(Boolean.TRUE);
    }
    
    public void editarParalelo(PeriodoLectivo c){
        this.periodo = c;
    }
    
    public void eliminarParalelo(PeriodoLectivo c){
        int i=0;
        for(PeriodoLectivo cl : periodoList){
            if(cl.getFechaInicio().equals(c.getFechaInicio()) && cl.getFechaFin().equals(c.getFechaFin())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                }
                periodoList.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void guardarNuevo(){
        if((periodo = (PeriodoLectivo) services.saveEntity(periodo)) != null){
            periodoList.add(periodo);
            JsfUti.messageInfo(null, "Info", "Se creó el periodo lectivo satisfactoriamente.");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear el periodo lectivo.");
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
    
}
