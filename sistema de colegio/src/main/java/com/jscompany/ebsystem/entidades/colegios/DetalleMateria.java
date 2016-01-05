/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jscompany.ebsystem.entidades.colegios;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JoaoIsrael
 */
@Entity
@Table(name = "detalle_materia")
@NamedQueries({
    @NamedQuery(name = "DetalleMateria.findAll", query = "SELECT d FROM DetalleMateria d")})
public class DetalleMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota1")
    private Float nota1;
    @Column(name = "nota2")
    private Float nota2;
    @Column(name = "nota3")
    private Float nota3;
    @Column(name = "prom1")
    private Float prom1;
    @Column(name = "nota4")
    private Float nota4;
    @Column(name = "nota5")
    private Float nota5;
    @Column(name = "nota6")
    private Float nota6;
    @Column(name = "prom2")
    private Float prom2;
    @Column(name = "total_prom")
    private Float totalProm;
    @OneToMany(mappedBy = "detalleMateria", fetch = FetchType.LAZY)
    private Collection<DesgloseNota> desgloseNotaCollection;
    @JoinColumn(name = "materia", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Materia materia;

    public DetalleMateria() {
    }

    public DetalleMateria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNota1() {
        return nota1;
    }

    public void setNota1(Float nota1) {
        this.nota1 = nota1;
    }

    public Float getNota2() {
        return nota2;
    }

    public void setNota2(Float nota2) {
        this.nota2 = nota2;
    }

    public Float getNota3() {
        return nota3;
    }

    public void setNota3(Float nota3) {
        this.nota3 = nota3;
    }

    public Float getProm1() {
        return prom1;
    }

    public void setProm1(Float prom1) {
        this.prom1 = prom1;
    }

    public Float getNota4() {
        return nota4;
    }

    public void setNota4(Float nota4) {
        this.nota4 = nota4;
    }

    public Float getNota5() {
        return nota5;
    }

    public void setNota5(Float nota5) {
        this.nota5 = nota5;
    }

    public Float getNota6() {
        return nota6;
    }

    public void setNota6(Float nota6) {
        this.nota6 = nota6;
    }

    public Float getProm2() {
        return prom2;
    }

    public void setProm2(Float prom2) {
        this.prom2 = prom2;
    }

    public Float getTotalProm() {
        return totalProm;
    }

    public void setTotalProm(Float totalProm) {
        this.totalProm = totalProm;
    }

    public Collection<DesgloseNota> getDesgloseNotaCollection() {
        return desgloseNotaCollection;
    }

    public void setDesgloseNotaCollection(Collection<DesgloseNota> desgloseNotaCollection) {
        this.desgloseNotaCollection = desgloseNotaCollection;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        if (!(object instanceof DetalleMateria)) {
            return false;
        }
        DetalleMateria other = (DetalleMateria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jscompany.ebsystem.entidades.entidadesColegios.DetalleMateria[ id=" + id + " ]";
    }
    
}
