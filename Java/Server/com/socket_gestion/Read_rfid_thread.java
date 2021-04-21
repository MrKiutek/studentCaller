package com.socket_gestion;

import com.entities.All_Cours;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/* ---------------Classe Read_rfid_thread--------------- */
/* Cette classe compose le thread qui lira le code rfid  */
/* envoyé par le module lecteur de carte				 */
/* ----------------------------------------------------- */

public class Read_rfid_thread implements Runnable {
	Socket socket;
	BufferedReader entree;
	PrintWriter sortie;

	All_Cours classeCours;

	/* ---Constructeur--- */
	public Read_rfid_thread(All_Cours d, Class c) {
		this.classeCours = d;
	}

	/* ---Fonction exécutées par le thread--- */
	@Override
	public void run() {
		int portEcoute = 3333;     //Port d'écoute du socket
		ServerSocket standardiste; //Déclaration du standardiste
		Socket msocket;            //Déclaration du socket
        String texte;
		
		try {
			standardiste = new ServerSocket(portEcoute);    //Instanciation du standardiste
			while(true) {
				msocket = standardiste.accept();  			//Attente d'un client appelant.
				entree = new BufferedReader(new InputStreamReader(msocket.getInputStream()));
				texte = entree.readLine();					//Lecture de l'entrée du socket

				classeCours.set_present(texte.substring(0, 8),texte.substring(8)); //Marquer un élève présent en utilisant les méthodes adéquat
				entree.close();
				msocket.close();
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}

	}
}
