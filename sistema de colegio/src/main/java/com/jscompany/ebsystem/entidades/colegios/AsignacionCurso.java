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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "asignacion_curso", schema = "colegios")
@NamedQueries({
    @NamedQuery(name = "AsignacionCurso.findAll", query = "SELECT a FROM AsignacionCurso a")})
public class AsignacionCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "asignacion_curso_paralelos", schema = "colegios",
        joinColumns = {@JoinColumn(name = "asignacion_curso", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "paralelo", referencedColumnName = "id")}
    )
    private Collection<Paralelo> paralelosCollection;
    @JoinColumn(name = "periodo_lectivo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeriodoLectivo periodoLectivo;
    @JoinColumn(name = "curso", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;
    @JoinColumn(name = "colegio", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Colegio colegio;
    @OneToMany(mappedBy = "asignacionCurso", fetch = FetchType.LAZY)
    private Collection<Matricula> matriculaCollection;
    @OneToMany(mappedBy = "asignacionCurso", fetch = FetchType.LAZY)
    private Collection<AsignacionProfesor> asignacionProfesorCollection;
    /*
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Materia.class)
    @JoinTable(
        name = "asignacion_curso_materias",
        joinColumns = {@JoinColumn(name = "asignacion_curso", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "materia", referencedColumnName = "id")}
    )*/
    @JoinTable(name = "asignacion_curso_materias", schema = "colegios", joinColumns = {
        @JoinColumn(name = "asignacion_curso", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "materia", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Materia> materiasCollection;

    public AsignacionCurso() {
    }

    public AsignacionCurso(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PeriodoLectivo getPeriodoLectivo() {
        return periodoLectivo;
    }

    public void setPeriodoLectivo(PeriodoLectivo periodoLectivo) {
        this.periodoLectivo = periodoLectivo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
    }

    public Collection<AsignacionProfesor> getAsignacionProfesorCollection() {
        return asignacionProfesorCollection;
    }

    public void setAsignacionProfesorCollection(Collection<AsignacionProfesor> asignacionProfesorCollection) {
        this.asignacionProfesorCollection = asignacionProfesorCollection;
    }

    public Collection<Paralelo> getParalelosCollection() {
        return paralelosCollection;
    }

    public void setParalelosCollection(Collection<Paralelo> paralelosCollection) {
        this.paralelosCollection = paralelosCollection;
    }

    public Collection<Materia> getMateriasCollection() {
        return materiasCollection;
    }

    public void setMateriasCollection(Collection<Materia> materiasCollection) {
        this.materiasCollection = materiasCollection;
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
        if (!(object instanceof AsignacionCurso)) {
            return false;
        }
        AsignacionCurso other = (AsignacionCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.AsignacionCurso[ id=" + id + " ]";
    }
    
}
