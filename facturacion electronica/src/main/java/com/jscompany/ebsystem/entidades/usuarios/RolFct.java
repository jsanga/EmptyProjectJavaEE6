/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Joao Sanga
 */
@Entity
@Table(name = "rol_fct", schema = "facturacion_usuarios")
@NamedQueries({
    @NamedQuery(name = "RolFct.findAll", query = "SELECT r FROM RolFct r"),
    @NamedQuery(name = "RolFct.findById", query = "SELECT r FROM RolFct r WHERE r.id = :id"),
    @NamedQuery(name = "RolFct.findByRolName", query = "SELECT r FROM RolFct r WHERE r.rolName = :rolName"),
    @NamedQuery(name = "RolFct.findByEstado", query = "SELECT r FROM RolFct r WHERE r.estado = :estado")})
public class RolFct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "rol_name")
    private String rolName;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<PersonaFct> personaFctCollection;

    public RolFct() {
    }

    public RolFct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<PersonaFct> getPersonaFctCollection() {
        return personaFctCollection;
    }

    public void setPersonaFctCollection(Collection<PersonaFct> personaFctCollection) {
        this.personaFctCollection = personaFctCollection;
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
        if (!(object instanceof RolFct)) {
            return false;
        }
        RolFct other = (RolFct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.RolFct[ id=" + id + " ]";
    }
    
}
