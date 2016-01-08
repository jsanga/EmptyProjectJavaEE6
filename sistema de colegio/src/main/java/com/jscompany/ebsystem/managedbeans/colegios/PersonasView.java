/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.lazymodels.PersonasLazy;
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
public class PersonasView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Persona persona;
    private List<Persona> personasEncontradasList;
    private PersonasLazy personasList;
    private List<Colegio> colegios;
    private Colegio colegio;
    private String cedula;
    private Rol rol;
    
    @PostConstruct
    public void init(){
        personasList = new PersonasLazy();
    }
    
    public void buscarPersona(){
        personasEncontradasList = new ArrayList<>();
        
        try{
            persona = (Persona) services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{"cedula"}, new Object[]{cedula});
            
            if(persona != null){
                personasEncontradasList.add(persona);
                JsfUti.messageInfo(null, "Info", "Se encontró la persona.");
            }
            else
                JsfUti.messageError(null, "Error", "No se encontró a la persona.");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void nuevoPersona(){
    
    }
    
    public void editarPersona(Persona p){
        persona = p;
        colegio = persona.getColegio();
    }
    
    public void eliminarPersona(Persona p){
        p.setEstado(Boolean.FALSE);
        services.updateAndPersistEntity(p);
        JsfUti.messageInfo(null, "Info", "La persona ya no es profesor de la instituciòn.");
    }
    
    public void ingresarPersona(Persona p){
        Persona temp;
        if((temp = (Persona) services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{}, new Object[]{p.getCedula()})) == null){
            p.setRol(rol);
            p.setEstado(Boolean.TRUE);
            services.updateAndPersistEntity(p);
            personasEncontradasList.remove(p);
            cedula = "";
            JsfUti.messageInfo(null, "Info", "La persona ha sido ingresada.");
        }else{
            JsfUti.messageError(null, "Error", "La persona ya ha sido ingresada, su nombre es "+temp.getNombres().toUpperCase()+" "+temp.getApellidos().toUpperCase()+".");
        }
    }
    
    public void guardarNuevo(){
        if((persona = (Persona) services.saveEntity(persona)) != null){
            JsfUti.messageInfo(null, "Info", "Se creó la persona satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la persona.");
    }
    
    public void guardarEdicion(){
        persona.setColegio(colegio);
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

    public List<Persona> getPersonasEncontradasList() {
        return personasEncontradasList;
    }

    public void setPersonasEncontradasList(List<Persona> personasEncontradasList) {
        this.personasEncontradasList = personasEncontradasList;
    }

    public PersonasLazy getPersonasList() {
        return personasList;
    }

    public void setPersonasList(PersonasLazy personasList) {
        this.personasList = personasList;
    }

    public List<Colegio> getColegios() {
        return colegios;
    }

    public void setColegios(List<Colegio> colegios) {
        this.colegios = colegios;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
