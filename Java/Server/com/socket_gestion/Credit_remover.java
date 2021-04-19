package com.socket_gestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.entities.All_Cours;
import com.entities.Cours;
import com.entities.Eleve;

import org.apache.commons.lang.time.DateUtils;

public class Credit_remover implements Runnable {

    All_Cours all_cours;

    public Credit_remover(All_Cours d, Class c) {
        this.all_cours = d;
        this.run();
    }

	@Override
    public void run() {
        Date now, cours_almost_end, cours_end;
        ArrayList<Cours> list_cours = this.all_cours.getListCours();
        Cours cours;
        HashMap<String,Eleve> list_eleve;

        while (true) {
            now = new Date(System.currentTimeMillis());

            for (int i = 0; i < list_cours.size(); i++) {
                cours = list_cours.get(i);
                cours_almost_end = DateUtils.addMinutes(cours.getDate_heure(), 85);
                cours_end = DateUtils.addMinutes(cours_almost_end, 5);

                if (now.after(cours_almost_end) && now.before(cours_end) && !cours.isCall_clotured()) {

                    list_eleve = cours.getAbsent();

                    for (String key : list_eleve.keySet()) {
                        if (list_eleve.get(key).getCreditAbsence() == 0) {
                            System.out.println("L'eleve n'a déjà plus de crédit d'absence");
                        } else {
                            list_eleve.get(key).setCreditAbsence(list_eleve.get(key).getCreditAbsence() - 1);
                        }

                    }
                    cours.setCall_clotured(true);
                    
                }
            }

        }

    }
}
