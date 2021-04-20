package Vue;

import java.awt.GridLayout;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import com.entities.Cours;
import Controler.ReceiveData;

public class Fcharge extends JFrame {
   
    JProgressBar progress;
    JPanel panel1,panel2;
    JLabel ltext;

    //Création des Arraylist
    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>();

    Fintbas Fintbas = new Fintbas();    //Crée un nom afin de pouvoir communiquer avec la classe Fintbas 

    private ImageIcon imagECE;
    
    public Fcharge() throws UnknownHostException, ClassNotFoundException, IOException{

        fenetre(550,350);               //Appel la fonction fenêtre et lui donnant sa taille
        orgaFen();                      //Appel de la fonction orgaFen
        useReceiveData();               //Appel de la fonction useReceiveData

        //Setter permettant de récupérer les Arraylist de la classe Fintbas
        Fintbas.setCours(cours);    
        Fintbas.setCla(cla);
        Fintbas.setHeure(heure);
        Fintbas.setDate(date);

        Fintbas.gene();                 //Appel de la fonction gene de la classe Fintbas

        dispose();                      //Femer la fentre Flogin
        Fintbas.setVisible(true);       //Permet d'afficher la fenêtre
    }
    
    //Fonction permettant la création de la fênetre 
    private void fenetre (int a, int b){
        this.setTitle("Student Caller");                        //Nom de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Permet de fermer le programme quand nous fermons la fenêtre   
        this.setSize(a,b);                                      //Taille de notre fenêtre       
        this.setLocationRelativeTo(null);                       //Centre la fenêtre au centre de l'écran
        this.setLayout(new GridLayout (2,1));                   //Crée une grille de 2 lignes et 1 colonne dans notre JFrame
    }

    //Fonction permettant de recvoir le tableau du serveur
    private void useReceiveData(){
        try {
            ReceiveData rcv = new ReceiveData();

            ArrayList<Cours> tab = rcv.start();                     //Création d'une Arraylist
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm:ss a");

            Fintbas.setListCours(tab);

            for(int i = 0; i<tab.size(); i++){
                this.cours.add(tab.get(i).getMatiere());
                this.cla.add(tab.get(i).getSalle());
                this.date.add(date.format(tab.get(i).getDate_heure()));
                this.heure.add(time.format(tab.get(i).getDate_heure()));    
            }
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }     
    }

    //Fonction permattant le design de notre fenêtre
    private void orgaFen (){
        panel1 = new JPanel();                                  //Création d'un JPanel
        panel1.add(image());                                    //Appel de la fonction image pour la mettre dans le JPanel
        this.add(panel1);                                       //Met le Jpanel dans le JFrame

        panel2 = new JPanel(new GridLayout(2,3));               // Création d'un Jpanel de grille 2 lignes et 3 colonnes
        
        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("Chargement ...",JLabel.CENTER);     //Ecrit l'information et le centre dans le Jlabel
        panel2.add(ltext);

        this.add(panel2);                                       //Met le JPanel dans le JFrame
    }

    //Fonction permettant de récupérer une image et de retourne un JLabel de l'image
    private JLabel image (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE.png"));  //Récupere l'image 
        return (new JLabel(imagECE));                                       //Retourne l'image dns un JLabel
    }

    //Fonction permettant d'envoyer les Arraylist à d'autre classe
    public ArrayList<String> getCours() {
        return this.cours;
    }

    public ArrayList<String> getCla() {
        return this.cla;
    }

    public ArrayList<String> getDate() {
        return this.date;
    }

    public ArrayList<String> getHeure() {
        return this.heure;
    }
}
