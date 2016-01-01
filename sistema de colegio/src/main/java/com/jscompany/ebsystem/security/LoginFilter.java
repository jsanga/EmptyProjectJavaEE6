/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.security;

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
        /*
        UserSession sesion = (UserSession)((HttpServletRequest)request).getSession().getAttribute("sesionUsr");
        
        if(sesion == null || !sesion.isLogged()){
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
        }
        else{
            String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());

            if(sesion.getRol().equals("admin")){
                if (path.startsWith("/faces/usuario/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/profesor/")) {                
                    String contextPath = ((HttpServletRequest)request).getContextPath();
                    sesion.setLogged(false);
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                }
                else
                    chain.doFilter(request, response);
            }   
            
            if(sesion.getRol().equals("estudiante")){
                if (path.startsWith("/faces/usuario/") || path.startsWith("/faces/admin/") || path.startsWith("/faces/profesor/")) {                
                    String contextPath = ((HttpServletRequest)request).getContextPath();
                    sesion.setLogged(false);
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                }
                else
                    chain.doFilter(request, response);
            }
            
            if(sesion.getRol().equals("profesor")){    
                if (path.startsWith("/faces/usuario/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/admin/")) {                
                    String contextPath = ((HttpServletRequest)request).getContextPath();
                    sesion.setLogged(false);
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                }
                else
                    chain.doFilter(request, response);
            }
            
            if(sesion.getRol().equals("usuario")){
                if (path.startsWith("/faces/admin/") || path.startsWith("/faces/estudiante/") || path.startsWith("/faces/profesor/")) {                
                    String contextPath = ((HttpServletRequest)request).getContextPath();
                    sesion.setLogged(false);
                    ((HttpServletResponse)response).sendRedirect(contextPath+"/faces/recursos/sinpermisos.xhtml");
                }
                else
                    chain.doFilter(request, response);
            }
        }
                */
        
    }

    @Override
    public void destroy() {
        
    }
    
}
