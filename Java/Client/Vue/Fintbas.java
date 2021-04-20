/*----------------- Le package ------------------------*/

package Vue;

/*----------------- Les bibliothéques -----------------*/

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import com.entities.Cours;
import Controler.ReceiveData;
import java.util.ArrayList;

/*---------------------- Classe -----------------------*/
/*------ Crée une fenêtre permettant de lire les ------*/
/*---- informations du Serveur. Il permet donc la -----*/
/*-------- communication serveur - utilisateur --------*/

public class Fintbas extends JFrame {
 
/*------------------ Variable global ------------------*/

    JLabel lcla, lcours, ldate, lheure,ltexte;
    JComboBox cbcla, cbcours, cbdate, cbheure;
    JButton bact, bgo,bdeco;
    ArrayList<Cours> listCours;
    JTable AffTableau1,AffTableau2;

    private ImageIcon imagECE, imageECE2;

    //Création des Arraylist
    ArrayList <String> cours = new ArrayList <>();
    ArrayList <String> cla = new ArrayList <>();
    ArrayList <String> date = new ArrayList <>();
    ArrayList <String> heure = new ArrayList <>(); 

/*------------------- Constructeur --------------------*/

    public Fintbas(){  
        fenetre();              //Appel de la fonction fenêtre
    }

