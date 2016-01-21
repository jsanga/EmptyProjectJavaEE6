/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.certificados;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.entidades.certificados.Certificado;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joao Sanga
 */
@ManagedBean
@ViewScoped
public class CertificadoView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Colegio colegio;
    private Certificado certificado;
    private List<Certificado> certificadosList;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
                return;
        colegio = (Colegio) services.getEntity(Colegio.class, uSession.getIdColegio());
        certificadosList = services.getListEntitiesByParameters(Querys.getCertificadosListByColegio, new String[]{"colegio"}, new Object[]{colegio});
    }
    
    public void crearCertificado(Certificado c){
        certificado = c;
    }

    public UtilSession getUtilSession() {
        return utilSession;
    }

    public void setUtilSession(UtilSession utilSession) {
        this.utilSession = utilSession;
    }

    public UserSession getuSession() {
        return uSession;
    }

    public void setuSession(UserSession uSession) {
        this.uSession = uSession;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public List<Certificado> getCertificadosList() {
        return certificadosList;
    }

    public void setCertificadosList(List<Certificado> certificadosList) {
        this.certificadosList = certificadosList;
    }
    
}
