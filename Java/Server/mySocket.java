package Java.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class mySocket extends Thread {
	Socket socket;
	BufferedReader entree;
	PrintStream sortie;

	public mySocket(Socket socket) {
		this.socket = socket;
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintStream(socket.getOutputStream());
			this.start();
		}
		catch(IOException exc) {
			try {
				socket.close();
			}
			catch(IOException e){}
		}
	}

	public void run() {
        
		String texte;
		int compteur = 0;

		try {
            while(true){
			sortie.println("que veux tu faire ?             1: Entrer une commande SQL            2:Arreter la connexion");
            texte = entree.readLine();
            if(texte.contains("1")){
                Bdd bdd = new Bdd();
                sortie.println("nop");

            }else{
            sortie.println("yes");
			sortie.close();
			entree.close();
			socket.close();

            }
        }
            
            
		}
		catch(IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {}
	}
}
