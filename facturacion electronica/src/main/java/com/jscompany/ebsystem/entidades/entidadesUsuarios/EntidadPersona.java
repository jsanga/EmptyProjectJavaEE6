/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.entidadesUsuarios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "entidad_persona")
@NamedQueries({
    @NamedQuery(name = "EntidadPersona.findAll", query = "SELECT e FROM EntidadPersona e")})
public class EntidadPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entidad_persona")
    private Long idEntidadPersona;
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 100)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cedula")
    private String cedula;
    @JoinColumn(name = "id_loguin", referencedColumnName = "id_loguin")
    @ManyToOne
    private Loguin idLoguin;

    public EntidadPersona() {
    }

    public EntidadPersona(Long idEntidadPersona) {
        this.idEntidadPersona = idEntidadPersona;
    }

    public EntidadPersona(Long idEntidadPersona, String cedula) {
        this.idEntidadPersona = idEntidadPersona;
        this.cedula = cedula;
    }

    public Long getIdEntidadPersona() {
        return idEntidadPersona;
    }

    public void setIdEntidadPersona(Long idEntidadPersona) {
        this.idEntidadPersona = idEntidadPersona;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Loguin getIdLoguin() {
        return idLoguin;
    }

    public void setIdLoguin(Loguin idLoguin) {
        this.idLoguin = idLoguin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidadPersona != null ? idEntidadPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadPersona)) {
            return false;
        }
        EntidadPersona other = (EntidadPersona) object;
        if ((this.idEntidadPersona == null && other.idEntidadPersona != null) || (this.idEntidadPersona != null && !this.idEntidadPersona.equals(other.idEntidadPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesUsuarios.EntidadPersona[ idEntidadPersona=" + idEntidadPersona + " ]";
    }
    
}
