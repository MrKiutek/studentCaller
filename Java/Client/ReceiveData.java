import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ReceiveData {

    static final String serverName = "localhost";
    static final int serverPort = 9999;

    public ReceiveData() throws UnknownHostException, IOException, ClassNotFoundException {


    }

    public int[] start() throws UnknownHostException, IOException, ClassNotFoundException{

        Socket socket = new Socket(serverName, serverPort);
        System.out.println("Socket client: " + socket);
 
 
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
 
        System.out.println("Client: donnees emises");
 
        Object objetRecu = in.readObject();
        int[] tableauRecu = (int[]) objetRecu;
 
        System.out.println("Client recoit: " + Arrays.toString(tableauRecu));
 
        in.close();
        socket.close();

        return tableauRecu;

    }
    
}
