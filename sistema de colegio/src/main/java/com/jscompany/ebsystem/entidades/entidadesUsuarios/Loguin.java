/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.entidadesUsuarios;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "loguin")
@NamedQueries({
    @NamedQuery(name = "Loguin.findAll", query = "SELECT l FROM Loguin l")})
public class Loguin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_loguin")
    private Long idLoguin;
    @Size(max = 40)
    @Column(name = "pass")
    private String pass;
    @Size(max = 40)
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "idLoguin")
    private Collection<EntidadPersona> entidadPersonaCollection;

    public Loguin() {
    }

    public Loguin(Long idLoguin) {
        this.idLoguin = idLoguin;
    }

    public Long getIdLoguin() {
        return idLoguin;
    }

    public void setIdLoguin(Long idLoguin) {
        this.idLoguin = idLoguin;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<EntidadPersona> getEntidadPersonaCollection() {
        return entidadPersonaCollection;
    }

    public void setEntidadPersonaCollection(Collection<EntidadPersona> entidadPersonaCollection) {
        this.entidadPersonaCollection = entidadPersonaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoguin != null ? idLoguin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loguin)) {
            return false;
        }
        Loguin other = (Loguin) object;
        if ((this.idLoguin == null && other.idLoguin != null) || (this.idLoguin != null && !this.idLoguin.equals(other.idLoguin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesUsuarios.Loguin[ idLoguin=" + idLoguin + " ]";
    }
    
}
