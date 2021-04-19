package com.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;




public class All_Cours implements Serializable{
    private ArrayList<Cours> list_cours;
    private transient DateFormat format;

    public All_Cours(){
        this.list_cours = new ArrayList<Cours>();
        this.format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public void add(Cours cours){
        this.list_cours.add(cours);
    }

    public Cours find(Date date, String salle){
        Date cours_begin, cours_end;
        
        for(int i=0; i<this.list_cours.size(); i++){

            cours_begin = this.list_cours.get(i).getDate_heure();
            cours_end = DateUtils.addMinutes(cours_begin, 90);

            if(date.after(cours_begin) && date.before(cours_end) && salle==this.list_cours.get(i).getSalle()){
                return this.list_cours.get(i);
            }
        }

        return null;
    }

    public void set_present(String rfid, String salle){

        Date now = new Date(System.currentTimeMillis());
        Cours actual = this.find(now, salle);
        if (actual != null){
            actual.setPresent(rfid);
        } else {
            System.out.println("Cours not found");
        }
    }

    public ArrayList<Cours> getListCours(){
        return this.list_cours;
    }
}
