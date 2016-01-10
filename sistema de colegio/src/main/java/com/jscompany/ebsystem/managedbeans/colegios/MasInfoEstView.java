/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.modelosdedatos.RelacionesPersona;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
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
public class MasInfoEstView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    private Long idEstudiante;
    private List<RelacionPersona> relacionList;
    private List<RelacionesPersona> relacionesList;
    private Persona persona, personaEs;
    
    @PostConstruct
    public void init(){
        try{
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void llenarInformacion(){
        RelacionesPersona temp;
        Long l1, l2;
        for(RelacionPersona rp : relacionList){
            l1 = Long.valueOf(rp.getPersona()+"");
            l2 = Long.valueOf(rp.getPersonaEs()+"");
            persona = (Persona) services.getEntityByParameters(Querys.getPersonaByID, new String[]{"idPersona"}, new Object[]{l1});
            personaEs = (Persona) services.getEntityByParameters(Querys.getPersonaByID, new String[]{"idPersona"}, new Object[]{l2});
            temp = new RelacionesPersona(persona, personaEs, rp.getTipoRelacion());
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
}
