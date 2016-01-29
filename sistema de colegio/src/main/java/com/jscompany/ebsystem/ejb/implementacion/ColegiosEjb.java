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
            for(Paralelo p : paralelos){
                acp = new AsignacionCursoParalelos();
                acp.setAsignacionCurso(asignacion);
                acp.setParalelo(p);
                acp.setMaxCupo(30);
                acp.setCupoDisponible(30);
                acp.setEstado(Boolean.TRUE);
                services.saveEntity(acp);
                for(Materia m : materias){
                    acm = new AsignacionCursoMaterias();
                    acm.setAsignacionCurso(asignacion);
                    acm.setMateria(m);
                    acm.setParalelo(p);
                    acm.setFueTomada(Boolean.FALSE);
                    acm.setEstado(Boolean.TRUE);
                    services.saveEntity(acm);
                }
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
            
            List<AsignacionCursoMaterias> asigCMList;
            List<AsignacionCursoParalelos> asigCPList;
            
            asigCMList = services.getListEntitiesByParameters(Querys.getAsigCursoMateriasByAsigCurso, new String[]{"asigCurso"}, new Object[]{asignacionCurso});
            asigCPList = services.getListEntitiesByParameters(Querys.getAsigCursoParalelos, new String[]{"asigCurso"}, new Object[]{asignacionCurso});
            
            for(AsignacionCursoMaterias temp : asigCMList){
                temp.setEstado(false);
                temp.setFueTomada(false);
                services.updateAndPersistEntity(temp);
            }
            
            for(AsignacionCursoParalelos temp : asigCPList){
                temp.setEstado(false);
                services.updateAndPersistEntity(temp);
            }
            
            services.updateEntity(asignacionCurso);
            
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
                    asigCM.setFueTomada(false);
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
    public AsignacionProfesor crearAsignacionProfesor(AsignacionProfesor asignacionP, AsignacionCurso acurso, List<AsignacionCursoMaterias> materias){
        AsignacionProfesor asignacion;
        try{
            asignacion = asignacionP;
            asignacion.setEstado(Boolean.TRUE);
            asignacion.setFechaCreacion(new Date());
            asignacion = (AsignacionProfesor) services.saveEntity(asignacion);
            
            for(AsignacionCursoMaterias temp : materias){
                temp.setAsignacionProfesor(asignacion);
                temp.setFueTomada(Boolean.TRUE);
                services.updateAndPersistEntity(temp);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            asignacion = null;
        }
        return asignacion;
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
