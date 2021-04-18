package Vue;

import javax.swing.*;

import Controler.ReceiveData;
import Modele.Cours;

import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Fcharge extends JFrame {
   
    JProgressBar progress;
    JPanel panel1,panel2;
    JLabel ltext;

    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>();

    Fintbas Fintbas = new Fintbas();

    private ImageIcon imagECE;
    
    int i;

    public Fcharge() throws UnknownHostException, ClassNotFoundException, IOException{

        fenetre(550,350);
        orgaFen(); 
        this.setVisible(true);

        useReceiveData();

        
        Fintbas.setCours(cours);
        Fintbas.setCla(cla);
        Fintbas.setHeure(heure);
        Fintbas.setDate(date);
        Fintbas.gene();

        dispose();
        Fintbas.setVisible(true);
    }
    
    private void fenetre (int a, int b){
        this.setTitle("Student Caller");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setSize(a,b);                                 
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout (2,1));
    }


    private void useReceiveData(){

        try {
            ReceiveData rcv = new ReceiveData();

            ArrayList<Cours> tab = rcv.start();
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

    private void orgaFen (){
        panel1 = new JPanel();
        panel1.add(image());
        this.add(panel1);

        panel2 = new JPanel(new GridLayout(2,3));
        
        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("");  
        panel2.add(ltext);

        ltext = new JLabel("Chargement ...",JLabel.CENTER);
        panel2.add(ltext);


        this.add(panel2);
    }

    private JLabel image (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE.png"));

        return (new JLabel(imagECE));
    }

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
