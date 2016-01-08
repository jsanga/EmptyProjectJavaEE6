/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.modelosdedatos.RelacionesPersona;
import com.jscompany.ebsystem.entidades.usuarios.RelacionPersona;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MasInfoView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Long idEstudiante;
    private List<RelacionPersona> relacionList;
    private List<RelacionesPersona> relacionesList;
    
    @PostConstruct
    public void init(){
        if(!utilSession.isNull())
            idEstudiante = (Long) utilSession.retornarValor("idEstudiante");
        if(idEstudiante==null){
            JsfUti.messageError(null, "Error", "Error al cargar la información.");
            return;
        }
        utilSession.borrarDatos();
        relacionList = services.getListEntitiesByParameters(Querys.getRelacionesByPersona, new String[]{"idPersona"}, new Object[]{idEstudiante});
        relacionesList = new ArrayList<>();
        this.llenarInformacion();
    }
    
    public void llenarInformacion(){
        RelacionesPersona temp;
        for(RelacionPersona rp : relacionList){
            temp = new RelacionesPersona(idEstudiante, Long.valueOf(rp.getPersonaEs()+""), rp.getTipoRelacion());
            relacionesList.add(temp);
        }
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public List<RelacionPersona> getRelacionList() {
        return relacionList;
    }

    public void setRelacionList(List<RelacionPersona> relacionList) {
        this.relacionList = relacionList;
    }

    public List<RelacionesPersona> getRelacionesList() {
        return relacionesList;
    }

    public void setRelacionesList(List<RelacionesPersona> relacionesList) {
        this.relacionesList = relacionesList;
    }
    
}
