/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.interfaces;

import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.entidades.usuarios.Notificacion;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmail;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefono;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JoaoIsrael
 */
@Local
public interface UsuariosServices {
    
    public Boolean validarUsuario(String username, String pass);
    
    public Loguin guardarLoguin(Loguin l);
    
    public Notificacion guardarNotificacion(Notificacion n);
    
    public Persona guardarPersona(Persona p, List<PersonaEmail> emailList, List<PersonaTelefono> telefonoList, Rol rol);
    
    public Rol guardarRol(Rol r);
    
    public Integer guardarPersonasEstudiantesExcel(List<Persona> personas, Rol rol, Colegio colegio);
    
}
