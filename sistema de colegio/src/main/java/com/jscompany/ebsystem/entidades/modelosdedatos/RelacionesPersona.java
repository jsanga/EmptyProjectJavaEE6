/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.modelosdedatos;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.TipoRelacionPersona;
import com.jscompany.ebsystem.services.AclService;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Joao Sanga
 */
public class RelacionesPersona implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    private Persona persona, personaEs;
    private TipoRelacionPersona tipoRelacion;
    
    public RelacionesPersona(Persona p1, Persona p2, TipoRelacionPersona tipo){
        persona = p1;
        personaEs = p2;
        tipoRelacion = tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersonaEs() {
        return personaEs;
    }

    public void setPersonaEs(Persona personaEs) {
        this.personaEs = personaEs;
    }

    public TipoRelacionPersona getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacionPersona tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
    
}
