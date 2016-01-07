/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Personal;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
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
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class EstudianteView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Estudiante estudiante;
    private List<Estudiante> estudianteList;
    private Persona persona;
    private List<Persona> personasList;
    private String cedula;
    private Rol rol;
    
    @PostConstruct
    public void init(){
        personasList = services.getListEntitiesByParameters(Querys.getEstudiantesList, new String[]{}, new Object[]{});
        rol = (Rol) services.getEntity(Rol.class, new Long(3));
        if(personasList == null)
            personasList = new ArrayList();
    }
    
    public void buscarPersona(){
        Profesor p;
        Personal user;
        try{
            persona = (Persona) services.getEntityByParameters(Querys.getPersonaByCedulaAndNOTRol, new String[]{"cedula", "idRol"}, new Object[]{cedula, rol.getId()});
            
            if(personasList == null)
                personasList = new ArrayList<>();
            if(persona != null){
                personasList.add(persona);
                JsfUti.messageInfo(null, "Info", "Se encontró la persona.");
            }
            else
                JsfUti.messageError(null, "Error", "No se encontró a la persona.");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void nuevoEstudiante(){
    
    }
    
    public void editarEstudiante(Persona est){
    
    }
    
    public void eliminarEstudiante(Persona est){
        est.setRol(null);
        services.updateAndPersistEntity(est);
        personasList.remove(est);
        JsfUti.messageInfo(null, "Info", "La persona ya no es estudiante de la instituciòn.");
    }
    
    public void ingresarEstudiante(Persona p){
        if(!personasList.contains(p)){
            p.setRol(rol);
            services.updateAndPersistEntity(p);
            personasList.add(p);
            JsfUti.messageInfo(null, "Info", "La persona ahora es un estudiante de la institución.");
        }else{
            JsfUti.messageError(null, "Error", "La persona que se trata de ingresar ya es un estudiante de la institución.");
        }
    }
    
    public void guardarNuevo(){
        if((estudiante = (Estudiante) services.saveEntity(estudiante)) != null){
            estudianteList.add(estudiante);
            JsfUti.messageInfo(null, "Info", "Se creó la persona satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la persona.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(estudiante))
            JsfUti.messageInfo(null, "Info", "Se editó la persona satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la persona.");
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
