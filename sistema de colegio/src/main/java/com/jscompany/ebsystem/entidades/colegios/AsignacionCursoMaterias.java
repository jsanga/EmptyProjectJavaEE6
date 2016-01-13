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

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "asignacion_curso_materias", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "AsignacionCursoMaterias.findAll", query = "SELECT a FROM AsignacionCursoMaterias a")})
public class AsignacionCursoMaterias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fue_tomada")
    private Boolean fueTomada;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "materia", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materia materia;
    @JoinColumn(name = "asignacion_curso", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsignacionCurso asignacionCurso;

    public AsignacionCursoMaterias() {
    }

    public AsignacionCursoMaterias(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getFueTomada() {
        return fueTomada;
    }

    public void setFueTomada(Boolean fueTomada) {
        this.fueTomada = fueTomada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        if (!(object instanceof AsignacionCursoMaterias)) {
            return false;
        }
        AsignacionCursoMaterias other = (AsignacionCursoMaterias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.AsignacionCursoMaterias[ id=" + id + " ]";
    }
    
}
