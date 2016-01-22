/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.entidades.colegios.TipoColegio;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JoaoIsrael
 */
@ManagedBean
@ViewScoped
public class CrudColegios implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private List<Colegio> colegiosList;
    private List<Colegio> colegiosMatrizList;
    private Colegio colegio, colegioMatriz;
    private TipoColegio tipoCol;
    private List<TipoColegio> tipos;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
            return;
        colegiosList = (List<Colegio>)services.getListEntitiesByParameters(Querys.getColegiosListNoState, new String[]{}, new Object[]{});
        if(colegiosList == null)
            colegiosList = new ArrayList();
        tipos = (List<TipoColegio>)services.getListEntitiesByParameters(Querys.getTipoColegioList, new String[]{}, new Object[]{});
        colegiosMatrizList = services.getListEntitiesByParameters(Querys.getColegiosByTipoList, new String[]{"tipo"}, new Object[]{tipos.get(1)});
    }
    
    public void nuevoColegio(){
        this.colegio = new Colegio();
        colegio.setEstado(Boolean.TRUE);
        colegio.setFechaCreacion(new Date());
    }
    
    public void editarColegio(Colegio c){
        this.colegio = c;
    }
    
    public void eliminarColegio(Colegio c){
        c.setEstado(Boolean.FALSE);
        if(services.updateAndPersistEntity(c))
            JsfUti.messageInfo(null, "Info", "Se deshabilitó el colegio satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al deshabilitar el colegio.");
    }
    
    public void habilitarColegio(Colegio c){
        c.setEstado(Boolean.TRUE);
        if(services.updateAndPersistEntity(c))
            JsfUti.messageInfo(null, "Info", "Se habilitó el colegio satisfactoriamente");
        else
            JsfUti.messageError(null, "Error", "Hubo un problema al habiitar el colegio.");
    }
    
    public void guardarNuevo(){
        if(colegio.getTipoColegio() == null){
            JsfUti.messageError(null, "Error", "El colegio debe tener un tipo antes de ser creado.");
            return;
        }
        if(colegio.getTipoColegio().getId() != 1){
            if(colegioMatriz!=null){
                colegio.setColegioMatriz(BigInteger.valueOf(colegioMatriz.getId()));
                if((colegio = (Colegio) services.saveEntity(colegio)) != null){
                    colegiosList.add(colegio);
                    JsfUti.messageInfo(null, "Info", "Se creó el colegio satisfactoriamente");
                }
                else
                    JsfUti.messageError(null, "Error", "Hubo un problema al crear el colegio.");
            }else{
                JsfUti.messageError(null, "Error", "Debe seleccionar un colegio matriz.");
            }
        }else{
            if((colegio = (Colegio) services.saveEntity(colegio)) != null){
                colegiosList.add(colegio);
                JsfUti.messageInfo(null, "Info", "Se creó el colegio satisfactoriamente");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al crear el colegio.");
        }        
    }
    
    public void guardarEdicion(){
        if(colegio.getTipoColegio() == null){
            JsfUti.messageError(null, "Error", "El colegio debe tener un tipo antes de ser editado.");
            return;
        }
        if(colegio.getTipoColegio().getId() != 1){
            if(colegioMatriz!=null){
                colegio.setColegioMatriz(BigInteger.valueOf(colegioMatriz.getId()));
                if(services.updateAndPersistEntity(colegio)){
                    colegiosList.add(colegio);
                    JsfUti.messageInfo(null, "Info", "Se editó el colegio satisfactoriamente");
                }
                else
                    JsfUti.messageError(null, "Error", "Hubo un problema al editar el colegio.");
            }else{
                JsfUti.messageError(null, "Error", "Debe seleccionar un colegio matriz.");
            }
        }else{
            if( services.updateAndPersistEntity(colegio)){
                colegiosList.add(colegio);
                JsfUti.messageInfo(null, "Info", "Se editó el colegio satisfactoriamente");
            }
            else
                JsfUti.messageError(null, "Error", "Hubo un problema al editar el colegio.");
        }
        
    }

    public List<Colegio> getColegiosList() {
        return colegiosList;
    }

    public void setColegiosList(List<Colegio> colegiosList) {
        this.colegiosList = colegiosList;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public List<TipoColegio> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoColegio> tipos) {
        this.tipos = tipos;
    }

    public List<Colegio> getColegiosMatrizList() {
        return colegiosMatrizList;
    }

    public void setColegiosMatrizList(List<Colegio> colegiosMatrizList) {
        this.colegiosMatrizList = colegiosMatrizList;
    }

    public Colegio getColegioMatriz() {
        return colegioMatriz;
    }

    public void setColegioMatriz(Colegio colegioMatriz) {
        this.colegioMatriz = colegioMatriz;
    }

    public TipoColegio getTipoCol() {
        return tipoCol;
    }

    public void setTipoCol(TipoColegio tipoCol) {
        this.tipoCol = tipoCol;
    }
    
}
