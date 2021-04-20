package com.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/* ------------------Classe Cours--------------------- */
/* Cette classe compose un cours. Il contient une      */
/* classe, une salle, une matière, une date/horaire,   */
/* une liste d'élèves présents et une autre d'absents. */
/* Implémente serializable pour pouvoir être envoyé    */
/* par socket                                          */
/* --------------------------------------------------- */

public class Cours implements Serializable {
    private String salle, matiere;
    private transient DateFormat format; //Format d'affichage de la date et de l'heure
    private Date date_heure;
    private transient Classe classe;
    private HashMap<String,Eleve> present;
    private HashMap<String,Eleve> absent;

    //Cette variable sert à savoir si les crédits d'absences ont déjà été retirés aux élèves absents :
    private boolean call_clotured;  
    private static final long serialVersionUID = 1;

    /* ---Constructeur--- */
    public Cours(Classe classe, String salle, String matiere, Date date_heure) {
        this.setClasse(classe);
        this.setSalle(salle);
        this.setMatiere(matiere);
        this.setFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
        this.setDate_heure(date_heure);

        //Par défaut, l'appel n'est pas cloturé. Il sera cloturé par un autre thread
        this.setCall_clotured(false); 
        this.absent = new HashMap<String,Eleve>();
        this.present = new HashMap<String,Eleve>();
        //Par défaut, tous les élèves de la classe sont absents et ils seront notés présents au fur et à mesure
        for (int i=0; i < classe.getListEleve().size(); i++){
            this.absent.put(classe.getListEleve().get(i).getRfid(),classe.getListEleve().get(i));
        }
    }

    
    public void setPresent(String rfid){
        /* Prend en paramètre le rfid d'un élève    */
        /* Le rfid d'une carte étudiant est utilisé */
        /* comme clé des tables de hashage. On      */
        /* l'élève dans la liste des présents et on */
        /* le retire des absents                    */
        this.present.put(rfid, this.absent.get(rfid));
        this.absent.remove(rfid);
    }


    /*------------------Getter-Setter------------------- */
    public HashMap<String,Eleve> getAbsent(){
        return this.absent;
    }

    public HashMap<String,Eleve> getPresent(){
        return this.present;
    }

    public boolean isCall_clotured() {
        return call_clotured;
    }

    public void setCall_clotured(boolean call_clotured) {
        this.call_clotured = call_clotured;
    }

    public DateFormat getFormat() {
        return format;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
