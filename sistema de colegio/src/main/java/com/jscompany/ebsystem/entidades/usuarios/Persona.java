/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Matricula;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "persona", schema = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 100)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @JoinColumn(name = "loguin", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Loguin loguin;
    @JoinColumn(name = "rol", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private Collection<AsignacionProfesor> asignacionProfesorCollection;
    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private Collection<Matricula> matriculaCollection;
    
    public Persona() {
    }

    public Persona(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Loguin getLoguin() {
        return loguin;
    }

    public void setLoguin(Loguin loguin) {
        this.loguin = loguin;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Collection<AsignacionProfesor> getAsignacionProfesorCollection() {
        return asignacionProfesorCollection;
    }

    public void setAsignacionProfesorCollection(Collection<AsignacionProfesor> asignacionProfesorCollection) {
        this.asignacionProfesorCollection = asignacionProfesorCollection;
    }

    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesUsuarios.Persona[ id=" + id + " ]";
    }
    
}
