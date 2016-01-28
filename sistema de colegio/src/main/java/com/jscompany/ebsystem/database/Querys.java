/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.database;

/**
 *
 * @author JoaoIsrael
 */
public class Querys {
    
    // Querys de usuarios
    public static String getUsuariobyUserPass = "select e from Loguin e where e.username = :user and e.pass = :pass";
    public static String getUsuarioByUser = "select e from Persona e where e.loguin.username = :username";
    
    // Querys de colegios
    public static String getColegiosList = "select e from Colegio e where e.estado = TRUE";
    public static String getColegiosListNoState = "select e from Colegio e";
    public static String getTipoColegioList = "select e from TipoColegio e where e.estado = TRUE";
    public static String getColegiosByTipoList = "select e from Colegio e where e.estado = TRUE and e.tipoColegio = :tipo";
    public static String getJornadasList = "select e from Jornada e where e.estado = TRUE";
    
    // Querys de asignaciones de curso, profesor y matriculas
    public static String getAsignacionesCursoList = "select e from AsignacionCurso e where e.estado = TRUE and e.colegio = :colegio";
    public static String getAsignacionesCursoListByColegioIdNoState = "select e from AsignacionCurso e where e.colegio = :colegio";
    public static String getAsignacionesProfesorList = "select e from AsignacionProfesor e where e.estado = TRUE";
    public static String getAsignacionesProfesorByAsignacionCursoAndParaleloList = "select e from AsignacionProfesor e where e.asignacionCurso = :asigCurso and e.paralelo = :paralelo";
    public static String getAsignacionesProfesorListByProfesorId = "select e from AsignacionProfesor e where e.estado = TRUE and e.profesor = :profesor";
    public static String getAsignacionProfesorMateriaByMateriaAndAsignacionIdNoState = "select e from AsignacionProfesorMaterias e where e.asignacionProfesor = :asigProf and materia = :materia";
    public static String getAsignacionProfesorMateriaByAsignacionId = "select e from AsignacionProfesorMaterias e where e.asignacionProfesor = :asigProf and e.estado = TRUE";
    public static String getAsignacionesProfesorListNoState = "select e from AsignacionProfesor e";
    public static String getMatriculasNoState = "select e from Matricula e";
    public static String getMatriculasByAsigCurAndParalelo = "select e from Matricula e where e.asignacionCurso = :asigCur and e.paralelo = :paralelo";
    public static String getDetalleMateriaListByProfId = "select e from DetalleMateria e";
    public static String getDetalleMateriaByProfEstMat = "select e from DetalleMateria e where e.materia = :materia and e.profesor = :profesor and e.estudiante = :estudiante";
    public static String getMatriculasByEstudianteNoState = "select e from Matricula e where e.estudiante = :estudiante";
    
    // Querys tablas intermedias
    public static String getAsigCursoMaterias = "select e from AsignacionCursoMaterias e where e.estado = TRUE AND e.asignacionCurso = :asigCurso and e.fueTomada = FALSE";
    public static String getAsigCursoMateriasByAsigCursoAndMateria = "select e from AsignacionCursoMaterias e where e.estado = TRUE AND e.asignacionCurso = :asigCurso and e.materia = :idMateria";
    public static String getAsigCursoMateriasByAsigCursoAndMateriaNoState = "select e from AsignacionCursoMaterias e where e.asignacionCurso = :asigCurso and e.materia = :idMateria";
    public static String getAsigCursoParalelos = "select e from AsignacionCursoParalelos e where e.estado = TRUE AND e.asignacionCurso = :asigCurso";
    public static String getAsigCursoParalelosByAsigCursoAndParaleloNoState = "select e from AsignacionCursoParalelos e where e.asignacionCurso = :asigCurso and e.paralelo = :idParalelo";
    
    // Querys de cursos
    public static String getCursosList = "select e from Curso e where e.estado = TRUE";
    public static String getCursosListNoState = "select e from Curso e";
    
    // Querys de personas
    public static String getPersonaList = "select e from Persona e where e.estado = TRUE";
    public static String getPersonaListNoState = "select e from Persona e";
    public static String getPersonaListByRol = "select e from Persona e where e.rol = :rol and e.estado = TRUE";
    public static String getPersonaListByRolAndColegio = "select e from Persona e where e.rol = :rol and e.estado = TRUE and e.colegio = :colegio";
    public static String getPersonaListByRolNoState = "select e from Persona e where e.rol = :rol";
    public static String getPersonaByCedula = "select e from Persona e where e.cedula = :cedula";
    public static String getPersonaByCedulaAndNOTRol = "select e from Persona e where e.cedula = :cedula and e.rol != :idRol";
    public static String getProfesorByPersonaID = "select e from Profesor e where e.persona = :idPersona";
    public static String getPersonalByPersonaID = "select e from Personal e where e.persona = :idPersona";
    public static String getPersonaByID = "select e from Persona e where e.id = :idPersona";
    public static String getRolById = "Select e from Rol e where e.id = :rolId";
    public static String getRolList = "select e from Rol e";
    public static String getRelacionesList = "select e from TipoRelacionPersona e";
    public static String getRelacionesByPersona = "select e from RelacionPersona e where e.persona = :idPersona";
    public static String getRelacionByPersonaPersonaEsAndTipoRelacion = "select e from RelacionPersona e where e.persona = :idPersona and e.personaEs = :personaEs and e.tipoRelacion = :tipoRelacion";
    
    // Querys de materias
    public static String getMateriasList = "select e from Materia e where e.estado = TRUE";
    public static String getMateriasListNoState = "select e from Materia e";
    
    // Querys de paralelos
    public static String getParalelosList = "select e from Paralelo e where e.estado = TRUE";
    public static String getParalelosListNoState = "select e from Paralelo e";
    
    // Querys de periodos lectivos
    public static String getPeriodosList = "select e from PeriodoLectivo e where e.estado = TRUE";
    public static String getPeriodosListNoState = "select e from PeriodoLectivo e";
    
    // Querys de certificados
    public static String getCertificadosListByColegio = "select e from Certificado e where e.tipoCertificado.colegio = :colegio and e.estado = TRUE";
    public static String getCertificadosListByColegioNoState = "select e from PeriodoLectivo e where e.tipoCertificado.colegio = :colegio ";

    // Querys de certificados
    
    
}
