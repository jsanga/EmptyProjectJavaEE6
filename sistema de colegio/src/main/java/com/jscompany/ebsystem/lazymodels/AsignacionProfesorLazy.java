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
        
    public AsignacionProfesorLazy() {
        super(AsignacionProfesor.class);
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {

        if (filters.containsKey("asignacionCurso.profesor.cedula")) {
            crit.createCriteria("asignacionCurso.profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("asignacionCurso.profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("asignacionCurso.profesor.cedula")) {
            crit.createCriteria("asignacionCurso.profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("asignacionCurso.profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("asignacionCurso.profesor.cedula")) {
            crit.createCriteria("asignacionCurso.profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("asignacionCurso.profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("asignacionCurso.curso.nombre")) {
            crit.createCriteria("asignacionCurso.curso").add(Restrictions.ilike("nombre", "%"+ filters.get("asignacionCurso.curso.nombre").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
        //crit.add(Restrictions.eq("colegio", colegio));
    }
}
