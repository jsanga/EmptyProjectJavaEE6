/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
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
public class CrudPersonas implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Persona persona;
    private List<Persona> personaList;
    
    @PostConstruct
    public void init(){
        personaList = services.getListEntitiesByParameters(Querys.getPersonaList, new String[]{}, new Object[]{});
        if(personaList == null)
            personaList = new ArrayList();
    }
    
    public void nuevaPersona(){
        this.persona = new Persona();
        persona.setEstado(Boolean.TRUE);
    }
    
    public void editarPersona(Persona c){
        this.persona = c;
    }
    
    public void eliminarPersona(Persona c){
        int i=0;
        for(Persona cl : personaList){
            if(cl.getCedula().equals(c.getCedula())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                }
                personaList.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void guardarNuevo(){
        if((persona = (Persona) services.saveEntity(persona)) != null){
            personaList.add(persona);
            JsfUti.messageInfo(null, "Info", "Se creó la persona satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la persona.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(persona))
            JsfUti.messageInfo(null, "Info", "Se editó la persona satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la persona.");
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    
}
