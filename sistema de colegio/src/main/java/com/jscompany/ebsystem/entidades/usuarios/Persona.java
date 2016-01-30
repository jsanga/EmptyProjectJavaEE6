/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

import com.jscompany.ebsystem.entidades.colegios.AsignacionProfesor;
import com.jscompany.ebsystem.entidades.colegios.Colegio;
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
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

/**
 *
 * @author Joao Sanga
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
    @Size(max = 100)
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;    
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    @Filter(name="activos")
    private Collection<PersonaEmail> personaEmailCollection;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    @Filter(name="activos")
    private Collection<PersonaTelefono> personaTelefonoCollection;
    @JoinColumn(name = "rol", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;
    @JoinColumn(name = "loguin", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Loguin loguin;
    @JoinColumn(name = "colegio", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Colegio colegio;
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Estudiante estudiante;
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Profesor profesor;
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Personal personal;
    
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public Collection<PersonaEmail> getPersonaEmailCollection() {
        return personaEmailCollection;
    }

    public void setPersonaEmailCollection(Collection<PersonaEmail> personaEmailCollection) {
        this.personaEmailCollection = personaEmailCollection;
    }

    public Collection<PersonaTelefono> getPersonaTelefonoCollection() {
        return personaTelefonoCollection;
    }

    public void setPersonaTelefonoCollection(Collection<PersonaTelefono> personaTelefonoCollection) {
        this.personaTelefonoCollection = personaTelefonoCollection;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Loguin getLoguin() {
        return loguin;
    }

    public void setLoguin(Loguin loguin) {
        this.loguin = loguin;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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
        return "com.jscompany.ebsystem.entidades.usuarios.Persona[ id=" + id + " ]";
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
}
