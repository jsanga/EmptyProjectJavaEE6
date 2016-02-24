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
    private Boolean estado;
    
    public AsignacionCursoLazy(Colegio idColegio) {
        super(AsignacionCurso.class);
        this.colegio = idColegio;
    }
    
    public AsignacionCursoLazy() {
        super(AsignacionCurso.class);
    }
    
    public AsignacionCursoLazy(Colegio idColegio, Boolean estado) {
        super(AsignacionCurso.class);
        this.colegio = idColegio;
        this.estado = estado;
    }
    
    @Override
    public void criteriaFilterSetup(Criteria crit, Map<String, Object> filters) throws Exception {
        
        Criteria d = crit.createCriteria("curso");
        
        if (filters.containsKey("curso.nombre")){
            d.add(Restrictions.ilike("nombre", "%"+  filters.get("curso.nombre").toString().trim() +"%" ));
        }
        if (filters.containsKey("curso.especializacion")){
            d.add(Restrictions.ilike("especializacion", "%"+  filters.get("curso.especializacion").toString().trim() +"%" ));
        }
        
        if(colegio!=null)
            crit.add(Restrictions.eq("colegio", colegio));
        
        if(estado!=null)
            crit.add(Restrictions.eq("estado", estado));
    }
}
