/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.ejb.implementacion;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.HibernateEjbInterceptor;
import com.jscompany.ebsystem.ejb.interfaces.UsuariosServices;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.usuarios.Estudiante;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.entidades.usuarios.Notificacion;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmail;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefono;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
import com.jscompany.ebsystem.entidades.usuarios.Rol;
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
    public Boolean guardarEdicionPersona(Persona p, Rol r, Colegio c){
        Boolean b;
        try{
            b=true;
            persona = p;
            
            switch(rol.getId().intValue()){
                case 1:
                    break;
                case 2:
                    Profesor pr = (Profesor)services.getEntityByParameters(Querys.getProfesorByPersonaID, new String[]{"idPersona"}, new Object[]{persona});
                    services.delete(pr);
                    break;
                case 3:
                    Estudiante e = (Estudiante)services.getEntityByParameters(Querys.getEstudianteByPersonaID, new String[]{"idPersona"}, new Object[]{persona});
                    services.delete(e);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
            persona.setColegio(c);
            persona.setRol(r);
            services.updateAndPersistEntity(persona);
            
            switch(rol.getId().intValue()){
                case 1:
                    break;
                case 2:
                    Profesor pr = new Profesor();
                    pr.setPersona(persona);
                    services.saveEntity(pr);
                    break;
                case 3:
                    Estudiante e = new Estudiante();
                    e.setPersona(persona);
                    services.saveEntity(e);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    
    @Override
    public Boolean guardarNuevaPersona(Persona p, Rol r, Colegio c){
        Boolean b;
        try{
            b=true;
            
            persona.setColegio(c);
            persona.setRol(r);
            services.saveEntity(persona);
            
            switch(rol.getId().intValue()){
                case 1:
                    break;
                case 2:
                    Profesor pr = new Profesor();
                    pr.setPersona(persona);
                    services.saveEntity(pr);
                    break;
                case 3:
                    Estudiante e = new Estudiante();
                    e.setPersona(persona);
                    services.saveEntity(e);
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }
        return b;
    }
    
    @Override
    public Integer guardarPersonasEstudiantesExcel(List<Persona> personas, Rol rol, Colegio colegio){
        Integer b;
        try{
            b = 0;
            Estudiante est;
            Persona p;
            for(Persona temp : personas){
                p = (Persona) services.getEntityByParameters(Querys.getPersonaByCedula, new String[]{"cedula"}, new Object[]{temp.getCedula()});
                if(p==null){
                    temp.setColegio(colegio);
                    temp.setRol(rol);
                    temp.setFechaEntrada(new Date());
                    temp = (Persona) services.saveEntity(temp);
                    est = new Estudiante();
                    est.setPersona(temp);
                    services.saveEntity(est);
                    b++;
                }           
            }
        }catch(Exception e){
            e.printStackTrace();
            b = 0;
        }
        return b;
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
