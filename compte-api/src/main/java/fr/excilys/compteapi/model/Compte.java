package fr.excilys.compteapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Compte {

    private Long id;
    private Long idclient;
    private double solde;

    @Id
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="idclient")
    public Long getIdclient() {
        return idclient;
    }

    public void setIdclient(Long idclient) {
        this.idclient = idclient;
    }

    @Column(name="solde")
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", idclient='" + idclient + '\'' +
                ", solde='" + solde + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return id.equals(compte.id) &&
                idclient.equals(compte.idclient) &&
                solde == compte.solde;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idclient, solde);
    }
}
