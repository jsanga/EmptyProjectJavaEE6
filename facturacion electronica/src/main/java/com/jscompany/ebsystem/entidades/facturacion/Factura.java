/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.facturacion;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Sanga
 */
@Entity
@Table(name = "factura", schema = "facturacion")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "cliente")
    private BigInteger cliente;
    @Column(name = "vendedor")
    private BigInteger vendedor;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Size(max = 20)
    @Column(name = "codigo")
    private String codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Float valorTotal;
    @JoinColumn(name = "impuesto", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ImpuestoFactura impuesto;
    @JoinColumn(name = "estado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoFactura estado;
    @JoinColumn(name = "sucursal", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Establecimiento sucursal;
    @JoinColumn(name = "descuento", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DescuentoFactura descuento;
    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY)
    private Collection<DetalleProducto> detalleProductoCollection;

    public Factura() {
    }

    public Factura(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getCliente() {
        return cliente;
    }

    public void setCliente(BigInteger cliente) {
        this.cliente = cliente;
    }

    public BigInteger getVendedor() {
        return vendedor;
    }

    public void setVendedor(BigInteger vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ImpuestoFactura getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(ImpuestoFactura impuesto) {
        this.impuesto = impuesto;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public Establecimiento getSucursal() {
        return sucursal;
    }

    public void setSucursal(Establecimiento sucursal) {
        this.sucursal = sucursal;
    }

    public DescuentoFactura getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoFactura descuento) {
        this.descuento = descuento;
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
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.facturacion.Factura[ id=" + id + " ]";
    }
    
}
