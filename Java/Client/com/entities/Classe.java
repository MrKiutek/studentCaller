package com.entities;


import java.io.Serializable;
import java.util.ArrayList;


public class Classe implements Serializable{
    private String nom;
    private ArrayList<Eleve> listEleve;

    public Classe(String nom, ArrayList<Eleve> listEleve) {
        this.nom = nom;
        this.listEleve = listEleve;
    }

    /*------------------Getter-Setter------------------- */
    public ArrayList<Eleve> getListEleve() {
        return listEleve;
    }

    public void setListEleve(ArrayList<Eleve> listEleve) {
        this.listEleve = listEleve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}