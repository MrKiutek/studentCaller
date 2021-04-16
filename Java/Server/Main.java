

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;





public class Main {
 

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {

        //Bdd BDD = new Bdd();

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
