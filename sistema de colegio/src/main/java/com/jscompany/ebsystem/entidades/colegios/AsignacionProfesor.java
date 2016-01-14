/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

import com.jscompany.ebsystem.entidades.usuarios.Persona;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "asignacion_profesor", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "AsignacionProfesor.findAll", query = "SELECT a FROM AsignacionProfesor a")})
public class AsignacionProfesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "profesor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona profesor;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionProfesor", fetch = FetchType.LAZY)
    private Collection<AsignacionProfesorMaterias> asignacionProfesorMateriasCollection;
    @JoinColumn(name = "paralelo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paralelo paralelo;
    @JoinColumn(name = "asignacion_curso", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsignacionCurso asignacionCurso;

    public AsignacionProfesor() {
    }

    public AsignacionProfesor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getProfesor() {
        return profesor;
    }

    public void setProfesor(Persona profesor) {
        this.profesor = profesor;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Collection<AsignacionProfesorMaterias> getAsignacionProfesorMateriasCollection() {
        return asignacionProfesorMateriasCollection;
    }

    public void setAsignacionProfesorMateriasCollection(Collection<AsignacionProfesorMaterias> asignacionProfesorMateriasCollection) {
        this.asignacionProfesorMateriasCollection = asignacionProfesorMateriasCollection;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    public AsignacionCurso getAsignacionCurso() {
        return asignacionCurso;
    }

    public void setAsignacionCurso(AsignacionCurso asignacionCurso) {
        this.asignacionCurso = asignacionCurso;
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
        if (!(object instanceof AsignacionProfesor)) {
            return false;
        }
        AsignacionProfesor other = (AsignacionProfesor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor[ id=" + id + " ]";
    }
    
}
