/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.facturacion;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private Collection<CaracteristicasProducto> caracteristicasProductoCollection;
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private Collection<DetalleProducto> detalleProductoCollection;

    public Producto() {
    }

    public Producto(Long id) {
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

    public Collection<CaracteristicasProducto> getCaracteristicasProductoCollection() {
        return caracteristicasProductoCollection;
    }

    public void setCaracteristicasProductoCollection(Collection<CaracteristicasProducto> caracteristicasProductoCollection) {
        this.caracteristicasProductoCollection = caracteristicasProductoCollection;
    }

    public Collection<DetalleProducto> getDetalleProductoCollection() {
        return detalleProductoCollection;
    }

    public void setDetalleProductoCollection(Collection<DetalleProducto> detalleProductoCollection) {
        this.detalleProductoCollection = detalleProductoCollection;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.facturacion.Producto[ id=" + id + " ]";
    }
    
}