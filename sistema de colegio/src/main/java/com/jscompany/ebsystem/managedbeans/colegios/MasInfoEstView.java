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
import com.jscompany.ebsystem.entidades.usuarios.TipoRelacionPersona;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
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
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Long idEstudiante;
    private List<RelacionPersona> relacionList;
    private RelacionPersona rp;
    private List<RelacionesPersona> relacionesList;
    private RelacionesPersona relacion;
    private Persona persona, personaEs;
    private List<TipoRelacionPersona> tipoRelacionList;
    
    @PostConstruct
    public void init(){
        try{
            if(!uSession.getIsLogged())
                return;
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
            tipoRelacionList = services.getListEntitiesByParameters(Querys.getRelacionesList, new String[]{}, new Object[]{});
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void habilitarRelacion(RelacionesPersona tp){
        rp = (RelacionPersona) services.getEntityByParameters(Querys.getRelacionByPersonaPersonaEsAndTipoRelacion, new String[]{"idPersona", "personaEs", "tipoRelacion"}, new Object[]{tp.getPersona(), tp.getPersonaEs(), tp.getTipoRelacion()});
        rp.setEstado(Boolean.TRUE);        
        tp.setEstado(Boolean.TRUE);
        
        if(services.updateAndPersistEntity(rp)){
            JsfUti.messageInfo(null, "Info", "Se actualizò el dato correctamente");
        }else
            JsfUti.messageError(null, "Error", "Error al actualizar la relaciòn");
        
    }
    
    public void guardarEdicion(){
        rp = (RelacionPersona) services.getEntity(RelacionPersona.class, relacion.getIdRelacionPersona());
        rp.setTipoRelacion(relacion.getTipoRelacion());
        if(services.updateAndPersistEntity(rp)){
            JsfUti.messageInfo(null, "Info", "Se actualizò el dato correctamente");
        }else
            JsfUti.messageError(null, "Error", "Error al actualizar la relaciòn");
    }
    
    public void deshabilitarRelacion(RelacionesPersona tp){
        rp = (RelacionPersona) services.getEntityByParameters(Querys.getRelacionByPersonaPersonaEsAndTipoRelacion, new String[]{"idPersona", "personaEs", "tipoRelacion"}, new Object[]{tp.getPersona(), tp.getPersonaEs(), tp.getTipoRelacion()});
        rp.setEstado(Boolean.FALSE);
        tp.setEstado(Boolean.FALSE);
        if(services.updateAndPersistEntity(rp)){
            JsfUti.messageInfo(null, "Info", "Se actualizò el dato correctamente");
        }else
            JsfUti.messageError(null, "Error", "Error al actualizar la relaciòn");
    }
    
    public void llenarInformacion(){
        RelacionesPersona temp;
        Long l1, l2;
        for(RelacionPersona rp : relacionList){
            l1 = Long.valueOf(rp.getPersona()+"");
            l2 = Long.valueOf(rp.getPersonaEs()+"");
            persona = (Persona) services.getEntityByParameters(Querys.getPersonaByID, new String[]{"idPersona"}, new Object[]{l1});
            personaEs = (Persona) services.getEntityByParameters(Querys.getPersonaByID, new String[]{"idPersona"}, new Object[]{l2});
            temp = new RelacionesPersona(persona, personaEs, rp.getTipoRelacion(), rp.getEstado(), rp.getId());
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

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public RelacionesPersona getRelacion() {
        return relacion;
    }

    public void setRelacion(RelacionesPersona relacion) {
        this.relacion = relacion;
    }

    public List<TipoRelacionPersona> getTipoRelacionList() {
        return tipoRelacionList;
    }

    public void setTipoRelacionList(List<TipoRelacionPersona> tipoRelacionList) {
        this.tipoRelacionList = tipoRelacionList;
    }
    
}
