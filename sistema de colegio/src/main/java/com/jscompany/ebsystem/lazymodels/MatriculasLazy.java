/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JoaoIsrael
 */
public class MatriculasLazy extends BaseLazyDataModel<Matricula> {
    
    private Colegio colegio;
    private Boolean estado;
    
    public MatriculasLazy(Colegio colegio) {
        super(Matricula.class);
        this.colegio = colegio;
    }
    
    public MatriculasLazy() {
        super(Matricula.class);
    }
    
    public MatriculasLazy(Colegio colegio, Boolean estado) {
        super(Matricula.class);
        this.colegio = colegio;
        this.estado = estado;
    }

    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        
        crit.createCriteria("estudiante");
        Criteria c = crit.createCriteria("estudiante.persona");
        
        if (filters.containsKey("estudiante.persona.cedula")) {
            c.add(Restrictions.ilike("cedula", "%"+ filters.get("estudiante.persona.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("estudiante.persona.nombres")) {
            c.add(Restrictions.ilike("nombres", "%"+ filters.get("estudiante.persona.nombres").toString().trim() +"%" ));
        }
        if (filters.containsKey("estudiante.persona.apellidos")) {
            c.add(Restrictions.ilike("apellidos", "%"+ filters.get("estudiante.persona.apellidos").toString().trim() +"%" ));
        }
        if (filters.containsKey("curso.nombre")) {
            c.add(Restrictions.ilike("nombre", "%"+ filters.get("curso.nombre").toString().trim() +"%" ));
        }
        
        if(this.colegio!=null)
            c.add(Restrictions.eq("colegio", this.colegio));
        
        if(this.estado != null)
            crit.add(Restrictions.eq("estado", true));
    }
    
}
