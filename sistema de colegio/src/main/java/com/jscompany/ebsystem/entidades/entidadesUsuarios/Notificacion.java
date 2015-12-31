/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.entidadesUsuarios;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "notificacion")
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findById", query = "SELECT n FROM Notificacion n WHERE n.id = :id"),
    @NamedQuery(name = "Notificacion.findByNotificacion", query = "SELECT n FROM Notificacion n WHERE n.notificacion = :notificacion"),
    @NamedQuery(name = "Notificacion.findByRemitente", query = "SELECT n FROM Notificacion n WHERE n.remitente = :remitente"),
    @NamedQuery(name = "Notificacion.findByDestinatario", query = "SELECT n FROM Notificacion n WHERE n.destinatario = :destinatario")})
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 1000)
    @Column(name = "notificacion")
    private String notificacion;
    @Column(name = "remitente")
    private BigInteger remitente;
    @Column(name = "destinatario")
    private BigInteger destinatario;

    public Notificacion() {
    }

    public Notificacion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public BigInteger getRemitente() {
        return remitente;
    }

    public void setRemitente(BigInteger remitente) {
        this.remitente = remitente;
    }

    public BigInteger getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(BigInteger destinatario) {
        this.destinatario = destinatario;
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
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesUsuarios.Notificacion[ id=" + id + " ]";
    }
    
}
