/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "asignacion_curso_paralelos", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "AsignacionCursoParalelos.findAll", query = "SELECT a FROM AsignacionCursoParalelos a")})
public class AsignacionCursoParalelos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "estado")
    private Boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_cupo")
    private int maxCupo;
    @Column(name = "cupo_disponible")
    private Integer cupoDisponible;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "paralelo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paralelo paralelo;
    @JoinColumn(name = "asignacion_curso", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsignacionCurso asignacionCurso;

    public AsignacionCursoParalelos() {
    }

    public AsignacionCursoParalelos(Long id) {
        this.id = id;
    }

    public AsignacionCursoParalelos(Long id, int maxCupo) {
        this.id = id;
        this.maxCupo = maxCupo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getMaxCupo() {
        return maxCupo;
    }

    public void setMaxCupo(int maxCupo) {
        this.maxCupo = maxCupo;
    }

    public Integer getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(Integer cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof AsignacionCursoParalelos)) {
            return false;
        }
        AsignacionCursoParalelos other = (AsignacionCursoParalelos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.AsignacionCursoParalelos[ id=" + id + " ]";
    }
    
}
