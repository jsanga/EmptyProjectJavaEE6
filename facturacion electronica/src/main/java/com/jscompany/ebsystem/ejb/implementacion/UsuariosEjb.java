/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.usuarios.LoguinFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmailFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaFct;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefonoFct;
import com.jscompany.ebsystem.entidades.usuarios.RolFct;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.util.Date;
import java.util.List;
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
    private AclService services;
    
    private PersonaFct persona;
    private RolFct rol;
    
    @Override
    public Boolean validarUsuario(String username, String pass){
        Boolean b;
        
        try{
            LoguinFct l = (LoguinFct)services.getEntityByParameters(Querys.getUsuariobyUserPass, new String[]{"user", "pass"}, new Object[]{username, pass});
            
            if(l!=null){
                persona = l.getPersonaFct();
                rol = persona.getRol();
                
                switch(rol.getRolName()){
                    case "admin":
                        JsfUti.redirectFaces("/faces/admin/perfil.xhtml");
                        break;
                    case "profesor":
                        JsfUti.redirectFaces("/faces/profesor/perfil.xhtml");
                        break;
                    case "estudiante":
                        JsfUti.redirectFaces("/faces/estudiante/perfil.xhtml");
                        break;
                    case "invitado":
                        JsfUti.redirectFaces("/faces/general/perfil.xhtml");
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
    public LoguinFct guardarLoguin(LoguinFct l){
        return null;
    }
    
    @Override
    public Boolean guardarEdicionPersona(PersonaFct p, RolFct r){
        Boolean b;
        try{
            b=true;
            
            
        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    
    @Override
    public Boolean guardarNuevaPersona(PersonaFct p, RolFct r){
        Boolean b;
        try{
            b=true;
            
            
        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    
    
    @Override
    public PersonaFct guardarPersona(PersonaFct p, List<PersonaEmailFct> emailList, List<PersonaTelefonoFct> telefonoList, RolFct rol){
        return null;
    }
    
    @Override
    public RolFct guardarRol(RolFct r){
        return null;
    }
    
}
