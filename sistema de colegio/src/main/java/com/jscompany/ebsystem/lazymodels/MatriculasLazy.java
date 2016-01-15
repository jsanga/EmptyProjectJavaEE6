/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JoaoIsrael
 */
public class MatriculasLazy extends BaseLazyDataModel<Matricula> {
    
    public MatriculasLazy() {
        super(Matricula.class);
    }

    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {

        if (filters.containsKey("profesor.cedula")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.nombres")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("nombres", "%"+ filters.get("profesor.nombres").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.apellidos")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("apellidos", "%"+ filters.get("profesor.apellidos").toString().trim() +"%" ));
        }
        if (filters.containsKey("curso.nombre")) {
            crit.createCriteria("curso").add(Restrictions.ilike("nombre", "%"+ filters.get("curso.nombre").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
    }
    
}
