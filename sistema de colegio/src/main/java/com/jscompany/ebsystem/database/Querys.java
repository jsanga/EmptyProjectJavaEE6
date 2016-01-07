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
    public static String getColegiosList = "select e from Colegio e";
    
    // Querys de cursos
    public static String getCursosList = "select e from Curso e";
    
    // Querys de personas
    public static String getPersonaList = "select e from Persona e";
    public static String getProfesoresList = "select e from Persona e where e.rol = 2";
    public static String getEstudiantesList = "select e from Persona e where e.rol = 3";
    public static String getPersonalList = "select e from Persona e where e.rol = 5";
    public static String getPersonaByCedula = "select e from Persona e where e.cedula = :cedula";
    public static String getPersonaByCedulaAndNOTRol = "select e from Persona e where e.cedula = :cedula and e.rol != :idRol";
    public static String getProfesorByPersonaID = "select e from Profesor e where e.persona = :idPersona";
    public static String getPersonalByPersonaID = "select e from Personal e where e.persona = :idPersona";
    public static String getEstudianteList = "select e from Estudiante e";
    
    // Querys de materias
    public static String getMateriasList = "select e from Materia e";
}
