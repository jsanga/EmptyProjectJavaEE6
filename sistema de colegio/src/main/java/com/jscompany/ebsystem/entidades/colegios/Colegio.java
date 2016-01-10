/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

import com.jscompany.ebsystem.entidades.usuarios.Persona;
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
import javax.validation.constraints.Size;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "colegio", schema = "colegios")
@FilterDef(name = "activos", 
    defaultCondition = "estado = 'TRUE'")
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c")})
public class Colegio implements Serializable {
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
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
    @Filter(name="activos")
    private Collection<AsignacionCurso> asignacionCursoCollection;
    @OneToMany(mappedBy = "colegio", fetch = FetchType.LAZY)
    @Filter(name="activos")
    private Collection<Persona> personasCollection;
    
    public Colegio() {
    }

    public Colegio(Long id) {
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Collection<AsignacionCurso> getAsignacionCursoCollection() {
        return asignacionCursoCollection;
    }

    public void setAsignacionCursoCollection(Collection<AsignacionCurso> asignacionCursoCollection) {
        this.asignacionCursoCollection = asignacionCursoCollection;
    }

    public Collection<Persona> getPersonasCollection() {
        return personasCollection;
    }

    public void setPersonasCollection(Collection<Persona> personasCollection) {
        this.personasCollection = personasCollection;
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
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.colegios.Colegio[ id=" + id + " ]";
    }
    
}
