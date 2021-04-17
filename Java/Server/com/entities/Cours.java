package com.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Cours {
    private String salle, matiere;
    private transient DateFormat format;
    private Date date_heure;
    private transient Classe classe;
    private HashMap<String,Eleve> present;
    private HashMap<String,Eleve> absent;

    public Cours(Classe classe, String salle, String matiere, Date date_heure) {
        this.setClasse(classe);
        this.setSalle(salle);
        this.setMatiere(matiere);
        this.setFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
        this.setDate_heure(date_heure);

        this.absent = new HashMap<String,Eleve>();
        this.present = new HashMap<String,Eleve>();
        for (int i=0; i < classe.getListEleve().size(); i++){
            this.absent.put(classe.getListEleve().get(i).getRfid(),classe.getListEleve().get(i));
        }
    }

    public void setPresent(String rfid){
        this.present.put(rfid, this.absent.get(rfid));
        this.absent.remove(rfid);
    }

    /*------------------Getter-Setter------------------- */
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
