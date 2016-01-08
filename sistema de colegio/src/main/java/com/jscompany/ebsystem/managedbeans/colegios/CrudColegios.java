/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class CrudColegios implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private List<Colegio> colegiosList;
    private Colegio colegio;
    
    @PostConstruct
    public void init(){
        colegiosList = (List<Colegio>)services.getListEntitiesByParameters(Querys.getColegiosList, new String[]{}, new Object[]{});
        if(colegiosList == null)
            colegiosList = new ArrayList();
    }
    
    public void nuevoColegio(){
        this.colegio = new Colegio();
        colegio.setEstado(Boolean.TRUE);
        colegio.setFechaCreacion(new Date());
    }
    
    public void editarColegio(Colegio c){
        this.colegio = c;
    }
    
    public void eliminarColegio(Colegio c){
        int i=0;
        for(Colegio cl : colegiosList){
            if(cl.getNombre().equals(c.getNombre()) && cl.getDireccion().equals(c.getDireccion())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                }
                colegiosList.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void guardarNuevo(){
        if((colegio = (Colegio) services.saveEntity(colegio)) != null){
            colegiosList.add(colegio);
            JsfUti.messageInfo(null, "Info", "Se creó el colegio satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear el colegio.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(colegio))
            JsfUti.messageInfo(null, "Info", "Se editó el colegio satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar el colegio.");
    }

    public List<Colegio> getColegiosList() {
        return colegiosList;
    }

    public void setColegiosList(List<Colegio> colegiosList) {
        this.colegiosList = colegiosList;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }
    
}