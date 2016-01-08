/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
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
public class CrudParalelos implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Paralelo paralelo;
    private List<Paralelo> paraleloList;
    
    @PostConstruct
    public void init(){
        paraleloList = services.getListEntitiesByParameters(Querys.getParalelosList, new String[]{}, new Object[]{});
        if(paraleloList == null)
            paraleloList = new ArrayList();
    }
    
    public void nuevoParalelo(){
        this.paralelo = new Paralelo();
        paralelo.setEstado(Boolean.TRUE);
    }
    
    public void editarParalelo(Paralelo c){
        this.paralelo = c;
    }
    
    public void eliminarParalelo(Paralelo c){
        int i=0;
        for(Paralelo cl : paraleloList){
            if(cl.getNombre().equals(c.getNombre())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                }
                paraleloList.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void guardarNuevo(){
        if((paralelo = (Paralelo) services.saveEntity(paralelo)) != null){
            paraleloList.add(paralelo);
            JsfUti.messageInfo(null, "Info", "Se creó el paralelo satisfactoriamente.");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear el paralelo.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(paralelo))
            JsfUti.messageInfo(null, "Info", "Se editó el paralelo satisfactoriamente.");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar el paralelo.");
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public List<Paralelo> getParaleloList() {
        return paraleloList;
    }

    public void setParaleloList(List<Paralelo> paraleloList) {
        this.paraleloList = paraleloList;
    }
    
}
