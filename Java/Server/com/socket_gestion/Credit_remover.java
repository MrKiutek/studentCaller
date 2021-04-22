package com.socket_gestion;

import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import java.util.HashMap;
import java.util.Set;

import com.entities.All_Cours;
import com.entities.Cours;
import com.entities.Eleve;
import com.json_computer.Json_doer;

/* ---------------Classe Credit_Remover---------------- */
/* Cette classe compose le thread qui retirera les      */
/* crédits d'absence des élèves.                        */
/* ---------------------------------------------------- */

public class Credit_remover implements Runnable {

    All_Cours all_cours;

    /* ---Constructeur--- */
    public Credit_remover(All_Cours d, Class c) {
        this.all_cours = d;
    }

    /* ---Fonction exécutées par le thread--- */
	@Override
    public void run() {
        Date now, cours_almost_end, cours_end;
        ArrayList<Cours> list_cours = this.all_cours.getListCours(); //Récupération de la liste des cours
        Cours cours;
        HashMap<String,Eleve> list_eleve;

        while (true) {
            //Récupère à chaque tour du while, la date et l'heure actuelle.
            now = new Date(System.currentTimeMillis());

            //Pour chaque cours de la liste, on verifie si la date/heure actuelle est comprise dans le cours
            for (int i = 0; i < list_cours.size(); i++) {
                cours = list_cours.get(i);
                cours_almost_end = DateUtils.addMinutes(cours.getDate_heure(), 85);
                cours_end = DateUtils.addMinutes(cours_almost_end, 5);

                //Si on se situe dans les 5 dernières minutes du cour et que l'appel n'a pas encore été cloturé,  
                //on retire un crédit d'absence à tous les élèves de la liste de absents.
                if (now.after(cours_almost_end) && now.before(cours_end) && !cours.isCall_clotured()) {
                    list_eleve = cours.getAbsent(); //Récupération de la liste des absents

                    for (String key : list_eleve.keySet()) {
                        if (list_eleve.get(key).getCreditAbsence() == 0) {
                            System.out.println("L'eleve n'a déjà plus de crédit d'absence");
                        } else {
                            list_eleve.get(key).setCreditAbsence(list_eleve.get(key).getCreditAbsence() - 1);
                        }

                    }
                    //Quand les crédits ont été retirés, on marque l'appel comme cloturé dans le cours pour éviter 
                    //la redondance de la supression des crédits d'absence
                    cours.setCall_clotured(true);
                    this.checkOtherCours(list_eleve,i);

                    Json_doer doer = new Json_doer();
                    doer.make(this.all_cours); 
                }
            }

        }

    }


    private void checkOtherCours(HashMap<String,Eleve> myList,int numeroCours){

        ArrayList<Cours> list_cours = this.all_cours.getListCours(); //Récupération de la liste des cours
        for(int i =0; i<list_cours.size(); i++){
            if(i != numeroCours){
            for(String key : list_cours.get(i).getAbsent().keySet()){
                if(myList.containsKey(key)){
                    if (list_cours.get(i).getAbsent().get(key).getCreditAbsence() == 0) {
                        System.out.println("L'eleve n'a déjà plus de crédit d'absence");
                    } else {
                    list_cours.get(i).getAbsent().get(key).setCreditAbsence(list_cours.get(i).getAbsent().get(key).getCreditAbsence()-1);

                }

            }

            for(String keyy : list_cours.get(i).getPresent().keySet()){
                if(myList.containsKey(keyy)){
                    if (list_cours.get(i).getPresent().get(keyy).getCreditAbsence() == 0) {
                        System.out.println("L'eleve n'a déjà plus de crédit d'absence");
                    } else {
                    list_cours.get(i).getPresent().get(keyy).setCreditAbsence(list_cours.get(i).getPresent().get(keyy).getCreditAbsence()-1);

                }

            }

        }
        }

    }
}
    }
}
