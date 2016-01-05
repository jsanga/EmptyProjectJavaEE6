/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesorMaterias;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.services.AclService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author JoaoIsrael
 */
@Stateless(name = "colegiosServices")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class ColegiosEjb implements ColegiosServices{
    
    @EJB(beanName = "aclService")
    protected AclService services;
    
    @Override
    public AsignacionCurso crearAsignacionCurso(AsignacionCurso asignacionCurso, List<Matricula> matriculas, List<AsignacionProfesor> asignacionProfesorList, List<Materia> materias, List<Paralelo> paralelos){
        return null;
    }
    
    @Override
    public AsignacionProfesor crearAsignacionProfesor(AsignacionProfesor asignacion, List<AsignacionProfesorMaterias> asignacionProfesorMateriasList){
        return null;
    }
    
}
