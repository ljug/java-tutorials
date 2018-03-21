/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "payementlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payementlog.findAll", query = "SELECT p FROM Payementlog p")
    , @NamedQuery(name = "Payementlog.findByPayId", query = "SELECT p FROM Payementlog p WHERE p.payId = :payId")
    , @NamedQuery(name = "Payementlog.findByToken", query = "SELECT p FROM Payementlog p WHERE p.token = :token")
    , @NamedQuery(name = "Payementlog.findByNoEtud", query = "SELECT p FROM Payementlog p WHERE p.noEtud = :noEtud")
    , @NamedQuery(name = "Payementlog.findByAuditeursId", query = "SELECT p FROM Payementlog p WHERE p.auditeursId = :auditeursId")
    , @NamedQuery(name = "Payementlog.findByNomComplet", query = "SELECT p FROM Payementlog p WHERE p.nomComplet = :nomComplet")
    , @NamedQuery(name = "Payementlog.findByMontant", query = "SELECT p FROM Payementlog p WHERE p.montant = :montant")
    , @NamedQuery(name = "Payementlog.findByDateLimitePayement", query = "SELECT p FROM Payementlog p WHERE p.dateLimitePayement = :dateLimitePayement")
    , @NamedQuery(name = "Payementlog.findByEtat", query = "SELECT p FROM Payementlog p WHERE p.etat = :etat")
    , @NamedQuery(name = "Payementlog.findByIdCentre", query = "SELECT p FROM Payementlog p WHERE p.idCentre = :idCentre")
    , @NamedQuery(name = "Payementlog.findByDebutTraitement", query = "SELECT p FROM Payementlog p WHERE p.debutTraitement = :debutTraitement")
    , @NamedQuery(name = "Payementlog.findByFindTraitement", query = "SELECT p FROM Payementlog p WHERE p.findTraitement = :findTraitement")
    , @NamedQuery(name = "Payementlog.findByPrisEnCompte", query = "SELECT p FROM Payementlog p WHERE p.prisEnCompte = :prisEnCompte")
    , @NamedQuery(name = "Payementlog.findByOmtPayementId", query = "SELECT p FROM Payementlog p WHERE p.omtPayementId = :omtPayementId")
    , @NamedQuery(name = "Payementlog.findByGenerePar", query = "SELECT p FROM Payementlog p WHERE p.generePar = :generePar")
    , @NamedQuery(name = "Payementlog.findByDateCreationModification", query = "SELECT p FROM Payementlog p WHERE p.dateCreationModification = :dateCreationModification")
    , @NamedQuery(name = "Payementlog.findByIdEmploye", query = "SELECT p FROM Payementlog p WHERE p.idEmploye = :idEmploye")
    , @NamedQuery(name = "Payementlog.findByPrecedent", query = "SELECT p FROM Payementlog p WHERE p.precedent = :precedent")})
public class Payementlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "payId")
    private String payId;
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @Column(name = "NoEtud")
    private int noEtud;
    @Basic(optional = false)
    @Column(name = "auditeursId")
    private String auditeursId;
    @Basic(optional = false)
    @Column(name = "NomComplet")
    private String nomComplet;
    @Basic(optional = false)
    @Column(name = "montant")
    private int montant;
    @Basic(optional = false)
    @Column(name = "dateLimitePayement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLimitePayement;
    @Basic(optional = false)
    @Column(name = "etat")
    private Character etat;
    @Column(name = "idCentre")
    private Short idCentre;
    @Column(name = "debutTraitement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date debutTraitement;
    @Column(name = "findTraitement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date findTraitement;
    @Column(name = "prisEnCompte")
    private Short prisEnCompte;
    @Column(name = "omtPayementId")
    private String omtPayementId;
    @Basic(optional = false)
    @Column(name = "generePar")
    private String generePar;
    @Basic(optional = false)
    @Column(name = "dateCreationModification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationModification;
    @Column(name = "idEmploye")
    private Short idEmploye;
    @Column(name = "precedent")
    private Integer precedent;

    public Payementlog() {
    }

    public Payementlog(String payId) {
        this.payId = payId;
    }

    public Payementlog(String payId, int noEtud, String auditeursId, String nomComplet, int montant, Date dateLimitePayement, Character etat, String generePar, Date dateCreationModification) {
        this.payId = payId;
        this.noEtud = noEtud;
        this.auditeursId = auditeursId;
        this.nomComplet = nomComplet;
        this.montant = montant;
        this.dateLimitePayement = dateLimitePayement;
        this.etat = etat;
        this.generePar = generePar;
        this.dateCreationModification = dateCreationModification;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNoEtud() {
        return noEtud;
    }

    public void setNoEtud(int noEtud) {
        this.noEtud = noEtud;
    }

    public String getAuditeursId() {
        return auditeursId;
    }

    public void setAuditeursId(String auditeursId) {
        this.auditeursId = auditeursId;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDateLimitePayement() {
        return dateLimitePayement;
    }

    public void setDateLimitePayement(Date dateLimitePayement) {
        this.dateLimitePayement = dateLimitePayement;
    }

    public Character getEtat() {
        return etat;
    }

    public void setEtat(Character etat) {
        this.etat = etat;
    }

    public Short getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(Short idCentre) {
        this.idCentre = idCentre;
    }

    public Date getDebutTraitement() {
        return debutTraitement;
    }

    public void setDebutTraitement(Date debutTraitement) {
        this.debutTraitement = debutTraitement;
    }

    public Date getFindTraitement() {
        return findTraitement;
    }

    public void setFindTraitement(Date findTraitement) {
        this.findTraitement = findTraitement;
    }

    public Short getPrisEnCompte() {
        return prisEnCompte;
    }

    public void setPrisEnCompte(Short prisEnCompte) {
        this.prisEnCompte = prisEnCompte;
    }

    public String getOmtPayementId() {
        return omtPayementId;
    }

    public void setOmtPayementId(String omtPayementId) {
        this.omtPayementId = omtPayementId;
    }

    public String getGenerePar() {
        return generePar;
    }

    public void setGenerePar(String generePar) {
        this.generePar = generePar;
    }

    public Date getDateCreationModification() {
        return dateCreationModification;
    }

    public void setDateCreationModification(Date dateCreationModification) {
        this.dateCreationModification = dateCreationModification;
    }

    public Short getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Short idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Integer getPrecedent() {
        return precedent;
    }

    public void setPrecedent(Integer precedent) {
        this.precedent = precedent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payementlog)) {
            return false;
        }
        Payementlog other = (Payementlog) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Payementlog[ payId=" + payId + " ]";
    }
    
}
