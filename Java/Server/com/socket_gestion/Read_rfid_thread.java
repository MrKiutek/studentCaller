package com.socket_gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Read_rfid_thread extends Thread {
	Socket socket;
	BufferedReader entree;
	PrintWriter sortie;

	public Read_rfid_thread() {
		this.start();
	}

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
				sortie = new PrintWriter(new BufferedWriter(new OutputStreamWriter(msocket.getOutputStream())), true);				
				texte = entree.readLine();

				//CALL FUNCTION WHICH MODIFY PRESENT absent

				System.out.println(texte);
				sortie.close();
				entree.close();
				socket.close();
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}
}
