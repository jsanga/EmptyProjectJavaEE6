/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.usuarios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.usuarios.UsuariosServices;
import com.jscompany.ebsystem.entidades.entidadesUsuarios.Loguin;
import com.jscompany.ebsystem.services.AclService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author JoaoIsrael
 */
@Stateless(name = "usuariosServices")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class UsuariosEjb implements UsuariosServices{
    
    @EJB(beanName = "aclService")
    protected AclService services;
    
    @Override
    public Boolean validarUsuario(String username, String pass){
        Boolean b;
        try{
            Loguin l = (Loguin)services.getEntityByParameters(Querys.getUsuariobyUserPass, new String[]{"user", "pass"}, new Object[]{username, pass});
            if(l!=null)
                b=true;
            else
                b=false;
        }catch(Exception e){
            b = false;
            e.printStackTrace();
        }
        return b;
    }
    
}
