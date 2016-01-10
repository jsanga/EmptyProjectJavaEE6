/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JoaoIsrael
 */
public class PersonasByRolLazy extends BaseLazyDataModel<Persona> {
    
    private Colegio idColegio;
    private Rol rol;
    
    public PersonasByRolLazy(Colegio idColegio, Rol rol) {
        super(Persona.class, "cedula");
        this.idColegio = idColegio;
        this.rol = rol;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {

        if (filters.containsKey("nombres")) {
            crit.add(Restrictions.ilike("nombres", "%" + filters.get("nombres").toString().trim() + "%"));
        }
        if (filters.containsKey("apellidos")) {
            crit.add(Restrictions.ilike("apellidos", "%" + filters.get("apellidos").toString().trim() + "%"));
        }
        if (filters.containsKey("cedula")) {
            crit.add(Restrictions.ilike("cedula", "%" + filters.get("cedula").toString().trim() + "%"));
        }
        if (filters.containsKey("colegio.nombre")) {
            crit.createCriteria("colegio").add(Restrictions.ilike("nombre", "%"+ filters.get("colegio.nombre").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
        crit.add(Restrictions.le("colegio", idColegio));
        crit.add(Restrictions.le("rol", rol));
    }
}