    //Fonction permettant de générer le design de la fenêtre
    public void gene(){
        paneltitre();           //Appel de la fonction paneltitre
        panelespace();          //Appel de la fonction panelespace
        panelcentre1();         //Appel de la fonction panelcentre1
        panelcentre2();         //Appel de la fonction panelcentre2
        panelcentre3();         //Appel de la fonction panelcentre3
        tableau();              //Appel de la fonction tableau
        panelespace();          //Appel de la fonction panelspace
        panelfin2();            //Appel de la fonction panelfin2
        panelespace();          //Appel de la fonction panelspace

        //Création d'un Listener permttant de savoir quand l'utilisateur appuye sur le bouton
        this.bgo.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String cours = "" + cbcours.getItemAt(cbcours.getSelectedIndex());      //Récupére les informations de la JComboBox
                lcours.setText(cours);                                                  //Mets l'information de la JComboBox dans le JLable
                String classe = "" + cbcla.getItemAt(cbcla.getSelectedIndex());         //Récupére les informations de la JComboBox
                lcla.setText(classe);                                                   //Mets l'information de la JComboBox dans le JLable
                String date = "" + cbdate.getItemAt(cbdate.getSelectedIndex());         //Récupére les informations de la JComboBox
                ldate.setText(date);                                                    //Mets l'information de la JComboBox dans le JLable
                String heure = "" + cbheure.getItemAt(cbheure.getSelectedIndex());      //Récupére les informations de la JComboBox
                lheure.setText(heure);                                                  //Mets l'information de la JComboBox dans le JLable
                try {
                    startSearch();                                                      //Appel la fonction startSearch
                } catch (ClassNotFoundException | IOException | InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }  
        });

        //Création d'un Listener permttant de savoir quand l'utilisateur appuye sur le bouton
        this.bact.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {                                                
                try {
                    Fcharge fcharge= new Fcharge();                     //Va dans la fonction Fcharge
                    dispose();                                          //Femer la fentre Fintbas
                } catch (ClassNotFoundException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }        
            }  
        });
    }
    
    //Fonction contenant les paramètres de la fenêtre
    private void fenetre(){
        this.setTitle("Student Caller");                                //Nom de notre fenêtre
        this.setExtendedState(Frame.MAXIMIZED_BOTH);                    //La fenêtre s'affiche en pleinne écran                            
        this.setLocationRelativeTo(null);                               //Centre la fenêtre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //Permet de fermer le programme quand nous fermons la fenêtre
        this.setResizable(true);                                        //Permet à l'utilisateur de redimensionner la fenêtre
        this.setLayout(new GridLayout(9,1));                            //Crée une grille de 9 lignes et 1 colonne pour le design de la fenêtre
    }

    //Fonction design du JPanel paneltitre
    private void paneltitre (){
        JPanel panel1 = new JPanel(new GridLayout(1,3));                //Crée un JPanel avec une grille de 1 ligne et 3 colonnes pour le design de la fenêtre

        panel1.add(image());                                            //La fonction image se met dans le JPanel

        ltexte = new JLabel("Liste de présence",JLabel.CENTER);         //Crée, écrit l'information et le centre dans le JLabel
        ltexte.setFont(new Font("Serif", Font.BOLD, 40));               //Défini la police et et la taille d'écriture
        panel1.add(ltexte);                                             //JLabel va dans le JPanel

        panel1.add(image2());                                           //La fonction image2 se met dans le JPanel

        this.add(panel1);                                               //Affiche le JPanel
    }

    //Fonction design du JPanel panelcentre1
    private void panelcentre1 (){
        JPanel panel2 = new JPanel(new GridLayout(1,7));                //Crée une grille de 1 ligne et 7 colonnes pour le design de la fenêtre
        
        bact = new JButton("Actualiser");                               //Crée un bouton Actualiser                                             
        panel2.add(bact);                                               //Met le bouton dans le JPanel

        DefaultListCellRenderer centr = new DefaultListCellRenderer();  //Crée une DefaultListCellRenderer
        centr.setHorizontalAlignment(JLabel.CENTER);                    //Commande permettant de centrer le texte d'un JComboBox
 
        cbcours =new JComboBox(cours.toArray());                        //Crée un JComboBox et récupere une Arraylist
        cbcours.setRenderer(centr);                                     //Permet de centrer les information à l'intérieur de la JComboBox    
        panel2.add(cbcours);                                            //JcomboBox va dans le JPanel

        cbcla =new JComboBox(cla.toArray());                            //Crée un JComboBox et récupere une Arraylist
        cbcla.setRenderer(centr);                                       //Permet de centrer les information à l'intérieur de la JComboBox
        panel2.add(cbcla);                                              //JComboBox va dans le JPanel

        cbdate =new JComboBox(date.toArray());                          //Crée un JComboBox et récupere une Arraylist  
        cbdate.setRenderer(centr);                                      //Permet de centrer les information à l'intérieur de la JComboBox
        panel2.add(cbdate);                                             //JcomboBox va dans le JPanel

        cbheure =new JComboBox(heure.toArray());                        //Crée un JComboBox et récupere une Arraylist 
        cbheure.setRenderer(centr);                                     //Permet de centrer les information à l'intérieur de la JComboBox
        panel2.add(cbheure);                                            //JComboBox va dans le JPanel

        bgo = new JButton("Recherche");                                 //Crée un bouton Recherche            
        panel2.add(bgo);                                                //Met le bouton dans le JPanel

        this.add(panel2);                                               //Met le JPanel dans le JFrame
    }

    //Fonction design du JPanel panelcentre2 
    private void panelcentre2 (){
        JPanel panel3 = new JPanel(new GridLayout(1,7));                //Crée un JPanel avec une grille de 1 ligne et 3 colonnes pour le design de la fenêtre
        
        ltexte = new JLabel("");                                        //Crée un JLabel vide
        panel3.add(ltexte);                                             //JLabel va dans le JPanel

        lcours = new JLabel("",JLabel.CENTER);                          //Crée un JLabel vide et le centre
        panel3.add(lcours);                                             //JLabel va dans le JPanel

        lcla = new JLabel("",JLabel.CENTER);                            //Crée un JLabel vide et le centre
        panel3.add(lcla);                                               //JLabel va dans le JPanel

        ldate = new JLabel("",JLabel.CENTER);                           //Crée un JLabel vide et le centre
        panel3.add(ldate);                                              //JLabel va dans le JPanel

        lheure = new JLabel("",JLabel.CENTER);                          //Crée un JLabel vide et le centre
        panel3.add(lheure);                                             //JLabel va dans le JPanel

        ltexte = new JLabel("");                                        //Crée un JLabel vide
        panel3.add(ltexte);                                             //JLabel va dans le JPanel
        
        this.add(panel3);                                               //Met le JPanel dans le JFrame
    }

    //Fonction design du JPanel paneltitre
    private void panelcentre3 (){
        JPanel panel4 = new JPanel(new GridLayout(1,5));                //Crée un JPanel avec une grille de 1 ligne et 5 colonnes pour le design de la fenêtre

        ltexte = new JLabel("");                                        //Crée un JLabel vide
        panel4.add(ltexte);                                             //JLabel va dans le JPanel

        ltexte = new JLabel("Present",JLabel.CENTER);                   //Crée, écrit l'information et le centre dans le JLabel
        ltexte.setFont(new Font("Serif", Font.BOLD, 20));               //Défini la police et et la taille d'écriture
        panel4.add(ltexte);                                             //JLabel va dans le JPanel

        ltexte = new JLabel("");                                        //Crée un JLabel vide
        panel4.add(ltexte);                                             //JLabel va dans le JPanel

        ltexte = new JLabel("Absent",JLabel.CENTER);                    //Crée, écrit l'information et le centre dans le JLabel
        ltexte.setFont(new Font("Serif", Font.BOLD, 20));               //Défini la police et et la taille d'écriture
        panel4.add(ltexte);                                             //JLabel va dans le JPanel

        ltexte = new JLabel("");                                        //Crée un JLabel vide
        panel4.add(ltexte);                                             //JLabel va dans le JPanel

        this.add(panel4);                                               //Met le JPanel dans le JFrame
    }

    //Fonction de la création d'un JTable
    private void tableau(){
        JPanel panel5 = new JPanel(new GridLayout(1,5));                //Création d'un JPanel avec une grille 1 ligne et 5 colonnes

        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel5.add(ltexte);                                             //JLabel va dans un JPanel

        AffTableau1 = new JTable(new ModeleStatique());                 //Création d'un JTable et on met les informations de ModelStatique dans le JTable                            

        panel5.add(AffTableau1);                                        //JTable va dans le JPanel
        this.add(panel5);                                               //JPanel va dans le JFrame
  
        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel5.add(ltexte);                                             //JLabel va dans un JPanel

        AffTableau2 = new JTable(new ModeleStatique());                 //Création d'un JTable et on met les informations de ModelStatique dans le JTable 
        panel5.add(AffTableau2);                                        //JTable va dans le JPanel
  
        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel5.add(ltexte);                                             //JLabel va dans un JPanel

        this.add(panel5);                                               //JPanel va dans le JFrame
    }

    //Fonction design d'un JPanel panelespace
    private void panelespace (){
        JPanel panel6 = new JPanel(new GridLayout(1,1));                //Création d'un JPanel avec une grille 1 ligne et 1 colonne

        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel6.add(ltexte);                                             //JLabel va dans un JPanel
 
        panel6.add(ltexte);                                             //JLabel va dans un JPanel
        this.add(panel6);                                               //JPanel va dans le JFrame
    }

    //Fonction design d'un JPanel panelfin2
    private void panelfin2 (){
        JPanel panel7 = new JPanel(new GridLayout(1,3));                //Création d'un JPanel avec une grille 1 ligne et 3 colonnes

        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel7.add(ltexte);                                             //JLabel va dans un JPanel
        
        bdeco = new JButton("Deconnection");                            //Création d'un JButton "Deconnection"
        panel7.add(bdeco);                                              //JButton va dans un JPanel
        
        ltexte = new JLabel("");                                        //Création d'un JLabel vide
        panel7.add(ltexte);                                             //JLabel va dans un JPanel
        
        this.add(panel7);                                               //JPanel va dans le JFrame

        //Création d'un listener permttant de savoir quand l'utilisateur appuye sur le bouton
        this.bdeco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                Flogin flog = new Flogin();                             //Appel de la fonction Flogin
                dispose();                                              //Femer la fentre Fintbas
            } 
        });
    }
    
    //Fonction permettant de retourner une image dans un JLabel
    private JLabel image (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE.png"));  //Permet récupérer une image
        return (new JLabel(imagECE));                                       //Retourne un JLabel contenant une image
    }

    //Fonction permettant de retourner une image dans un JLabel
    private JLabel image2 (){
        imagECE = new ImageIcon (this.getClass().getResource("/ECE2.png")); //Permet récupérer une image
        return (new JLabel(imagECE));                                       //Retourne un JLabel contenant une image
    }
    
    //Fonction permettant d'actualiser
    private int startSearch() throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException{ //Fonction permettant le remplissage des deux tableau en fonction des paramètres choisi par l'utilisateur

    //Récupération des données selectionnées par l'utilisateur
    String cours = "" + cbcours.getItemAt(cbcours.getSelectedIndex());  
    String classe = "" + cbcla.getItemAt(cbcla.getSelectedIndex());  
    String date = "" + cbdate.getItemAt(cbdate.getSelectedIndex());  
    String heure = "" + cbheure.getItemAt(cbheure.getSelectedIndex());

    //Création des formats pour la date et l'heure
    DateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat timeForm = new SimpleDateFormat("hh:mm:ss a");

    //Indice du cours selectionné
    int index= 0;

    // Reception de la liste des cours directement depuis le serveur
    ArrayList<Cours> rd = new ReceiveData().start();

    //Suppression des données dans les tableaux
    ((ModeleStatique)AffTableau1.getModel()).clearTable();
    ((ModeleStatique)AffTableau2.getModel()).clearTable();

    //Récupération de l'indice du cours selectionné par l'utilisateur dans la liste
    for(int i = 0; i<rd.size(); i++){

        if(cours.equals(rd.get(i).getMatiere()) && classe.equals(rd.get(i).getSalle()) && date.equals(dateForm.format(rd.get(i).getDate_heure())) && heure.equals(timeForm.format(rd.get(i).getDate_heure()))){
            index = i;
            break;  
        }else{
            return 0;
        }
    }

    //Ajout de tout les présents dans le tableau de présents et affichage de celui-ci
    for(String key : rd.get(index).getPresent().keySet() ){
        ((ModeleStatique)AffTableau1.getModel()).addRow(rd.get(index).getPresent().get(key).getNom(), rd.get(index).getPresent().get(key).getPrenom(), String.valueOf(rd.get(index).getPresent().get(key).getCreditAbsence()));
    }

    //Ajout de tout les absent dans le tableau des absent et affichage de celui-ci
    for(String key : rd.get(index).getAbsent().keySet() ){
        ((ModeleStatique)AffTableau2.getModel()).addRow(rd.get(index).getAbsent().get(key).getNom(), rd.get(index).getAbsent().get(key).getPrenom(), String.valueOf(rd.get(index).getAbsent().get(key).getCreditAbsence()));
        }
        return 1;
    }

    /*--------------------Getter-Setter---------------------*/

    //Fonction setter permettant de recevoir une variable d'autre classe
    public void setCours(ArrayList<String> Cours) {
        this.cours = Cours;
    }

    //Fonction setter permettant de recevoir une variable d'autre classe
    public void setCla(ArrayList<String> cla) {
        this.cla = cla;
    }

    //Fonction setter permettant de recevoir une variable d'autre classe
    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    //Fonction setter permettant de recevoir une variable d'autre classe
    public void setHeure(ArrayList<String> heure) {
        this.heure = heure;
    }

    //Fonction setter permettant de recevoir une variable d'autre classe
    public void setListCours(ArrayList<Cours> tab){
        this.listCours = tab;    
    }
}