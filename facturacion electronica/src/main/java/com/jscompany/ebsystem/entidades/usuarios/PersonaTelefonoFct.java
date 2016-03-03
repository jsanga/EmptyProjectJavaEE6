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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "persona_telefono_fct", schema = "facturacion_usuarios")
@NamedQueries({
    @NamedQuery(name = "PersonaTelefonoFct.findAll", query = "SELECT p FROM PersonaTelefonoFct p"),
    @NamedQuery(name = "PersonaTelefonoFct.findById", query = "SELECT p FROM PersonaTelefonoFct p WHERE p.id = :id"),
    @NamedQuery(name = "PersonaTelefonoFct.findByTelefono", query = "SELECT p FROM PersonaTelefonoFct p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PersonaTelefonoFct.findByEstado", query = "SELECT p FROM PersonaTelefonoFct p WHERE p.estado = :estado")})
public class PersonaTelefonoFct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "persona", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonaFct persona;

    public PersonaTelefonoFct() {
    }

    public PersonaTelefonoFct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public PersonaFct getPersona() {
        return persona;
    }

    public void setPersona(PersonaFct persona) {
        this.persona = persona;
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
        if (!(object instanceof PersonaTelefonoFct)) {
            return false;
        }
        PersonaTelefonoFct other = (PersonaTelefonoFct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.PersonaTelefonoFct[ id=" + id + " ]";
    }
    
}
