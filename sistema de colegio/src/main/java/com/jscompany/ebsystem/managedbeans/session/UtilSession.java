/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@SessionScoped
public class UtilSession implements Serializable {

    public static final Long serialVersionUID = 1L;
    
    private Map<String, Object> parametros;
    
    public void instanciarParametros(){
        parametros = new HashMap<>();
    }
    
    public void agregarParametro(String nombre, Object value) {
        parametros.put(nombre, value);
    }

    public boolean tieneParametro(Object parametro) {
        return parametros.containsKey(parametro);
    }
    
    public boolean isNull(){
        if(parametros==null)
            return true;
        return false;
    }

    public boolean estaVacio() {
        if (parametros != null) {
            return parametros.isEmpty();
        } else {
            return true;
        }
    }

    public Object retornarValor(Object parametro) {
        Object o;
        try{
            o = parametros.get(parametro);
        }catch(Exception e){
            o=null;
            e.printStackTrace();
        }
        return o;
    }

    public void borrarDatos() {
        parametros = null;
    }
    
    public Boolean validarCantidadDeParametrosDelServlet(){
        if(parametros!=null && parametros.size()>0)
            return true;
        return false;
    }

    public Map getParametros() {
        return parametros;
    }
}
