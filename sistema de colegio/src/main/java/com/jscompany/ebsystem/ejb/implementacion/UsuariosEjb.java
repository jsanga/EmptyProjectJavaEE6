/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.entidades.usuarios.Notificacion;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmail;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefono;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import javax.interceptor.Interceptors;

/**
 *
 * @author JoaoIsrael
 */
@Stateless(name = "usuariosServices")
@Interceptors(value = {HibernateEjbInterceptor.class})
public class UsuariosEjb implements UsuariosServices{
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    private Persona persona;
    private Rol rol;
    
    @Override
    public Boolean validarUsuario(String username, String pass){
        Boolean b;
        
        try{
            Loguin l = (Loguin)services.getEntityByParameters(Querys.getUsuariobyUserPass, new String[]{"user", "pass"}, new Object[]{username, pass});
            
            if(l!=null){
                persona = l.getPersona();
                rol = l.getPersona().getRol();
                
                switch(rol.getRolName()){
                    case "admin":
                        JsfUti.redirectFaces("/faces/admin/principal.xhtml");
                        break;
                    case "profesor":
                        JsfUti.redirectFaces("/faces/profesor/principal.xhtml");
                        break;
                    case "estudiante":
                        JsfUti.redirectFaces("/faces/estudiante/principal.xhtml");
                        break;
                    case "invitado":
                        JsfUti.redirectFaces("/faces/general/principal.xhtml");
                        break;
                    default:
                        System.out.println("Error!");
                        break;
                }
                
                b = true;
            }else
                b=false;
        }catch(Exception e){
            b = false;
            e.printStackTrace();
        }
        return b;
    }
    
    @Override
    public Loguin guardarLoguin(Loguin l){
        return null;
    }
    
    @Override
    public Notificacion guardarNotificacion(Notificacion n){
        return null;
    }
    
    @Override
    public Persona guardarPersona(Persona p, List<PersonaEmail> emailList, List<PersonaTelefono> telefonoList, Rol rol){
        return null;
    }
    
    @Override
    public Rol guardarRol(Rol r){
        return null;
    }
    
}
