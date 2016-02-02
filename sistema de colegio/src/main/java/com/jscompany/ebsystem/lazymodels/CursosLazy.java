/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Joao Sanga
 */
public class CursosLazy extends BaseLazyDataModel<Curso> {
    
    
    public CursosLazy() {
        super(Curso.class);
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        
        if (filters.containsKey("nombre")) {
            crit.add(Restrictions.ilike("nombre", "%" + filters.get("nombre").toString().trim() + "%"));
        }
        if (filters.containsKey("especializacion")) {
            crit.add(Restrictions.ilike("especializacion", "%" + filters.get("especializacion").toString().trim() + "%"));
        }
    }
    
}
