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
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoMaterias;
import com.jscompany.ebsystem.entidades.colegios.AsignacionCursoParalelos;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.DetalleMateria;
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
        AsignacionCursoMaterias acm;
        AsignacionCursoParalelos acp;
        try{
            asignacion = asignacionCurso;
            asignacion = (AsignacionCurso) services.saveEntity(asignacion);
            for(Materia m : materias){
                acm = new AsignacionCursoMaterias();
                acm.setAsignacionCurso(asignacion);
                acm.setMateria(m);
                acm.setFueTomada(Boolean.FALSE);
                acm.setEstado(Boolean.TRUE);
                services.saveEntity(acm);
            }
            for(Paralelo p : paralelos){
                acp = new AsignacionCursoParalelos();
                acp.setAsignacionCurso(asignacion);
                acp.setParalelo(p);
                acp.setMaxCupo(30);
                acp.setCupoDisponible(30);
                acp.setEstado(Boolean.TRUE);
                services.saveEntity(acp);
            }
        }catch(Exception e){
            e.printStackTrace();
            asignacion = null;
        }
        return asignacion;
    }
    
    @Override
    public Boolean actualizarAsignacionCurso(AsignacionCurso asignacionCurso, List<Materia> materias, List<Paralelo> paralelos){
        Boolean b;
        try{
            b = true;
            AsignacionCursoMaterias asigCM;
            AsignacionCursoParalelos asigCP;
            
            services.updateEntity(asignacionCurso);
            for(AsignacionCursoMaterias acm : asignacionCurso.getAsignacionCursoMateriasCollection()){
                acm.setEstado(false);
                services.updateAndPersistEntity(acm);
            }
            for(AsignacionCursoParalelos acp : asignacionCurso.getAsignacionCursoParalelosCollection()){
                acp.setEstado(false);
                services.updateAndPersistEntity(acp);
            }
            for(Materia m : materias){
                asigCM = (AsignacionCursoMaterias) services.getEntityByParameters(Querys.getAsigCursoMateriasByAsigCursoAndMateriaNoState, new String[]{"asigCurso", "idMateria"}, new Object[]{asignacionCurso, m});
                if(asigCM == null){
                    asigCM = new AsignacionCursoMaterias();
                    asigCM.setFueTomada(false);
                    asigCM.setEstado(true);
                    asigCM.setAsignacionCurso(asignacionCurso);
                    asigCM.setMateria(m);
                    services.saveEntity(asigCM);
                }else{
                    asigCM.setEstado(true);
                    services.updateAndPersistEntity(asigCM);
                }
            }
            for(Paralelo p : paralelos){
                asigCP = (AsignacionCursoParalelos) services.getEntityByParameters(Querys.getAsigCursoParalelosByAsigCursoAndParaleloNoState, new String[]{"asigCurso", "idParalelo"}, new Object[]{asignacionCurso, p});
                if(asigCP == null){
                

                    asigCP = new AsignacionCursoParalelos();
                    asigCP.setCupoDisponible(30);
                    asigCP.setAsignacionCurso(asignacionCurso);
                    asigCP.setEstado(true);
                    asigCP.setMaxCupo(30);
                    asigCP.setParalelo(p);
                    services.saveEntity(asigCP);
                }else{
                    asigCP.setEstado(true);
                    services.updateAndPersistEntity(asigCP);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
    
    @Override
    public AsignacionProfesor crearAsignacionProfesor(AsignacionProfesor asignacionP, AsignacionCurso acurso, List<Materia> materias){
        AsignacionProfesor asignacion = null;
        AsignacionCursoMaterias acm;
        try{
            /*
            AsignacionProfesorMaterias apm;
            asignacion = asignacionP;
            asignacion.setEstado(Boolean.TRUE);
            asignacion.setFechaCreacion(new Date());
            asignacion = (AsignacionProfesor) services.saveEntity(asignacion);
            for(Materia m : materias){
                acm = (AsignacionCursoMaterias) services.getEntityByParameters(Querys.getAsigCursoMateriasByAsigCursoAndMateria, new String[]{"asigCurso","idMateria"}, new Object[]{acurso, m});
                acm.setFueTomada(Boolean.TRUE);
                apm = new AsignacionProfesorMaterias();
                apm.setAsignacionProfesor(asignacion);
                apm.setMateria(m);
                services.saveEntity(apm);
                services.updateAndPersistEntity(acm);
            }
            */
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
            /*
            AsignacionProfesorMaterias newAsig;
            List<AsignacionProfesorMaterias> apmList;
            
            services.updateAndPersistEntity(asignacionProfesor);
            
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
            */
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
    
    @Override
    public Boolean guardarNotas(List<DetalleMateria> detalleMateriaList){
        Boolean b;
        try{
            b = true;
            for(DetalleMateria temp : detalleMateriaList){
                if(temp.getId()==null)
                    services.saveEntity(temp);
                else
                    services.updateAndPersistEntity(temp);
            }            
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
}
