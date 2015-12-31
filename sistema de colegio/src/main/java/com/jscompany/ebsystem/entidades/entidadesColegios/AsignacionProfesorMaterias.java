/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.entidadesColegios;

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

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "asignacion_profesor_materias")
@NamedQueries({
    @NamedQuery(name = "AsignacionProfesorMaterias.findAll", query = "SELECT a FROM AsignacionProfesorMaterias a"),
    @NamedQuery(name = "AsignacionProfesorMaterias.findById", query = "SELECT a FROM AsignacionProfesorMaterias a WHERE a.id = :id")})
public class AsignacionProfesorMaterias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "asignacion_profesor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsignacionProfesor asignacionProfesor;
    @JoinColumn(name = "materia", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Materia materia;

    public AsignacionProfesorMaterias() {
    }

    public AsignacionProfesorMaterias(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AsignacionProfesor getAsignacionProfesor() {
        return asignacionProfesor;
    }

    public void setAsignacionProfesor(AsignacionProfesor asignacionProfesor) {
        this.asignacionProfesor = asignacionProfesor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        if (!(object instanceof AsignacionProfesorMaterias)) {
            return false;
        }
        AsignacionProfesorMaterias other = (AsignacionProfesorMaterias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesColegios.AsignacionProfesorMaterias[ id=" + id + " ]";
    }
    
}
