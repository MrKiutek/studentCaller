package com.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.json_computer.Json_doer;

import org.apache.commons.lang.time.DateUtils;

/* ------------------Classe All_Cours----------------- */
/* Cette classe compose la liste des cours.            */
/* Cette classe servira à faire des traitements sur la */
/* liste des cours comme trouver un cours pour pouvoir */
/* marquer un élève comme présent pour un cours.       */
/* On pourra aussi ajouter un cours à la liste.        */
/* Implémente serializable pour pouvoir être envoyé    */
/* par socket                                          */
/* --------------------------------------------------- */

public class All_Cours implements Serializable{
    private ArrayList<Cours> list_cours;
    private transient DateFormat format;

    /* ---Constructeur--- */
    public All_Cours(){
        this.list_cours = new ArrayList<Cours>();
        this.format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    /* ---Ajoute un cours à la liste--- */
    public void add(Cours cours){
        this.list_cours.add(cours);
    }

    /* ---Trouve un cours grave à une date/heure et à une salle--- */
    public Cours find(Date date, String salle){

        //Ces variables définissent les moments de début et de fin d'un cours
        Date cours_begin, cours_end;
        
        /* On parcours la liste des cours, en récupérant l'heure  */
        /* de début du cours et en définissant l'heure de fin du  */
        /* cours. En faisant cela, on pourra tester et déterminer */
        /* si la date/heure actuelle est comprise entre les       */
        /* moments de début et de fin d'un cours. Si c'est le cas,*/
        /* on a trouvé notre cours, on le retourne à l'appelant   */
        for(int i=0; i<this.list_cours.size(); i++){

            cours_begin = this.list_cours.get(i).getDate_heure();
            cours_end = DateUtils.addMinutes(cours_begin, 90);

            if(date.after(cours_begin) && date.before(cours_end) && salle.equals(list_cours.get(i).getSalle())){
                return this.list_cours.get(i);
            }
        }

        return null;
    }

    /* ---Marque un élève comme présent à un cours--- */
    public void set_present(String rfid, String salle){

        /* En sachant que le module lecteur de carte renvoie un    */
        /* uid (celui de la carte rfid) et un nom de salle de      */
        /* cours, on utilise le nom de salle et le moment auquel   */
        /* cette fonction est appelée pour trouver un cours.       */
        /*                                                         */
        /* Une fois le cours trouvé, on utilise la méthode de la   */
        /* classe cours pour marquer un élèvre comme présent grâce */
        /* au code rfid de sa carte étudiant                       */
        Date now = new Date(System.currentTimeMillis());
        Cours actual = this.find(now, salle);
        if (actual != null && !actual.getPresent().containsKey(rfid)){
            actual.setPresent(rfid);
            Json_doer doer = new Json_doer();
            doer.make(this); 
        } else {
            System.out.println("Cours not found or already present");
        }
    }

    /*------------------Getter-------------------- */
    public ArrayList<Cours> getListCours(){
        return this.list_cours;
    }
}
