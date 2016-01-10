/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import java.io.Serializable;
import java.math.BigInteger;
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
import org.hibernate.annotations.FilterDef;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "relacion_persona", schema = "usuarios")
@FilterDef(name = "activos", 
    defaultCondition = "estado = 'TRUE'")
@NamedQueries({
    @NamedQuery(name = "RelacionPersona.findAll", query = "SELECT r FROM RelacionPersona r")})
public class RelacionPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "persona")
    private BigInteger persona;
    @Column(name = "persona_es")
    private BigInteger personaEs;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "tipo_relacion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoRelacionPersona tipoRelacion;

    public RelacionPersona() {
    }

    public RelacionPersona(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getPersona() {
        return persona;
    }

    public void setPersona(BigInteger persona) {
        this.persona = persona;
    }

    public BigInteger getPersonaEs() {
        return personaEs;
    }

    public void setPersonaEs(BigInteger personaEs) {
        this.personaEs = personaEs;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoRelacionPersona getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacionPersona tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
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
        if (!(object instanceof RelacionPersona)) {
            return false;
        }
        RelacionPersona other = (RelacionPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.RelacionPersona[ id=" + id + " ]";
    }
    
}
