/*----------------- Le package ------------------------*/

package Vue;

/*----------------- Les bibliothéques -----------------*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/*---------------------- Classe -----------------------*/
/*Cette classe à pour but de créer une fenêtre d'erreur*/

public class Ferreur extends JFrame{

/*------------------ Variable global ------------------*/

    JLabel ltext1,ltext2,rien;
    JButton br;

/*------------------- Constructeur --------------------*/

    public Ferreur(){
        fenetre(450,150);                       //Appel la fonction fenêtre en lui donnant sa taille
        this.setLayout(new GridLayout(3,1));    //Crée une grille de 3 lignes et 1 colonne dans notre fenêtre
        panelDebut();                           //Appel la fonction panelDebut
        panelFin();                             //Appel de la fonction panelFin
        this.setVisible(true);                  //Permet d'afficher la fenêtre
    }

    //Fonction de paramétres de la fenêtre
    private void fenetre (int a, int b){
        this.setTitle("Student Caller");                        //Nom de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Permet de fermer le programme quand nous fermons la fenêtre  
        this.setSize(a,b);                                      //Défini la taille de la fenêtre                               
        this.setLocationRelativeTo(null);                       //Met la fenêtre au centre
    }

    //Fonction design de la JPanel de tête
    private void panelDebut() {
        JPanel panel1 = new JPanel(new GridLayout(1,1));        //Création d'un JPanel de grille 1 ligne et 1 colonne
        JPanel panel2 = new JPanel(new GridLayout(1,1));        //Création d'un JPanel de grille 1 ligne et 1 colonne

        ltext1 = new JLabel("Erreur !!",JLabel.CENTER);         //Crée, écrit l'information de notre JPanel et le centrer 
        ltext1.setFont(new Font("Serif", Font.BOLD, 20));       //Renseigne la police et la taille d'écriture
        panel1.add(ltext1);                                     //Met le JLabel dans le JPanel
        this.add(panel1);                                       //Affiche notre JPanel

        ltext2 = new JLabel("L'identifiant et/ou le mot de passe est faux",JLabel.CENTER);  //Crée et écrit l'information de notre JPanel et le centrer
        ltext2.setFont(new Font("Serif", Font.BOLD, 20));                                   //Renseigne la police et la taille d'écriture
        panel2.add(ltext2);                                                                 //Met le JLabel dans le JPanel
        this.add(panel2);                                                                   //Affiche notre JPanel
    }

    //Fonction design de la JPanel de fin
    private void panelFin (){
        JPanel panel3 = new JPanel(new GridLayout(1,3));        //Création d'un JPanel de grille 1 ligne et 3 colonnes
        rien = new JLabel("");                                  //Crée un JLabel vide
        panel3.add(rien);                                       //JLabel va dans le JPanel

        br = new JButton("Retour");                             //Création d'un JButton
        panel3.add(br);                                         //JButton va dans le JPanel

        rien = new JLabel("");                                  //Crée un JLabel vide
        panel3.add(rien);                                       //JLabel va dans le JPanel

        this.add(panel3);                                       //JPabel va dans le JFrame

        //Listener permettant de savoir quand l'utilsateur appuye sur le bouton
        this.br.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                Flogin flog = new Flogin();                    //Va dans la classe Flog
                dispose();                                     //Femer la fentre Ferreur
            }
        });
    }
}
