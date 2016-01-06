/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Curso;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class CrudCursos implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Curso curso;
    private List<Curso> cursosList;
    
    @PostConstruct
    public void init(){
        cursosList = services.getListEntitiesByParameters(Querys.getCursosList, new String[]{}, new Object[]{});
        if(cursosList == null)
            cursosList = new ArrayList();
    }
    
    public void nuevoCurso(){
        this.curso = new Curso();
        curso.setEstado(Boolean.TRUE);
    }
    
    public void editarCurso(Curso c){
        this.curso = c;
    }
    
    public void eliminarCurso(Curso c){
        int i=0;
        for(Curso cl : cursosList){
            if(cl.getNombre().equals(c.getNombre())){
                if(c.getId() != null){
                    c.setEstado(Boolean.FALSE);
                    services.updateAndPersistEntity(c); 
                }
                cursosList.remove(i);
                break;
            }
            i++;
        }
    }
    
    public void guardarNuevo(){
        if((curso = (Curso) services.saveEntity(curso)) != null){
            cursosList.add(curso);
            JsfUti.messageInfo(null, "Info", "Se creó el curso satisfactoriamente");
        }
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al crear el curso.");
    }
    
    public void guardarEdicion(){
        if(services.updateAndPersistEntity(curso))
            JsfUti.messageInfo(null, "Info", "Se editó el curso satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al editar el curso.");
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursosList() {
        return cursosList;
    }

    public void setCursosList(List<Curso> cursosList) {
        this.cursosList = cursosList;
    }
    
}
