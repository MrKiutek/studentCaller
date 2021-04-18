import com.entities.*;
import com.json_computer.*;
import com.socket_gestion.*;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args){

        String maClasse = "ING 4 APP SE";
        ArrayList<Eleve> listEleve = new ArrayList<Eleve>();
        listEleve.add(new Eleve("KALIFA", "Ethane", "86BFD1F4", maClasse));
        listEleve.add(new Eleve("COUSCOUS", "Saucisse", "CD77475B", maClasse));
        Classe classe = new Classe(maClasse, listEleve);
        ArrayList<Cours> cours = new ArrayList<Cours>();
        All_Cours all_cours = new All_Cours();

        all_cours.add(new Cours(classe, "P445", "Projet Technologique", new Date(121, 3, 18, 23, 20)));
        System.out.println("all_cours created");


        Runnable myNewThread1 = new Read_rfid_thread(all_cours, all_cours.getClass());
        new Thread(myNewThread1).start();
        System.out.println("set present");

        Runnable myNewThread2 = new Credit_remover(all_cours, all_cours.getClass());
        new Thread(myNewThread2).start();
        System.out.println("credit remover");

        


        Json_doer faiseur = new Json_doer();
        faiseur.make(all_cours);        
        // cours = faiseur.read();
        // System.out.println("Json Read");

        while(true){
            System.out.println(listEleve.get(0).getPrenom() + listEleve.get(0).getCreditAbsence() );
            System.out.println(listEleve.get(1).getPrenom() + listEleve.get(1).getCreditAbsence() );
        }

    }

}
