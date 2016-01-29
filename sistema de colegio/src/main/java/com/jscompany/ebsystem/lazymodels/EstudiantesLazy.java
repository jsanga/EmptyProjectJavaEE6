/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Joao Sanga
 */
public class EstudiantesLazy extends BaseLazyDataModel<Estudiante> {
    
    private Colegio colegio;
    
    public EstudiantesLazy(Colegio idColegio) {
        super(Estudiante.class);
        this.colegio = idColegio;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        
        if (filters.containsKey("persona.cedula")) {
            crit.createCriteria("persona").add(Restrictions.ilike("cedula", "%"+ filters.get("persona.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("persona.nombres")) {
            crit.createCriteria("persona").add(Restrictions.ilike("nombres", "%"+ filters.get("persona.nombres").toString().trim() +"%" ));
        }
        if (filters.containsKey("persona.apellidos")) {
            crit.createCriteria("persona").add(Restrictions.ilike("apellidos", "%"+ filters.get("persona.apellidos").toString().trim() +"%" ));
        }
        
        crit.createCriteria("persona").add(Restrictions.eq("colegio", colegio));
    }
    
}
