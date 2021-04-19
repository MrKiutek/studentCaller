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
import com.entities.Cours;

import java.util.ArrayList;

import java.io.ObjectOutputStream;

public class Send_data_thread implements Runnable {
	ObjectOutputStream  sortie;

	All_Cours classeCours;

	public Send_data_thread(All_Cours d, Class c) {

		this.classeCours = d;
	}

	public void run() {
		int portEcoute = 3334;
		ServerSocket standardiste;
		Socket msocket;
		
		try {
			standardiste = new ServerSocket(portEcoute);
			while(true) {
				msocket = standardiste.accept();
				sortie = new ObjectOutputStream(msocket.getOutputStream());
				
				ArrayList<Cours> myList = classeCours.getListCours();

				sortie.writeObject(myList);
				sortie.close();
				msocket.close();
			}
		}
		catch(IOException exc) {
	 		System.out.println("probleme de connexion");
		}
	}
}
