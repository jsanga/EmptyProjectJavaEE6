/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.usuarios;

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
 * @author Joao Sanga
 */
@Entity
@Table(name = "persona_fct", schema = "facturacion_usuarios")
@NamedQueries({
    @NamedQuery(name = "PersonaFct.findAll", query = "SELECT p FROM PersonaFct p"),
    @NamedQuery(name = "PersonaFct.findById", query = "SELECT p FROM PersonaFct p WHERE p.id = :id"),
    @NamedQuery(name = "PersonaFct.findByCedula", query = "SELECT p FROM PersonaFct p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "PersonaFct.findByNombres", query = "SELECT p FROM PersonaFct p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PersonaFct.findByApellidos", query = "SELECT p FROM PersonaFct p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "PersonaFct.findByDireccion", query = "SELECT p FROM PersonaFct p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonaFct.findByFechaEntrada", query = "SELECT p FROM PersonaFct p WHERE p.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "PersonaFct.findByEstado", query = "SELECT p FROM PersonaFct p WHERE p.estado = :estado"),
    @NamedQuery(name = "PersonaFct.findByFechaNacimiento", query = "SELECT p FROM PersonaFct p WHERE p.fechaNacimiento = :fechaNacimiento")})
public class PersonaFct implements Serializable {
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
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private Collection<PersonaEmailFct> personaEmailFctCollection;
    @JoinColumn(name = "sexo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SexoFct sexo;
    @JoinColumn(name = "rol", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RolFct rol;
    @JoinColumn(name = "loguin", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private LoguinFct loguin;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private Collection<PersonaTelefonoFct> personaTelefonoFctCollection;

    public PersonaFct() {
    }

    public PersonaFct(Long id) {
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

    public Collection<PersonaEmailFct> getPersonaEmailFctCollection() {
        return personaEmailFctCollection;
    }

    public void setPersonaEmailFctCollection(Collection<PersonaEmailFct> personaEmailFctCollection) {
        this.personaEmailFctCollection = personaEmailFctCollection;
    }

    public SexoFct getSexo() {
        return sexo;
    }

    public void setSexo(SexoFct sexo) {
        this.sexo = sexo;
    }

    public RolFct getRol() {
        return rol;
    }

    public void setRol(RolFct rol) {
        this.rol = rol;
    }

    public LoguinFct getLoguin() {
        return loguin;
    }

    public void setLoguin(LoguinFct loguin) {
        this.loguin = loguin;
    }

    public Collection<PersonaTelefonoFct> getPersonaTelefonoFctCollection() {
        return personaTelefonoFctCollection;
    }

    public void setPersonaTelefonoFctCollection(Collection<PersonaTelefonoFct> personaTelefonoFctCollection) {
        this.personaTelefonoFctCollection = personaTelefonoFctCollection;
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
        if (!(object instanceof PersonaFct)) {
            return false;
        }
        PersonaFct other = (PersonaFct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.PersonaFct[ id=" + id + " ]";
    }
    
}
