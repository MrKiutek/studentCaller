import com.entities.*;
import com.json_computer.*;
import com.socket_gestion.*;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args){
      
        Json_doer doer = new Json_doer();
        All_Cours all_cours = doer.read();
        System.out.println("This exists for debugging 1");

        
        new Thread(new Credit_remover(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 2");

        new Thread(new Read_rfid_thread(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 3");

        new Thread(new Send_data_thread(all_cours, all_cours.getClass())).start();
        System.out.println("This exists for debugging 4");

    }

}
