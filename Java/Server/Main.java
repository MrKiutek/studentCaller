import com.entities.*;
import com.json_computer.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;





public class Main {
 	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {

		String maClasse = "ING 4 APP SE";
        ArrayList<Eleve> listEleve = new ArrayList<Eleve>();
        listEleve.add(new Eleve("KALIFA", "Ethane", "86BFD1F4", maClasse));
        listEleve.add(new Eleve("COUSCOUS", "Saucisse", "CD77475B", maClasse));
        Classe classe = new Classe(maClasse, listEleve);
        ArrayList<Cours> cours = new ArrayList<Cours>();
        cours.add(new Cours(classe, "P445", "Projet Technologique", new Date(121,3,19,13,0)));
        
        Json_doer faiseur = new Json_doer();
        faiseur.make(cours);
        System.out.println("Json Written");

        cours.get(0).setPresent("86BFD1F4");
        faiseur.make(cours);
        System.out.println("Ethane set present");

        cours = faiseur.read();
        System.out.println("Json Read");


		int portEcoute = 3333;
		ServerSocket standardiste;
		Socket socket;

		try {
			standardiste = new ServerSocket(portEcoute);
			while(true) {
				socket = standardiste.accept();
	 			new mySocket(socket);
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}

	}	
