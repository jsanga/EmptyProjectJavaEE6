/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.certificados;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "certificado")
@NamedQueries({
    @NamedQuery(name = "Certificado.findAll", query = "SELECT c FROM Certificado c")})
public class Certificado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creador")
    private BigInteger usuarioCreador;
    @Column(name = "tipo_certificado")
    private BigInteger tipoCertificado;
    @Size(max = 1000)
    @Column(name = "url")
    private String url;

    public Certificado() {
    }

    public Certificado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(BigInteger usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public BigInteger getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(BigInteger tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.usuarios.Certificado[ id=" + id + " ]";
    }
    
}
