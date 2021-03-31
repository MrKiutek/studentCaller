package Java.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {

    public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		String text;
		int portEcouteServeur = 10302;
		BufferedReader lecteurFichier;
		BufferedReader entree;
		PrintStream sortie;
		String ligne;
		Socket socket;
		try {
			socket = new Socket("192.168.3.11", portEcouteServeur);
			lecteurFichier = new BufferedReader(new FileReader("test.txt"));
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintStream(socket.getOutputStream());
			do{
				System.out.println(entree.readLine());
				sortie.println(sc.nextLine());
				text = entree.readLine();


			}while(text.contains("nop"));
			
		}
		catch(FileNotFoundException exc) {
			System.out.println("Fichier introuvable");
		}
		catch(UnknownHostException exc) {
			System.out.println("Destinataire inconnu");
		}
		catch(IOException exc) {
			System.out.println("Probleme d'entree-sortie");
		}
	}
    
}
