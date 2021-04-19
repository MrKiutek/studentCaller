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
        all_cours.add(new Cours(classe, "P333", "Math", new Date(121, 3, 19, 23, 20)));
        all_cours.add(new Cours(classe, "P222", "Physique", new Date(121, 3, 20, 23, 20)));
        System.out.println("all_cours created");

        /*
        Runnable myNewThread1 = new Send_data_thread(all_cours, all_cours.getClass());
        new Thread(myNewThread1).start();*/

        new Thread(new Read_rfid_thread(all_cours, all_cours.getClass())).start();
        System.out.println("bite");

        new Thread(new Send_data_thread(all_cours, all_cours.getClass())).start();
        System.out.println("data");


        while(true){
        }

    }

}
