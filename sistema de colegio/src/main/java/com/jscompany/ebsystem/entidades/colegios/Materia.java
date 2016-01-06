/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "materia", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findById", query = "SELECT m FROM Materia m WHERE m.id = :id"),
    @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Materia.findByEstado", query = "SELECT m FROM Materia m WHERE m.estado = :estado")})
public class Materia implements Serializable {
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
    @JoinColumn(name = "asignacion_curso", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsignacionCurso asignacionCurso;
    @OneToMany(mappedBy = "materia", fetch = FetchType.LAZY)
    private Collection<DetalleMateria> detalleMateriaCollection;
    @OneToMany(mappedBy = "materia", fetch = FetchType.LAZY)
    private Collection<AsignacionProfesorMaterias> asignacionProfesorMateriasCollection;

    public Materia() {
    }

    public Materia(Long id) {
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

    public AsignacionCurso getAsignacionCurso() {
        return asignacionCurso;
    }

    public void setAsignacionCurso(AsignacionCurso asignacionCurso) {
        this.asignacionCurso = asignacionCurso;
    }

    public Collection<DetalleMateria> getDetalleMateriaCollection() {
        return detalleMateriaCollection;
    }

    public void setDetalleMateriaCollection(Collection<DetalleMateria> detalleMateriaCollection) {
        this.detalleMateriaCollection = detalleMateriaCollection;
    }

    public Collection<AsignacionProfesorMaterias> getAsignacionProfesorMateriasCollection() {
        return asignacionProfesorMateriasCollection;
    }

    public void setAsignacionProfesorMateriasCollection(Collection<AsignacionProfesorMaterias> asignacionProfesorMateriasCollection) {
        this.asignacionProfesorMateriasCollection = asignacionProfesorMateriasCollection;
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
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.Materia[ id=" + id + " ]";
    }
    
}
