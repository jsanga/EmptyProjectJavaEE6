/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "loguin_fct", schema = "facturacion_usuarios")
@NamedQueries({
    @NamedQuery(name = "LoguinFct.findAll", query = "SELECT l FROM LoguinFct l"),
    @NamedQuery(name = "LoguinFct.findById", query = "SELECT l FROM LoguinFct l WHERE l.id = :id"),
    @NamedQuery(name = "LoguinFct.findByUsername", query = "SELECT l FROM LoguinFct l WHERE l.username = :username"),
    @NamedQuery(name = "LoguinFct.findByPass", query = "SELECT l FROM LoguinFct l WHERE l.pass = :pass"),
    @NamedQuery(name = "LoguinFct.findByEstado", query = "SELECT l FROM LoguinFct l WHERE l.estado = :estado")})
public class LoguinFct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "username")
    private String username;
    @Size(max = 50)
    @Column(name = "pass")
    private String pass;
    @Column(name = "estado")
    private Boolean estado;
    @OneToOne(mappedBy = "loguin", fetch = FetchType.LAZY)
    private PersonaFct personaFct;

    public LoguinFct() {
    }

    public LoguinFct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public PersonaFct getPersonaFct() {
        return personaFct;
    }

    public void setPersonaFct(PersonaFct personaFct) {
        this.personaFct = personaFct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoguinFct)) {
            return false;
        }
        LoguinFct other = (LoguinFct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.LoguinFct[ id=" + id + " ]";
    }
    
}
