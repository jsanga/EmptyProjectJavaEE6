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
import com.jscompany.ebsystem.services.AclService;
import java.util.Date;
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
            AsignacionProfesorMaterias apm;
            asignacion = asignacionP;
            asignacion.setEstado(Boolean.TRUE);
            asignacion.setFechaCreacion(new Date());
            asignacion = (AsignacionProfesor) services.saveEntity(asignacion);
            //asignacion.setMateriasCollection(materias);
            for(Materia m : materias){
                apm = new AsignacionProfesorMaterias();
                apm.setAsignacionProfesor(asignacion);
                apm.setMateria(m);
                services.saveEntity(apm);
            }
            //services.updateAndPersistEntity(asignacion);
        }catch(Exception e){
            e.printStackTrace();
            asignacion = null;
        }
        return asignacion;
    }
    
    @Override
    public Boolean actualizarAsignacionProfesor(AsignacionProfesor asignacionProfesor, List<Materia> materias){
        Boolean b;
        try{
            b = true;
            AsignacionProfesorMaterias newAsig;
            
            services.updateAndPersistEntity(asignacionProfesor);
            for(AsignacionProfesorMaterias temp : asignacionProfesor.getAsignacionProfesorMateriasCollection()){
                temp.setEstado(false);
                services.updateAndPersistEntity(temp);
            }
            for(Materia m : materias){
                AsignacionProfesorMaterias temp = (AsignacionProfesorMaterias) services.getEntityByParameters(Querys.getAsignacionProfesorMateriaByMateriaAndAsignacionIdNoState, new String[]{"asigProf", "materia"}, new Object[]{asignacionProfesor, m});
                if(temp == null){
                    newAsig = new AsignacionProfesorMaterias();
                    newAsig.setAsignacionProfesor(asignacionProfesor);
                    newAsig.setMateria(m);
                    services.saveEntity(newAsig);
                }else{
                    temp.setEstado(true);
                    services.updateAndPersistEntity(temp);
                }                
            }            
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
}
