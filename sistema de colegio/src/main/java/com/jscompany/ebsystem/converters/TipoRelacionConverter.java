/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.converters;

import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.entidades.usuarios.TipoRelacionPersona;
import com.jscompany.ebsystem.util.EntityManagerServices;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Joao Sanga
 */
@FacesConverter("tipoRelacionConverter")
public class TipoRelacionConverter implements Converter, Serializable{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        if(value==null||value.equals("Seleccionar"))
            return null;
        else{
            TipoRelacionPersona temp = (TipoRelacionPersona) EntityManagerServices.aclService().getEntity(TipoRelacionPersona.class, Long.parseLong(value));
            return temp;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {        
        if(value==null||value.equals("Seleccionar"))
            return null;
        else
            return ((TipoRelacionPersona)value).getId().toString();        
    } 
}