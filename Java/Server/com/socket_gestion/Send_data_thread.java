package com.socket_gestion;

import com.entities.All_Cours;
import com.entities.Cours;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ObjectOutputStream;

/* ---------------Classe Send_data_thread---------------- */
/* Cette classe compose le thread qui enverra les données */
/* à afficher aux clients appelants.					  */
/* ------------------------------------------------------ */

public class Send_data_thread implements Runnable {
	ObjectOutputStream  sortie;

	All_Cours classeCours;

	/* ---Constructeur--- */
	public Send_data_thread(All_Cours d, Class c) {
		this.classeCours = d;
	}

	/* ---Fonction exécutées par le thread--- */
	@Override
	public void run() {
		int portEcoute = 3334;      //Port d'écoute du socket
		ServerSocket standardiste;  //Déclaration du standardiste
		Socket msocket;				//Déclaration du socket
		
		try {
			standardiste = new ServerSocket(portEcoute); //Instanciation du standardiste
			while(true) {
				msocket = standardiste.accept();         //Attente d'un client appelant.
				sortie = new ObjectOutputStream(msocket.getOutputStream());
				
				ArrayList<Cours> myList = classeCours.getListCours();

				sortie.writeObject(myList);				 //Envoi de la liste des cours sur la sortie du socket
				sortie.close();
				msocket.close();
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}
}
