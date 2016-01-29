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

        
        if (filters.containsKey("asignacionCurso.curso.nombre")) {
            crit.createCriteria("asignacionCurso.curso").add(Restrictions.ilike("nombre", "%"+ filters.get("asignacionCurso.curso.nombre").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.persona.nombres")) {
            crit.createCriteria("profesor.persona").add(Restrictions.ilike("nombres", "%"+ filters.get("profesor.persona.nombres").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
        //crit.add(Restrictions.eq("colegio", colegio));
        crit.createCriteria("asignacionCurso").add(Restrictions.eq("colegio", colegio));
    }
}
