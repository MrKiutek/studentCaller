/*----------------- Le package ------------------------*/

package Vue;

/*----------------- Les bibliothéques -----------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

/*---------------------- Classe -----------------------*/
/*-------- Création d'une fenêtre de connection -------*/

public class Flogin extends JFrame {

/*------------------ Variable global ------------------*/

    JLabel lco,lmp, lti,ltext,rien;
    JButton bco;
    JPasswordField tmp;
    JTextField tco;
 
/*------------------- Constructeur --------------------*/

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

    //Fonction permettant de faire le design de notre JPanel de tête
    private void paneltitre (){
        JPanel panel1 = new JPanel(new GridLayout(1,1));    //Création d'un JPanel avec une grille de 1 ligne et 1 colonne

        lti = new JLabel("S'identifier :",JLabel.CENTER);   //Crée, écrit l'information et le centre dans le JLabel
        lti.setFont(new Font("Serif", Font.BOLD, 30));      //Défini la police et et la taille d'écriture
        panel1.add(lti);                                    //JLabel va dans le JPanel

        this.add(panel1);                                   //Affiche le JPanel
    }

    //Fonction permettant de faire le design de notre JPanel du milieu
    private void panelmilieu(){
        JPanel panel2 = new JPanel(new GridLayout(2,2));    //Création d'un JPanel avec une grille de 2 lignes et 2 colonnes

        lco = new JLabel("Identifiant :",JLabel.CENTER);;   //Création, le centré et écrire l'infromation dans un JLabel
        panel2.add(lco);                                    //Met le JLabel dans le JPanel

        tco = new JTextField();                             //Création d'un JTextField
        panel2.add(tco);                                    //Met le JTextField dans le JPanel

        lmp = new JLabel("Mot de passe :",JLabel.CENTER);   //Création, le centré et écrire l'infromation dans un JLabel
        panel2.add(lmp);                                    //Met le JLabel dans le JPanel

        tmp = new JPasswordField();                         //Création d'un JPasswordField    
        panel2.add(tmp);                                    //Met le JPasswordField  dans le JPanel
        
        this.add(panel2);                                   //Affichage de notre JPanel contennant les JLabel

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
        //Marche dans la case d'écriture du mot de passe
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

    //Fonction permettant de faire le design de notre JPanel de fin
    private void panelfin (){
        JPanel panel3 = new JPanel(new GridLayout(1,3));    //Création d'un JPanel avec une grille de 1 ligne et 3 colonnes

        rien = new JLabel("");                              //Crée un JLabel vide
        panel3.add(rien);                                   //JLabel va dans le JPanel

        bco = new JButton("Connection");                    //Création d'un Jbouton
        panel3.add(bco);                                    //JButton va dans le JPanel

        rien = new JLabel("");                              //Crée un JLabel vide
        panel3.add(rien);                                   //JLabel va dans le JPanel

        this.add(panel3);                                   //Met le JPanel dans le JFrame

        //Création d'un listener testant si l'utilsateur appuye sur le bouton
        this.bco.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                //Teste si l'identifiant et le code sont bon
                if (connect(tco.getText(), tmp.getText()) == true){
                    try {
                        Fcharge fcharge= new Fcharge();     //Va à la classe Fcharge
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

    //Fonction permettant de tester si l'identifiant et le code sont bon puis retourne Vrais ou Faux
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