package Controler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.entities.Cours;

public class ReceiveData {

    static final String serverName = "192.168.43.97";
    static final int serverPort = 3334;

    public ReceiveData() throws UnknownHostException, IOException, ClassNotFoundException {


    }

    public ArrayList<Cours> start() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        
        Socket socket = new Socket(serverName, serverPort);
        System.out.println("Socket client: " + socket);
 
 
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
 
        System.out.println("Client: donnees emises");
 
        ArrayList<Cours> tableauRecu = (ArrayList<Cours>) in.readObject();
 
        in.close();
        socket.close();
        
        /*
        Cours a,b,c;
        a = new Cours("P111", "physique", new Date(2021, 8, 11, 11, 11));
        b = new Cours("P113", "eletronique", new Date(2021, 8, 12, 12, 12));
        c = new Cours("P114", "projet technologique", new Date(2021, 8, 14, 14, 14));

        a.setPresent(new Eleve("GERMAIN", "Adrien", "123", "ING3"));
        a.setPresent(new Eleve("ANAVOIZAT", "Alexis", "456", "ING3"));
        a.setAbsent(new Eleve("KALIFA", "Ethane", "789", "ING3"));

        ArrayList<Cours> tab = new ArrayList<Cours>();
        tab.add(a);
        tab.add(b);
        tab.add(c);*/

        

        return tableauRecu;

    }
    
}
