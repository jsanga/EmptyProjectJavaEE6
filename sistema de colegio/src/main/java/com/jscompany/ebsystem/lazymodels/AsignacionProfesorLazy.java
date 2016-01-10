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

        if (filters.containsKey("profesor.cedula")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.cedula")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("profesor.cedula")) {
            crit.createCriteria("profesor").add(Restrictions.ilike("cedula", "%"+ filters.get("profesor.cedula").toString().trim() +"%" ));
        }
        if (filters.containsKey("curso.nombre")) {
            crit.createCriteria("curso").add(Restrictions.ilike("nombre", "%"+ filters.get("curso.nombre").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
        crit.add(Restrictions.le("colegio", colegio));
    }
}
