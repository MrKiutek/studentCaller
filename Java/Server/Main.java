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
        listEleve.add(new Eleve("GERMAIN", "Adrien", "B663E7F2", maClasse));
        listEleve.add(new Eleve("Blank", "first", "C914D1A3", maClasse));
        listEleve.add(new Eleve("Blank", "second", "2A9B5F80", maClasse));
        
        Classe classe = new Classe(maClasse, listEleve);
        ArrayList<Cours> cours = new ArrayList<Cours>();
        All_Cours all_cours = new All_Cours();

        all_cours.add(new Cours(classe, "P445", "Projet Technologique", new Date(121, 3, 19, 13, 00)));
        System.out.println("all_cours created");
        
        /*
        Json_doer doer = new Json_doer();
        doer.make(all_cours); 

       
        Json_doer doer = new Json_doer();
        All_Cours all_cours = doer.read();
        System.out.println("This exists for debugging 1");*/

        
        new Thread(new Credit_remover(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 2");

        new Thread(new Read_rfid_thread(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 3");

        new Thread(new Send_data_thread(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 4");

    }

}
