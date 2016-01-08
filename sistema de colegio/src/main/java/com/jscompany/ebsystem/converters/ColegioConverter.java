/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.converters;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.EntityManagerServices;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Joao Sanga
 */
@FacesConverter("colegioConverter")
public class ColegioConverter implements Converter, Serializable{
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        if(value==null||value.equals("Seleccionar"))
            return null;
        else{
            Colegio temp = (Colegio) EntityManagerServices.aclService().getEntity(Colegio.class, Long.parseLong(value));
            return temp;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {        
        if(value==null||value.equals("Seleccionar"))
            return null;
        else
            return ((Colegio)value).getId().toString();        
    } 
    
}
