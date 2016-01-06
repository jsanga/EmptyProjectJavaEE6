/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "periodo_lectivo", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "PeriodoLectivo.findAll", query = "SELECT p FROM PeriodoLectivo p"),
    @NamedQuery(name = "PeriodoLectivo.findById", query = "SELECT p FROM PeriodoLectivo p WHERE p.id = :id"),
    @NamedQuery(name = "PeriodoLectivo.findByFechaInicio", query = "SELECT p FROM PeriodoLectivo p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PeriodoLectivo.findByFechaFin", query = "SELECT p FROM PeriodoLectivo p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PeriodoLectivo.findByEstado", query = "SELECT p FROM PeriodoLectivo p WHERE p.estado = :estado")})
public class PeriodoLectivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "periodoLectivo", fetch = FetchType.LAZY)
    private Collection<AsignacionCurso> asignacionCursoCollection;

    public PeriodoLectivo() {
    }

    public PeriodoLectivo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<AsignacionCurso> getAsignacionCursoCollection() {
        return asignacionCursoCollection;
    }

    public void setAsignacionCursoCollection(Collection<AsignacionCurso> asignacionCursoCollection) {
        this.asignacionCursoCollection = asignacionCursoCollection;
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
        if (!(object instanceof PeriodoLectivo)) {
            return false;
        }
        PeriodoLectivo other = (PeriodoLectivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.PeriodoLectivo[ id=" + id + " ]";
    }
    
}
