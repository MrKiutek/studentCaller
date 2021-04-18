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

        all_cours.add(new Cours(classe, "P445", "Projet Technologique", new Date(121, 3, 18, 23, 0)));
        System.out.println("all_cours created");


        all_cours.set_present("86BFD1F4", "P445");
        System.out.println("set present");




        Json_doer faiseur = new Json_doer();
        faiseur.make(cours);
        System.out.println("Json Written");

        cours.get(0).setPresent("86BFD1F4");
        faiseur.make(cours);
        System.out.println("Ethane set present");

        cours = faiseur.read();
        System.out.println("Json Read");

        //Read_rfid_thread thread_read = new Read_rfid_thread();
        //Send_data_thread thread_send = new Send_data_thread();

    }

}
