/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.interfaces.usuarios;

import javax.ejb.Local;

/**
 *
 * @author JoaoIsrael
 */
@Local
public interface UsuariosServices {
    
    public Boolean validarUsuario(String username, String pass);
    
}
