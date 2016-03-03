/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.interfaces;

import com.jscompany.ebsystem.entidades.usuarios.LoguinFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmailFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefonoFct;
import com.jscompany.ebsystem.entidades.usuarios.RolFct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JoaoIsrael
 */
@Local
public interface UsuariosServices {
    
    public Boolean validarUsuario(String username, String pass);
    
    public LoguinFct guardarLoguin(LoguinFct l);
    
    public PersonaFct guardarPersona(PersonaFct p, List<PersonaEmailFct> emailList, List<PersonaTelefonoFct> telefonoList, RolFct rol);
    
    public RolFct guardarRol(RolFct r);
    
    public Boolean guardarNuevaPersona(PersonaFct p, RolFct r);
    
    public Boolean guardarEdicionPersona(PersonaFct p, RolFct r);
    
}
