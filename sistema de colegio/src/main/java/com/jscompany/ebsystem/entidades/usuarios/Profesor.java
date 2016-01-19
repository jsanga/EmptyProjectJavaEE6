/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.DetalleMateria;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Filter;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "profesor", schema = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @JoinColumn(name = "tipo_contrato", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoContrato tipoContrato;
    @JoinColumn(name = "persona", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor", fetch = FetchType.LAZY)
    private Collection<DetalleMateria> detalleMateriaCollection;
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    @Filter(name="activos")
    private Collection<AsignacionProfesor> asignacionProfesorCollection;
    
    public Profesor() {
    }

    public Profesor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.Profesor[ id=" + id + " ]";
    }

    public Collection<DetalleMateria> getDetalleMateriaCollection() {
        return detalleMateriaCollection;
    }

    public void setDetalleMateriaCollection(Collection<DetalleMateria> detalleMateriaCollection) {
        this.detalleMateriaCollection = detalleMateriaCollection;
    }
    
}
