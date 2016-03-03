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
@Table(name = "sexo_fct", schema = "facturacion_usuarios")
@NamedQueries({
    @NamedQuery(name = "SexoFct.findAll", query = "SELECT s FROM SexoFct s"),
    @NamedQuery(name = "SexoFct.findById", query = "SELECT s FROM SexoFct s WHERE s.id = :id"),
    @NamedQuery(name = "SexoFct.findByNombreSexo", query = "SELECT s FROM SexoFct s WHERE s.nombreSexo = :nombreSexo"),
    @NamedQuery(name = "SexoFct.findByEstado", query = "SELECT s FROM SexoFct s WHERE s.estado = :estado")})
public class SexoFct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "nombre_sexo")
    private String nombreSexo;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "sexo", fetch = FetchType.LAZY)
    private Collection<PersonaFct> personaFctCollection;

    public SexoFct() {
    }

    public SexoFct(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
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
        if (!(object instanceof SexoFct)) {
            return false;
        }
        SexoFct other = (SexoFct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.SexoFct[ id=" + id + " ]";
    }
    
}
