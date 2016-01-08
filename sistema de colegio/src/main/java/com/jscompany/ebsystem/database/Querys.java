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
    
    // Querys de cursos
    public static String getCursosList = "select e from Curso e";
    
    // Querys de personas
    public static String getPersonaList = "select e from Persona e";
    public static String getProfesoresList = "select e from Persona e where e.rol = :rol";
    public static String getEstudiantesList = "select e from Persona e where e.rol = :rol";
    public static String getPersonalList = "select e from Persona e where e.rol = 5";
    public static String getPersonaByCedula = "select e from Persona e where e.cedula = :cedula";
    public static String getPersonaByCedulaAndNOTRol = "select e from Persona e where e.cedula = :cedula and e.rol != :idRol";
    public static String getProfesorByPersonaID = "select e from Profesor e where e.persona = :idPersona";
    public static String getPersonalByPersonaID = "select e from Personal e where e.persona = :idPersona";
    public static String getEstudianteList = "select e from Estudiante e";
    public static String getRolById = "Select e from Rol e where e.id = :rolId";
    public static String getRolList = "select e from Rol e";
    public static String getRelacionesList = "select e from TipoRelacionPersona e";
    public static String getRelacionesByPersona = "select e from RelacionPersona e where e.persona = :idPersona";
    
    // Querys de materias
    public static String getMateriasList = "select e from Materia e";
}
