package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import Vue.Ferreur;

public class Flogin extends JFrame {

    JLabel lco,lmp, lti,ltext,rien;
    JButton bco;
    JPasswordField tmp;
    JTextField tco;

    private ImageIcon imagECE;
 
    public Flogin(){  
        
        fenetre(350,250);                       //Appel la fonction fenêtre et lui donnant sa taille
        this.setLayout(new GridLayout(3,1));    //Crée une grille de 3 lignes et 1 colonne pour notre design de la fenêtre
        paneltitre();                           //Appel la fonction paneltitre
        panelmilieu();                          //Appel la fonction panelmilieu
        panelfin();                             //Appel la fonction panelfin
        this.setVisible(true);                  //Permet d'afficher la fenêtre
    }

    //Fonction permettant la création de la fênetre 
    private void fenetre (int a, int b){
        this.setTitle("Student Caller");                      //Nom de notre fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Permet de fermer le programme quand nous fermons la fenêtre  
        this.setSize(a,b);                                    //Taille de notre fenêtre       
        this.setLocationRelativeTo(null);                     //Centre la fenêtre au centre de l'écran
        this.setResizable(false);                             //La taille de la fenêtre ne peut pas être redimensionné
    }

    //Fonction permettant de faire le design de notre Jpanel de tête
    private void paneltitre (){
        JPanel panel1 = new JPanel(new GridLayout(1,1));    //Création d'un Jpanel avec une grille de 1 ligne et 1 colonne

        lti = new JLabel("S'identifier :",JLabel.CENTER);   //Crée, écrit l'information et le centre dans le Jlabel
        lti.setFont(new Font("Serif", Font.BOLD, 30));      //Défini la police et et la taille d'écriture
        panel1.add(lti);                                    //Jlabel va dans le Jpanel

        this.add(panel1);                                   //Affiche le Jpanel
    }

    //Fonction permettant de faire le design de notre Jpanel du milieu
    private void panelmilieu(){
        JPanel panel2 = new JPanel(new GridLayout(2,2));    //Création d'un Jpanel avec une grille de 2 lignes et 2 colonnes

        lco = new JLabel("Identifiant :",JLabel.CENTER);;
        panel2.add(lco);

        tco = new JTextField();  
        panel2.add(tco);

        lmp = new JLabel("Mot de passe :",JLabel.CENTER);   
        panel2.add(lmp);

        tmp = new JPasswordField();    
        panel2.add(tmp);
        
        this.add(panel2);                                   //Affichage de notre Jpanel contennant les Jlabel

        //Création d'un listener permttant de savoir quand l'utilisateur appuye sur entrée
        //Marche dans la case d'écriture de l'identifiant
        this.tmp.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //Teste si l'identifiant et le code sont bon
                if (connect(tco.getText(), tmp.getText()) == true){
                    try {
                        Fcharge fc = new Fcharge();         //Va  à la classe Fcharge
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else{
                    Ferreur fenetreErreur = new Ferreur();  //Va à la classe Ferreur
                }
                dispose();                                  //Femer la fentre Flogin
            }});
        
        //Création d'un listener permttant de savoir quand l'utilisateur appuye sur entrée
        //Marche dans la case d'écriture du code
        this.tco.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //Teste si l'identifiant et le code sont bon
                if (connect(tco.getText(), tmp.getText()) == true){
                    try {
                        Fcharge fc = new Fcharge();         //Va  à la classe Fcharge
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else{
                    Ferreur fenetreErreur = new Ferreur();  //Va à la classe Ferreur
                }
                dispose();                                  //Femer la fentre Flogin
            }});
    }

    //sFonction permettant de faire le design de notre Jpanel de fin
    private void panelfin (){
        JPanel panel3 = new JPanel(new GridLayout(1,3));    //Création d'un Jpanel avec une grille de 1 ligne et 3 colonnes

        rien = new JLabel("");
        panel3.add(rien);

        bco = new JButton("Connection");                    //Création d'un Jbouton
        panel3.add(bco);

        this.add(panel3);
        rien = new JLabel("");

        panel3.add(rien);                                   //Affiche le Jpanel contennat les Jlabel et le Jbouton

        //Listener losrque l'utilsateur appuye sur le bouton
        this.bco.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                //Teste si l'identifiant et le code sont bon
                if (connect(tco.getText(), tmp.getText()) == true){
                    try {
                        Fcharge fcharge= new Fcharge();     //Va  à la classe Fcharge
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }  
                }
                else{
                    Ferreur fenetreErreur = new Ferreur();  //Va à la classe Ferreur
                }
                dispose();                                  //Femer la fentre Flogin
            } 
        });
    }

    //Fonction permettant de tester si l'identifiant et le code sont bon et retourne Vrais ou Faux
    private boolean connect (String id, String mp){
        //Test si l'identifiant et le code son bon
        if ((id.equals("admin")) && (mp.equals("1234"))){
            return true;
        }
        else {
            return false;
        }
    }
}