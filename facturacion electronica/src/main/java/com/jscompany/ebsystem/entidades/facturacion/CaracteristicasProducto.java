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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "caracteristicas_producto")
@NamedQueries({
    @NamedQuery(name = "CaracteristicasProducto.findAll", query = "SELECT c FROM CaracteristicasProducto c"),
    @NamedQuery(name = "CaracteristicasProducto.findById", query = "SELECT c FROM CaracteristicasProducto c WHERE c.id = :id"),
    @NamedQuery(name = "CaracteristicasProducto.findByNombre", query = "SELECT c FROM CaracteristicasProducto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CaracteristicasProducto.findByDetalle", query = "SELECT c FROM CaracteristicasProducto c WHERE c.detalle = :detalle")})
public class CaracteristicasProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 300)
    @Column(name = "detalle")
    private String detalle;
    @JoinTable(name = "producto_has_caracteristicas", joinColumns = {
        @JoinColumn(name = "caracteristica", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "producto", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Producto> productoCollection;

    public CaracteristicasProducto() {
    }

    public CaracteristicasProducto(Long id) {
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
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
        if (!(object instanceof CaracteristicasProducto)) {
            return false;
        }
        CaracteristicasProducto other = (CaracteristicasProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.facturacion.CaracteristicasProducto[ id=" + id + " ]";
    }
    
}
