/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
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
    public AsignacionCurso crearAsignacionCursoMatriculaAsignacionProfesor(AsignacionCurso asignacionCurso, List<Matricula> matriculas, List<AsignacionProfesor> asignacionProfesorList, List<Materia> materias, List<Paralelo> paralelos){
        return null;
    }
    
    @Override
    public AsignacionCurso crearAsignacionCurso(AsignacionCurso asignacionCurso, List<Materia> materias, List<Paralelo> paralelos){
        AsignacionCurso asignacion;
        try{
            asignacion = asignacionCurso;
            asignacion = (AsignacionCurso) services.saveEntity(asignacion);
            //asignacion.set(materias);
            //asignacion.setParalelosCollection(paralelos);
            services.updateAndPersistEntity(asignacion);
        }catch(Exception e){
            e.printStackTrace();
            asignacion = null;
        }
        return asignacion;
    }
    
    @Override
    public Boolean actualizarAsignacionCurso(AsignacionCurso asignacionCurso){
        Boolean b;
        try{
            b = true;
            services.updateEntity(asignacionCurso);
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
    
    @Override
    public AsignacionProfesor crearAsignacionProfesor(AsignacionProfesor asignacionP, List<Materia> materias){
        AsignacionProfesor asignacion;
        try{
            asignacion = asignacionP;
            asignacion = (AsignacionProfesor) services.saveEntity(asignacion);
            //asignacion.setMateriasCollection(materias);
            services.updateAndPersistEntity(asignacion);
        }catch(Exception e){
            e.printStackTrace();
            asignacion = null;
        }
        return asignacion;
    }
    
    @Override
    public Boolean actualizarAsignacionProfesor(AsignacionProfesor asignacionProfesor){
        Boolean b;
        try{
            b = true;
            services.updateEntity(asignacionProfesor);
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
}
