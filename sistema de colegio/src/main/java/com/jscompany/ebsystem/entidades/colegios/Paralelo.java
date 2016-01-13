/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "paralelo", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "Paralelo.findAll", query = "SELECT p FROM Paralelo p")})
public class Paralelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paralelo", fetch = FetchType.LAZY)
    private Collection<AsignacionCursoParalelos> asignacionCursoParalelosCollection;
    @OneToMany(mappedBy = "paralelo", fetch = FetchType.LAZY)
    private Collection<AsignacionProfesor> asignacionProfesorCollection;

    public Paralelo() {
    }

    public Paralelo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<AsignacionCursoParalelos> getAsignacionCursoParalelosCollection() {
        return asignacionCursoParalelosCollection;
    }

    public void setAsignacionCursoParalelosCollection(Collection<AsignacionCursoParalelos> asignacionCursoParalelosCollection) {
        this.asignacionCursoParalelosCollection = asignacionCursoParalelosCollection;
    }

    public Collection<AsignacionProfesor> getAsignacionProfesorCollection() {
        return asignacionProfesorCollection;
    }

    public void setAsignacionProfesorCollection(Collection<AsignacionProfesor> asignacionProfesorCollection) {
        this.asignacionProfesorCollection = asignacionProfesorCollection;
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
        if (!(object instanceof Paralelo)) {
            return false;
        }
        Paralelo other = (Paralelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.Paralelo[ id=" + id + " ]";
    }
    
}
