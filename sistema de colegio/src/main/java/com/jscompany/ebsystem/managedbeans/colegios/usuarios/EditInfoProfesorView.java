/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.managedbeans.colegios.usuarios;

import com.jscompany.ebsystem.database.Querys;
import com.jscompany.ebsystem.ejb.interfaces.ColegiosServices;
import com.jscompany.ebsystem.entidades.usuarios.Loguin;
import com.jscompany.ebsystem.entidades.usuarios.Persona;
import com.jscompany.ebsystem.entidades.usuarios.PersonaEmail;
import com.jscompany.ebsystem.entidades.usuarios.PersonaTelefono;
import com.jscompany.ebsystem.entidades.usuarios.Profesor;
import com.jscompany.ebsystem.managedbeans.session.UserSession;
import com.jscompany.ebsystem.managedbeans.session.UtilSession;
import com.jscompany.ebsystem.services.AclService;
import com.jscompany.ebsystem.util.JsfUti;
import java.io.Serializable;
import java.util.ArrayList;
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
public class EditInfoProfesorView implements Serializable{
    
    public static final Long serialVerisonUID = 1L;
    
    @EJB(beanName = "aclService")
    private AclService services;
    
    @EJB(beanName = "colegiosServices")
    private ColegiosServices colServices;
    
    @ManagedProperty (value = "#{utilSession}")
    private UtilSession utilSession;
    
    @ManagedProperty(value = "#{userSession}")
    private UserSession uSession;
    
    private Loguin loguin;
    private Persona person;
    private Profesor prof;
    private PersonaEmail pemail, pemailedit;
    private List<PersonaEmail> pemailList;
    private PersonaTelefono ptelf, ptelfedit;
    private List<PersonaTelefono> ptelfList;
    private String pass1, pass2;
    
    private Boolean mostrarPass;
    
    @PostConstruct
    public void init(){
        if(!uSession.getIsLogged())
                return;
        person = (Persona) services.getEntityByParameters(Querys.getUsuarioByUser, new String[]{"username"}, new Object[]{uSession.getUsername()});
        loguin = person.getLoguin();
        if(loguin == null){
            //
            JsfUti.messageError(null, "Error", "El usuario no tiene loguin");
            return;
        }
        prof = person.getProfesor();
        if(prof == null || !person.getRol().getRolName().equals("profesor")){
            //
            JsfUti.messageError(null, "Error", "El usuario no es profesor de la institución");
            return;
        }
        
        pemailList = (List<PersonaEmail>) person.getPersonaEmailCollection();
        if(pemailList==null)
            pemailList = new ArrayList();
        ptelfList = (List<PersonaTelefono>) person.getPersonaTelefonoCollection();
        if(ptelfList==null)
            ptelfList = new ArrayList();
        if(loguin.getPass().equals("123"))
            mostrarPass = true;
        else
            mostrarPass = false;
        pemail = new PersonaEmail();
        pemail.setEstado(true);
        ptelf = new PersonaTelefono();
        ptelf.setEstado(true);
    }
    
    public void cambiarPassword(){
        if(pass1.equals(pass2)){
            JsfUti.messageInfo(null, "Info", "Se actualizó la contraseña correctamente.");
            loguin.setPass(pass1);
            services.updateAndPersistEntity(loguin);
        }else{
            JsfUti.messageInfo(null, "Info", "La contraseña no coincide.");
        }
        pass1 = pass2 = null;
    }
    
    public void cambiarEmail(){
        if(pemailedit.getId()!=null)
            services.updateAndPersistEntity(pemailedit);
    }
    
    public void cambiarTelefono(){
        if(ptelfedit.getId()!=null)
            services.updateAndPersistEntity(ptelfedit);
    }
    
    public void agregarEmail(){
        pemailList.add(pemail);
        pemail = new PersonaEmail();
        pemail.setEstado(true);
    }
    
    public void agregarTelefono(){
        ptelfList.add(ptelf);
        ptelf = new PersonaTelefono();
        ptelf.setEstado(true);
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

    public Loguin getLoguin() {
        return loguin;
    }

    public void setLoguin(Loguin loguin) {
        this.loguin = loguin;
    }

    public Persona getPerson() {
        return person;
    }

    public void setPerson(Persona person) {
        this.person = person;
    }

    public Profesor getProf() {
        return prof;
    }

    public void setProf(Profesor prof) {
        this.prof = prof;
    }

    public PersonaEmail getPemail() {
        return pemail;
    }

    public void setPemail(PersonaEmail pemail) {
        this.pemail = pemail;
    }

    public PersonaTelefono getPtelf() {
        return ptelf;
    }

    public void setPtelf(PersonaTelefono ptelf) {
        this.ptelf = ptelf;
    }

    public Boolean getMostrarPass() {
        return mostrarPass;
    }

    public void setMostrarPass(Boolean mostrarPass) {
        this.mostrarPass = mostrarPass;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public List<PersonaEmail> getPemailList() {
        return pemailList;
    }

    public void setPemailList(List<PersonaEmail> pemailList) {
        this.pemailList = pemailList;
    }

    public List<PersonaTelefono> getPtelfList() {
        return ptelfList;
    }

    public void setPtelfList(List<PersonaTelefono> ptelfList) {
        this.ptelfList = ptelfList;
    }

    public PersonaEmail getPemailedit() {
        return pemailedit;
    }

    public void setPemailedit(PersonaEmail pemailedit) {
        this.pemailedit = pemailedit;
    }

    public PersonaTelefono getPtelfedit() {
        return ptelfedit;
    }

    public void setPtelfedit(PersonaTelefono ptelfedit) {
        this.ptelfedit = ptelfedit;
    }
    
}
