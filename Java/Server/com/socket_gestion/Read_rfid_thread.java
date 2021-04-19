package com.socket_gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.entities.All_Cours;

public class Read_rfid_thread implements Runnable {
	Socket socket;
	BufferedReader entree;

	All_Cours classeCours;

	public Read_rfid_thread(All_Cours d, Class c) {
		this.classeCours = d;
	}

	@Override
	public void run() {
		int portEcoute = 3333;
		ServerSocket standardiste;
		Socket msocket;
        String texte;
		
		try {
			standardiste = new ServerSocket(portEcoute);
			while(true) {
				msocket = standardiste.accept();
				entree = new BufferedReader(new InputStreamReader(msocket.getInputStream()));
				texte = entree.readLine();

				//CALL FUNCTION WHICH MODIFY PRESENT absent

				classeCours.set_present(texte.substring(0, 7),texte.substring(8));
				entree.close();
				socket.close();
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}

	}
}