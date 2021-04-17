

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class mySocket extends Thread {
	Socket socket;
	BufferedReader entree;
	PrintWriter sortie;

	public mySocket(Socket socket) {
		this.socket = socket;
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
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
            texte = entree.readLine();
            System.out.println(texte);
			sortie.close();
			entree.close();
			socket.close();
            
		}
		catch(IOException e) {}
	}
}
