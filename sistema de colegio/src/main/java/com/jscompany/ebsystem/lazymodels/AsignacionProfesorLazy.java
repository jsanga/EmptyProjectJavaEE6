/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JoaoIsrael
 */
public class AsignacionProfesorLazy extends BaseLazyDataModel<AsignacionProfesor> {
        
    private Colegio colegio;
    
    public AsignacionProfesorLazy(Colegio idColegio) {
        super(AsignacionProfesor.class);
        this.colegio = idColegio;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {

        crit.createCriteria("profesor");
        Criteria c = crit.createCriteria("profesor.persona");
        crit.createCriteria("asignacionCurso").add(Restrictions.eq("colegio", colegio));
        
        //d.add(Restrictions.eq("colegio", colegio));
        
        if (filters.containsKey("asignacionCurso.curso.nombre")) {
            crit.createCriteria("asignacionCurso.curso").add(Restrictions.ilike("nombre", "%"+ filters.get("asignacionCurso.curso.nombre").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.persona.apellidos")) {
            c.add(Restrictions.ilike("apellidos", "%"+ filters.get("profesor.persona.apellidos").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.persona.nombres")) {
            c.add(Restrictions.ilike("nombres", "%"+ filters.get("profesor.persona.nombres").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.persona.cedula")) {
            c.add(Restrictions.ilike("cedula", "%"+ filters.get("profesor.persona.cedula").toString().trim() +"%" ));
        }
    }
}
