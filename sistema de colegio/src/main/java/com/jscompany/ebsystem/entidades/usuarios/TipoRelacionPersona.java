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
@Table(name = "tipo_relacion_persona", schema = "usuarios")
@NamedQueries({
    @NamedQuery(name = "TipoRelacionPersona.findAll", query = "SELECT t FROM TipoRelacionPersona t")})
public class TipoRelacionPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 150)
    @Column(name = "nombre_relacion")
    private String nombreRelacion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "tipoRelacion", fetch = FetchType.LAZY)
    private Collection<RelacionPersona> relacionPersonaCollection;

    public TipoRelacionPersona() {
    }

    public TipoRelacionPersona(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRelacion() {
        return nombreRelacion;
    }

    public void setNombreRelacion(String nombreRelacion) {
        this.nombreRelacion = nombreRelacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<RelacionPersona> getRelacionPersonaCollection() {
        return relacionPersonaCollection;
    }

    public void setRelacionPersonaCollection(Collection<RelacionPersona> relacionPersonaCollection) {
        this.relacionPersonaCollection = relacionPersonaCollection;
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
        if (!(object instanceof TipoRelacionPersona)) {
            return false;
        }
        TipoRelacionPersona other = (TipoRelacionPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.TipoRelacionPersona[ id=" + id + " ]";
    }
    
}
