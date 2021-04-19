package com.entities;

import java.io.Serializable;

public class Eleve implements Serializable{
    String nom, prenom, rfid, classe;
    int creditAbsence;

    public Eleve(String nom, String prenom, String rfid, String classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.rfid = rfid;
        this.classe = classe;
        this.creditAbsence = 15;
    }

    /*------------------Getter-Setter------------------- */
    public String getNom() {
        return nom;
    }

    public int getCreditAbsence() {
        return creditAbsence;
    }

    public void setCreditAbsence(int creditAbsence) {
        this.creditAbsence = creditAbsence;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
