/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Joao Sanga
 */
public class ProfesoresLazy extends BaseLazyDataModel<Profesor> {
    
    private Colegio colegio;
    
    public ProfesoresLazy(Colegio idColegio) {
        super(Profesor.class);
        this.colegio = idColegio;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        
        if (filters.containsKey("persona.cedula")) {
            crit.createCriteria("persona").add(Restrictions.ilike("cedula", "%"+ filters.get("persona.cedula").toString().trim() +"%" ));
        }
        
        crit.createCriteria("persona").add(Restrictions.eq("colegio", colegio));
    }
    
}
