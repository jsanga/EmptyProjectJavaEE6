/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.security;

import com.jscompany.ebsystem.managedbeans.session.UserSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Usuario
 */
public class LoginFilter implements Filter{
    

    @Override
    public void init(FilterConfig filterConfig2) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {        
        
        UserSession sesion = (UserSession)((HttpServletRequest)request).getSession().getAttribute("userSession");
        String contextPath;
        
        if(!sesion.getIsLogged()){
            contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
        }
        else{
            String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());
            contextPath = ((HttpServletRequest)request).getContextPath();
            
            if(sesion.getRolPersona().equals("admin")){
                if (path.startsWith("/faces/general/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/profesor/")) {
                    sesion.cerrarSesion();
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                    return;
                }else{
                    if(path.startsWith("/faces/admin/")){
                        chain.doFilter(request, response);
                    }else
                        ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/notexists.xhtml");
                }
            }   
            
            if(sesion.getRolPersona().equals("estudiante")){
                if (path.startsWith("/faces/general/") || path.startsWith("/faces/admin/") || path.startsWith("/faces/profesor/")) {  
                    sesion.cerrarSesion();
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                    return;
                }else{
                    if(path.startsWith("/faces/estudiante/")){
                        chain.doFilter(request, response);
                    }else
                        ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/notexists.xhtml");
                }
            }
            
            if(sesion.getRolPersona().equals("profesor")){    
                if (path.startsWith("/faces/general/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/admin/")) {  
                    sesion.cerrarSesion();
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                    return;
                }else{
                    if(path.startsWith("/faces/profesor/")){
                        chain.doFilter(request, response);
                    }else
                        ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/notexists.xhtml");
                }
            }
            
            if(sesion.getRolPersona().equals("invitado")){
                if (path.startsWith("/faces/admin/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/profesor/")) {
                    sesion.cerrarSesion();
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                    return;
                }else{
                    if(path.startsWith("/faces/general/")){
                        chain.doFilter(request, response);
                    }else
                        ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/notexists.xhtml");
                }
            }
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
