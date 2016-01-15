/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.interfaces;

import com.jscompany.ebsystem.entidades.colegios.AsignacionCurso;
import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Materia;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
import com.jscompany.ebsystem.entidades.colegios.Paralelo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JoaoIsrael
 */
@Local
public interface ColegiosServices {
    
    public AsignacionCurso crearAsignacionCursoMatriculaAsignacionProfesor(AsignacionCurso asignacionCurso, List<Matricula> matriculas, List<AsignacionProfesor> asignacionProfesorList, List<Materia> materias, List<Paralelo> paralelos);
    
    public AsignacionProfesor crearAsignacionProfesor(AsignacionProfesor asignacion, List<Materia> materias);
    
    public AsignacionCurso crearAsignacionCurso(AsignacionCurso asignacionCurso, List<Materia> materias, List<Paralelo> paralelos);
    
    public Boolean actualizarAsignacionCurso(AsignacionCurso asignacion);
    
    public Boolean actualizarAsignacionProfesor(AsignacionProfesor asignacionProfesor, List<Materia> materias);
    
}
