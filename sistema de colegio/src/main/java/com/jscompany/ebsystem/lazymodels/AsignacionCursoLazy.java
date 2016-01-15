/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.lazymodels;

import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JoaoIsrael
 */
public class AsignacionCursoLazy extends BaseLazyDataModel<AsignacionCurso> {
    
    private Colegio colegio;
    
    public AsignacionCursoLazy(Colegio idColegio) {
        super(AsignacionCurso.class);
        this.colegio = idColegio;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {

        if (filters.containsKey("curso.nombre")) {
            crit.createCriteria("curso").add(Restrictions.ilike("nombre", "%"+ filters.get("curso.nombre").toString().trim() +"%" ));
        }
        if (filters.containsKey("periodoLectivo.fechaInicio")) {
            crit.createCriteria("periodoLectivo").add(Restrictions.ilike("fechaInicio", "%"+ filters.get("periodoLectivo.fechaInicio").toString().trim() +"%" ));
        }
        if (filters.containsKey("periodoLectivo.fechaFin")) {
            crit.createCriteria("periodoLectivo").add(Restrictions.ilike("fechaFin", "%"+ filters.get("periodoLectivo.fechaFin").toString().trim() +"%" ));
        }
        //crit.add(Restrictions.le("estado", true));
        crit.add(Restrictions.eq("colegio", colegio));
    }
}
