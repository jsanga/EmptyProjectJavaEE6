/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Materia;
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
public class CrudMaterias implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Materia materia;
    private List<Materia> materiaList;
    
    @PostConstruct
    public void init(){
        materiaList = services.getListEntitiesByParameters(Querys.getMateriasList, new String[]{}, new Object[]{});
        if(materiaList == null)
            materiaList = new ArrayList();
    }
    
    public void nuevaMateria(){
        this.materia = new Materia();
        materia.setEstado(Boolean.TRUE);
    }
    
    public void editarMateria(Materia c){
        this.materia = c;
    }
    
    public void eliminarMateria(Materia c){
        int i=0;
        for(Materia cl : materiaList){
            if(cl.getNombre().equals(c.getNombre())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                    JsfUti.messageInfo(null, "Info", "Se eliminó la materia satisfactoriamente.");
                }
                materiaList.remove(i);
                break;
            }
            i++;
        }        
    }
    
    public void guardarNuevo(){
        if((materia = (Materia) services.saveEntity(materia)) != null){
            materiaList.add(materia);
            JsfUti.messageInfo(null, "Info", "Se creó la materia satisfactoriamente.");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear la materia.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(materia))
            JsfUti.messageInfo(null, "Info", "Se editó la materia satisfactoriamente.");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar la materia.");
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }
    
}
